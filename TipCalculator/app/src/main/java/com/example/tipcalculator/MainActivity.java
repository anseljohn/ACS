package com.example.tipcalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText cost;
    private Spinner tipSpinner;
    private TextView finalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tipSpinner = findViewById(R.id.tipSpinner);
        cost = findViewById(R.id.costInput);
        finalCost = findViewById(R.id.finalCost);

        CharSequence[] tipsArray = {"0.0", "5.0", "10.0", "15.0", "20.0"};
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipsArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipSpinner.setAdapter(adapter);

        cost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (cost.getText() != null && ! cost.getText().toString().isEmpty() && ! cost.getText().toString().equals(".")) {
                    double costValue = Double.parseDouble(cost.getText().toString());
                    double tipPercentage = Double.parseDouble(tipSpinner.getSelectedItem().toString());
                    DecimalFormat format = new DecimalFormat("##.00");
                    String finalCostString = format.format((costValue * (tipPercentage / 100)) + costValue);
                    String[] split = finalCostString.split("\\.");
                    if (split[1].length() == 1) {
                        finalCostString += "0";
                    }
                    if (finalCostString.substring(0,1).equals(".")) {
                        finalCostString = "0" + finalCostString;
                    }
                    finalCost.setText("$" + finalCostString);

                    split = cost.getText().toString().split("\\.");
                    if (cost.getText().toString().contains(".") && split.length == 2 && split[1].length() > 2) {
                        split[1] = split[1].substring(0, 2);
                        cost.setText(split[0] + "." + split[1]);
                        cost.setSelection(cost.getText().toString().length());
                    }

                } else if (cost.getText().toString().isEmpty()) {
                    cost.setText("");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        tipSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (cost.getText() != null && cost.getText().toString().length() > 0) {
                    double costValue = Double.parseDouble(cost.getText().toString());
                    double tipPercentage = Double.parseDouble(adapterView.getItemAtPosition(i).toString());
                    DecimalFormat format = new DecimalFormat("##.00");
                    String finalCostString = format.format((costValue * (tipPercentage / 100)) + costValue);
                    String[] split = finalCostString.split("\\.");
                    if (split[1].length() == 1) {
                        finalCostString += "0";
                    }
                    finalCost.setText("$" + finalCostString);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                adapterView.setSelection(2);
            }
        });
    }
}
