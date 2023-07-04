package com.mealmastercookingrecipesapp.View;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.mealmastercookingrecipesapp.Controller.ApiHandler;
import com.mealmastercookingrecipesapp.Controller.FragmentManager;
import com.mealmastercookingrecipesapp.Model.Recipe;
import com.mealmastercookingrecipesapp.Model.RecipeCallbackArray;
import com.mealmastercookingrecipesapp.R;

public class NewFragment extends Fragment {

    ApiHandler apiHandler;
    private LinearLayout fragmentLayout;

    private String search;

    public NewFragment (ApiHandler apiHandler, String search) {
        this.apiHandler = apiHandler;
        this.search = search;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Aufblasen des Layouts für das Fragment
        View rootView = inflater.inflate(R.layout.new_fragment, container, false);
        fragmentLayout = rootView.findViewById(R.id.fragment_layout1);

        apiHandler.complexSearch(new RecipeCallbackArray() {
            @Override
            public void onSuccess(Recipe[] recipes) {
                // Hier kannst du das Array von Rezepten verwenden
                for (Recipe recipe : recipes) {
                    FragmentManager.addImageToFragment(recipe.getTitle(), recipe.getImageUrl(), recipe.getId(), rootView, R.drawable.baseline_favorite_24 , getContext(), getResources(), "newFragment");
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
