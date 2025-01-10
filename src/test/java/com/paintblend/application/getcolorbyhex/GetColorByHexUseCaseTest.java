package com.paintblend.application.getcolorbyhex;

import com.paintblend.domain.color.Color;
import com.paintblend.domain.color.ColorRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetColorByHexUseCaseTest {

    private final ColorRepository colorRepository = mock(ColorRepository.class);
    private final GetColorByHexUseCase getColorByHexUseCase = new GetColorByHexUseCase(colorRepository);

    @Test
    void shouldReturnColorByHex() {
        // GIVEN
        String hexColor = "A23B5C";
        Color.RGB rgbColor = new Color.RGB(162, 59, 92);
        Color expectedColor = new Color(
                hexColor,
                rgbColor
        );

        // WHEN
        when(colorRepository.getColorByHex(hexColor)).thenReturn(Optional.of(expectedColor));
        Optional<Color> result = getColorByHexUseCase.getColorByHex(hexColor);

        // THEN
        assertThat(result)
                .isPresent()
                .contains(expectedColor);
    }

    @Test
    void shouldReturnAnEmptyOptional() {
        // WHEN
        String hexColor = "A23B5C";
        when(colorRepository.getColorByHex(hexColor)).thenReturn(Optional.empty());
        Optional<Color> result = getColorByHexUseCase.getColorByHex(hexColor);

        // THEN
        assertThat(result).isEmpty();
    }

}