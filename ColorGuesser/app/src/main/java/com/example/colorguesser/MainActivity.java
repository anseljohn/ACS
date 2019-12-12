package com.example.colorguesser;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        v = (Button) findViewById(R.id.button2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR, new int[] {0x37FF92, 0x37FCFF});
        gradientDrawable.setCornerRadius(0f);
//        mainView.setBackground(gradientDrawable);
        Log.e("BRUH", v.toString());
        Log.e("BRUH2", gradientDrawable.toString());
    }
}
