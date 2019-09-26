package com.example.tipcalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText cost = findViewById(R.id.costInput);
    private Spinner tipSpinner = findViewById(R.id.tipSpinner);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayAdapter<String> tipSpinnerArrayAdapter;
        tipSpinnerArrayAdapter = new ArrayAdapter<String>(this, (int)R.id.tipSpinner, getResources().getStringArray(R.array.tipPercentages)); //Arrays.asList("0.0", "5.0", "10.0", "15.0", "20.0"));
        tipSpinner.setAdapter(tipSpinnerArrayAdapter);
        cost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                double costValue = Double.parseDouble(cost.getText().toString());
                double tipPercentage = Double.parseDouble(tipSpinner.getSelectedItem().toString());
                Toast.makeText(getApplicationContext(), String.valueOf((costValue * tipPercentage) + costValue), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
