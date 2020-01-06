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
        setContentView(R.layout.activity_main3);

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
            txt.setText(Math.round(ds.get(i)) + "");
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
        avgTxt.setText("Average distance off of " + Math.round(avg));
    }

    public void onClick( View v ) {
        Intent openMainActivity = new Intent(Main3Activity.this, MainActivity.class);
        openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityIfNeeded(openMainActivity, 0);
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
