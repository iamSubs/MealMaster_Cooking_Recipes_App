package com.mealmastercookingrecipesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mealmastercookingrecipesapp.databinding.ActivityMainBinding;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    ApiHandler apiHandler;
    String url = "https://api.spoonacular.com/recipes/random?number=1&apiKey=";

    String apiKey = "90910a4b8b1745c9804f372de4c007e4";
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