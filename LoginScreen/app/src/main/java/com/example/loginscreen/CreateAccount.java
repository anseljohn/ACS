package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Paint;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.FileWriter;

public class CreateAccount extends AppCompatActivity {

    Button createAcc;
    TextView already;
    EditText pass;
    EditText passConf;
    TextView errors;
    CheckBox showPass;

    enum ConfirmType {
        PASS_REQ,
        CONF_PASS,
        USER
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        createAcc = findViewById(R.id.createBut);
        already = findViewById(R.id.already);
        pass = findViewById(R.id.cpass);
        passConf = findViewById(R.id.cconf);
        errors = findViewById(R.id.errors);
        showPass = findViewById(R.id.showPass);

        already.setPaintFlags(already.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        already.setText("Already have an account?");

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String username =
                String password = pass.getText().toString();
                String confPass = passConf.getText().toString();
                boolean valid = checkAccount("test", password, confPass);
                if (valid) {

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
//                    if (valid[1] == ConfirmType.PASS_REQ) {
//                    } else if (valid[1] == ConfirmType.CONF_PASS) {
//                        Toast.makeText(getApplicationContext(), "Passwords don't match", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
//                    }
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

        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (pass.getText().length() > 0) {
                    showPass.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        showPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                        pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        pass.setSelection(pass.length());

                    if (passConf.getText().length() > 0) {
                        passConf.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        passConf.setSelection(pass.length());
                    }
                } else {
                        pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        pass.setSelection(pass.length());

                    if (passConf.getText().length() > 0) {
                        passConf.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        passConf.setSelection(pass.length());
                    }
                }
            }
        });
    }

    public boolean checkAccount(String username, String password, String confPass) {

        boolean valid = false;

        if (username.length() < 1 || password.length() < 1 || confPass.length() < 1) {
            errors.setText("Please fill out all the fields.");
            return false;
        } else {
            valid = true;
        }

        if (! confPass.equals(password)) {
            errors.setText("Passwords do not match.");
            return false;
        } else {
            valid = true;
        }

        String passError = "Your password does not meet the following password requirements.\nIt must contain at least:\n";
        boolean passEr = false;
        if (! password.matches("^.{5,10}$")) {
            passEr = true;
            passError += " - 5 to 10 characters\n";
        } if (! password.matches(".*[A-Z].*")) {
            passEr = true;
            passError += " - 1 uppercase letter\n";
        } if (! password.matches(".*[0-9].*")) {
            passEr = true;
            passError += " - 1 number (0-9)";
        } if (! password.matches(".*[a-z].*")) {
            passEr = true;
            passError += " - 1 lowercase letter\n";
        } if (! password.matches(".*[!@#$%^&*()].*")) {
            passEr = true;
            passError += " - 1 special character !@#$%^&*()";
        } if (passEr) {
            errors.setText(passError);
            return false;
        } else {
            valid = true;
        }

        if (valid) {
            Toast.makeText(getApplicationContext(), "Account created!", Toast.LENGTH_SHORT).show();
        }

        return valid;
    }
}
