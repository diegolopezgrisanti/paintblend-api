package com.paintblend.infrastructure.entrypoint.rest;

import com.paintblend.application.getcolorbyhex.GetColorByHexUseCase;
import com.paintblend.domain.color.Color;
import com.paintblend.infrastructure.entrypoint.rest.response.ColorResponseDTO;
import com.paintblend.infrastructure.entrypoint.rest.response.error.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/colors")
public class ColorController {

    private final GetColorByHexUseCase getColorByHexUseCase;

    public ColorController(GetColorByHexUseCase getColorByHexUseCase) {
        this.getColorByHexUseCase = getColorByHexUseCase;
    }

    @GetMapping("/{hex}")
    public ResponseEntity<Object> getColorByHex(@PathVariable String hex) {
        Optional<Color> color = getColorByHexUseCase.getColorByHex(hex);
        if(color.isEmpty()) {
            ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                    "No matching color proportions were found for the specified hex color."
            );

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDTO);
        }

        ColorResponseDTO colorResponseDTO = new ColorResponseDTO(
                color.get().hex(),
                color.get().red(),
                color.get().yellow(),
                color.get().blue()
        );

        return ResponseEntity.status(HttpStatus.OK).body(colorResponseDTO);
    }
}