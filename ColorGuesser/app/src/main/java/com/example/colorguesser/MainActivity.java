package com.example.colorguesser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generateBackground();
    }

    public void generateBackground() {
        final View bg = findViewById(R.id.layout);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                bg.setBackgroundColor(Color.parseColor(rgbToHex(getRandomColor())));
            }
        };
    }

    public int[] getRandomColor() {
        Random random = new Random();
        return new int[] {random.nextInt(255), random.nextInt(255), random.nextInt(255)};
    }

    public String rgbToHex(int[] rgb) {
        return String.format("#%02x%02x%02x", rgb[0], rgb[1], rgb[2]);
    }
}
