package com.mealmastercookingrecipesapp.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mealmastercookingrecipesapp.Controller.ApiHandler;
import com.mealmastercookingrecipesapp.Controller.FavoriteManager;
import com.mealmastercookingrecipesapp.Controller.FragmentController;
import com.mealmastercookingrecipesapp.R;
import com.mealmastercookingrecipesapp.Model.Recipe;
import com.mealmastercookingrecipesapp.Model.RecipeCallback;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {
    ApiHandler apiHandler;
    FavoriteManager favoriteManager;
    FragmentController fragmentController;

    public FavoriteFragment(ApiHandler apiHandler, FragmentManager fragmentManager) {
        this.apiHandler = apiHandler;
        fragmentController = new FragmentController(fragmentManager, apiHandler);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        favoriteManager = new FavoriteManager(getContext());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


        LinearLayout container = (LinearLayout) getView().findViewById(R.id.fragment_layout_fav);
        if (container != null) {
            container.removeAllViews();
        } else {
            return;
        }

        // Get the updated favorites list
        ArrayList<String> favoritesList = favoriteManager.getFavorites();

        // Add each favorite to the Fragment
        for (String id : favoritesList) {
            apiHandler.getRecipeById(id, new RecipeCallback() {
                @Override
                public void onSuccess(Recipe recipe) {
                    fragmentController.addImageToFragment(recipe.getTitle(), recipe.getImageUrl(), recipe.getId(), getView(), R.drawable.baseline_favorite_24, getContext(), getResources(), "fav");
                }

                @Override
                public void onError(Exception error) {
                    // Handle error
                }
            });
        }
    }




}