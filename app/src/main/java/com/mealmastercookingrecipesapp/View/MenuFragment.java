package com.mealmastercookingrecipesapp.View;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mealmastercookingrecipesapp.Controller.ApiHandler;
import com.mealmastercookingrecipesapp.Controller.FragmentController;
import com.mealmastercookingrecipesapp.R;

public class MenuFragment extends Fragment {

    ApiHandler apiHandler;

    private CardView cardView1;
    private CardView cardView2;
    private CardView cardView3;
    private CardView cardView4;
    private CardView cardView5;
    private CardView cardView6;
    private CardView cardView7;
    private CardView cardView8;
    private CardView cardView9;
    private CardView cardView10;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;
    private ImageView imageView7;
    private ImageView imageView8;
    private ImageView imageView9;
    private ImageView imageView10;
    FragmentController fragmentController;

    public MenuFragment(ApiHandler apiHandler, FragmentManager fragmentManager) {
        this.apiHandler = apiHandler;
        fragmentController = new FragmentController(fragmentManager, apiHandler);
    }




    public void hideAllImages(){
        cardView1.setVisibility(View.GONE);
        cardView2.setVisibility(View.GONE);
        cardView3.setVisibility(View.GONE);
        cardView4.setVisibility(View.GONE);
        cardView5.setVisibility(View.GONE);
        cardView6.setVisibility(View.GONE);
        cardView7.setVisibility(View.GONE);
        cardView8.setVisibility(View.GONE);
        cardView9.setVisibility(View.GONE);
        cardView10.setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        cardView1 = rootView.findViewById(R.id.cardView1);
        cardView2 = rootView.findViewById(R.id.cardView2);
        cardView3 = rootView.findViewById(R.id.cardView3);
        cardView4 = rootView.findViewById(R.id.cardView4);
        cardView5 = rootView.findViewById(R.id.cardView5);
        cardView6 = rootView.findViewById(R.id.cardView6);
        cardView7 = rootView.findViewById(R.id.cardView7);
        cardView8 = rootView.findViewById(R.id.cardView8);
        cardView9 = rootView.findViewById(R.id.cardView9);
        cardView10 = rootView.findViewById(R.id.cardView10);

        imageView1 = rootView.findViewById(R.id.imageView1);
        imageView2 = rootView.findViewById(R.id.imageView2);
        imageView3 = rootView.findViewById(R.id.imageView3);
        imageView4 = rootView.findViewById(R.id.imageView4);
        imageView5 = rootView.findViewById(R.id.imageView5);
        imageView6 = rootView.findViewById(R.id.imageView6);
        imageView7 = rootView.findViewById(R.id.imageView7);
        imageView8 = rootView.findViewById(R.id.imageView8);
        imageView9 = rootView.findViewById(R.id.imageView9);
        imageView10 = rootView.findViewById(R.id.imageView10);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Image Click", "Image 1 clicked");
                hideAllImages();
                Fragment newFragment = new CategorieSerachFragment(apiHandler, "query=chicken", fragmentController);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Image Click", "Image 2 clicked");
                hideAllImages();
                Fragment newFragment = new CategorieSerachFragment(apiHandler, "query=pork", fragmentController);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Image Click", "Image 3 clicked");
                hideAllImages();
                Fragment newFragment = new CategorieSerachFragment(apiHandler, "query=salad", fragmentController);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Image Click", "Image 4 clicked");
                hideAllImages();
                Fragment newFragment = new CategorieSerachFragment(apiHandler, "query=Vegetarian", fragmentController);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Image Click", "Image 5 clicked");
                hideAllImages();
                Fragment newFragment = new CategorieSerachFragment(apiHandler, "query=Vegan", fragmentController);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Image Click", "Image 6 clicked");
                hideAllImages();
                Fragment newFragment = new CategorieSerachFragment(apiHandler, "query=Soups", fragmentController);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Image Click", "Image 7 clicked");
                hideAllImages();
                Fragment newFragment = new CategorieSerachFragment(apiHandler, "query=Breakfast", fragmentController);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Image Click", "Image 8 clicked");
                hideAllImages();
                Fragment newFragment = new CategorieSerachFragment(apiHandler, "query=Breads", fragmentController);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Image Click", "Image 9 clicked");
                hideAllImages();
                Fragment newFragment = new CategorieSerachFragment(apiHandler, "query=Dessert", fragmentController);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Image Click", "Image 10 clicked");
                hideAllImages();
                Fragment newFragment = new CategorieSerachFragment(apiHandler, "query=Snack", fragmentController);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, newFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }


}