package com.example.spellinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndGame extends AppCompatActivity {

//        Intent gameActivityIntent = getIntent();
        int wordsCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        wordsCorrect = GameActivity.wordsCorrect;
        TextView endScoreTextView = findViewById(R.id.endScore);
        endScoreTextView.setText("Score: " + wordsCorrect + "/20");

        Button startAgainButton = findViewById(R.id.startAgain);
        startAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameActivityIntent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(gameActivityIntent);
            }
        });
    }
}
