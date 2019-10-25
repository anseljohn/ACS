package com.example.spellinggame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class GameActivity extends AppCompatActivity {

    private TextView timerTextView;
    private TextView wordToSpellTextView;
    private Switch submitAnswerSwitch;
    private int selectedRadioButtonId;
    private View selectedRadioButton;
    private RadioGroup radioButtonGroup;
    private int wordsToGo;
    public static int wordsCorrect = 0;
    private Word wordToSpell;
    private TextView wordsToGoTextView;
    private TextView wordsCorrectTextView;

    private Word[] wordsArray = {new Word(false, "abcense"),
                                 new Word(true, "absence"),
                                 new Word(true, "intelligence"),
                                 new Word(false, "inteligence"),
                                 new Word(true, "immediately"),
                                 new Word(false, "imediately"),
                                 new Word(true, "privilege"),
                                 new Word(false, "privelege"),
                                 new Word(true, "underrate"),
                                 new Word(false, "underate"),
                                 new Word(true, "writing"),
                                 new Word(false,"writeing"),
                                 new Word(true, "indict"),
                                 new Word(false, "indite"),
                                 new Word(true, "liaison"),
                                 new Word(false, "liason"),
                                 new Word(true, "outrageous"),
                                 new Word(false, "outragous"),
                                 new Word(true, "tyranny"),
                                 new Word(false, "tyrany")};
    private ArrayList<Word> words = new ArrayList<>(Arrays.asList(wordsArray));

    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("Got to oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        wordsCorrect = 0;
        wordToSpell = words.get((int) (Math.random() * 20));
        wordsToGo = 20;
        radioButtonGroup = findViewById(R.id.answerChoices);
        submitAnswerSwitch = findViewById(R.id.submitSwitch);
        timerTextView = findViewById(R.id.timerText);
        wordToSpellTextView = findViewById(R.id.wordText);
        wordToSpellTextView.setText(wordToSpell.word);
        wordsCorrectTextView = findViewById(R.id.wordsCorrect);
        wordsToGoTextView = findViewById(R.id.wordsToGo);

        timer = new CountDownTimer(300000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) millisUntilFinished / 1000;
                int minutes = seconds / 60;
                seconds %= 60;
                timerTextView.setText(String.format("%d:%02d", minutes, seconds));
            }

            @Override
            public void onFinish() {
                Intent toEndGame = new Intent(getApplicationContext(), EndGame.class);
                startActivity(toEndGame);
            }
        }.start();

        submitAnswerSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedRadioButtonId = radioButtonGroup.getCheckedRadioButtonId();
                selectedRadioButton = radioButtonGroup.findViewById(selectedRadioButtonId);
                int selectedRadioButtonIndex = radioButtonGroup.indexOfChild(selectedRadioButton);
                nextWord(selectedRadioButtonIndex);
                radioButtonGroup.check(R.id.radioSkipSelection);
                submitAnswerSwitch.setChecked(false);
            }
        });
        System.out.println("End create");
    }

    public void nextWord(int selectedRadioButtonIndex) {
        wordsToGo--;
        if (wordToSpell.spelledCorrectly) {
            if (selectedRadioButtonIndex == 0) {
                wordsCorrect++;
            } else if (selectedRadioButtonIndex == 2) {
                end();
            }
        } else {
            if (selectedRadioButtonIndex == 0) {
                end();
            } else if (selectedRadioButtonIndex == 2) {
                wordsCorrect++;
            }

        }
        if (wordsToGo == 0) {
            end();
        } else {
            words.remove(wordToSpell);
            wordToSpell = words.get((int) (Math.random() * wordsToGo));
            wordToSpellTextView.setText(wordToSpell.word);
            String setWordsToGo = wordsToGo + "/20";
            wordsToGoTextView.setText(setWordsToGo);
            String setCorrectWords = wordsCorrect + "/20";
            wordsCorrectTextView.setText(setCorrectWords);
        }

    }

    public void end() {
        Intent main = new Intent(getApplicationContext(), EndGame.class);
        main.putExtra("score", Integer.toString(wordsCorrect));
        setResult(RESULT_OK, main);
        startActivity(main);
    }
}
