package com.mealmastercookingrecipesapp.Controller;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.mealmastercookingrecipesapp.Model.Recipe;
import com.mealmastercookingrecipesapp.Model.RecipeCallback;
import com.mealmastercookingrecipesapp.R;
import com.mealmastercookingrecipesapp.View.RecipeFragment;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;
// View view, Context context, Resources resources, boolean isSearchFragment)
public class FragmentManager {
    public static void addImageToFragment(String title, String imageUrl, String id, View view, int iconId, Context context, Resources resources, String isSearchFragment) {

public class FragmentController {

    public FragmentManager fragmentManager;
    public ApiHandler apiHandler;
    public FragmentController(FragmentManager fragmentManager, ApiHandler apiHandler) {
        this.fragmentManager = fragmentManager;
        this.apiHandler = apiHandler;
    }

    public void openRecipeFragment(Recipe recipe) {
        RecipeFragment recipeFragment = RecipeFragment.newInstance(recipe.getTitle(), recipe.getImageUrl(), recipe.getId(), recipe.getSummary(), recipe.getServings(), recipe.getReadyInMinutes());

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, recipeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void addImageToFragment(String title, String imageUrl, String id, View view, int iconId, Context context, Resources resources, String whichFragment) {
        // Erstelle einen TextView für den Titel des Bildes
        FavoriteManager favoriteManager = new FavoriteManager(context);
        TextView textView = new TextView(context);
        textView.setText(title);
        textView.setTypeface(null, Typeface.BOLD); // Fett anzeigen
        textView.setTextSize(16); // Textgröße auf 12 setzen
        textView.setTextColor(resources.getColor(R.color.black));
        // Erstelle ein neues ImageView-Element
        ImageView imageView = new ImageView(context);
        //Erstelle final copy of ImageView
        final ImageView finalImageView = imageView;
        // Definiere die gewünschte Zielgröße für das Bild
        int targetWidth = resources.getDisplayMetrics().widthPixels; // Breite des Bildschirms
        int targetHeight = (int) (targetWidth * 2); // Verhältnis der Höhe zur Breite (hier: 0.75)
        // Lade das Bild von der URL und setze es in das ImageView mit Picasso
        Picasso.get()
                .load(imageUrl)
                .resize(targetWidth, targetHeight)
                .centerInside()
                .transform(new RoundedCornersTransformation(60, 0))
                .into(imageView);
        // Konfiguriere die Layout-Parameter für das ImageView im LinearLayout
        LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        int margin = resources.getDimensionPixelSize(R.dimen.image_margin);
        imageLayoutParams.setMargins(margin, margin/4, margin, margin/2);

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
                apiHandler.getRecipeById(id, new RecipeCallback() {
                    @Override
                    public void onSuccess(Recipe recipe) {
                        openRecipeFragment(recipe);
                    }

                    @Override
                    public void onError(Exception error) {
                        // Handle error
                    }
                });

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

        // Füge das Icon hinzu
        ImageView iconView = new ImageView(context);
        iconView.setImageResource(iconId);

        if (favoriteManager.isFavorite(id)) {
            iconView.setColorFilter(ContextCompat.getColor(context, R.color.pink), PorterDuff.Mode.SRC_IN);
        } else {
            iconView.setColorFilter(ContextCompat.getColor(context, R.color.grey), PorterDuff.Mode.SRC_IN);
        }

        //final boolean[] isFavorised = {false};
        iconView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String imageViewId = (String) finalImageView.getTag();

                if (favoriteManager.isFavorite(imageViewId)) {
                    iconView.setColorFilter(ContextCompat.getColor(context, R.color.grey), PorterDuff.Mode.SRC_IN);
                    //zur sicherheit check, ob schon de-favorisiert
                    //entferne von Arrayliste der favorite
                    favoriteManager.removeFromFavorites(imageViewId);
                    Toast.makeText(context, "Removed ID: " + imageViewId, Toast.LENGTH_SHORT).show();

                } else {
                    iconView.setColorFilter(ContextCompat.getColor(context, R.color.pink), PorterDuff.Mode.SRC_IN);
                    //zur sicherheit check, ob schon favorisiert
                    //füge zur ArrayListe der favoriten hinzu
                    favoriteManager.addToFavorites(imageViewId);
                    Toast.makeText(context, "Added ID: " + imageViewId, Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Um die größe des Icons einzustellen
        int iconSize = resources.getDimensionPixelSize(R.dimen.icon_size);

        // Konfiguriere die Layout-Parameter für den IconView
        FrameLayout.LayoutParams iconLayoutParams = new FrameLayout.LayoutParams(
                iconSize,
                iconSize
        );

        //Positioniere den Icon oben rechts von ImageView
        iconLayoutParams.gravity = Gravity.TOP | Gravity.END;

        //Fügt ein Margin für das IconView hinzu
        int iconMargin = resources.getDimensionPixelSize(R.dimen.icon_margin);
        int iconMarginRight = resources.getDimensionPixelSize(R.dimen.icon_margin_right);
        iconLayoutParams.setMargins(0, iconMargin, iconMarginRight, 0);

        iconView.setLayoutParams(iconLayoutParams);

        FrameLayout frameLayout = new FrameLayout(context);

        //Füge das Imageview und IconView zum FrameLayout hinzu um sie übereinander zu rendern
        frameLayout.addView(imageView, imageLayoutParams);
        frameLayout.addView(iconView, iconLayoutParams);

        // Füge den TextView und das FrameLayout zum LinearLayout hinzu
        linearLayout.addView(textView);
        linearLayout.addView(frameLayout);

        if (whichFragment == "fav"){
            LinearLayout fragmentLayout = view.findViewById(R.id.fragment_layout_fav);
            fragmentLayout.addView(linearLayout);
        } else if (whichFragment == "home") {
            LinearLayout fragmentLayout = view.findViewById(R.id.fragment_layout_home);
            fragmentLayout.addView(linearLayout);
        } else {
            LinearLayout fragmentLayout = view.findViewById(R.id.fragment_layout_home);
            fragmentLayout.addView(linearLayout);
        }


    }
}
