package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner tipSpinner = findViewById(R.id.tipSpinner);
        ArrayAdapter<String> tipSpinnerArrayAdapter;
        tipSpinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.tipSpinner, getResources().getStringArray(R.string.tipPercentages))//Arrays.asList("0.0", "5.0", "10.0", "15.0", "20.0"));
        tipSpinner.setAdapter(tipSpinnerArrayAdapter);
    }
}
