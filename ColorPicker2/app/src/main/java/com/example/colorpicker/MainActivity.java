package com.example.colorpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick( View v ) {
        Intent myIntent = new Intent(this, Main2Activity.class);
        MainActivity.this.startActivity(myIntent);
    }
    public void viewHistory ( View v ) {
        Intent myIntent = new Intent(this, Main3Activity.class);
        MainActivity.this.startActivity(myIntent);
    }
}


