package com.paintblend.application.getcolorbyhex;

import com.paintblend.domain.color.Color;
import com.paintblend.domain.color.ColorRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetColorByHexUseCaseTest {

    private final ColorRepository colorRepository = mock(ColorRepository.class);
    private final GetColorByHexUseCase getColorByHexUseCase = new GetColorByHexUseCase(colorRepository);

    @Test
    void shouldReturnColorFromRepositoryWhenExists() {
        // GIVEN
        String hexColor = "0000FF";
        Color.RGB rgbColor = new Color.RGB(0, 0, 255);
        Color.RYB rybColor = new Color.RYB(0, 0, 255);
        Color.CMY cmyColor = new Color.CMY(1.0, 1.0, 0.0);
        Color.Parts partsColor = new Color.Parts(1, 1, 0);
        Color expectedColor = new Color(hexColor, rgbColor, rybColor, cmyColor, partsColor);
        when(colorRepository.getColorByHex(hexColor)).thenReturn(Optional.of(expectedColor));

        // WHEN
        Color result = getColorByHexUseCase.getColorByHex(hexColor);

        // THEN
        assertEquals(expectedColor, result);
        verify(colorRepository).getColorByHex(hexColor);
    }

    @Test
    void shouldGenerateColorWhenNotInRepository() {
        // GIVEN
        String hexColor = "A23B5C";
        when(colorRepository.getColorByHex(hexColor)).thenReturn(Optional.empty());

        // WHEN
        Color result = getColorByHexUseCase.getColorByHex(hexColor);

        // THEN
        assertNotNull(result);
        assertEquals(hexColor, result.hex());
        assertEquals(new Color.RGB(162, 59, 92), result.rgb());
        assertNotNull(result.cmy());
        assertEquals(0.3647, result.cmy().cyan());
        assertEquals(0.7686, result.cmy().magenta());
        assertEquals(0.6392, result.cmy().yellow());
        assertNull(result.ryb());
        assertNull(result.parts());

        verify(colorRepository).getColorByHex(hexColor);
    }

}