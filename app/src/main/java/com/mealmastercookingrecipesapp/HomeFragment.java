package com.mealmastercookingrecipesapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class HomeFragment extends Fragment {

    ApiHandler apiHandler;

    TextView homeTextView;
    ProgressBar spinner;
    String textHome;
    public HomeFragment(ApiHandler apiHandler) {
        this.apiHandler = apiHandler;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        spinner = view.findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);
        homeTextView = view.findViewById(R.id.homeTextView);
        textHome = apiHandler.getRecipeByName("results");
        homeTextView.setText(textHome);
        if (homeTextView.length() > 1){
            spinner.setVisibility(View.GONE);
        }
        return view;
    }
}