package com.mealmastercookingrecipesapp;

import java.util.ArrayList;

public class FavoriteManager {
    private static final String FAVORITES_FILE = "favorites.txt";
    private ArrayList<String> favoriteIds;

    public FavoriteManager() {
        if(FileHandler.FileExists(FAVORITES_FILE)) {
            favoriteIds = FileHandler.LoadFromFile(FAVORITES_FILE);
        } else {
          favoriteIds = new ArrayList<>();
        }
    }

    public void addToFavorites(String id) {
        favoriteIds.add(id);
        FileHandler.SaveToFile(favoriteIds, FAVORITES_FILE);
    }

    public void removeFromFavorites(String id) {
        favoriteIds.remove(id);
        FileHandler.SaveToFile(favoriteIds, FAVORITES_FILE);
    }

    public boolean isFavorite(String id) {
        return favoriteIds.contains(id);
    }
}
