package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class WeatherActivity extends AppCompatActivity {

    private ArrayList<Weather> weatherArrayList = new ArrayList<>();
    private ImageView weatherImage;
    private TextView[] highCurrentLowTextViews = new TextView[3];
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        currentIndex = 0;

        Random randomGenerator = new Random();

        weatherArrayList.add(new Weather(Weather.WeatherType.values()[randomGenerator.nextInt(3 + 1)]));
        weatherImage = findViewById(R.id.currentWeather);

    }
}
