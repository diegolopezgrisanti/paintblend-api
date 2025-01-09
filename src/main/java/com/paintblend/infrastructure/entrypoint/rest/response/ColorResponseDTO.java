package com.paintblend.infrastructure.entrypoint.rest.response;

public record ColorResponseDTO(String hex, RGB rgb) {
    public record RGB(int red, int green, int blue) {
    }
}