package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class WeatherActivity extends AppCompatActivity {

    private ArrayList<Weather> weatherArrayList = new ArrayList<>();
    ImageView weatherImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        Random randomGenerator = new Random();

        boolean add = weatherArrayList.add(new Weather(Weather.WeatherType.values()[randomGenerator.nextInt(3)]));
        weatherImage = findViewById(R.id.currentWeather);
    }
}
