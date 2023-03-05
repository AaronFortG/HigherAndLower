package com.example.howpopular.application;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.howpopular.R;

public class GameOverActivity extends AppCompatActivity {
    private TextView tvWinLoseText;
    private TextView tvGameScore;
    private Button bRanking;
    private Button bMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        setupConnections();
        setupListeners();
        setupView(getIntent());
    }

    private void setupConnections() {
        tvWinLoseText = findViewById(R.id.tvWinLoseText);
        tvGameScore = findViewById(R.id.tvGameScore);
        bRanking = findViewById(R.id.bRanking);
        bMainMenu = findViewById(R.id.bMainMenu);
    }

    private void setupListeners() {
        bRanking.setOnClickListener(v -> gotoRanking());
        bMainMenu.setOnClickListener(v -> gotoMainMenu());
    }

    // Change activity to Ranking
    private void gotoRanking() {
        Intent intent = new Intent(this, RankingActivity.class);
        setResult(RESULT_OK, intent);
        startActivityForResult(intent, 1);
    }

    // Change activity to MainActivity
    private void gotoMainMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        setResult(RESULT_OK, intent);
        startActivityForResult(intent, 1);
    }

    @SuppressLint("SetTextI18n")
    private void setupView(Intent intent) {
        // Get the data from the intent that started this activity
        boolean winner = intent.getBooleanExtra(GameActivity.WINNER_INDEX, false);
        int score = intent.getIntExtra(GameActivity.SCORE_INDEX, 0);

        if (winner) {
            tvWinLoseText.setText(getString(R.string.quizWinner));
        }
        else {
            tvWinLoseText.setText(getString(R.string.quizLoser));
        }

        tvGameScore.setText(getString(R.string.score) + " " + score + " " + getString(R.string.score2));
    }
}
