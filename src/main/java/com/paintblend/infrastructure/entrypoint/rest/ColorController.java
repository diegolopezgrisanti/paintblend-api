package com.paintblend.infrastructure.entrypoint.rest;

import com.paintblend.application.getcolorbyhex.GetColorByHexUseCase;
import com.paintblend.domain.color.Color;
import com.paintblend.infrastructure.entrypoint.rest.response.ColorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ColorController {

    private final GetColorByHexUseCase getColorByHexUseCase;

    public ColorController(GetColorByHexUseCase getColorByHexUseCase) {
        this.getColorByHexUseCase = getColorByHexUseCase;
    }

    @GetMapping("/color")
    public ResponseEntity<Object> getColorByHex(@RequestParam String hex) {
        Optional<Color> colorOpt = getColorByHexUseCase.getColorByHex(hex);
        if(colorOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(createColorResponse(colorOpt.get()));

        }

        Color generatedColor = getColorByHexUseCase.convertHexToColor(hex);
        return ResponseEntity.status(HttpStatus.OK).body(createColorResponse(generatedColor));
    }

    private ColorResponseDTO createColorResponse(Color color) {
        ColorResponseDTO.RGB rgb = new ColorResponseDTO.RGB(
                color.rgb().red(),
                color.rgb().green(),
                color.rgb().blue()
        );

        ColorResponseDTO.RYB ryb = null;
        if (color.ryb() != null) {
            ryb = new ColorResponseDTO.RYB(
                    color.ryb().red(),
                    color.ryb().yellow(),
                    color.ryb().blue()
            );
        }

        ColorResponseDTO.CMY cmy = new ColorResponseDTO.CMY(
                color.cmy().cyan(),
                color.cmy().magenta(),
                color.cmy().yellow()
        );

        ColorResponseDTO.Parts parts = null;
        if (color.parts() != null) {
            parts = new ColorResponseDTO.Parts(
                    color.parts().cyanParts(),
                    color.parts().magentaParts(),
                    color.parts().yellowParts()
            );
        }

        return new ColorResponseDTO(color.hex(), rgb, ryb, cmy, parts);
    }
}