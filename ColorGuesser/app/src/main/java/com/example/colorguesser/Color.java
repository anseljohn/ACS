package com.example.colorguesser;

public class Color {
    public int r;
    public int g;
    public int b;

    public Color (int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public static Color fromHex(String hex) {
        return new Color(Integer.valueOf( hex.substring( 1, 3 ), 16 ),
                         Integer.valueOf( hex.substring( 3, 5 ), 16 ),
                         Integer.valueOf( hex.substring( 5, 7 ), 16 ) );
    }

    public static String toHex(Color c) {
        return String.format("#%02x%02x%02x", c.r, c.g, c.b);
    }

    public String toHex() {
        return toHex(this);
    }

    public double distance(Color c) {
        return Math.abs(Math.sqrt(Math.pow(c.r - r, 2) + Math.pow(c.g - g, 2) + Math.pow(c.b - b, 2)));
    }

    public static double distance(Color c, Color o) {
        return c.distance(o);
    }
}
