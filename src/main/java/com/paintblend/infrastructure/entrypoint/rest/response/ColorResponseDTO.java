package com.paintblend.infrastructure.entrypoint.rest.response;

public record ColorResponseDTO(String hex, RGB rgb, RYB ryb) {
    public record RGB(int red, int green, int blue) {}
    public record RYB(int red, int yellow, int blue) {}
}