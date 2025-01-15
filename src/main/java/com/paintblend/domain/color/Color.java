package com.paintblend.domain.color;

public record Color(String hex, RGB rgb, RYB ryb, CMY cmy, Parts parts) {
    public record RGB(int red, int green, int blue) {}
    public record RYB(int red, int yellow, int blue) {}
    public record CMY(double cyan, double magenta, double yellow) {}
    public record Parts(int cyanParts, int magentaParts, int yellowParts) {}
}