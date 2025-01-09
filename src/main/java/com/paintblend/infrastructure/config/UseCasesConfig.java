package com.paintblend.infrastructure.config;

import com.paintblend.application.getcolorbyhex.GetColorByHexUseCase;
import com.paintblend.domain.color.ColorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    @Bean
    public GetColorByHexUseCase getColorByHexUseCase(ColorRepository colorRepository) {
        return new GetColorByHexUseCase(colorRepository);
    }
}