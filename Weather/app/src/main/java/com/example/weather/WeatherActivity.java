package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class WeatherActivity extends AppCompatActivity {

    private ArrayList<Weather> weatherArrayList = new ArrayList<>();
    private ImageView weatherImage;
    private TextView[] highCurrentLowTextViews = new TextView[3];
    private Button nextDayButton;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

//        weatherImage = findViewById(R.id.currentWeather);
        nextDayButton = findViewById(R.id.nextWeather);
        currentIndex = 0;
        Random randomGenerator = new Random();

        highCurrentLowTextViews[0] = findViewById(R.id.highTemp);
        highCurrentLowTextViews[1] = findViewById(R.id.currentTemp);
        highCurrentLowTextViews[2] = findViewById(R.id.lowTemp);

        weatherArrayList.add(new Weather(Weather.WeatherType.values()[randomGenerator.nextInt(3)]));
        for (int i = 0; i < highCurrentLowTextViews.length; i++) {
            highCurrentLowTextViews[i].setText("hi");//weatherArrayList.get(currentIndex).getHighCurrentLow()[currentIndex]);
        }

        nextDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextDay();
            }
        });
    }

    public void nextDay() {
        currentIndex++;
    }

    public void previousDay() {
        if (currentIndex != 0) {
            currentIndex--;
        }
    }
}
