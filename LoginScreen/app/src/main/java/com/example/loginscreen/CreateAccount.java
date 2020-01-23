package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Paint;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.FileWriter;

public class CreateAccount extends AppCompatActivity {

    Button createAcc;
    TextView already;
    EditText pass;
    EditText passConf;

    enum ConfirmType {
        PASS_REQ,
        CONF_PASS,
        USER,
        VALID
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        createAcc = findViewById(R.id.createBut);
        already = findViewById(R.id.already);
        pass = findViewById(R.id.cpass);
        passConf = findViewById(R.id.cconf);

        already.setPaintFlags(already.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        already.setText("Already have an account?");

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User u = new User(pass.getText().toString(), passConf.getText().toString());
                ConfirmType returned = checkAccount(u);
                if (returned == ConfirmType.VALID) {
                    Gson gson = new Gson();
                    String json = gson.toJson(u);
                    try {
                        FileWriter file = new FileWriter("accounts.json");
                        file.write(json);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    public boolean checkAccount(User u) {
        boolean valid = false;
        ConfirmType cf = null;
        String password = pass.getText().toString();
        String confPass = passConf.getText().toString();

        if (! confPass.equals(password)) {
            return false;
        }

        if (! password.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z]{5,10}")) {
            return false;
        }

        if (cf == null) {
            cf = ConfirmType.VALID;
        }



        return false;
    }
}
