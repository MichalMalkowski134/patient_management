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
public class UpdateSettingsActivity extends AppCompatActivity {
    EditText name, specialization, room;
    Button update, delete, back;
    DatabaseHelper DB;
    Singleton singleton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_settings);
        name = findViewById(R.id.name);
        specialization = findViewById(R.id.specialization);
        room = findViewById(R.id.room);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        back = findViewById(R.id.btnBack);
        DB = new DatabaseHelper(this);
        singleton = Singleton.getInstance();

        Cursor res = DB.getdoctor(singleton.getValue());
        res.moveToNext();
        name.setText(res.getString(0));
        specialization.setText(res.getString(1));
        room.setText(res.getString(2));
//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String nameTXT = name.getText().toString();
//                String specializationTXT = specialization.getText().toString();
//                int roomTXT = Integer.valueOf(room.getText().toString());
//
//                Boolean checkinsertdata = DB.updateUser(singleton.getValue(), nameTXT, specializationTXT, roomTXT);
//                if(checkinsertdata==true)
//                    Toast.makeText(UpdateSettingsActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(UpdateSettingsActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
//            }        });

//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Boolean checkudeletedata = DB.deletedoctor(singleton.getValue());
//                if(checkudeletedata==true)
//                {
//                    Intent intent = new Intent(UpdateSettingsActivity.this ,
//                            MainActivity.class);
//                    UpdateSettingsActivity.this.startActivity(intent);
//                    Toast.makeText(UpdateSettingsActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
//                }
//                else
//                    Toast.makeText(UpdateSettingsActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
//            }        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateSettingsActivity.this ,
                        SettingsActivity.class);
                UpdateSettingsActivity.this.startActivity(intent);
            }        });
    }}