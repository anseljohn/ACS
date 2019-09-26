package com.example.spellinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private TextView timerTextView;

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
