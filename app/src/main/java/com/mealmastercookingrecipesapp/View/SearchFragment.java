package com.mealmastercookingrecipesapp.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.mealmastercookingrecipesapp.Controller.ApiHandler;
import com.mealmastercookingrecipesapp.Controller.FragmentController;
import com.mealmastercookingrecipesapp.R;
import com.mealmastercookingrecipesapp.Model.Recipe;
import com.mealmastercookingrecipesapp.Model.RecipeCallbackArray;

import java.util.ArrayList;


public class SearchFragment extends Fragment {

    ApiHandler apiHandler;
    FragmentController fragmentController;

    private String[] suggestions = {
            "Pizza", "Pasta", "Salad", "Soup", "Smoothie", "Burger", "Cake", "Tacos", "Sushi", "Curry",
            "Omelette", "Risotto", "Nachos", "Stir Fry", "Sandwich", "Grilled Chicken", "Pancakes", "Waffles",
            "Biryani", "Goulash", "Hummus", "Lasagna", "Fried Rice", "Guacamole", "Enchiladas", "Pho",
            "Sashimi", "Falafel", "Bruschetta", "Chili", "Fajitas", "Samosa", "Scones", "Cobb Salad",
            "Gazpacho", "Ratatouille", "Beef Stroganoff", "Quiche", "Pad Thai", "Ceviche", "Gyros",
            "Frittata", "Beef Wellington", "Tiramisu", "Miso Soup", "Eggplant Parmesan", "Shawarma",
            "Caesar Salad", "Key Lime Pie", "Calamari", "Potato Salad", "Peking Duck", "Pierogi",
            "Beignets", "Lobster Bisque", "Fried Chicken", "Croissant", "Tandoori Chicken", "Ramen",
            "Clam Chowder", "Gumbo", "Crab Cakes", "Tom Yum Soup", "Fondue", "Creme Brulee", "Empanadas",
            "Philly Cheesesteak", "Baklava", "Caprese Salad", "Croquettes", "Shrimp Scampi", "Ravioli",
            "Chicken Shawarma", "Cannoli", "Fish and Chips", "Escargots", "Greek Salad", "Huevos Rancheros",
            "Lamb Kebabs", "Mushroom Risotto", "Dumplings", "Cinnamon Rolls", "Coq au Vin", "Hot and Sour Soup",
            "Egg Drop Soup", "Spanakopita", "Chicken Parmesan", "Fish Tacos", "Strawberry Shortcake",
            "Beef Tacos", "Baba Ganoush", "Peking Pork", "Crispy Pork Belly", "Shrimp Po' Boy", "Bacon Wrapped Scallops",
            "Beef Teriyaki", "Stuffed Peppers", "Pulled Pork Sandwich", "Chicken Quesadilla", "Beef Burrito",
            "Oysters Rockefeller", "Braised Lamb Shank", "Chicken Satay", "Sweet and Sour Chicken",
            "Lemon Meringue Pie", "Chicken Alfredo", "Mongolian Beef", "French Onion Soup", "Tofu Stir Fry"
    };


    public SearchFragment(ApiHandler apiHandler, FragmentManager fragmentManager) {
        this.apiHandler = apiHandler;
        this.fragmentController = new FragmentController(fragmentManager, apiHandler);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        SeekBar seekBar = view.findViewById(R.id.seekBar);
        TextView seekBarText = view.findViewById(R.id.SeekBarText);
        Switch veganSwitch = view.findViewById(R.id.VeganSwitch);
        Switch vegetarianSwitch = view.findViewById(R.id.VegetarianSwitch);
        Switch glutenFree = view.findViewById(R.id.GlutenFreeSwitch);
        ImageView searchImageView = view.findViewById(R.id.searchImageView);
        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView);
        TextView addIngredientTextView = view.findViewById(R.id.AddIngredientTextView);
        ArrayList<String> ingredientList = new ArrayList<>();

        LinearLayout searchBarLayout = view.findViewById(R.id.searchBarLayout);
        LinearLayout settingsLayout = view.findViewById(R.id.settingsLayout);
        LinearLayout switchesLayout = view.findViewById(R.id.switchesLayout);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, suggestions);
        autoCompleteTextView.setAdapter(adapter);



        veganSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    vegetarianSwitch.setChecked(false);
                }
            }
        });

        vegetarianSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    veganSwitch.setChecked(false);
                }
            }
        });

        addIngredientTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Eingabe");

                // Inhalt des Dialogs - EditText
                final EditText editText = new EditText(getContext());
                builder.setView(editText);

                // Speichern-Button
                builder.setPositiveButton("Speichern", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String inputText = editText.getText().toString();

                        // Erstelle ein neues TextView
                        TextView newTextView = new TextView(getContext());
                        newTextView.setText(inputText);
                        newTextView.setTextColor(Color.BLACK); // Textfarbe auf Schwarz setzen
                        newTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12); // Textgröße auf 12sp setzen

                        // Setze die gewünschten Layout-Parameter für das TextView
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        int margin16dp = (int) TypedValue.applyDimension(
                                TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());
                        layoutParams.setMargins(margin16dp, 0, 0, 0); // 16dp linker Margin
                        newTextView.setLayoutParams(layoutParams);
                        newTextView.setText(inputText);

                        // Füge das TextView zum Fragment-Layout hinzu
                        LinearLayout linearLayout = getView().findViewById(R.id.settingsLayout);
                        linearLayout.addView(newTextView);
                        ingredientList.add(inputText);
                    }
                });

                // Abbrechen-Button
                builder.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                // AlertDialog anzeigen
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Aktualisiere den angezeigten Wert
                String progressText = progress + " min";
                seekBarText.setText(progressText);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Keine Aktion erforderlich
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Keine Aktion erforderlich
            }
        });

        autoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    String currentText = autoCompleteTextView.getText().toString();
                    if (currentText.equals("Search for Recipes")) {
                        autoCompleteTextView.setText("");
                    }
                }
            }
        });

        searchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsLayout.setVisibility(View.GONE);
                searchBarLayout.setVisibility(View.GONE);
                switchesLayout.setVisibility(View.GONE);


                String search = "query=" + autoCompleteTextView.getText();

                if (veganSwitch.isChecked()) {
                    if (glutenFree.isChecked()){
                        search+= "&diet=gluten free,vegan";
                    } else {
                        search += "&diet=vegan";
                    }
                }

                if (vegetarianSwitch.isChecked()) {
                    if (glutenFree.isChecked()){
                        search+= "&diet=gluten free,vegetarian";
                    } else {
                        search += "&diet=vegetarian";
                    }
                }
                if (seekBar.getProgress() >= 1) {
                    search += "&maxReadyTime=" + seekBar.getProgress();
                }


                if (!ingredientList.isEmpty()){
                    search += "&includeIngredients=";
                    for (String ingredient : ingredientList){
                        search += ingredient+ ",";
                    }
                }

                int duration = Toast.LENGTH_SHORT; // oder Toast.LENGTH_LONG für längere Anzeigedauer
                Toast.makeText(getContext(), search, duration).show();

                apiHandler.complexSearch(new RecipeCallbackArray() {
                    @Override
                    public void onSuccess(Recipe[] recipes) {
                        // Hier kannst du das Array von Rezepten verwenden
                        for (Recipe recipe : recipes) {
                            fragmentController.addImageToFragment(recipe.getTitle(), recipe.getImageUrl(), recipe.getId(), view, R.drawable.baseline_favorite_24 , getContext(), getResources(), "search");
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        // Fehlerbehandlung
                    }
                }, search);
            }
        });




        return view;
    }
}