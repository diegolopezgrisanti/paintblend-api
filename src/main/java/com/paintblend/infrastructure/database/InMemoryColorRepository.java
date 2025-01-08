package com.paintblend.infrastructure.database;

import com.paintblend.domain.color.Color;
import com.paintblend.domain.color.ColorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryColorRepository implements ColorRepository {

    Color color1 = new Color(
            "A23B5C",
            6.35,
            8.82,
            3.64
    );

    Color color2 = new Color(
            "4F9B44",
            3.92,
            6.27,
            4.92
    );

    List<Color> colors = new ArrayList<>(List.of(color1, color2));

    @Override
    public Optional<Color> getColorByHex(String hex) {
        return colors.stream()
                .filter(color -> color.hex().equals(hex))
                .findFirst();
    }
}