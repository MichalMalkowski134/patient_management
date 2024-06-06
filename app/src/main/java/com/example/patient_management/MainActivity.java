package com.example.patient_management;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button login_button;
    EditText username;
    EditText password;
    DatabaseManager dbManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.userNameEditText);
        password = (EditText) findViewById(R.id.passwordEditText);

        dbManager = new DatabaseManager(this);
        try {
            dbManager.open();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void editUsers(View v) {
        Intent intent = new Intent(MainActivity.this ,
                EditUsersActivity.class);
        MainActivity.this.startActivity(intent);
    }

    public void buttonLoginPressed(View v) {

    }

}