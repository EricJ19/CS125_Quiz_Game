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
public class Player {
    /** the players name. */
    private String name;
    /** the amount of points the player got. */
    private int points;
    /** the chosen topic for this player. */
    private int topic;
    /** the constructor to create new player. */
    public Player(String setName, int setPoints, int setTopic) {
        name = setName;
        points = setPoints;
        topic = setTopic;
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
    /** return topic chosen. */
    public int getTopic() { return topic; }
}
