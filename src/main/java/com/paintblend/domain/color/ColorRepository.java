package com.paintblend.domain.color;

import java.util.Optional;

public interface ColorRepository {

    Optional<Color> getColorByHex(String hex);
}