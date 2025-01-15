package com.paintblend.application.getcolorbyhex;

import com.paintblend.domain.color.Color;
import com.paintblend.domain.color.ColorRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
            return Optional.of(convertHexToColor(hex));
        }
    }

    public Color convertHexToColor(String hex) {
        // Convert HEX to RGB
        int red = (int) hexToDecimal(hex.substring(0, 2));
        int green = (int) hexToDecimal(hex.substring(2, 4));
        int blue = (int) hexToDecimal(hex.substring(4, 6));
        Color.RGB rgb = new Color.RGB(red, green, blue);

        // Convert RGB to CMY
        Color.CMY cmy = rgbToCmy(red, green, blue);

        return new Color(hex, rgb, null, cmy, null);
    }

    private double hexToDecimal(String hex) {
        return Integer.parseInt(hex, 16);
    }

    private Color.CMY rgbToCmy(int red, int green, int blue) {
        double cyan = 1 - (red / 255.0);
        double magenta = 1 - (green / 255.0);
        double yellow = 1 - (blue / 255.0);

        // Round the values before returning the CMY object
        cyan = roundTo4DecimalPlaces(cyan);
        magenta = roundTo4DecimalPlaces(magenta);
        yellow = roundTo4DecimalPlaces(yellow);

        return new Color.CMY(cyan, magenta, yellow);
    }

    private double roundTo4DecimalPlaces(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}