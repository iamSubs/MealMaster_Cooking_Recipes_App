package com.mealmastercookingrecipesapp;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class HomeFragment extends Fragment {

    ApiHandler apiHandler;
    MainActivity mainActivity;

    TextView homeTextView;
    ProgressBar spinner;
    String textHome;

    ImageView imageView3;
    public HomeFragment(ApiHandler apiHandler) {
        this.apiHandler = apiHandler;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        for (int i = 0; i < 10; i++){
            apiHandler.getRandomRecipe( new RecipeCallback() {
                @Override
                public void onSuccess(Recipe recipe) {
                    FragmentManager.addImageToFragment(recipe.getTitle(), recipe.getImageUrl(), recipe.getId(), view, getContext(), getResources(), false);
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