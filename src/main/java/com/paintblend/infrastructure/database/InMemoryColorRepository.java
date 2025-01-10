package com.paintblend.infrastructure.database;

import com.paintblend.domain.color.Color;
import com.paintblend.domain.color.ColorRepository;

import java.util.*;

public class InMemoryColorRepository implements ColorRepository {

    Color.RGB rgbColor1 = new Color.RGB(162, 59, 92);
    Color color1 = new Color(
            "A23B5C",
            rgbColor1
    );

    Color.RGB rgbColor2 = new Color.RGB(79, 155, 68);
    Color color2 = new Color(
            "4F9B44",
            rgbColor2
    );

    Map<String, Color> colorMap = new HashMap<>();

    public InMemoryColorRepository() {
        colorMap.put(color1.hex(), color1);
        colorMap.put(color2.hex(), color2);
    }

    @Override
    public Optional<Color> getColorByHex(String hex) {
        return Optional.ofNullable(colorMap.get(hex));
    }
}