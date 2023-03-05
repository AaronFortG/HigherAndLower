package com.example.howpopular.entities;

public class User {
    private final String username;
    private int maxScore;

    public User(String username) {
        this.username = username;
        maxScore = 0;
    }

    public String getUsername() {
        return username;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public boolean newScore(int newScore) {
        if (newScore > maxScore) {
            maxScore = newScore;
            return true;
        }

        return false;
    }
}
