package com.mealmastercookingrecipesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.mealmastercookingrecipesapp.Controller.ApiHandler;
import com.mealmastercookingrecipesapp.View.FavoriteFragment;
import com.mealmastercookingrecipesapp.View.HomeFragment;
import com.mealmastercookingrecipesapp.View.MenuFragment;
import com.mealmastercookingrecipesapp.View.SearchFragment;
import com.mealmastercookingrecipesapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ApiHandler apiHandler;
    String url = "https://api.spoonacular.com/recipes/random?number=1&apiKey=";

    String apiKey = "8901d0f1e4ac4e18b88b126d663d62bd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        apiHandler = new ApiHandler(this, url, apiKey);
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment(apiHandler));

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragment(apiHandler));
                    break;
                case R.id.menu:
                    replaceFragment(new MenuFragment(apiHandler));
                    break;
                case R.id.search:
                    replaceFragment(new SearchFragment(apiHandler));
                    break;
                case R.id.favorite:
                    replaceFragment(new FavoriteFragment(apiHandler));
                    break;
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}