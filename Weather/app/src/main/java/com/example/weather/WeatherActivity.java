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
    private TextView highTV;
    private TextView currentTV;
    private TextView lowTV;
    private Button nextDayButton;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

//        weatherImage = findViewById(R.id.currentWeather);
        highTV = findViewById(R.id.highTemp);
        currentTV = findViewById(R.id.currentTemp);
        lowTV = findViewById(R.id.lowTemp);
        nextDayButton = findViewById(R.id.nextWeather);
        currentIndex = 0;
        Random randomGenerator = new Random();

        weatherArrayList.add(new Weather(Weather.WeatherType.values()[randomGenerator.nextInt(3)]));
        highTV.setText(weatherArrayList.get(currentIndex).getHighCurrentLow()[0] + "");
        currentTV.setText(weatherArrayList.get(currentIndex).getHighCurrentLow()[1]);
        lowTV.setText(weatherArrayList.get(currentIndex).getHighCurrentLow()[2]);

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
