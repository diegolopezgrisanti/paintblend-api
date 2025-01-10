package com.paintblend.domain.color;

public record Color(String hex, RGB rgb, RYB ryb) {
    public record RGB(int red, int green, int blue) {}
    public record RYB(int red, int yellow, int blue) {}
}