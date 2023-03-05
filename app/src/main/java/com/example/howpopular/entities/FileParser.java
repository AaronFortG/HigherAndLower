package com.example.howpopular.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileParser {

    private static final String QUIZZES_FILE_URL = "files/quizzes.txt";

    public static Quiz[] loadQuizes(AppCompatActivity activity) {
        ArrayList<Quiz> quizzes = new ArrayList<>();

        try {
            InputStream inputStream = activity.getAssets().open(QUIZZES_FILE_URL); // Replace "file.txt" with the name of your file
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] quizInfo = line.split(":");

                // Get name and the popularity of the quizz
                String quizName = quizInfo[0];
                String number = quizInfo[1].substring(1).replace(".", "");
                int quizPopularity = Integer.parseInt(number);    // Get the popularity without the space

                // Get an InputStream to the image file from the assets folder
                InputStream is = activity.getAssets().open("images/" + quizName + ".jpg");

                // Read the image using ImageIO.read()
                Bitmap image = BitmapFactory.decodeStream(is);

                quizzes.add(new Quiz(quizName, quizPopularity, image, quizInfo[1].substring(1)));
            }
            reader.close();
        } catch (IOException e) {
            // Handle the exception
        }
        return quizzes.toArray(new Quiz[0]);
    }
}
