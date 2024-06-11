package com.example.patient_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PatientsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patients);


    }

    public void addPatient(View v) {
        Intent intent = new Intent(PatientsActivity.this ,
                AddPatientActivity.class);
        PatientsActivity.this.startActivity(intent);
    }

    public void backToMenu(View v) {
        Intent intent = new Intent(PatientsActivity.this ,
                MenuActivity.class);
        PatientsActivity.this.startActivity(intent);
    }
}