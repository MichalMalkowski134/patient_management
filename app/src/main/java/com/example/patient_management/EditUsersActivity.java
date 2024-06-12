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
import android.widget.Toast;
public class EditUsersActivity extends AppCompatActivity {
    EditText username, password, nameSurname, specialization, email, phone;
    Button insert, back;
    DatabaseHelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_users);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        nameSurname = findViewById(R.id.nameSurname);
        specialization = findViewById(R.id.specialization);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);


        insert = findViewById(R.id.btnInsert);
        back = findViewById(R.id.btnBack);
        DB = new DatabaseHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameTXT = username.getText().toString();
                String passwordTXT = password.getText().toString();
                String nameSurnameTXT = nameSurname.getText().toString();
                String specializationTXT = specialization.getText().toString();
                String emailTXT = email.getText().toString();
                String phoneTXT = phone.getText().toString();

                DB.insertdoctor(nameSurnameTXT, "", 0);
                Cursor res = DB.getlastdoctor();
                res.moveToNext();
                Boolean checkinsertdata = DB.insertuser(usernameTXT, passwordTXT, nameSurnameTXT,
                        specializationTXT , emailTXT, phoneTXT ,res.getString(0));
                if(checkinsertdata==true)
                {
                    Toast.makeText(EditUsersActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(EditUsersActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditUsersActivity.this ,
                        MainActivity.class);
                EditUsersActivity.this.startActivity(intent);
            }        });
    }}













