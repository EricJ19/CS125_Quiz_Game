package com.example.cs125finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity of the game after main menu and shows the randomly generated questions page.
 */
public class NewGameActivity extends AppCompatActivity {
    final Button answer1 = findViewById(R.id.answer1);
    final Button answer2 = findViewById(R.id.answer2);
    final Button answer3 = findViewById(R.id.answer3);
    final Button answer4 = findViewById(R.id.answer4);
    final TextView question = findViewById(R.id.question);
    final TextView playerScore = findViewById(R.id.playerScore);
    private int questionNumb;
    public static String currentScore;
    public static String previousCurrentScore;
    private static int numberTimesPlayed;
    public Player newPlayer;
    private boolean correct;
    private int activeScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newPlayer = new Player(savedInstanceState.getString("newPlayerName"), 0);
        setContentView(R.layout.new_game_activity);
        updateQandA();
        numberTimesPlayed++;
    }
    public NewGameActivity() {
        questionNumb = 0;
        numberTimesPlayed = 0;
        activeScore = 0;
    }
    /**
     * Generates the random questions.
     */
    public void questionGenerate() {
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // Change the label's text
                question.setText("");
            }
        });
    }
    /**
     * Generates the random answers.
     */
    public void answersGenerate() {
        answer1.setText("");
        answer2.setText("");
        answer3.setText("");
        answer4.setText("");
    }
    /**
     * Update questions and answers when an answer is clicked.
     * If the number of questions answered is 10, then
     */
    public void updateQandA() {
        final int correctAnswer = 2;
        if (questionNumb == 10) {
            if (numberTimesPlayed == 0) {
                previousCurrentScore = "0";
            } else {
                previousCurrentScore = currentScore;
            }
            currentScore = playerScore.toString();
            Intent intent = new Intent(this, FinalScores.class);
            startActivity(intent);
        }
        questionNumb++;
        questionGenerate();
        answersGenerate();
        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // Change the label's text
                if (correctAnswer == 1) {
                    answer1.setText("Correct: Placeholder for later");
                    correct = true;
                    updatePlayerScore();
                } else {
                    answer1.setText("Wrong: Placeholder for later");
                    correct = false;
                    updatePlayerScore();
                }
                updateQandA();
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // Change the label's text
                if (correctAnswer == 2) {
                    answer2.setText("Correct: Placeholder for later");
                    correct = true;
                    updatePlayerScore();
                } else {
                    answer2.setText("Wrong: Placeholder for later");
                    correct = false;
                    updatePlayerScore();
                }
                updateQandA();
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // Change the label's text
                if (correctAnswer == 3) {
                    answer3.setText("Correct: Placeholder for later");
                    correct = true;
                    updatePlayerScore();
                } else {
                    answer3.setText("Wrong: Placeholder for later");
                    correct = false;
                    updatePlayerScore();
                }
                updateQandA();
            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // Change the label's text
                if (correctAnswer == 4) {
                    answer4.setText("Correct: Placeholder for later");
                    correct = true;
                    updatePlayerScore();
                } else {
                    answer4.setText("Wrong: Placeholder for later");
                    correct = false;
                    updatePlayerScore();
                }
                updateQandA();
            }
        });
    }
    /**
     * Updates the score part of UI after the answer is clicked by user.
     */
    public void updatePlayerScore() {
        if (correct) {
            activeScore++;
            playerScore.setText(String.valueOf(activeScore));
        }
    }

}
