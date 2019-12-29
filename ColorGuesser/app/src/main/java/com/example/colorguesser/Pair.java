package com.example.colorguesser;

public class Pair {

    public Color actual;
    public Color guess;
    public double distance;

    public Pair(Color actual, Color guess) {
        this.actual = actual;
        this.guess = guess;
        this.distance = actual.distance(guess);
    }
}
