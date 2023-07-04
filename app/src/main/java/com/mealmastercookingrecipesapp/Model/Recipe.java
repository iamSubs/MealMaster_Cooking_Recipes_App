package com.mealmastercookingrecipesapp.Model;

public class Recipe {

    String id;
    String title;
    String imageUrl;
    String instruction;
    int readyInMinutes;

    int servings;
    String summary;
    boolean vegetarian;
    boolean vegan;
    boolean glutenFree;


    public Recipe(String id, String title, String imageUrl, String instruction, int readyInMinutes, int servings, String summary, boolean vegetarian, boolean vegan, boolean glutenFree) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.instruction = instruction;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.summary = summary;
        this.vegetarian = vegetarian;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
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

    public String getSummary(){
        return summary;
    }

    public int getServings(){
        return servings;
    }

}
