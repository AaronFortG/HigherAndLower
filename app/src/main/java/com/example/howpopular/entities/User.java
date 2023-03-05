package com.example.howpopular.entities;

public class User {
    private final String username;
    private int maxScore;

    public User(String username) {
        this.username = username;
        maxScore = 0;
    }
}
