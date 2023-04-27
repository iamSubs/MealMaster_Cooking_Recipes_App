package com.mealmastercookingrecipesapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class ApiHandler {

    String url;
    private RequestQueue requestQueue;
    private Context context;
    String result;

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
                    result = response.getString(name);
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
        return result;
    }

}
