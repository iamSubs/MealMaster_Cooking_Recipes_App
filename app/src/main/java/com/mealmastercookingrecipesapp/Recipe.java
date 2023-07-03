package com.mealmastercookingrecipesapp;

import java.io.Serializable;

public class Recipe {

    String id;
    String title;
    String imageUrl;
    String instruction;
    int readyInMinutes;

    public Recipe(String id, String title, String imageUrl, String instruction, int readyInMinutes) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.instruction = instruction;
        this.readyInMinutes = readyInMinutes;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getInstruction() {
        return instruction;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }
}
