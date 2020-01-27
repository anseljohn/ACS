package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class TextEditor extends AppCompatActivity {

    Button next;
    EditText text;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_editor);
        next = findViewById(R.id.next2);
        text = findViewById(R.id.text);
        logout = findViewById(R.id.logout);

        Gson gson1 = new Gson();
        User u1 = gson1.fromJson(getIntent().getStringExtra("loggedInUser"), User.class);

        text.setText(u1.text);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                User u = gson.fromJson(getIntent().getStringExtra("loggedInUser"), User.class);

                if (text.getText().toString().length() > 0) {
                    u.text = text.getText().toString();
                    getSharedPreferences("accounts", MODE_PRIVATE).edit().putString(u.username, gson.toJson(u)).commit();
                    Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
