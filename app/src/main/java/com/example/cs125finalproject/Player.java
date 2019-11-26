package com.example.cs125finalproject;

public class Player {
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
}
