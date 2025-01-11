package com.paintblend.infrastructure.database;

import com.paintblend.domain.color.Color;
import com.paintblend.domain.color.ColorRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public class JdbcColorRepository implements ColorRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcColorRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Optional<Color> getColorByHex(String hex) {
        return namedParameterJdbcTemplate.query(
                """
                        SELECT * FROM colors WHERE hex = :hex
                        """,
                Map.of("hex", hex),
                new ColorRowMapper()
        ).stream().findFirst();
    }

    private static class ColorRowMapper implements RowMapper<Color> {
        @Override
        public Color mapRow(ResultSet rs, int rowNum) throws SQLException {
            String hex = rs.getString("hex");
            String rgbString = rs.getString("rgb");
            String rybString = rs.getString("ryb");

            Color.RGB rgb = parseRGB(rgbString);
            Color.RYB ryb = parseRYB(rybString);

            return new Color(hex, rgb, ryb);
        }

        private Color.RGB parseRGB(String rgbString) {
            String[] parts = rgbString.replace("rgb(", "").replace(")", "").split(",");
            return new Color.RGB(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
        }

        private Color.RYB parseRYB(String rybString) {
            String[] parts = rybString.replace("ryb(", "").replace(")", "").split(",");
            return new Color.RYB(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
        }
    }
}