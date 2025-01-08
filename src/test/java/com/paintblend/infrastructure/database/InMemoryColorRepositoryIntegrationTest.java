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
        Optional<Color> result = colorRepository.getColorByHex("A23B5C");

        // THEN
        assertThat(result)
                .isPresent()
                .contains(new Color("A23B5C", 6.35, 8.82, 3.64));
    }

    @Test
    void shouldReturnAnEmptyWhenColorNotExists() {
        // WHEN
        Optional<Color> result = colorRepository.getColorByHex("D47F2F");

        // THEN
        assertThat(result).isEmpty();
    }

}