package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView createtv;
    Button submit;
    EditText username;
    EditText password;
    TextView errors;
    CheckBox showpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createtv = findViewById(R.id.already);
        submit = findViewById(R.id.next);
        username = findViewById(R.id.user);
        password = findViewById(R.id.pass);
        errors = findViewById(R.id.errors2);
        showpa = findViewById(R.id.showpa);

        showpa.setVisibility(View.INVISIBLE);

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (password.getText().toString().length() > 0) {
                    showpa.setVisibility(View.VISIBLE);
                } else {
                    showpa.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        showpa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    password.setSelection(password.length());
                } else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    password.setSelection(password.length());
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gson gson = new Gson();
                if (username.getText().toString().length() < 1 && password.getText().toString().length() < 1) {
                    errors.setText("Please fill out all fields.");
                } else if (! getSharedPreferences("accounts", MODE_PRIVATE).contains(username.getText().toString())) {
                    errors.setText("User does not exist.");
                } else if (password.getText().toString().length() < 1) {
                    errors.setText("Please enter your password");
                } else {
                    User u = gson.fromJson(getSharedPreferences("accounts", MODE_PRIVATE).getString(username.getText().toString(), "notauser"), User.class);
                    if(u.decrypt(u.password, u.username).equals(password.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Logging in!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), TextEditor.class);
                        i.putExtra("loggedInUser", gson.toJson(u));
                        startActivity(i);
                    } else {
                        errors.setText("Invalid password!");
                    }
                }
            }
        });

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
