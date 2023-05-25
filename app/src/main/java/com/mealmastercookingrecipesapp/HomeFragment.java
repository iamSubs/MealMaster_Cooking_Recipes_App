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

    public void addImageToFragment(String title, String imageUrl, String id, View view) {
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

        // Füge die ID als Tag zum ImageView hinzu
        imageView.setTag(id);

        // Füge den OnClickListener zum ImageView hinzu
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Extrahiere die ID aus dem ImageView-Tag
                String clickedId = (String) v.getTag();
                // Zeige die ID an (hier wird einfach eine Toast-Nachricht angezeigt)
                Toast.makeText(getContext(), "Clicked ID: " + clickedId, Toast.LENGTH_SHORT).show();
            }
        });

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
        for (int i = 0; i < 10; i++){
            apiHandler.getRecipeByName("", new RecipeCallback() {
                @Override
                public void onSuccess(Recipe recipe) {
                    addImageToFragment(recipe.getTitle(), recipe.getImageUrl(), recipe.getId(), view);
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