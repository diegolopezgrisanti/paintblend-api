package com.paintblend.infrastructure.entrypoint.rest;

import com.paintblend.application.getcolorbyhex.GetColorByHexUseCase;
import com.paintblend.domain.color.Color;
import com.paintblend.infrastructure.entrypoint.rest.response.ColorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ColorController {

    private final GetColorByHexUseCase getColorByHexUseCase;

    public ColorController(GetColorByHexUseCase getColorByHexUseCase) {
        this.getColorByHexUseCase = getColorByHexUseCase;
    }

    @GetMapping("/color")
    public ResponseEntity<Object> getColorByHex(@RequestParam String hex) {
        if (!isValidHex(hex)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("The provided HEX code is invalid.");
        }
        hex = hex.startsWith("#") ? hex.substring(1) : hex;

        Color color = getColorByHexUseCase.getColorByHex(hex);

        if(color == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Color not found for the provided HEX code.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(createColorResponse(color));
    }

    private boolean isValidHex(String hex) {
        return hex != null && hex.matches("^#?[0-9a-fA-F]{6}$");
    }

    private ColorResponseDTO createColorResponse(Color color) {
        ColorResponseDTO.RGB rgb = new ColorResponseDTO.RGB(
                color.rgb().red(),
                color.rgb().green(),
                color.rgb().blue()
        );

        ColorResponseDTO.RYB ryb = color.ryb() != null ?
            new ColorResponseDTO.RYB(
                    color.ryb().red(),
                    color.ryb().yellow(),
                    color.ryb().blue()
            ) : null;

        ColorResponseDTO.CMY cmy = new ColorResponseDTO.CMY(
                color.cmy().cyan(),
                color.cmy().magenta(),
                color.cmy().yellow()
        );

        ColorResponseDTO.Parts parts = color.parts() != null ?
            new ColorResponseDTO.Parts(
                    color.parts().cyanParts(),
                    color.parts().magentaParts(),
                    color.parts().yellowParts()
            ) : null;

        return new ColorResponseDTO(color.hex(), rgb, ryb, cmy, parts);
    }
}