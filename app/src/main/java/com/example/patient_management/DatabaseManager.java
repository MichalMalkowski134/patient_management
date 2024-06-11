//package com.example.patient_management;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//
//import java.sql.SQLDataException;
//
//public class DatabaseManager {
//    private DatabaseHelper dbHelper;
//    private Context context;
//    private SQLiteDatabase database;
//
//    public DatabaseManager(Context ctx) {
//        context = ctx;
//    }
//
//    public DatabaseManager open() throws SQLDataException {
//        dbHelper = new DatabaseHelper(context);
//        database = dbHelper.getWritableDatabase();
//        return this;
//    }
//
//    public void close(){
//        dbHelper.close();
//    }
//
//    public void insert_user(String username, String password){
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(DatabaseHelper.USER_NAME, username);
//        contentValues.put(DatabaseHelper.USER_PASSWORD, password);
//        database.insert(DatabaseHelper.USER_TABLE, null, contentValues);
//    }
//
//    public Cursor fetch() {
//        String [] columns = new String[] {DatabaseHelper.USER_ID, DatabaseHelper.USER_NAME, DatabaseHelper.USER_PASSWORD};
//        Cursor cursor = database.query(DatabaseHelper.USER_TABLE, columns, null, null, null, null, null);
//        if(cursor != null) {
//            cursor.moveToFirst();
//        }
//        return cursor;
//    }
//
//    public int update(int id, String username, String password) {
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(DatabaseHelper.USER_NAME, username);
//        contentValues.put(DatabaseHelper.USER_PASSWORD, password);
//        int ret = database.update(DatabaseHelper.USER_TABLE, contentValues, DatabaseHelper.USER_ID + "=" + id, null);
//        return ret;
//    }
//
//    public void delete(int id) {
//        database.delete(DatabaseHelper.USER_TABLE, DatabaseHelper.USER_ID + "=" + id, null);
//    }
//}
