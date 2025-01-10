package com.paintblend.domain.color;

public record Color(String hex, RGB rgb) {
    public record RGB(int red, int green, int blue) {
    }
}