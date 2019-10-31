package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

import static android.icu.text.UnicodeSet.CASE;

public class WeatherActivity extends AppCompatActivity {

    private ArrayList<Weather> weatherArrayList = new ArrayList<>();
    private ImageView cloudyImage;
    private ImageView rainyImage;
    private ImageView sunnyImage;
    private TextView highTV;
    private TextView currentTV;
    private TextView lowTV;
    private TextView day;
    private Button nextDayButton;
    private Button previousButton;
    private Button todayButton;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        cloudyImage = findViewById(R.id.cloudyWeather);
        rainyImage = findViewById(R.id.rainyWeather);
        sunnyImage = findViewById(R.id.sunnyWeather);
        setImagesInvisible();

        previousButton = findViewById(R.id.previousButton);
        previousButton.setVisibility(View.INVISIBLE);
        todayButton = findViewById(R.id.todayButton);
        todayButton.setVisibility(View.INVISIBLE);

        highTV = findViewById(R.id.highTemp);
        currentTV = findViewById(R.id.currentTemp);
        lowTV = findViewById(R.id.lowTemp);
        nextDayButton = findViewById(R.id.nextWeather);
        day = findViewById(R.id.date);

        currentIndex = 0;
        addDay();
        updateCurrent();

        nextDayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextDay();
            }
        });
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousDay();
            }
        });

        todayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = 0;
                updateCurrent();
            }
        });
    }

    public void updateCurrent() {
        if (currentIndex > 0) {
            previousButton.setVisibility(View.VISIBLE);
            todayButton.setVisibility(View.VISIBLE);
        } else {
            previousButton.setVisibility(View.INVISIBLE);
            todayButton.setVisibility(View.INVISIBLE);
        }
        setImagesInvisible();
        highTV.setText(getResources().getString(R.string.upArrow) + " " + weatherArrayList.get(currentIndex).getHighCurrentLow()[0] + "");
        currentTV.setText(weatherArrayList.get(currentIndex).getHighCurrentLow()[1] + "");
        lowTV.setText(getResources().getString(R.string.downArrow) + " " + weatherArrayList.get(currentIndex).getHighCurrentLow()[2]);
        switch (weatherArrayList.get(currentIndex).getWeatherType()) {
            case RAINY :
                rainyImage.setVisibility(View.VISIBLE);
                break;
            case SUNNY :
                sunnyImage.setVisibility(View.VISIBLE);
                break;
            case CLOUDY :
                cloudyImage.setVisibility(View.VISIBLE);
                break;
            default :
                setImagesInvisible();
        }
        day.setText(getResources().getString(R.string.dayText) + " " + (currentIndex + 1));
    }

    public void nextDay() {
        currentIndex++;
        addDay();
    }

    public void addDay() {
        Random randomGenerator = new Random();
        weatherArrayList.add(new Weather(Weather.WeatherType.values()[randomGenerator.nextInt(3)]));
        updateCurrent();
    }

    public void previousDay() {
        if (currentIndex != 0) {
            currentIndex--;
            updateCurrent();
        }
    }

    public void setImagesInvisible() {
        cloudyImage.setVisibility(View.INVISIBLE);
        rainyImage.setVisibility(View.INVISIBLE);
        sunnyImage.setVisibility(View.INVISIBLE);
    }
}
