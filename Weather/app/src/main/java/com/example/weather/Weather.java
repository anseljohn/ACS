package com.example.weather;

import java.util.Random;

public class Weather {

    public enum WeatherType {
        SUNNY,
        CLOUDY,
        RAINY
    }

    private WeatherType weatherType;
    private int[] highCurrentLow = new int[3];

    public Weather(WeatherType weatherType) {
        this.weatherType = weatherType;
        Random getTemps = new Random();
        highCurrentLow[2] = getTemps.nextInt(87) + 15;
        highCurrentLow[0] = getTemps.nextInt(102 - highCurrentLow[2]) + highCurrentLow[2];
        highCurrentLow[1] = getTemps.nextInt((highCurrentLow[0] - highCurrentLow[2]) + 1) + highCurrentLow[2];
    }

    public WeatherType getWeatherType() {
        return weatherType;
    }

    public int[] getHighCurrentLow() {
        return highCurrentLow;
    }
}
