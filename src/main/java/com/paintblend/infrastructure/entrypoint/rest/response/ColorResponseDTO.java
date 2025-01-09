package com.paintblend.infrastructure.entrypoint.rest.response;

public record ColorResponseDTO(
        String hex,
        double red,
        double yellow,
        double blue
) {}
