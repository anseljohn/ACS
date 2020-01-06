package com.example.colorpicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Game extends AppCompatActivity {

    private EditText re;
    private EditText ge;
    private EditText be;
    private TextView rt;
    private TextView gt;
    private TextView bt;
    private TextView actualtv;
    private TextView rv;
    private TextView gv;
    private TextView bv;
    private Button submit;
    private Button menu;
    private Button next;
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
    private TextView resultt;
    private ImageView thumbs;
    private TextView distt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        bg = findViewById(R.id.mylayout);

        thumbs = findViewById(R.id.tumbs);
        resultt = findViewById(R.id.goodworse);
        distt = findViewById(R.id.distanceTxtView);

        next = findViewById(R.id.next);
        menu = findViewById(R.id.menu);

        re = findViewById(R.id.redEditTxt);
        ge = findViewById(R.id.greenEditTxt);
        be = findViewById(R.id.blueEditTxt);

        rv = findViewById(R.id.predRed);
        gv = findViewById(R.id.predGreen);
        bv = findViewById(R.id.predBlue);
        rt = findViewById(R.id.actRedValue);
        gt = findViewById(R.id.actGreenValue);
        bt = findViewById(R.id.actBlueValue);
        title = findViewById(R.id.titleTxtBox);
        actualtv = findViewById(R.id.showText);

        submit = findViewById(R.id.guessBTN);
        menu = findViewById(R.id.menu);

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

        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                re.setSelection(re.getText().length());
            }
        });

    }

    public void subOnClick ( View v ) {
        if ( !re.getText().toString().equals("") || !ge.getText().toString().equals("") || !be.getText().toString().equals("") )  {
            if ( Integer.parseInt(re.getText().toString()) < 255 || Integer.parseInt(ge.getText().toString()) < 255 || Integer.parseInt(be.getText().toString() ) < 255 ) {
                System.out.println(re.getText().toString());
                title.setVisibility(View.INVISIBLE);
                rt.setText(red + "");
                gt.setText(green + "");
                bt.setText(blue + "");
                rv.setText("Your Predicted Red Value:");
                gv.setText("Your Predicted Green Value:");
                bv.setText("Your Predicted Blue Value:");
                actualtv.setVisibility(View.VISIBLE);
                rt.setVisibility(View.VISIBLE);
                gt.setVisibility(View.VISIBLE);
                bt.setVisibility(View.VISIBLE);
                menu.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                submit.setVisibility(View.INVISIBLE);
                int r = red - Integer.parseInt(re.getText().toString());
                int g = green - Integer.parseInt(ge.getText().toString());
                int b = blue - Integer.parseInt(be.getText().toString());
                double dist = Math.sqrt((Math.pow(r, 2) + Math.pow(g, 2) + Math.pow(b, 2)));
                avg = 0;
                for (int i = 0; i < dists.size(); i++) {
                    avg += dists.get(i);
                }
                avg = avg / dists.size();
                if (dist < avg) {
                    resultt.setText("You did better than your average!");
                    thumbs.setImageResource(R.drawable.thumbup);
                    distt.setText("You guess was a distance of " + Math.round(dist) + " off!");
                } else {
                    resultt.setText("You did worse than your average!");
                    thumbs.setImageResource(R.drawable.thumbdown);
                    distt.setText("You guess was a distance of " + Math.round(dist) + " off!");
                }
                resultt.setVisibility(View.VISIBLE);
                thumbs.setVisibility(View.VISIBLE);
                distt.setVisibility(View.VISIBLE);
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
        actualtv.setVisibility(View.INVISIBLE);
        rt.setVisibility(View.INVISIBLE);
        gt.setVisibility(View.INVISIBLE);
        bt.setVisibility(View.INVISIBLE);
        menu.setVisibility(View.INVISIBLE);
        next.setVisibility(View.INVISIBLE);
        resultt.setVisibility(View.INVISIBLE);
        thumbs.setVisibility(View.INVISIBLE);
        distt.setVisibility(View.INVISIBLE);
        rv.setText("Enter Predicted Red Value:");
        gv.setText("Enter Predicted Green Value:");
        bv.setText("Enter Predicted Blue Value:");
        red = (int)(Math.random()*256);
        green = (int)(Math.random()*256);
        blue = (int)(Math.random()*256);
        submit.setVisibility(View.VISIBLE);
        bg.setBackgroundColor(Color.rgb(red,green,blue));
    }

    public void mainMenuClick( View v ) {
        Intent myIntent = new Intent(this, MainActivity.class);
        Game.this.startActivity(myIntent);
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
