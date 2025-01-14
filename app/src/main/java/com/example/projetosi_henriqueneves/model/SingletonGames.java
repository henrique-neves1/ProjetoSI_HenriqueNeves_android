package com.example.projetosi_henriqueneves.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetosi_henriqueneves.listeners.GameListener;
import com.example.projetosi_henriqueneves.listeners.GamesListener;

import java.util.ArrayList;

public class SingletonGames {
    private ArrayList<Game> games;
    private static GameDBHelper gamesDB=null;
    private static SingletonGames instance=null;
    public static RequestQueue volleyQueue=null;
    public static final String mUrlAPIGames = "172.22.21.222:8081/api/games";

    private GameListener gameListener;
    private GamesListener gamesListener;

    public static synchronized SingletonGames getInstance(Context context){
        if(instance==null) {
            instance = new SingletonGames();
            volleyQueue = Volley.newRequestQueue(context);
        }
        return instance;
    }

    public void getGames() {
         Get();
    }




    private void Get() {

        String url = mUrlAPIGames;

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the API response here
                        System.out.println("Response: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle any errors
                        error.printStackTrace();
                        System.out.println("Error: " + error.getMessage());
                    }
                });

        // Add the request to the RequestQueue
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        requestQueue.add(stringRequest);

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("172.22.21.222:8081/api/games")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        apiService = retrofit.create(ApiService.class);
    }

//    public static synchronized ApiManager getInstance() {
//        if (instance == null) {
//            instance = new ApiManager();
//        }
//        return instance;
//    }
//
//    public ApiService getApiService() {
//        return apiService;
//    }
}
