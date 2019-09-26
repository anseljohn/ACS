package com.example.spellinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private TextView timerTextView;
    private Word[] wordsArray = {new Word(false, "abcense"),
                                 new Word(true, "absence"),
                                 new Word(true, "intelligence"),
                                 new Word(false, "inteligence"),
                                 new Word(true, "immediately"),
                                 new Word(false, "imediately"),
                                 new Word(true, "privilege"),
                                 new Word(false, "privelege"),
                                 new Word(true, "underrate"),
                                 new Word(false, "underate"),
                                 new Word(true, "writing"),
                                 new Word(false,"writeing"),
                                 new Word(true, "indict"),
                                 new Word(false, "indite"),
                                 new Word(true, "liaison"),
                                 new Word(false, "liason"),
                                 new Word(true, "outrageous"),
                                 new Word(false, "outragous"),
                                 new Word(true, "tyranny"),
                                 new Word(false, "tyrany")};
    private List<Word> words = Arrays.asList(wordsArray);

    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        timerTextView = findViewById(R.id.timerText);


        timer = new CountDownTimer(300000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) millisUntilFinished / 1000;
                int minutes = seconds / 60;
                seconds %= 60;
                timerTextView.setText(String.format("%d:%02d", minutes, seconds));
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }
}
