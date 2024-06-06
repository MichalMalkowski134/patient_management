package com.example.patient_management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditUsersActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    DatabaseManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_users);
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

    public void mainPage(View v) {
        Intent intent = new Intent(EditUsersActivity.this ,
                MainActivity.class);
        EditUsersActivity.this.startActivity(intent);
    }

    public void addUser(View v) {
        dbManager.insert_user("cos", "cos");
    }
}
