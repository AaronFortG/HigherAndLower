package com.example.howpopular.application;

import androidx.annotation.NonNull;
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
    private Button bLogout;
    private static ArrayList<User> userDB = new ArrayList<>();
    private static final String KEY_INDEX = "index";
    public static User currentUser;
    private static final int ID_LOGIN_ACTIVITY = 1;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable(KEY_INDEX, userDB);
    }

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI Design
        setupConnections();
        setupListeners();

        // Get the information when this activity is launched.
        String username = getIntent().getStringExtra("LOGIN_USERNAME");
        loginUser(username);
    }

    // Method triggered when activity is brought to front.
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);

        // Get the information when this activity is launched.
        String username = intent.getStringExtra("LOGIN_USERNAME");
        loginUser(username);
    }

    private void setupConnections() {
        // Initialize buttons
        bStartGame = findViewById(R.id.bStartGame);
        bRanking = findViewById(R.id.bRanking);
        bLogout = findViewById(R.id.bLogout);
    }

    private void setupListeners() {
        // Check when buttons are clicked
        bStartGame.setOnClickListener(view -> startGame());
        bRanking.setOnClickListener(view -> gotoRanking());
        bLogout.setOnClickListener(view -> gotoLogin());
    }

    private void gotoLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, ID_LOGIN_ACTIVITY);
    }

    private void loginUser(String username) {
        // Check if user already exists.
        for (User user : userDB) {
            if (user.sameUsername(username)) {
                currentUser = user;
                return;
            }
        }

        // If user does not exist, update current user and add it to list.
        if (currentUser == null || !currentUser.sameUsername(username)) {
            currentUser = new User(username);
            userDB.add(currentUser);
        }
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

    @Override
    public void onBackPressed() {
        //We don't go back
    }

}