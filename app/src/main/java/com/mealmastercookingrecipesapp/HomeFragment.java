package com.mealmastercookingrecipesapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    public void addImageToFragment(String title, String imageUrl) {
        // Erstelle ein neues ImageView-Element
        ImageView imageView = new ImageView(getContext());

        // Lade das Bild von der URL und setze es in das ImageView mit Picasso
        Picasso.get().load(imageUrl).into(imageView);

        // Erstelle einen TextView für den Titel des Bildes
        TextView textView = new TextView(getContext());
        textView.setText(title);

        // Erstelle ein LinearLayout, um das ImageView und den TextView zu gruppieren
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // Füge das ImageView und den TextView zum LinearLayout hinzu
        linearLayout.addView(imageView);
        linearLayout.addView(textView);

        // Füge das LinearLayout mit dem Bild und Titel zum Layout des Fragments hinzu
        LinearLayout fragmentLayout = getView().findViewById(R.id.fragment_layout);
        fragmentLayout.addView(linearLayout);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        String imageUrl = "https://spoonacular.com/recipeImages/715497-312x231.jpg";
        // Erstelle einen Handler, um die Prüfung zu wiederholen
        Handler handler = new Handler();

// Definiere eine Variable, um den Zustand zu verfolgen
        final boolean[] isConditionMet = {false};

// Definiere ein Runnable, das die Überprüfung durchführt
        Runnable checkRunnable = new Runnable() {
            @Override
            public void run() {
                if (!isConditionMet[0]) {
                    // Die Bedingung ist erfüllt und wird nur einmal ausgeführt
                    addImageToFragment("Chicken", "https://spoonacular.com/recipeImages/715497-312x231.jpg");
                    addImageToFragment("Chicken", "https://spoonacular.com/recipeImages/715497-312x231.jpg");
                    addImageToFragment("Chicken", "https://spoonacular.com/recipeImages/715497-312x231.jpg");
                    isConditionMet[0] = true;
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