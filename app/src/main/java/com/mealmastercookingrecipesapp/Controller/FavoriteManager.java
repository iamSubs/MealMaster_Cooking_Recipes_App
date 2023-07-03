package com.mealmastercookingrecipesapp.Controller;

import android.content.Context;

import java.util.ArrayList;

public class FavoriteManager {
    private final String FAVORITES_FILE = "favorites.txt";
    private ArrayList<String> favoriteIds;
    private FileHandler fileHandler;

    public FavoriteManager(Context context) {
        fileHandler = new FileHandler(context);
        if(fileHandler.FileExists(FAVORITES_FILE)) {
            favoriteIds = fileHandler.LoadFromFile(FAVORITES_FILE);
        } else {
          favoriteIds = new ArrayList<>();
        }
    }

    public void addToFavorites(String id) {
        favoriteIds.add(id);
        fileHandler.SaveToFile(favoriteIds, FAVORITES_FILE);
    }

    public void removeFromFavorites(String id) {
        favoriteIds.remove(id);
        fileHandler.SaveToFile(favoriteIds, FAVORITES_FILE);
    }

    public ArrayList<String> getFavorites(){
        if(fileHandler.FileExists(FAVORITES_FILE)) {
            favoriteIds = fileHandler.LoadFromFile(FAVORITES_FILE);
        } else {
            favoriteIds = new ArrayList<>();
        }
        return favoriteIds;
    }

    public boolean isFavorite(String id) {
        return favoriteIds.contains(id);
    }
}
