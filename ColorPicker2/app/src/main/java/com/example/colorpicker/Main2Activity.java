package com.example.colorpicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private EditText redEdt;
    private EditText greenEdt;
    private EditText blueEdt;
    private TextView redTxt;
    private TextView greenTxt;
    private TextView blueTxt;
    private TextView textViewGood;
    private TextView redView;
    private TextView greenView;
    private TextView blueView;
    private Button subBtn;
    private Button menuBtn;
    private Button nextBtn;
    private TextView title;
    private int red;
    private int green;
    private int blue;
    private ConstraintLayout bg;
    private static ArrayList<Integer> reds;
    private static ArrayList<Integer> greens;
    private static ArrayList<Integer> blues;
    private static ArrayList<Double> dists;
    private double avg;
    private TextView howYouDo;
    private ImageView thumbs;
    private TextView distTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bg = findViewById(R.id.mylayout);

        thumbs = findViewById(R.id.tumbs);
        howYouDo = findViewById(R.id.gfr);
        distTxt = findViewById(R.id.distanceTxtView);

        nextBtn = findViewById(R.id.nxtBtn);
        menuBtn = findViewById(R.id.nxtBtn);

        redEdt = findViewById(R.id.redEditTxt);
        greenEdt = findViewById(R.id.greenEditTxt);
        blueEdt = findViewById(R.id.blueEditTxt);

        redView = findViewById(R.id.predRed);
        greenView = findViewById(R.id.predGreen);
        blueView = findViewById(R.id.predBlue);
        redTxt = findViewById(R.id.actRedValue);
        greenTxt = findViewById(R.id.actGreenValue);
        blueTxt = findViewById(R.id.actBlueValue);
        title = findViewById(R.id.titleTxtBox);
        textViewGood = findViewById(R.id.showText);

        subBtn = findViewById(R.id.guessBTN);
        menuBtn = findViewById(R.id.menuBtn);

        red = (int)(Math.random()*256);
        green = (int)(Math.random()*256);
        blue = (int)(Math.random()*256);

        bg.setBackgroundColor(Color.rgb(red,green,blue));

        update();

        if ( reds == null ) {
            reds = new ArrayList<>();
            greens = new ArrayList<>();
            blues = new ArrayList<>();
            dists = new ArrayList<>();
        }

        redEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redEdt.setSelection(redEdt.getText().length());
            }
        });

    }

    public void subOnClick ( View v ) {
        if ( !redEdt.getText().toString().equals("") || !greenEdt.getText().toString().equals("") || !blueEdt.getText().toString().equals("") )  {
            if ( Integer.parseInt(redEdt.getText().toString()) < 255 || Integer.parseInt(greenEdt.getText().toString()) < 255 || Integer.parseInt(blueEdt.getText().toString() ) < 255 ) {
                System.out.println(redEdt.getText().toString());
                title.setText("How'd you do?");
                redTxt.setText(red + "");
                greenTxt.setText(green + "");
                blueTxt.setText(blue + "");
                redView.setText("Your Predicted Red Value:");
                greenView.setText("Your Predicted Green Value:");
                blueView.setText("Your Predicted Blue Value:");
                textViewGood.setVisibility(View.VISIBLE);
                redTxt.setVisibility(View.VISIBLE);
                greenTxt.setVisibility(View.VISIBLE);
                blueTxt.setVisibility(View.VISIBLE);
                menuBtn.setVisibility(View.VISIBLE);
                nextBtn.setVisibility(View.VISIBLE);
                subBtn.setVisibility(View.INVISIBLE);
                int r = red - Integer.parseInt(redEdt.getText().toString());
                int g = green - Integer.parseInt(greenEdt.getText().toString());
                int b = blue - Integer.parseInt(blueEdt.getText().toString());
                double dist = Math.sqrt((Math.pow(r, 2) + Math.pow(g, 2) + Math.pow(b, 2)));
                avg = 0;
                for (int i = 0; i < dists.size(); i++) {
                    avg += dists.get(i);
                }
                avg = avg / dists.size();
                if (dist < avg) {
                    howYouDo.setText("You did better than your average!");
                    thumbs.setImageResource(R.drawable.thumbup);
                    distTxt.setText("You guess was a distance of " + Math.round(dist) + " off!");
                } else {
                    howYouDo.setText("You did worse than your average!");
                    thumbs.setImageResource(R.drawable.thumbdown);
                    distTxt.setText("You guess was a distance of " + Math.round(dist) + " off!");
                }
                howYouDo.setVisibility(View.VISIBLE);
                thumbs.setVisibility(View.VISIBLE);
                distTxt.setVisibility(View.VISIBLE);
                reds.add(red);
                greens.add(green);
                blues.add(blue);
                dists.add(dist);
            }
            else {
                Toast.makeText(this, "Please enter valid guesses!", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this, "Please enter valid guesses!", Toast.LENGTH_LONG).show();
        }
    }

    public void nxtClick ( View v ) {
        title.setText("Enter Your Guess!");
        textViewGood.setVisibility(View.INVISIBLE);
        redTxt.setVisibility(View.INVISIBLE);
        greenTxt.setVisibility(View.INVISIBLE);
        blueTxt.setVisibility(View.INVISIBLE);
        menuBtn.setVisibility(View.INVISIBLE);
        nextBtn.setVisibility(View.INVISIBLE);
        howYouDo.setVisibility(View.INVISIBLE);
        thumbs.setVisibility(View.INVISIBLE);
        distTxt.setVisibility(View.INVISIBLE);
        redView.setText("Enter Predicted Red Value:");
        greenView.setText("Enter Predicted Green Value:");
        blueView.setText("Enter Predicted Blue Value:");
        red = (int)(Math.random()*256);
        green = (int)(Math.random()*256);
        blue = (int)(Math.random()*256);
        subBtn.setVisibility(View.VISIBLE);
        bg.setBackgroundColor(Color.rgb(red,green,blue));
    }

    public void mainMenuClick( View v ) {
        Intent myIntent = new Intent(this, MainActivity.class);
        Main2Activity.this.startActivity(myIntent);
    }

    public static ArrayList<Integer> getReds() {
        return reds;
    }

    public static ArrayList<Integer> getGreens() {
        return greens;
    }

    public static ArrayList<Integer> getBluess() {
        return blues;
    }

    public static ArrayList<Double> getDists() {
        return dists;
    }

    public void update() {

        try {
            reds = Main3Activity.getReds();
            greens = Main3Activity.getrGreens();
            blues = Main3Activity.getBlues();
            dists = Main3Activity.getDists();
        }
        catch ( Exception e ) {

        }

    }

}
