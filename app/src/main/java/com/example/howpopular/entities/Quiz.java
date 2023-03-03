package com.example.howpopular.entities;

import android.graphics.Bitmap;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Quiz {
    private final String name;
    private final int popularity;
    private final String shownValue;    // Used to show the number as "12.345" with thousands
    private final Bitmap image;

    public Quiz(String name, int popularity, Bitmap image, String shownValue) {
        this.name = name;
        this.popularity = popularity;
        this.image = image;
        this.shownValue = shownValue;
    }

    public String getName() {
        return name;
    }

    public int getPopularity() {
        return popularity;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getValue() {
        return shownValue;
    }
}
