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

    public String getRecipeByName(String name){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
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
                                String image = recipeNode.get("image").asText();
                                testText = image;
                            }
                        } else {
                            System.out.println("Das JSON-Objekt enthält keine 'recipes'-Eigenschaft oder diese ist kein Array.");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }catch (Exception e){
                    result = "";
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
        return testText;
    }

}
