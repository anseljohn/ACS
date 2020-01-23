package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView createtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createtv = findViewById(R.id.already);
        createtv.setPaintFlags(createtv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        createtv.setText("Create an account");

        createtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getApplicationContext(), CreateAccount.class);
                startActivity(i);
            }
        });
    }
}
