package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Paint;
import android.widget.Toast;

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
//                String username =
                String password = pass.getText().toString();
                String confPass = passConf.getText().toString();
                Object[] valid = checkAccount("test", password, confPass);
                if ((Boolean) valid[0]) {

                    User u = new User(password, confPass);
                    Gson gson = new Gson();
                    String json = gson.toJson(u);
                    try {
                        SharedPreferences  mPrefs = getPreferences(MODE_PRIVATE);
                        SharedPreferences.Editor prefsEditor = mPrefs.edit();
                        prefsEditor.putString(u.username, json);
                        prefsEditor.commit();
                        Toast.makeText(getApplicationContext(), "Account created!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "NOT WORKING", Toast.LENGTH_SHORT).show();
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

    public Object[] checkAccount(String username, String password, String confPass) {

        if (! confPass.equals(password)) {
            return new Object[]{false, ConfirmType.CONF_PASS} ;
        }
        if (! password.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z]{5,10}")) {
            return new Object[]{false, ConfirmType.PASS_REQ};
        }

        return new Object[]{true};
    }
}
