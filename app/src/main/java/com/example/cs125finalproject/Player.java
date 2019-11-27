package com.example.cs125finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        final Intent intent = new Intent(this, MainActivity.class);
        Button goBackToMainMenu = findViewById(R.id.goBackToMainMenu);
        goBackToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // Change the label's text
                startActivity(intent);
            }
        });

    }
    /** the players name. */
    private String name;
    /** the amount of points the player got. */
    private int points;
    /** the constructor to create new player. */
    public Player(String setName, int setPoints) {
        name = setName;
        points = setPoints;
    }
    /** return player name. */
    public String getName() {
        return name;
    }
    /** returns amount of points gotten by player. */
    public int getPoints() {
        return points;
    }
    /** set the amount of points gotten by player. */
    public void setPoints(int setPoints) {
        points = setPoints;
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
