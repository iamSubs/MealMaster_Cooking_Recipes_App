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

public class ApiHandler {

    String url;
    private RequestQueue requestQueue;
    private Context context;
    String result;
    String testText;

    public ApiHandler(Context context, String url, String key) {
        this.context = context;
        this.url = url + key;
        requestQueue = Volley.newRequestQueue(context);
    }

    public void getRecipeByName(String name, final RecipeCallback callback){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
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

    public void getRecipeById(String id, final RecipeCallback callback){
        String apiKey = "90910a4b8b1745c9804f372de4c007e4";
        String specificUrl = "https://api.spoonacular.com/recipes/" + id + "/information?apiKey=" + apiKey;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, specificUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Recipe recipe = null;
                    result = response.toString();
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(result);
                        // Directly extract recipe information since it's not in an array
                        String title = jsonNode.get("title").asText();
                        String imageUrl = jsonNode.get("image").asText();
                        String instructions = jsonNode.get("instructions").asText();
                        String readyInMinutes = jsonNode.get("readyInMinutes").asText();
                        recipe = new Recipe(id, title, imageUrl, instructions, Integer.parseInt(readyInMinutes));
                        // Call the callback and pass the result
                        callback.onSuccess(recipe);
                    } catch (Exception e) {
                        e.printStackTrace();
                        // Call the callback with null in case of an error
                        callback.onError(e);
                    }
                }catch (Exception e){
                    result = "";
                    // Call the callback with null in case of an error
                    callback.onError(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Call the callback with null in case of an error
                callback.onError(error);
            }
        });
        requestQueue.add(request);
    }


}
