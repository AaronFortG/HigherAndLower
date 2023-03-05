package com.example.howpopular.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.howpopular.entities.Quiz;
import com.example.howpopular.R;
import com.example.howpopular.entities.FileParser;

public class MainActivity extends AppCompatActivity {

    private boolean loggedIn;   // Check if user is logged to show the username or the text field.
    private Button bStartGame;
    private Button bRanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI Design
        setupConnections();
        setupListeners();
    }

    private void setupConnections() {
        // Initialize buttons
        bStartGame = findViewById(R.id.bStartGame);
        bRanking = findViewById(R.id.bRanking);
    }

    private void setupListeners() {
        // Check when buttons are clicked
        bStartGame.setOnClickListener(view -> startGame());
        bRanking.setOnClickListener(view -> gotoRanking());
    }

    // Change activity to GameActivity
    private void startGame() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    // Change activity to RankingActivity
    private void gotoRanking() {
        Intent intent = new Intent(this, RankingActivity.class);
        startActivity(intent);
    }
}