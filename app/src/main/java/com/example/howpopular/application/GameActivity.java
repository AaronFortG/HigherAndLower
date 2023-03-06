package com.example.howpopular.application;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.howpopular.R;
import com.example.howpopular.entities.Answer;
import com.example.howpopular.entities.FileParser;
import com.example.howpopular.entities.Quiz;

import java.util.Arrays;
import java.util.Collections;

public class GameActivity extends AppCompatActivity {
    private Quiz currentQuiz;
    private Quiz previousQuiz;
    private Quiz[] quizzes;
    private int numQuestion;
    private boolean gameFinished;
    private boolean firstBackPressed;
    public static final String WINNER_INDEX = "PLAYER_WINNER";
    public static final String SCORE_INDEX = "USER_SCORE";
    private Button bHigher;
    private Button bLower;

    private TextView tvPreviousQuizName;
    private TextView tvPreviousQuizPopularity;
    private TextView tvCorrectAnswers;
    private ImageView ivPreviousQuizImage;

    private TextView tvCurrentQuizName;
    private TextView tvCurrentQuizPopularity;
    private ImageView ivCurrentQuizImage;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Save questions and randomize them.
        quizzes = FileParser.loadQuizes(this);
        Collections.shuffle(Arrays.asList(quizzes));
        numQuestion = 1;
        gameFinished = false;
        firstBackPressed = true;

        previousQuiz = quizzes[0];
        currentQuiz = quizzes[numQuestion];

        // UI Design
        setupConnections();
        setupListeners();
        updateDesignView();
    }

    @Override
    public void onBackPressed() {
        if (firstBackPressed) {
            // Warn user
        }
        else {
            super.onBackPressed();
        }
    }

    private void setupConnections() {
        bHigher = findViewById(R.id.bHigher);
        bLower = findViewById(R.id.bLower);

        tvPreviousQuizName = findViewById(R.id.tvPreviousQuizName);
        tvPreviousQuizPopularity = findViewById(R.id.tvPreviousQuizPopularity);
        tvCorrectAnswers = findViewById(R.id.tvCorrectAnswers);
        ivPreviousQuizImage = findViewById(R.id.ivPreviousQuizImage);

        tvCurrentQuizName = findViewById(R.id.tvCurrentQuizName);
        tvCurrentQuizPopularity = findViewById(R.id.tvCurrentQuizPopularity);
        ivCurrentQuizImage = findViewById(R.id.ivCurrentQuizImage);
    }

    private void setupListeners() {
        bHigher.setOnClickListener(view -> checkAnswer(Answer.HIGHER));
        bLower.setOnClickListener(view -> checkAnswer(Answer.LOWER));
    }

    private void checkAnswer(Answer playerAnswer) {
        boolean isHigher = currentQuiz.getPopularity() > previousQuiz.getPopularity();

        switch (playerAnswer) {
            case HIGHER:
                if (isHigher) {
                    nextQuiz();
                }
                else {
                    gameOver(false);
                }
                break;

            case LOWER:
                if (isHigher) {
                    gameOver(false);
                }
                else {
                    nextQuiz();
                }
                break;
        }
    }

    private void gameOver(boolean winner) {
        gameFinished = true;

        // Update user score (in case it's higher).
        MainActivity.currentUser.newScore(numQuestion - 1);

        // Change intent to GAME OVER class (send a boolean if player won or not).
        Intent intent;
        intent = new Intent(this, GameOverActivity.class);
        intent.putExtra(WINNER_INDEX, winner);  // Send data (boolean) to other activity.
        intent.putExtra(SCORE_INDEX, numQuestion - 1);
        startActivity(intent);
        finish();   // Avoid user uses the back button.
    }

    private void nextQuiz() {
        previousQuiz = currentQuiz; // Swap quizzes
        numQuestion++;

        // Check if there are any quizzes left.
        if (numQuestion < quizzes.length) {
            currentQuiz = quizzes[numQuestion];

            updateDesignView();
        }
        else {
            // Game finished
            gameOver(true); // This is TRUE because next question is only generated if player answered correctly previously.
        }
    }

    @SuppressLint("SetTextI18n")
    private void updateDesignView() {
        tvPreviousQuizName.setText(previousQuiz.getName());
        tvPreviousQuizPopularity.setText(previousQuiz.getValue() + " " + getString(R.string.views));
        tvCorrectAnswers.setText((numQuestion - 1) + " " + getString(R.string.correctAnswers));
        ivPreviousQuizImage.setImageBitmap(previousQuiz.getImage());

        tvCurrentQuizName.setText(currentQuiz.getName());
        tvCurrentQuizPopularity.setText(getString(R.string.moreViews) + " " + previousQuiz.getName() + "?");
        ivCurrentQuizImage.setImageBitmap(currentQuiz.getImage());
    }
}
