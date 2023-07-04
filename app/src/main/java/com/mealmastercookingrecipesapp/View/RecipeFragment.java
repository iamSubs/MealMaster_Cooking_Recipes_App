package com.mealmastercookingrecipesapp.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.Spanned;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.android.material.tabs.TabLayout;
import com.mealmastercookingrecipesapp.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeFragment extends Fragment {


    public RecipeFragment() {
        // Required empty public constructor
    }

    public static RecipeFragment newInstance(String title, String imageUrl, String id, String summary, int servings, int readyInMinutes, String[] ingredients , String instruction) {
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("imageUrl", imageUrl);
        args.putString("id", id);
        args.putString("summary", summary);
        args.putString("servings", Integer.toString(servings));
        args.putString("readyInMinutes", Integer.toString(readyInMinutes));
        args.putStringArray("ingredients", ingredients);
        args.putString("instruction", instruction);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);

        TextView titleTextView = view.findViewById(R.id.recipeTitleTextView);
        TextView summaryTextView = view.findViewById(R.id.summaryTextView);
        TextView servingsTextView = view.findViewById(R.id.servingsTextView);
        TextView ingredientsTextView = view.findViewById(R.id.ingredientsTextView);
        TextView readyInMinutesTextView = view.findViewById(R.id.readyInMinutesTextView);
        TextView instructionTextView = view.findViewById(R.id.instructionTextView);
        ImageView imageView = view.findViewById(R.id.imageViewTESTER);


        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        LinearLayout overviewLayout = view.findViewById(R.id.overviewLayout);
        LinearLayout descriptionLayout = view.findViewById(R.id.descriptionLayout);
        LinearLayout ingredientsLayout = view.findViewById(R.id.ingredientsLayout);
        LinearLayout instructionLayout = view.findViewById(R.id.instructionLayout);
        ingredientsLayout.setVisibility(View.GONE);
        instructionLayout.setVisibility(view.GONE);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String tabName = tab.getText().toString();
                if (tabName.equals("Ingredients")){
                    ingredientsLayout.setVisibility(View.VISIBLE);
                    descriptionLayout.setVisibility(View.GONE);
                    overviewLayout.setVisibility(View.GONE);
                    instructionLayout.setVisibility(view.GONE);
                } else if (tabName.equals("Overview")){
                    descriptionLayout.setVisibility(View.VISIBLE);
                    ingredientsLayout.setVisibility(View.GONE);
                    overviewLayout.setVisibility(View.VISIBLE);
                    instructionLayout.setVisibility(view.GONE);
                } else if (tabName.equals("Instruction")){
                    instructionLayout.setVisibility(view.VISIBLE);
                    descriptionLayout.setVisibility(View.GONE);
                    ingredientsLayout.setVisibility(View.GONE);
                    overviewLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Not needed for this example
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Not needed for this example
            }
        });

        Bundle args = getArguments();
        if (args != null) {
            String title = args.getString("title");
            String imageUrl = args.getString("imageUrl");
            String id = args.getString("id");
            String summary = args.getString("summary");
            String servings = args.getString("servings");
            String readyInMinutes = args.getString("readyInMinutes");
            String[] ingredients = args.getStringArray("ingredients");
            String instruction = args.getString("instruction");

            // Setze den Titel
            titleTextView.setText(title);
            Spanned spannedText = Html.fromHtml(summary, Html.FROM_HTML_MODE_LEGACY);
            Spanned spannedTextInstruction = Html.fromHtml(instruction, Html.FROM_HTML_MODE_LEGACY);
            instructionTextView.setText(spannedTextInstruction);
            summaryTextView.setText(spannedText);
            servingsTextView.setText("Servings: " + servings);
            readyInMinutesTextView.setText("Ready in: " + readyInMinutes + "min");

            for (String s : ingredients){
                ingredientsTextView.setText(ingredientsTextView.getText() + "- " + s + "\n");
            }

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
                    .transform(new RoundedCornersTransformation(60, 0))
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

            // Füge den OnClickListener hinzu, um auf die ID zu reagieren
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        return view;
    }
}