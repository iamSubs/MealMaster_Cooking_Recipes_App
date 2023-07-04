package com.mealmastercookingrecipesapp.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.mealmastercookingrecipesapp.Controller.ApiHandler;
import com.mealmastercookingrecipesapp.R;
import com.mealmastercookingrecipesapp.View.FavoriteFragment;
import com.mealmastercookingrecipesapp.View.HomeFragment;
import com.mealmastercookingrecipesapp.View.MenuFragment;
import com.mealmastercookingrecipesapp.View.SearchFragment;
import com.mealmastercookingrecipesapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ApiHandler apiHandler;
    String url = "https://api.spoonacular.com/recipes/random?number=1&apiKey=";

    String apiKey = "c53222da84d74352b9d096c2d64225d1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        apiHandler = new ApiHandler(this, url, apiKey);
        setContentView(binding.getRoot());
        FragmentManager fragmentManager = getSupportFragmentManager();
        replaceFragment(new HomeFragment(apiHandler, fragmentManager));

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragment(apiHandler, fragmentManager));
                    break;
                case R.id.menu:
                    replaceFragment(new MenuFragment(apiHandler, fragmentManager));
                    break;
                case R.id.search:
                    replaceFragment(new SearchFragment(apiHandler, fragmentManager));
                    break;
                case R.id.favorite:
                    replaceFragment(new FavoriteFragment(apiHandler, fragmentManager));
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