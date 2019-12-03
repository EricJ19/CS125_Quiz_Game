package com.example.cs125finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreHistory extends AppCompatActivity {
    private static String[] playerLast9ScoresStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_history);
        playerLast9ScoresStr = new String[9];
        if (Main2Activity.numberTimesPlayed == 0) {
            for (int i = 0; i < playerLast9ScoresStr.length; i++) {
                playerLast9ScoresStr[i] = "Player 0";
            }
        }
        Player currentPlayer = Main2Activity.newPlayer;
        String currentInfo = currentPlayer.getName() + " " + currentPlayer.getPoints();
        updatePlayerScoresUI(currentInfo, Main2Activity.previousCurrentNameScore);
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
    /**
     *
     * @param currentScore - the score the user just received.
     * @param previousCurrentScore - the previous score the user just received.
     * Updates the user score history and displays it in the player_last_scores xml.
     */

    public void updatePlayerScoresUI(String currentScore, String previousCurrentScore) {
        TextView playerCurrentScore = findViewById(R.id.playerCurrentScore);
        TextView playerLast9Scores1 = findViewById(R.id.playerLast9Scores1);
        TextView playerLast9Scores2 = findViewById(R.id.playerLast9Scores2);
        TextView playerLast9Scores3 = findViewById(R.id.playerLast9Scores3);
        TextView playerLast9Scores4 = findViewById(R.id.playerLast9Scores4);
        TextView playerLast9Scores5 = findViewById(R.id.playerLast9Scores5);
        TextView playerLast9Scores6 = findViewById(R.id.playerLast9Scores6);
        TextView playerLast9Scores7 = findViewById(R.id.playerLast9Scores7);
        TextView playerLast9Scores8 = findViewById(R.id.playerLast9Scores8);
        TextView playerLast9Scores9 = findViewById(R.id.playerLast9Scores9);


        for (int i = 1; i < playerLast9ScoresStr.length; i++) {
            playerLast9ScoresStr[i] = playerLast9ScoresStr[i - 1];
        }
        // updates the previous score of the player, which will be placed in playerLast9Scored1 and is first in player's history.
        playerLast9ScoresStr[0] = previousCurrentScore;


        playerCurrentScore.setText(currentScore);
        playerLast9Scores9.setText(playerLast9ScoresStr[8]);
        playerLast9Scores8.setText(playerLast9ScoresStr[7]);
        playerLast9Scores7.setText(playerLast9ScoresStr[6]);
        playerLast9Scores6.setText(playerLast9ScoresStr[5]);
        playerLast9Scores5.setText(playerLast9ScoresStr[4]);
        playerLast9Scores4.setText(playerLast9ScoresStr[3]);
        playerLast9Scores3.setText(playerLast9ScoresStr[2]);
        playerLast9Scores2.setText(playerLast9ScoresStr[1]);
        playerLast9Scores1.setText(playerLast9ScoresStr[0]);
    }
}
