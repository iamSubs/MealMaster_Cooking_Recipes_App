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

    public void addImageToFragment(String title, String imageUrl, View view) {
        // Erstelle einen TextView für den Titel des Bildes
        TextView textView = new TextView(getContext());
        textView.setText(title);
        textView.setTypeface(null, Typeface.BOLD); // Fett anzeigen
        textView.setTextSize(16); // Textgröße auf 12 setzen
        textView.setTextColor(getResources().getColor(R.color.black));
        // Erstelle ein neues ImageView-Element
        ImageView imageView = new ImageView(getContext());
        // Definiere die gewünschte Zielgröße für das Bild
        int targetWidth = getResources().getDisplayMetrics().widthPixels; // Breite des Bildschirms
        int targetHeight = (int) (targetWidth * 2); // Verhältnis der Höhe zur Breite (hier: 0.75)
        // Lade das Bild von der URL und setze es in das ImageView mit Picasso
        Picasso.get()
                .load(imageUrl)
                .resize(targetWidth, targetHeight)
                .centerInside()
                .transform(new RoundedCornersTransformation(120, 0))
                .into(imageView);
        // Konfiguriere die Layout-Parameter für das ImageView im LinearLayout
        LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        int margin = getResources().getDimensionPixelSize(R.dimen.image_margin);
        imageLayoutParams.setMargins(margin, margin/4, margin, margin/4);
        // Setze die Skalierungsart des Bildes auf "fitXY", um es an die ImageView-Größe anzupassen
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        // Erstelle ein LinearLayout, um das ImageView und den TextView zu gruppieren
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        // Konfiguriere die Layout-Parameter für den TextView
        LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        textLayoutParams.setMargins(margin,0,0,0);
        textView.setLayoutParams(textLayoutParams);
        // Füge das ImageView und den TextView zum LinearLayout hinzu
        linearLayout.addView(textView);
        linearLayout.addView(imageView, imageLayoutParams);
        // Füge das LinearLayout mit dem Bild und Titel zum Layout des Fragments hinzu
        LinearLayout fragmentLayout = view.findViewById(R.id.fragment_layout);
        fragmentLayout.addView(linearLayout);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        String imageUrl = "https://spoonacular.com/recipeImages/715497-312x231.jpg";
        String url = apiHandler.getRecipeByName("");
        String url1 = apiHandler.getRecipeByName("");
        String url2 = apiHandler.getRecipeByName("");
        addImageToFragment("Chicken", url, view);
        addImageToFragment("Chicken", imageUrl, view);


        Handler handler = new Handler();
        final boolean[] isConditionMet = {false};
        Runnable checkRunnable = new Runnable() {
            @Override
            public void run() {
                if (!isConditionMet[0]) {
                    // Die Bedingung ist erfüllt und wird nur einmal ausgeführt
                    String url = apiHandler.getRecipeByName("");
                    addImageToFragment("Chicken", url, view);
                    addImageToFragment("Chicken", imageUrl, view);
                    addImageToFragment("Chicken", url1, view);
                    addImageToFragment("Meat", url2, view);
                    isConditionMet[0] = true;
                }
                if (!isConditionMet[0]) {
                    handler.postDelayed(this, 5000);
                }
            }
        };
        handler.post(checkRunnable);
        return view;
    }
}