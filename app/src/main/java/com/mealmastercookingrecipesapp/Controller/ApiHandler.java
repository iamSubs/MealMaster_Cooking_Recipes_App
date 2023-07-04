package com.mealmastercookingrecipesapp.Controller;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mealmastercookingrecipesapp.Model.Recipe;
import com.mealmastercookingrecipesapp.Model.RecipeCallback;
import com.mealmastercookingrecipesapp.Model.RecipeCallbackArray;

import org.json.JSONArray;
import org.json.JSONException;
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
                                recipe = new Recipe(id, title, imagelink, instructions, Integer.parseInt(readyInMinutes), 0, "", false, false, false);
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
                                Recipe recipe = new Recipe(id, title, imagelink, "", 1, 0, "", false, false, false);
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

    private String[] ingredientExtractor(JSONArray jsonArray) {
        String[] ingredients = new String[jsonArray.length()];
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject ingredientObject = jsonArray.getJSONObject(i);
                String ingredientName = ingredientObject.getString("original");
                ingredients[i] = ingredientName;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ingredients;
    }

    public void getRecipeById(String id, final RecipeCallback callback){
        String specificUrl = "https://api.spoonacular.com/recipes/" + id + "/information?apiKey=" + key;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, specificUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Recipe recipe = null;
                    result = response.toString();
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(result);

                        // Extract ingredients using the extractIngredients function
                        String ingredientsJson = jsonNode.get("extendedIngredients").toString();
                        JSONArray ingredientsArray = new JSONArray(ingredientsJson);
                        String[] ingredients = ingredientExtractor(ingredientsArray);

                        // Directly extract recipe information since it's not in an array
                        String title = jsonNode.get("title").asText();
                        String imageUrl = jsonNode.get("image").asText();
                        String instructions = jsonNode.get("instructions").asText();
                        String readyInMinutes = jsonNode.get("readyInMinutes").asText();
                        String servings = jsonNode.get("servings").asText();
                        String summary = jsonNode.get("summary").asText();
                        String vegetarian = jsonNode.get("vegetarian").asText();
                        String vegan = jsonNode.get("vegan").asText();
                        String glutenFree = jsonNode.get("glutenFree").asText();

                        recipe = new Recipe(id, title, imageUrl, instructions, Integer.parseInt(readyInMinutes), Integer.parseInt(servings), summary, Boolean.parseBoolean(vegetarian), Boolean.parseBoolean(vegan), Boolean.parseBoolean(glutenFree));
                        // Call the callback and pass the result
                        recipe.setIngredients(ingredients);
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
