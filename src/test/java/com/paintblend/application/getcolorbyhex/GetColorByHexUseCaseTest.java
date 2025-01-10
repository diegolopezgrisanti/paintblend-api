package com.paintblend.application.getcolorbyhex;

import com.paintblend.domain.color.Color;
import com.paintblend.domain.color.ColorRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GetColorByHexUseCaseTest {

    private final ColorRepository colorRepository = mock(ColorRepository.class);
    private final GetColorByHexUseCase getColorByHexUseCase = new GetColorByHexUseCase(colorRepository);

    @Test
    void shouldReturnColorByHexWithRYBValues() {
        // GIVEN
        String hexColor = "0000FF";
        Color.RGB rgbColor = new Color.RGB(0, 0, 255);
        Color.RYB rybColor = new Color.RYB(0, 0, 255);
        Color expectedColor = new Color(hexColor, rgbColor, rybColor);
        when(colorRepository.getColorByHex(hexColor)).thenReturn(Optional.of(expectedColor));

        // WHEN
        Optional<Color> result = getColorByHexUseCase.getColorByHex(hexColor);

        // THEN
        assertThat(result)
                .isPresent()
                .hasValueSatisfying(color -> {
                    assertThat(color.hex()).isEqualTo(hexColor);
                    assertThat(color.rgb()).isEqualTo(rgbColor);
                    assertThat(color.ryb()).isEqualTo(rybColor);
                });
        verify(colorRepository).getColorByHex(hexColor);
    }

    @Test
    void shouldReturnAnColorWithoutRYBValues() {
        // GIVEN
        String hexColor = "A23B5C";
        when(colorRepository.getColorByHex(hexColor)).thenReturn(Optional.empty());

        // WHEN
        Optional<Color> result = getColorByHexUseCase.getColorByHex(hexColor);

        // THEN
        assertThat(result)
                .isPresent()
                .hasValueSatisfying(color -> {
                    assertThat(color.hex()).isEqualTo(hexColor);
                    assertThat(color.rgb()).isNotNull();
                    assertThat(color.ryb()).isNull();
                    assertThat(color.rgb().red()).isEqualTo(162);
                    assertThat(color.rgb().green()).isEqualTo(59);
                    assertThat(color.rgb().blue()).isEqualTo(92);
                });
        verify(colorRepository).getColorByHex(hexColor);
    }

}