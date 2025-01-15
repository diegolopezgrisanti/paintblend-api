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
            new Color.RYB(255, 0, 0),
            new Color.CMY(0.0, 1.0, 1.0),
            new Color.Parts(0, 1, 1)
    );

    Color limeColor = new Color(
            "00FF00",
            new Color.RGB(0, 255, 0),
            new Color.RYB(0, 255, 255),
            new Color.CMY(1.0, 0.0, 1.0),
            new Color.Parts(1, 0, 1)
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
        String cmyString = "cmy(" + color.cmy().cyan() + "," + color.cmy().magenta() + "," + color.cmy().yellow() + ")";
        String partsString = "parts(" + color.parts().cyanParts() + "," + color.parts().magentaParts() + "," + color.parts().yellowParts() + ")";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("hex", color.hex())
                .addValue("rgb", rgbString)
                .addValue("ryb", rybString)
                .addValue("cmy", cmyString)
                .addValue("parts", partsString);

        namedParameterJdbcTemplate.update(
                """
                        INSERT INTO colors (hex, rgb, ryb, cmy, parts)
                        VALUES (:hex, :rgb, :ryb, :cmy, :parts)
                        """,
                params
        );
    }

}