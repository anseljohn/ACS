package com.example.colorguesser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Collections;

public class Game extends AppCompatActivity {

    private EditText rG;
    private EditText gG;
    private EditText bG;

    ArrayList<EditText> vals;
    EditText[] prev;

    boolean[] filled = new boolean[]{false, false, false};

    Button sub;

    com.example.colorguesser.Color actual;
    com.example.colorguesser.Color guess;
    Pair p;

    ConstraintLayout lay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        lay = findViewById(R.id.lay);
        actual = new com.example.colorguesser.Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));

        lay.setBackgroundColor(Color.parseColor(actual.toHex()));

        rG = findViewById(R.id.redValue);
        gG = findViewById(R.id.greenValue);
        bG = findViewById(R.id.blueValue);

        prev = new EditText[] {rG, gG, bG};
        vals = new ArrayList<>();
        Collections.addAll(vals, prev);

        sub = findViewById(R.id.sub);
        sub.setEnabled(false);

        for (final EditText val : vals) {
            val.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (!val.getText().toString().isEmpty()) {
                        filled[vals.indexOf(val)] = true;
                        if (Integer.parseInt(val.getText().toString()) > 255) val.setText("255");
                    }

                    if (filled[0] && filled[1] && filled[2]) sub.setEnabled(true);
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = new Pair(actual, guess);
            }
        });


    }
}
