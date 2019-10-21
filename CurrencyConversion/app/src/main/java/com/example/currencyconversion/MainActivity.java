package com.example.currencyconversion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button convertButton;
    private TextView rupeesText;
    private EditText USDInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        convertButton = findViewById(R.id.convertButton);
        rupeesText = findViewById(R.id.rupees);
        USDInput = findViewById(R.id.USDInput);

        USDInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //                USDInput.setText("$" + USDInput.getText());
                String[] splitByDecimals;
                int afterDecimalLength;
                if (USDInput.getText().toString().contains(".")) {
                    splitByDecimals = USDInput.getText().toString().split("\\.");
                    if (splitByDecimals.length == 2) {
                        afterDecimalLength = splitByDecimals[1].length();
                        Log.println(Log.ASSERT, "DECIMAL PLACES: ", Integer.toString(afterDecimalLength));
                        if (afterDecimalLength > 2) {
                            afterDecimalLength--;
                            USDInput.setText(splitByDecimals[0] + "." + splitByDecimals[1].substring(0, 2));
                            USDInput.setSelection(USDInput.getText().toString().length());
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (USDInput.getText().toString().equals(".") || Double.parseDouble(USDInput.getText().toString()) == 0.0) {
                    rupeesText.setText("₹ 0.00");
                } else{
                    DecimalFormat format = new DecimalFormat("₹ ##.00");
                    rupeesText.setText(format.format(71.53 * Double.parseDouble(USDInput.getText().toString())));
                }
            }
        });



    }
}
