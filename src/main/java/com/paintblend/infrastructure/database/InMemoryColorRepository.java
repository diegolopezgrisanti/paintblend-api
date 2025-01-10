package com.paintblend.infrastructure.database;

import com.paintblend.domain.color.Color;
import com.paintblend.domain.color.ColorRepository;

import java.util.*;

public class InMemoryColorRepository implements ColorRepository {

    private final Map<String, Color> colorMap = new HashMap<>();

    Color redColor = new Color(
            "FF0000",
            new Color.RGB(255, 0, 0),
            new Color.RYB(255, 0, 0)
    );

    Color limeColor = new Color(
            "00FF00",
            new Color.RGB(0, 255, 0),
            new Color.RYB(0, 255, 255)
    );

    Color blueColor = new Color(
            "0000FF",
            new Color.RGB(0, 0, 255),
            new Color.RYB(0, 0, 255)
    );

    Color yellowColor = new Color(
            "FFFF00",
            new Color.RGB(255, 255, 0),
            new Color.RYB(0, 255, 0)
    );

    public InMemoryColorRepository() {
        colorMap.put(redColor.hex(), redColor);
        colorMap.put(limeColor.hex(), limeColor);
        colorMap.put(blueColor.hex(), blueColor);
        colorMap.put(yellowColor.hex(), yellowColor);
    }

    @Override
    public Optional<Color> getColorByHex(String hex) {
        return Optional.ofNullable(colorMap.get(hex));
    }
}