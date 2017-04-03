package com.example.humor.seletiene_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by humor on 03/04/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    String sqlCreateLibros = "CREATE TABLE libros(" +
            "id             INTEGER PRIMARY KEY AUTOINCREMENT," +
            "libro          TEXT," +
            "autor          TEXT," +
            "descripcion    TEXT)";

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreateLibros);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS libros");
        db.execSQL(sqlCreateLibros);
    }
}
