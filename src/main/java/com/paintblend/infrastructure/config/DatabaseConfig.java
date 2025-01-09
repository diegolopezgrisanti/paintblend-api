package com.paintblend.infrastructure.config;

import com.paintblend.infrastructure.database.InMemoryColorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    public InMemoryColorRepository inMemoryColorRepository(){
        return new InMemoryColorRepository();
    }
}
