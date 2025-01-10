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
        Optional<Color> color = colorRepository.getColorByHex(hex);
        if (color.isPresent()) {
            return color;
        } else {
            return Optional.of(convertHexToRgb(hex));
        }
    }

    public Color convertHexToRgb(String hex) {
        int red = (int) hexToDecimal(hex.substring(0, 2));
        int green = (int) hexToDecimal(hex.substring(2, 4));
        int blue = (int) hexToDecimal(hex.substring(4, 6));

        Color.RGB rgb = new Color.RGB(red, green, blue);
        return new Color(hex, rgb, null);
    }

    private double hexToDecimal(String hex) {
        return Integer.parseInt(hex, 16);
    }

}