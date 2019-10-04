package com.example.tipcalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText cost;
    private Spinner tipSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tipSpinner = findViewById(R.id.tipSpinner);
        cost = findViewById(R.id.costInput);

        List<String> tipsArrayList = Arrays.asList(getResources().getStringArray(R.array.tipPercentages));
        ArrayAdapter<String> tipsArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipsArrayList);
        tipsArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipSpinner.setAdapter(tipsArrayAdapter);

        cost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
//                double costValue = Double.parseDouble(cost.getText().toString());
//                double tipPercentage = Double.parseDouble(tipSpinner.getSelectedItem().toString());
//                Toast.makeText(getApplicationContext(), String.valueOf((costValue * tipPercentage) + costValue), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
