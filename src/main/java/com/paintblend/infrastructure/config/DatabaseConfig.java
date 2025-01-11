package com.paintblend.infrastructure.config;

import com.paintblend.infrastructure.database.JdbcColorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DatabaseConfig {

    @Bean
    public JdbcColorRepository jdbcColorRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new JdbcColorRepository(namedParameterJdbcTemplate);
    }
}
