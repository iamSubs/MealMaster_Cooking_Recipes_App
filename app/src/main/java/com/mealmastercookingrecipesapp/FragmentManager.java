package com.mealmastercookingrecipesapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class FragmentManager {
    public static void addImageToFragment(String title, String imageUrl, String id, View view, Context context, Resources resources, boolean isSearchFragment) {
        // Erstelle einen TextView für den Titel des Bildes
        TextView textView = new TextView(context);
        textView.setText(title);
        textView.setTypeface(null, Typeface.BOLD); // Fett anzeigen
        textView.setTextSize(16); // Textgröße auf 12 setzen
        textView.setTextColor(resources.getColor(R.color.black));
        // Erstelle ein neues ImageView-Element
        ImageView imageView = new ImageView(context);
        // Definiere die gewünschte Zielgröße für das Bild
        int targetWidth = resources.getDisplayMetrics().widthPixels; // Breite des Bildschirms
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
        int margin = resources.getDimensionPixelSize(R.dimen.image_margin);
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
                Toast.makeText(context, "Clicked ID: " + clickedId, Toast.LENGTH_SHORT).show();
            }
        });

        // Erstelle ein LinearLayout, um das ImageView und den TextView zu gruppieren
        LinearLayout linearLayout = new LinearLayout(context);
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
        if (isSearchFragment){
            LinearLayout fragmentLayout = view.findViewById(R.id.searchFragmentLinearLayoutRecipes);
            fragmentLayout.addView(linearLayout);
        } else {
            LinearLayout fragmentLayout = view.findViewById(R.id.fragment_layout);
            fragmentLayout.addView(linearLayout);
        }


    }
}
