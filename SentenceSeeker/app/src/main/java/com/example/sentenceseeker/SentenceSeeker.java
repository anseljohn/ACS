package com.example.sentenceseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class SentenceSeeker extends AppCompatActivity {
    private SeekBar seekBar;
    private EditText sentenceInput;
    private int seekBarMax;
    private String[] splitWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence_seeker);

        seekBar = findViewById(R.id.seekThroughSentence);
        sentenceInput = findViewById(R.id.sentenceInput);

        sentenceInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                splitWords = sentenceInput.getText().toString().split(" ");
                seekBarMax = splitWords.length;
                seekBar.setMax(seekBarMax - 1);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int MAX = seekBarMax;
                if (progress < MAX) {
                    TextView wordOutputTextView = findViewById(R.id.wordOutput);
                    String toOutput = "Word #" + (progress + 1) + ": " + splitWords[progress];
                    wordOutputTextView.setText(toOutput);
                }

            }
        });
    }
}
