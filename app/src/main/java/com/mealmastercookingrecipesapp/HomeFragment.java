package com.mealmastercookingrecipesapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

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
        spinner = view.findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);
        homeTextView = view.findViewById(R.id.homeTextView);
        textHome = apiHandler.getRecipeByName("results");
        imageView3 = view.findViewById(R.id.imageView3);
        String imageUrl = "https://spoonacular.com/recipeImages/715497-312x231.jpg";
        // Erstelle einen Handler, um die Prüfung zu wiederholen
        Handler handler = new Handler();

// Definiere eine Variable, um den Zustand zu verfolgen
        final boolean[] isConditionMet = {false};

// Definiere ein Runnable, das die Überprüfung durchführt
        Runnable checkRunnable = new Runnable() {
            @Override
            public void run() {
                String result = apiHandler.getRecipeByName("results");
                if (apiHandler.getRecipeByName("results") != null && !isConditionMet[0]) {
                    // Die Bedingung ist erfüllt und wird nur einmal ausgeführt
                    isConditionMet[0] = true;
                    spinner.setVisibility(View.GONE);
                    homeTextView.setText(result);
                    Picasso.get().load(imageUrl).into(imageView3);
                }

                // Prüfe weiterhin, bis die Bedingung erfüllt ist
                if (!isConditionMet[0]) {
                    // Wenn der Wert leer ist, führe die Überprüfung erneut nach einer Verzögerung aus
                    handler.postDelayed(this, 5000); // Hier kannst du die Verzögerungszeit in Millisekunden anpassen
                }
            }
        };

        // Starte die Überprüfung
        handler.post(checkRunnable);
        return view;
    }
}