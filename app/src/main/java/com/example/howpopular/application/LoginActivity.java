package com.example.howpopular.application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.howpopular.R;

import io.github.muddz.styleabletoast.StyleableToast;

public class LoginActivity extends AppCompatActivity {
    private Button bLogin;
    private EditText etUsername;
    private String userName;
    private static final String USER_INDEX = "LOGIN_USERNAME";

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
            userName = etUsername.getText().toString();
            // Check if name is valid
            if (!userName.equals("")) {
                gotoMainMenu();
            }
            else {
                StyleableToast.makeText(LoginActivity.this, getString(R.string.wrongLogin), Toast.LENGTH_SHORT, R.style.wrongToastStyle).show();
            }
        });
    }

    // Change activity to MainActivity
    private void gotoMainMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(USER_INDEX, userName);  // Send data (User) to other activity.
        setResult(RESULT_OK, intent);
        startActivityForResult(intent, 1);
    }

}
