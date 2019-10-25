package com.example.spellinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Finished extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);

        int wordsCorrect = GameActivity.wordsCorrect;
        TextView finishedTextView = findViewById(R.id.finishedScore);
        finishedTextView.setText("Score: " + wordsCorrect + "/20");
        Button startAgainButton = findViewById(R.id.startAgain2);
        startAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameActivityIntent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(gameActivityIntent);
            }
        });
    }
}
