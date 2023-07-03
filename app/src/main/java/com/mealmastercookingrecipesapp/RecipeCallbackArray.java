package com.mealmastercookingrecipesapp;

public interface RecipeCallbackArray {
    void onSuccess(Recipe[] recipes); // Array von Rezepten
    void onError(Exception error);
}
