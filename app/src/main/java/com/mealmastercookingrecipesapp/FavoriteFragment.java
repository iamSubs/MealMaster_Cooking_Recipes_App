package com.mealmastercookingrecipesapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FavoriteFragment extends Fragment {
    ApiHandler apiHandler;
    public FavoriteFragment(ApiHandler apiHandler) {
        this.apiHandler = apiHandler;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);



        return view;
    }
}