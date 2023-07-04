package com.mealmastercookingrecipesapp.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.mealmastercookingrecipesapp.Controller.ApiHandler;
import com.mealmastercookingrecipesapp.Controller.FragmentController;
import com.mealmastercookingrecipesapp.Model.Recipe;
import com.mealmastercookingrecipesapp.Model.RecipeCallbackArray;
import com.mealmastercookingrecipesapp.R;

public class CategorieSerachFragment extends Fragment {

    ApiHandler apiHandler;
    private String search;
    FragmentController fragmentController;

    public CategorieSerachFragment(ApiHandler apiHandler, String search, FragmentController fragmentController) {
        this.apiHandler = apiHandler;
        this.search = search;
        this.fragmentController = fragmentController;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Aufblasen des Layouts für das Fragment
        View rootView = inflater.inflate(R.layout.fragment_categorieserach, container, false);

        apiHandler.complexSearch(new RecipeCallbackArray() {
            @Override
            public void onSuccess(Recipe[] recipes) {
                // Hier kannst du das Array von Rezepten verwenden
                for (Recipe recipe : recipes) {
                    fragmentController.addImageToFragment(recipe.getTitle(), recipe.getImageUrl(), recipe.getId(), rootView, R.drawable.baseline_favorite_24 , getContext(), getResources(), "newFragment");
                }
            }

            @Override
            public void onError(Exception e) {
                // Fehlerbehandlung
            }
        }, search);
        return rootView;
    }
}
