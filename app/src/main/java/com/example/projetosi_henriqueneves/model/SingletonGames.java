package com.example.games.model;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetosi_henriqueneves.listeners.GameListener;
import com.example.projetosi_henriqueneves.listeners.GamesListener;
import com.example.projetosi_henriqueneves.model.GameDBHelper;
import com.example.projetosi_henriqueneves.utils.GameJsonParser;
import com.example.projetosi_henriqueneves.model.Game;

import org.json.JSONArray;

import java.util.ArrayList;

public class SingletonGames {

    private ArrayList<Game> games;
    private static SingletonGames instance = null;
    private GameDBHelper gameDB = null;
    private static RequestQueue volleyQueue = null;
    public static final String mUrlAPIGames = "http://172.22.21.222:8081/api/games";

    private GamesListener gamesListener;
    private GameListener gameListener;

    public static synchronized SingletonGames getInstance(Context context) {
        if (instance == null) {
            instance = new SingletonGames(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return instance;
    }

    private SingletonGames(Context context) {
        games = new ArrayList<>();
        gameDB = new GameDBHelper(context);
    }

    public GamesListener getGamesListener() {
        return gamesListener;
    }

    public void setGamesListener(GamesListener gamesListener) {
        this.gamesListener = gamesListener;
    }

    public void setGameListener(GameListener gameListener) {
        this.gameListener = gameListener;
    }

    public GameListener getGameListener() {
        return gameListener;
    }

    public ArrayList<Game> getGamesBD() {
        games = gameDB.getAllGamesBD();
        return new ArrayList<>(games);
    }

    public Game getGame(int id) {
        for (Game g : games) {
            if (g.getId() == id) {
                return g;
            }
        }
        return null;
    }

    public void addGamesBD(ArrayList<Game> games) {
        gameDB.removeAllGamesBD();
        for (Game g : games)
            gameDB.addGameBD(g);
    }

    public void getAllGamesAPI(final Context context) {
        if (!GameJsonParser.isConnectionInternet(context)) {
            Toast.makeText(context, "No internet connection", Toast.LENGTH_LONG).show();
            games = gameDB.getAllGamesBD();

            if (gamesListener != null) {
                gamesListener.onRefreshGameList(games);
            }
        } else {
            JsonArrayRequest reqGames = new JsonArrayRequest(Request.Method.GET, mUrlAPIGames, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    games = GameJsonParser.parserJsonGames(response);
                    addGamesBD(games);

                    if (gamesListener != null) {
                        gamesListener.onRefreshGameList(games);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
            volleyQueue.add(reqGames);
        }
    }
}