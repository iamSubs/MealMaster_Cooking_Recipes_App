package com.mealmastercookingrecipesapp;

import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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

    FavoriteManager favoriteManager;

    TextView homeTextView;
    ProgressBar spinner;
    String textHome;

    ImageView imageView3;
    public HomeFragment(ApiHandler apiHandler) {
        this.apiHandler = apiHandler;
    }

    public void addImageToFragment(String title, String imageUrl, String id, View view, int iconId) {
        // Erstelle einen TextView für den Titel des Bildes
        TextView textView = new TextView(getContext());
        textView.setText(title);
        textView.setTypeface(null, Typeface.BOLD); // Fett anzeigen
        textView.setTextSize(16); // Textgröße auf 12 setzen
        textView.setTextColor(getResources().getColor(R.color.black));
        // Erstelle ein neues ImageView-Element
        ImageView imageView = new ImageView(getContext());
        //Erstelle final copy of ImageView
        final ImageView finalImageView = imageView;
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

        // Füge das Icon hinzu
        ImageView iconView = new ImageView(getContext());
        iconView.setImageResource(iconId);

        if (favoriteManager.isFavorite(id)) {
            iconView.setColorFilter(ContextCompat.getColor(getContext(), R.color.pink), PorterDuff.Mode.SRC_IN);
        } else {
            iconView.setColorFilter(ContextCompat.getColor(getContext(), R.color.grey), PorterDuff.Mode.SRC_IN);
        }

        //final boolean[] isFavorised = {false};
        iconView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String imageViewId = (String) finalImageView.getTag();

                if (favoriteManager.isFavorite(imageViewId)) {
                    iconView.setColorFilter(ContextCompat.getColor(getContext(), R.color.grey), PorterDuff.Mode.SRC_IN);
                    //zur sicherheit check, ob schon de-favorisiert
                        //entferne von Arrayliste der favorite
                    favoriteManager.removeFromFavorites(imageViewId);
                    Toast.makeText(getContext(), "Removed ID: " + imageViewId, Toast.LENGTH_SHORT).show();

                } else {
                    iconView.setColorFilter(ContextCompat.getColor(getContext(), R.color.pink), PorterDuff.Mode.SRC_IN);
                    //zur sicherheit check, ob schon favorisiert
                        //füge zur ArrayListe der favoriten hinzu
                    favoriteManager.addToFavorites(imageViewId);
                    Toast.makeText(getContext(), "Added ID: " + imageViewId, Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Um die größe des Icons einzustellen
        int iconSize = getResources().getDimensionPixelSize(R.dimen.icon_size);

        // Konfiguriere die Layout-Parameter für den IconView
        FrameLayout.LayoutParams iconLayoutParams = new FrameLayout.LayoutParams(
                iconSize,
                iconSize
        );

        //Positioniere den Icon oben rechts von ImageView
        iconLayoutParams.gravity = Gravity.TOP | Gravity.END;

        //Fügt ein Margin für das IconView hinzu
        int iconMargin = getResources().getDimensionPixelSize(R.dimen.icon_margin);
        int iconMarginRight = getResources().getDimensionPixelSize(R.dimen.icon_margin_right);
        iconLayoutParams.setMargins(0, iconMargin, iconMarginRight, 0);

        iconView.setLayoutParams(iconLayoutParams);

        FrameLayout frameLayout = new FrameLayout(getContext());

        //Füge das Imageview und IconView zum FrameLayout hinzu um sie übereinander zu rendern
        frameLayout.addView(imageView, imageLayoutParams);
        frameLayout.addView(iconView, iconLayoutParams);

        // Füge den TextView und das FrameLayout zum LinearLayout hinzu
        linearLayout.addView(textView);
        linearLayout.addView(frameLayout);

        // Füge das LinearLayout mit dem Bild und Titel zum Layout des Fragments hinzu
        LinearLayout fragmentLayout = view.findViewById(R.id.fragment_layout);
        fragmentLayout.addView(linearLayout);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        favoriteManager = new FavoriteManager(getContext());
        String imageUrl = "https://spoonacular.com/recipeImages/715497-312x231.jpg";
        for (int i = 0; i < 10; i++){
            apiHandler.getRecipeByName("", new RecipeCallback() {
                @Override
                public void onSuccess(Recipe recipe) {
                    addImageToFragment(recipe.getTitle(), recipe.getImageUrl(), recipe.getId(), view, R.drawable.baseline_favorite_24);
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