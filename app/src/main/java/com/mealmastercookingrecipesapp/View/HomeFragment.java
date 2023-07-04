package com.mealmastercookingrecipesapp.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mealmastercookingrecipesapp.Controller.ApiHandler;
import com.mealmastercookingrecipesapp.Controller.FavoriteManager;
import com.mealmastercookingrecipesapp.Controller.FragmentController;
import com.mealmastercookingrecipesapp.MainActivity;
import com.mealmastercookingrecipesapp.R;
import com.mealmastercookingrecipesapp.Model.Recipe;
import com.mealmastercookingrecipesapp.Model.RecipeCallback;

public class HomeFragment extends Fragment {

    ApiHandler apiHandler;
    MainActivity mainActivity;

    FavoriteManager favoriteManager;

    TextView homeTextView;
    ProgressBar spinner;
    String textHome;
    FragmentController fragmentController;

    ImageView imageView3;
    public HomeFragment(ApiHandler apiHandler, FragmentManager fragmentManager) {
        this.apiHandler = apiHandler;
        fragmentController = new FragmentController(fragmentManager, apiHandler);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        favoriteManager = new FavoriteManager(getContext());
        String imageUrl = "https://spoonacular.com/recipeImages/715497-312x231.jpg";
        for (int i = 0; i < 10; i++){
            apiHandler.getRandomRecipe( new RecipeCallback() {
                @Override
                public void onSuccess(Recipe recipe) {
                    FragmentManager.addImageToFragment(recipe.getTitle(), recipe.getImageUrl(), recipe.getId(), view, R.drawable.baseline_favorite_24, getContext(), getResources(), "homeFragment");
                    fragmentController.addImageToFragment(recipe.getTitle(), recipe.getImageUrl(), recipe.getId(), view, R.drawable.baseline_favorite_24, getContext(), getResources(), "home");
                }
                @Override
                public void onError(Exception error) {
                    // Hier kannst du Fehlerbehandlung durchführen,
                    // falls ein Fehler bei der API-Anfrage auftritt.
                }
            });
        }
        return view;
    }
}