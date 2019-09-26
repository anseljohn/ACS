package com.example.spellinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button beginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beginButton = findViewById(R.id.beginButton);

        beginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent toChangeActivity = new Intent(getApplicationContext(), GameActivity.class);
                    startActivity(toChangeActivity);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
