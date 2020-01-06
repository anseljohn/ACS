package com.example.colorpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.Gravity.CENTER;

public class Scores extends AppCompatActivity {

    private static ArrayList<Integer> rs;
    private static ArrayList<Integer> bs;
    private static ArrayList<Integer> gs;
    private static ArrayList<Double> ds;

    private LinearLayout lays;
    private TextView avgTxt;
    private double avg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        avgTxt = findViewById(R.id.avgTxtView);

        lays = findViewById(R.id.layout);

        rs = Game.getrs();
        bs = Game.getbss();
        gs = Game.getgs();
        ds = Game.getds();

        avg = 0;
        try {
            update();
        }
        catch (Exception e) {
            avgTxt.setText("No games. Go play some!");
        }

    }

    public void update() {
        for ( int i = 0; i < rs.size(); i++ ) {
            TextView txt = new TextView(this);
            txt.setText(ds.get(i) + "");
            txt.setBackgroundColor(Color.rgb(rs.get(i),gs.get(i),bs.get(i)));
            txt.setGravity(CENTER);
            txt.setHeight(100);
            txt.setTextColor(Color.rgb(255,255,255));
            txt.setTextSize((float)25);
            txt.setShadowLayer((float)1.5, 2 ,2, Color.rgb(0,0,0));

            lays.addView(txt);
            lays.setGravity(CENTER);

            avg += ds.get(i);
        }
        avg = avg/rs.size();
        avgTxt.setText("Average: " + (Math.round(avg * 100.0)/100.0));
    }

    public void onClick( View v ) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(i, 0);
    }

    public static ArrayList<Integer> getrs() {
        return rs;
    }

    public static ArrayList<Integer> getrgs() {
        return gs;
    }
    public static ArrayList<Integer> getbs() {
        return bs;
    }
    public static ArrayList<Double> getds() {
        return ds;
    }

}
