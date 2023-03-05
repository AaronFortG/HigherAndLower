package com.example.howpopular.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.howpopular.R;
import com.example.howpopular.entities.User;

import io.github.muddz.styleabletoast.StyleableToast;

public class LoginActivity extends AppCompatActivity {

    private User loginUser;
    private Button bLogin;
    private EditText etUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // UI Design
        setupConnections();
        setupListeners();
    }

    private void setupConnections() {
        bLogin = findViewById(R.id.bLogin);
        etUsername = findViewById(R.id.etLogin);
    }

    private void setupListeners() {
        bLogin.setOnClickListener(v -> {
            // Check if name is valid
            if (!etUsername.getText().toString().equals("")) {
                LoginActivity.this.gotoMainMenu();
            }
            else {
                StyleableToast.makeText(LoginActivity.this, getString(R.string.wrongLogin), Toast.LENGTH_SHORT, R.style.wrongToastStyle).show();
            }
        });
    }

    // Change activity to RankingActivity
    private void gotoMainMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
