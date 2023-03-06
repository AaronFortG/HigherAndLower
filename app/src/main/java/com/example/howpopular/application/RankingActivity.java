package com.example.howpopular.application;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.howpopular.R;
import com.example.howpopular.entities.User;

import java.util.ArrayList;

public class RankingActivity extends AppCompatActivity {
    private TextView[] tvRanking;
    private static final int MAX_RANKING = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        // UI Design
        setupConnections();
        loadRanking();
    }

    @SuppressLint("DiscouragedApi")
    private void setupConnections() {
        tvRanking = new TextView[MAX_RANKING];
        int[] tvRankingIds = new int[MAX_RANKING];

        for (int i = 0; i < MAX_RANKING; i++) {
            tvRankingIds[i] = getResources().getIdentifier("tvRanking" + (i + 1), "id", getPackageName());
            tvRanking[i] = findViewById(tvRankingIds[i]);
        }
    }

    @SuppressLint("SetTextI18n")
    private void loadRanking() {
        ArrayList<User> users = MainActivity.getUserDB();
        int i;

        // Sort users for their maximum score
        users.sort((user1, user2) -> user2.getMaxScore() - user1.getMaxScore());

        for (i = 0; i < users.size(); i++) {
            tvRanking[i].setText(users.get(i).getUsername() + " " + getString(R.string.rankingSeparator) + " " + users.get(i).getMaxScore() + " " + getString(R.string.rankingPoints));
        }

        // Fill ranking with empty values
        while (i < MAX_RANKING) {
            tvRanking[i].setText(getString(R.string.rankingEmpty));
            i++;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RankingActivity.this, MainActivity.class);
        intent.putExtra("LOGIN_USERNAME", MainActivity.currentUser.getUsername());
        startActivity(intent);
        super.onBackPressed();
    }
}
