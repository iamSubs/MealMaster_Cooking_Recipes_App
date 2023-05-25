package com.mealmastercookingrecipesapp;

public interface RecipeCallback {
    void onSuccess(Recipe recipe);
    void onError(Exception error);
}
