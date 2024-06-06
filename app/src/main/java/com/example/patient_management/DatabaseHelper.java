package com.example.patient_management;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "PATIENT_MANAGEMENT2.DB";
    static final int DB_VERSION = 1;

    static final String USER_TABLE = "USERS";
    static final String USER_ID= "ID";
    static final String USER_NAME= "username";
    static final String USER_PASSWORD= "password";

//    static final String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + " ( " + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//            + USER_NAME + " TEXT NOT NULL, " + USER_PASSWORD + " TEXT NOT NULL );";
    static final String CREATE_USER_TABLE = "CREATE TABLE Persons ( PersonID int );";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE );
    }
}
