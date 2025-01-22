package com.paintblend.infrastructure.entrypoint.rest;

import com.paintblend.application.getcolorbyhex.GetColorByHexUseCase;
import com.paintblend.domain.color.Color;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class ColorControllerContractTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetColorByHexUseCase getColorByHexUseCase;

    @Test
    void shouldReturnColorWhenHexExists() throws Exception {
        String hexColor = "FF0000";

        Color mockColor = new Color(
                hexColor,
                new Color.RGB(255,0,0),
                new Color.RYB(255,0,0),
                new Color.CMY(0.0000, 1.0000,1.0000),
                new Color.Parts(0,1,1)
        );

        when(getColorByHexUseCase.getColorByHex(hexColor)).thenReturn(mockColor);

        mockMvc.perform(get("/color")
                .param("hex", hexColor))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hex").value("FF0000"))
                .andExpect(jsonPath("$.rgb.red").value(255))
                .andExpect(jsonPath("$.rgb.green").value(0))
                .andExpect(jsonPath("$.rgb.blue").value(0))
                .andExpect(jsonPath("$.ryb.red").value(255))
                .andExpect(jsonPath("$.ryb.yellow").value(0))
                .andExpect(jsonPath("$.ryb.blue").value(0))
                .andExpect(jsonPath("$.cmy.cyan").value(0.0000))
                .andExpect(jsonPath("$.cmy.magenta").value(1.0000))
                .andExpect(jsonPath("$.cmy.yellow").value(1.0000))
                .andExpect(jsonPath("$.parts.cyanParts").value(0))
                .andExpect(jsonPath("$.parts.magentaParts").value(1))
                .andExpect(jsonPath("$.parts.yellowParts").value(1));
    }

    @Test
    void shouldReturnNotFoundWhenHexDoesNotExist() throws Exception {
        String nonExistentHex = "000000"; // Example of a hex that does not exist in DB

        when(getColorByHexUseCase.getColorByHex(nonExistentHex)).thenReturn(null);

        mockMvc.perform(get("/color")
                        .param("hex", nonExistentHex))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Color not found for the provided HEX code."));
    }

}