package com.mealmastercookingrecipesapp.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mealmastercookingrecipesapp.Controller.ApiHandler;
import com.mealmastercookingrecipesapp.R;

public class MenuFragment extends Fragment {

    ApiHandler apiHandler;

    public MenuFragment(ApiHandler apiHandler) {
        this.apiHandler = apiHandler;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }
}