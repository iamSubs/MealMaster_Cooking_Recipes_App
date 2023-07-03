package com.mealmastercookingrecipesapp.Model;

import com.mealmastercookingrecipesapp.Model.Recipe;

public interface RecipeCallback {
    void onSuccess(Recipe recipe);
    void onError(Exception error);
}
