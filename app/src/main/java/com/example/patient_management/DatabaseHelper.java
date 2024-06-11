package com.example.patient_management;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "patient_management.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table USERS(username TEXT primary key, password TEXT, doctor TEXT)");
        DB.execSQL("create Table DOCTORS(ID INTEGER primary key AUTOINCREMENT, name TEXT, specialization TEXT, room INTEGER)");
        DB.execSQL("create Table MESSAGES(ID INTEGER primary key AUTOINCREMENT, from_ INTEGER, to_ INTEGER, message TEXT, date DATETIME)");
        DB.execSQL("create Table PATIENTS(ID INTEGER primary key AUTOINCREMENT, name TEXT, doctor INTEGER, pesel TEXT)");
        DB.execSQL("create Table VISITS(ID INTEGER primary key AUTOINCREMENT, password TEXT, patient INTEGER, doctor INTEGER, description TEXT, date DATETIME, prescription TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists USERS");
    }
    public Boolean insertuser(String username, String password, String doctor)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("doctor", doctor);
        long result=DB.insert("USERS", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean deleteuser (String username)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from USERS where username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            long result = DB.delete("USERS", "username=?", new String[]{username});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select username, password, doctor from USERS", null);
        return cursor;
    }
}