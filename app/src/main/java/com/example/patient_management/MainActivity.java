package com.example.patient_management;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    //DatabaseManager dbManager;
    DatabaseHelper DB;
    Singleton singleton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DB = new DatabaseHelper(this);
        username = findViewById(R.id.userNameEditText);
        password = findViewById(R.id.passwordEditText);
        singleton = Singleton.getInstance();
        //DB.deleteDatabase(MainActivity.this);
    }


    public void editUsers(View v) {
        Intent intent = new Intent(MainActivity.this ,
                EditUsersActivity.class);
        MainActivity.this.startActivity(intent);
    }

    public void login(View v) {
        username = findViewById(R.id.userNameEditText);
        password = findViewById(R.id.passwordEditText);

        Cursor res = DB.getuser();
        if(res.getCount()==0){
            Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        while(res.moveToNext()){

            if(username.getText().toString().equals(res.getString(0)) && password.getText().toString().equals(res.getString(1)))
            {
                singleton.setValue(res.getString(0));
                Intent intent = new Intent(MainActivity.this ,
                        MenuActivity.class);
                MainActivity.this.startActivity(intent);
            }
        }
        // TU EWENTUALNIE COS JAK ZLE LOGOWANIE
    }
}