package com.mealmastercookingrecipesapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class SearchFragment extends Fragment {

    ApiHandler apiHandler;

    Button loadButton;
    TextView textView;

    public SearchFragment(ApiHandler apiHandler) {
        this.apiHandler = apiHandler;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        loadButton = view.findViewById(R.id.loadButton);
        textView = view.findViewById(R.id.textView);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = apiHandler.getRecipeByName("results");
                textView.setText(text);
            }
        });
        return view;
    }
}