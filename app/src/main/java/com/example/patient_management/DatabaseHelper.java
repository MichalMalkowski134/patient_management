package com.example.patient_management;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "patient_management.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table USERS(username TEXT primary key, password TEXT, nameSurname TEXT, specialization TEXT, email TEXT, phone TEXT,doctor TEXT)");
        DB.execSQL("create Table DOCTORS(ID INTEGER primary key AUTOINCREMENT, name TEXT, specialization TEXT, room INTEGER)");
        DB.execSQL("create Table MESSAGES(ID INTEGER primary key AUTOINCREMENT, from_ TEXT, to_ TEXT, message TEXT, date TEXT )");
        DB.execSQL("create Table PATIENTS(pesel TEXT primary key, name TEXT, doctor INTEGER)");
        DB.execSQL("create Table VISITS(ID INTEGER primary key AUTOINCREMENT, patient INTEGER, doctor INTEGER, description TEXT, date TEXT, prescription TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists USERS");
        DB.execSQL("drop Table if exists PATIENTS");
    }

    public void deleteDatabase(Context context) {
        context.deleteDatabase("patient_management.db");
    }

    public Boolean insertuser(String username, String password, String nameSurname,
                              String specialization, String email, String phone, String doctor)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("nameSurname", nameSurname);
        contentValues.put("specialization", specialization);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
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

    public Boolean insertmessage(String from_, String to_, String message)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("from_", from_);
        contentValues.put("to_", to_);
        contentValues.put("message", message);
        Date currentTime = Calendar.getInstance().getTime();
        contentValues.put("date", String.valueOf(currentTime));
        long result=DB.insert("MESSAGES", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean insertvisits(String patient, String doctor, String description, String date)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("patient", patient);
        contentValues.put("doctor", doctor);
        contentValues.put("description", description);
        contentValues.put("date", date);
        long result=DB.insert("VISITS", null, contentValues);
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

    public Boolean updateUser(String username, String nameSurname, String specialization, String email, String phone)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nameSurname", nameSurname);
        contentValues.put("specialization", specialization);
        contentValues.put("email", email);
        contentValues.put("phone", phone);
        Cursor cursor = DB.rawQuery("Select * from USERS where username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            long result = DB.update("USERS", contentValues, "username=?", new String[]{username});
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

    public Boolean deleteUser (String username)
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

    public Cursor getmessages ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select from_, to_, message, date from MESSAGES ORDER BY ID DESC", null);
        return cursor;
    }

    public Cursor getvisits ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select patient, doctor, description, date from VISITS", null);
        return cursor;
    }

    public Cursor getdoctor (String username)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from USERS where username = ?", new String[]{username});
        return cursor;
    }

    public Cursor getlastdoctor ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT ID, name FROM DOCTORS WHERE ID=(SELECT max(ID) FROM DOCTORS);", null);
        return cursor;
    }
}