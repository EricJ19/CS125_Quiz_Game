package com.example.cs125finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_history);
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
        TextView playerLastScore = findViewById(R.id.playerLastScore);

        playerCurrentScore.setText(currentScore);
        playerLastScore.setText(previousCurrentScore);
    }
}
