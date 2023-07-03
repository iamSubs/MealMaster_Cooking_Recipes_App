package com.mealmastercookingrecipesapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class NewFragment extends Fragment {

    ApiHandler apiHandler;
    private TextView textViewResult;

    public NewFragment (ApiHandler apiHandler) {
        this.apiHandler = apiHandler;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Aufblasen des Layouts für das Fragment
        View rootView = inflater.inflate(R.layout.new_fragment, container, false);
        textViewResult = rootView.findViewById(R.id.api);
        textViewResult.setText("TEST");


        // API-Anfrage durchführen und Ergebnis ausgeben
        //apiHandler.getRecipeByName("banana");



        return rootView;
    }
}
