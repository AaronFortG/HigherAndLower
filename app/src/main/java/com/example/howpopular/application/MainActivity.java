package com.example.howpopular.application;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.howpopular.R;
import com.example.howpopular.entities.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private boolean loggedIn;   // Check if user is logged to show the username or the text field.
    private Button bStartGame;
    private Button bRanking;
    private static ArrayList<User> userDB;
    private User currentUser;
    private static final int ID_LOGIN_ACTIVITY = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Comprovar si s'han tornat les dades correctament.
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        // Gestionar la resposta de l'activitat del login.
        if (requestCode == ID_LOGIN_ACTIVITY) {
            if (data != null) {
                String username = data.getStringExtra("LOGIN_USERNAME");
                loginUser(username);
            }
        }
    }

    private void loginUser(String username) {
        // Check if user already exists.
        for (User user : userDB) {
            if (user.sameUsername(username)) {
                currentUser = user;
            }
        }

        // If user does not exist, add it to list.
        if (currentUser == null) {
            currentUser = new User(username);
            userDB.add(currentUser);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI Design
        setupConnections();
        setupListeners();

        String username = getIntent().getStringExtra("LOGIN_USERNAME");
        loginUser(username);
    }

    private void setupConnections() {
        // Initialize buttons
        bStartGame = findViewById(R.id.bStartGame);
        bRanking = findViewById(R.id.bRanking);
        userDB = new ArrayList<>();
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

    public static ArrayList<User> getUserDB() {
        return userDB;
    }
}