package com.example.patient_management;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
        DB.execSQL("create Table PATIENTS(pesel TEXT primary key, name TEXT, doctor INTEGER)");
        DB.execSQL("create Table VISITS(ID INTEGER primary key AUTOINCREMENT, password TEXT, patient INTEGER, doctor INTEGER, description TEXT, date DATETIME, prescription TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists USERS");
        DB.execSQL("drop Table if exists PATIENTS");
    }

    public void deleteDatabase(Context context) {
        context.deleteDatabase("patient_management.db");
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

    public Boolean insertpatient(String pesel, String name, int doctor)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("pesel", pesel);
        contentValues.put("name", name);
        contentValues.put("doctor", doctor);
        long result=DB.insert("PATIENTS", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean insertdoctor(String name, String specialization, int room)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("specialization", specialization);
        contentValues.put("room", room);
        long result=DB.insert("DOCTORS", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updatedoctor(int ID, String name, String specialization, int room)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("specialization", specialization);
        contentValues.put("room", room);
        Cursor cursor = DB.rawQuery("Select * from DOCTORS where ID = ?", new String[]{Integer.toString(ID)});
        if (cursor.getCount() > 0) {
            long result = DB.update("DOCTORS", contentValues, "ID=?", new String[]{Integer.toString(ID)});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
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

    public Boolean deletepatient (String pesel)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from PATIENTS where pesel = ?", new String[]{pesel});
        if (cursor.getCount() > 0) {
            long result = DB.delete("PATIENTS", "pesel=?", new String[]{pesel});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean deletedoctor (int ID)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from DOCTORS where ID = ?", new String[]{Integer.toString(ID)});
        if (cursor.getCount() > 0) {
            long result = DB.delete("DOCTORS", "ID=?", new String[]{Integer.toString(ID)});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getuser ()
    {
        Log.i("myTag", "This is my message");
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select username, password, doctor from USERS", null);
        return cursor;
    }

    public Cursor getpatient ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select pesel, name from PATIENTS", null);
        return cursor;
    }

    public Cursor getdoctor (int i)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select name, specialization, room from DOCTORS where ID = ?", new String[]{Integer.toString(i)});
        return cursor;
    }

    public Cursor getlastdoctor ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT ID, name FROM DOCTORS WHERE ID=(SELECT max(ID) FROM DOCTORS);", null);
        return cursor;
    }
}