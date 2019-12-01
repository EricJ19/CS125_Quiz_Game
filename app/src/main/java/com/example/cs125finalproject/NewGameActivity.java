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
    /**
     * Buttons to the answers.
     */
    final Button answer1 = findViewById(R.id.answer1);
    final Button answer2 = findViewById(R.id.answer2);
    final Button answer3 = findViewById(R.id.answer3);
    final Button answer4 = findViewById(R.id.answer4);
    /**
     * The question TextView.
     */
    final TextView question = findViewById(R.id.question);
    /**
     * The playerScore TextView.
     */
    final TextView playerScore = findViewById(R.id.playerScore);
    /**
     * This is an internal counter (not visible to player)
     * reflects the question number the user is currently on.
     */
    private int questionNumb;
    /**
     * Current name and score of player for the player history UI.
     */
    public static String currentNameScore;
    /**
     * Previous current name and score of player for the player history UI.
     */
    public static String previousCurrentNameScore;
    /**
     * Number of times the player has played the quiz. Starts at -1 but updates to 0 on first create, which continues to update.
     */
    public static int numberTimesPlayed = -1;
    /**
     * Player stores player info: name, score.
     */
    public Player newPlayer;
    /**
     * Correct - true if answer is button clicked, false if answer is not button clicked.
     */
    private boolean correct;
    /**
     * The score counter that updates as questions are answered is visible to player.
     */
    private int activeScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionNumb = 0;
        activeScore = 0;
        newPlayer = new Player(savedInstanceState.getString("newPlayerName"),
                0, savedInstanceState.getInt("chosenTopic"));
        setContentView(R.layout.new_game_activity);
        numberTimesPlayed++;
        updateQandA();
    }
    /**
     * Generates the random questions.
     */
    public void questionGenerate() {
        question.setText("");
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
     * If the number of questions answered is 10 (after user clicked 10 answers), then the app will go to players page.
     * currentScore will be set to the score of this session.
     * previousCurrentScore is the score of last session and is "0" if there was no session previously.
     */
    public void updateQandA() {
        //the correctAnswer is based on the question and answer. Code doesn't reflect that and instead has int 2 for testing purposes.
        final int correctAnswer = 2;
        if (questionNumb == 10) {
            if (numberTimesPlayed == 0) {
                previousCurrentNameScore = "Player 0";
            } else {
                //important that previousCurrentScore is modified before currentScore.
                //This is so the previousCurrentScore is updated with the last CurrentScore before CurrentScore
                // is modified to reflect the score now.
                previousCurrentNameScore = currentNameScore;
            }
            newPlayer.setPoints(activeScore);
            currentNameScore = newPlayer.getName() + " " + newPlayer.getPoints();
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
                    correct = true;
                    updatePlayerScore();
                } else {
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
                    correct = true;
                    updatePlayerScore();
                } else {
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
                    correct = true;
                    updatePlayerScore();
                } else {
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
                    correct = true;
                    updatePlayerScore();
                } else {
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
        } else {
            activeScore--;
            playerScore.setText(String.valueOf(activeScore));
        }
    }

}
