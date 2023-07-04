package com.mealmastercookingrecipesapp.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

    public static RecipeFragment newInstance(String title, String imageUrl, String id, String summary, int servings, int readyInMinutes) {
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("imageUrl", imageUrl);
        args.putString("id", id);
        args.putString("summary", summary);
        args.putString("servings", Integer.toString(servings));
        args.putString("readyInMinutes", Integer.toString(readyInMinutes));
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);

        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView summaryTextView = view.findViewById(R.id.summaryTextView);
        TextView servingsTextView = view.findViewById(R.id.servingsTextView);
        TextView readyInMinutesTextView = view.findViewById(R.id.readyInMinutesTextView);
        ImageView imageView = view.findViewById(R.id.imageViewTESTER);


        Bundle args = getArguments();
        if (args != null) {
            String title = args.getString("title");
            String imageUrl = args.getString("imageUrl");
            String id = args.getString("id");
            String summary = args.getString("summary");
            String servings = args.getString("servings");
            String readyInMinutes = args.getString("readyInMinutes");

            // Setze den Titel
            titleTextView.setText(title);
            Spanned spannedText = Html.fromHtml(summary, Html.FROM_HTML_MODE_LEGACY);
            summaryTextView.setText(spannedText);
            servingsTextView.setText(servings);
            readyInMinutesTextView.setText(readyInMinutes + "min");

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