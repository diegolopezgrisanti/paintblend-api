package com.paintblend.infrastructure.database;

import com.paintblend.domain.color.Color;
import com.paintblend.domain.color.ColorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JdbcColorRepositoryIntegrationTest {

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @BeforeEach
    void setUp() {
        JdbcTestUtils.deleteFromTables(
                namedParameterJdbcTemplate.getJdbcTemplate(),
                "colors"
        );
    }

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

    @Test
    void shouldReturnAColorByHex() {
        // GIVEN
        givenExistingColor(redColor);
        givenExistingColor(limeColor);

        // WHEN
        Optional<Color> result = colorRepository.getColorByHex(redColor.hex());

        // THEN
        assertThat(result)
                .isPresent()
                .contains(redColor);
    }

    private void givenExistingColor(Color color) {
        String rgbString = "rgb(" + color.rgb().red() + "," + color.rgb().green() + "," + color.rgb().blue() + ")";
        String rybString = "ryb(" + color.ryb().red() + "," + color.ryb().yellow() + "," + color.ryb().blue() + ")";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("hex", color.hex())
                .addValue("rgb", rgbString)
                .addValue("ryb", rybString);

        namedParameterJdbcTemplate.update(
                """
                        INSERT INTO colors (hex, rgb, ryb)
                        VALUES (:hex, :rgb, :ryb)
                        """,
                params
        );
    }

}