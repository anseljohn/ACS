package com.example.colorpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.FILL;

public class Main3Activity extends AppCompatActivity {

    private static ArrayList<Integer> reds;
    private static ArrayList<Integer> blues;
    private static ArrayList<Integer> greens;
    private static ArrayList<Double> dists;
    private LinearLayout lays;
    private TextView avgTxt;
    private double avg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        avgTxt = findViewById(R.id.avgTxtView);

        lays = findViewById(R.id.layout);

        reds = Main2Activity.getReds();
        blues = Main2Activity.getBluess();
        greens = Main2Activity.getGreens();
        dists = Main2Activity.getDists();

        avg = 0;
        try {
            update();
        }
        catch (Exception e) {
            avgTxt.setText("No data to report");
        }

    }

    public void update() {
        for ( int i = 0; i < reds.size(); i++ ) {
            TextView txt = new TextView(this);
            txt.setText(Math.round(dists.get(i)) + "");
            txt.setBackgroundColor(Color.rgb(reds.get(i),greens.get(i),blues.get(i)));
            txt.setGravity(CENTER);
            txt.setHeight(100);
            txt.setTextColor(Color.rgb(255,255,255));
            txt.setTextSize((float)25);
            txt.setShadowLayer((float)1.5, 2 ,2, Color.rgb(0,0,0));
            lays.addView(txt);
            lays.setGravity(CENTER);
            avg += dists.get(i);
        }
        avg = avg/reds.size();
        avgTxt.setText("Average distance off of " + Math.round(avg));
    }

    public void onClick( View v ) {
        Intent openMainActivity = new Intent(Main3Activity.this, MainActivity.class);
        openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(openMainActivity, 0);
    }

    public static ArrayList<Integer> getReds() {
        return reds;
    }

    public static ArrayList<Integer> getrGreens() {
        return greens;
    }
    public static ArrayList<Integer> getBlues() {
        return blues;
    }
    public static ArrayList<Double> getDists() {
        return dists;
    }

}
