package com.example.cs125finalproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Shows the list of the last 10 scores.
 */
public class Player extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_game_activity);
        updatePlayerScoresUI(NewGameActivity.currentScore, NewGameActivity.previousCurrentScore);
    }
    private String[] playerLast9ScoresStr = new String[9];
    public void updatePlayerScoresUI(String currentScore, String previousCurrentScore) {
        TextView playerCurrentScore = findViewById(R.id.playerCurrentScore);
        TextView playerLast9Scores = findViewById(R.id.playerLast9Scores);
        for (int i = 0; i < playerLast9ScoresStr.length - 1; i++) {
            playerLast9ScoresStr[i] = playerLast9ScoresStr[i + 1];
        }
        playerLast9ScoresStr[8] = previousCurrentScore;
        playerCurrentScore.setText(currentScore);
        playerLast9Scores.setText(playerLast9ScoresStr.toString());
    }

}
