package com.mealmastercookingrecipesapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.util.ArrayList;

public class ApiHandler {

    String url;

    String key;
    private RequestQueue requestQueue;
    private Context context;
    String result;
    String RandomRecipeUrl = "https://api.spoonacular.com/recipes/random?number=1&apiKey=";

    public ApiHandler(Context context, String url, String key) {
        this.context = context;
        this.url = url + key;
        this.key = key;
        requestQueue = Volley.newRequestQueue(context);
    }

    String complexSearchUrl = "https://api.spoonacular.com/recipes/complexSearch?&apiKey=";

    public void getRandomRecipe(final RecipeCallback callback){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, RandomRecipeUrl + key, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Recipe recipe = null;
                    result = response.toString();
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(result);

                        // Auf einzelne Elemente zugreifen
                        JsonNode recipesNode = jsonNode.get("recipes");
                        if (recipesNode != null && recipesNode.isArray()) {
                            for (JsonNode recipeNode : recipesNode) {
                                String title = recipeNode.get("title").asText();
                                String id = recipeNode.get("id").asText();
                                String imagelink = recipeNode.get("image").asText();
                                String instructions = recipeNode.get("instructions").asText();
                                String readyInMinutes = recipeNode.get("readyInMinutes").asText();
                                recipe = new Recipe(id, title, imagelink, instructions, Integer.parseInt(readyInMinutes));
                            }
                        } else {
                            System.out.println("Das JSON-Objekt enthält keine 'recipes'-Eigenschaft oder diese ist kein Array.");
                        }
                        // Rückruf aufrufen und das Ergebnis übergeben
                        callback.onSuccess(recipe);
                    } catch (Exception e) {
                        e.printStackTrace();
                        // Bei einem Fehler den Rückruf mit null aufrufen
                        callback.onError(e);
                    }
                }catch (Exception e){
                    result = "";
                    // Bei einem Fehler den Rückruf mit null aufrufen
                    callback.onError(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Bei einem Fehler den Rückruf mit null aufrufen
                callback.onError(error);
            }
        });
        requestQueue.add(request);
    }

    public void complexSearch(final RecipeCallbackArray callback, String search){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, complexSearchUrl + key + "&" + search, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    result = response.toString();
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(result);

                        // Auf einzelne Elemente zugreifen
                        JsonNode recipesNode = jsonNode.get("results");
                        if (recipesNode != null && recipesNode.isArray()) {
                            ArrayList<Recipe> recipeList = new ArrayList<>();
                            for (JsonNode recipeNode : recipesNode) {
                                String title = recipeNode.get("title").asText();
                                String id = recipeNode.get("id").asText();
                                String imagelink = recipeNode.get("image").asText();
                                Recipe recipe = new Recipe(id, title, imagelink, "", 1);
                                recipeList.add(recipe);
                            }
                            // Rückruf aufrufen und das Ergebnis übergeben
                            callback.onSuccess(recipeList.toArray(new Recipe[0]));
                        } else {
                            System.out.println("Das JSON-Objekt enthält keine 'recipes'-Eigenschaft oder diese ist kein Array.");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        // Bei einem Fehler den Rückruf mit null aufrufen
                        callback.onError(e);
                    }
                } catch (Exception e) {
                    result = "";
                    // Bei einem Fehler den Rückruf mit null aufrufen
                    callback.onError(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Bei einem Fehler den Rückruf mit null aufrufen
                callback.onError(error);
            }
        });
        requestQueue.add(request);
    }

}
