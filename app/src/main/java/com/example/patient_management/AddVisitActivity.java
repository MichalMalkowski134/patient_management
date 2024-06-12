package com.example.patient_management;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddVisitActivity extends AppCompatActivity {
    EditText patient, description, date;
    Button insert, back;
    DatabaseHelper DB;
    Singleton singleton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_visit);
        patient = findViewById(R.id.patient);
        description = findViewById(R.id.description);
        date = findViewById(R.id.date);
        singleton = Singleton.getInstance();

        insert = findViewById(R.id.btnInsert);
        back = findViewById(R.id.btnBack);
        DB = new DatabaseHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String patientTXT = patient.getText().toString();
                String descriptionTXT = description.getText().toString();
                String dateTXT = date.getText().toString();
                Boolean checkinsertdata = DB.insertvisits(patientTXT, singleton.getValue(), descriptionTXT, dateTXT);
                if(checkinsertdata==true)
                {
                    Toast.makeText(AddVisitActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(AddVisitActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddVisitActivity.this ,
                        CalendarActivity.class);
                AddVisitActivity.this.startActivity(intent);
            }        });
    }}