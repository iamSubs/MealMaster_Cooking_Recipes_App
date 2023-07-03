package com.mealmastercookingrecipesapp.Model;

import com.mealmastercookingrecipesapp.Model.Recipe;

public interface RecipeCallbackArray {
    void onSuccess(Recipe[] recipes); // Array von Rezepten
    void onError(Exception error);
}
