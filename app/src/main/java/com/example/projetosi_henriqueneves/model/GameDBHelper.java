package com.example.projetosi_henriqueneves.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GameDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "communigames.db";
    private static final int DATABASE_VERSION = 1;

    public GameDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE games (id INTEGER PRIMARY KEY, name TEXT, coverUrl TEXT, description TEXT, price REAL, developer TEXT, publisher TEXT, releaseDate TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS games");
        onCreate(db);
    }
}
