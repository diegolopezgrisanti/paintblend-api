package com.paintblend.infrastructure.database;

import com.paintblend.domain.color.Color;
import com.paintblend.domain.color.ColorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class InMemoryColorRepositoryIntegrationTest {

    @Autowired
    private ColorRepository colorRepository;

    @Test
    void shouldReturnAnColorByHex() {
        // WHEN
        Optional<Color> result = colorRepository.getColorByHex("0000FF");

        // THEN
        assertThat(result)
                .isPresent()
                .hasValueSatisfying(color -> {
                    assertThat(color.hex()).isEqualTo("0000FF");
                    assertThat(color.rgb()).isEqualTo(new Color.RGB(0, 0, 255));
                    assertThat(color.ryb()).isEqualTo(new Color.RYB(0, 0, 255));
                });
    }

    @Test
    void shouldReturnAnEmptyWhenColorNotExists() {
        // WHEN
        Optional<Color> result = colorRepository.getColorByHex("D47F2F");

        // THEN
        assertThat(result).isEmpty();
    }

}