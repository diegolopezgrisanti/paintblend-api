package com.paintblend.application.getcolorbyhex;

import com.paintblend.domain.color.Color;
import com.paintblend.domain.color.ColorRepository;

import java.util.Optional;

public class GetColorByHexUseCase {

    private final ColorRepository colorRepository;

    public GetColorByHexUseCase(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public Optional<Color> getColorByHex(String hex) {
        return colorRepository.getColorByHex(hex);
    }
}