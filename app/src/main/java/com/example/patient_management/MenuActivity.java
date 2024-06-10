package com.example.patient_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }

    public void openMessages(View v) {
        Intent intent = new Intent(MenuActivity.this ,
                MessagesActivity.class);
        MenuActivity.this.startActivity(intent);
    }

    public void openPatients(View v) {
        Intent intent = new Intent(MenuActivity.this ,
                PatientsActivity.class);
        MenuActivity.this.startActivity(intent);
    }

    public void openCalendar(View v) {
        Intent intent = new Intent(MenuActivity.this ,
                CalendarActivity.class);
        MenuActivity.this.startActivity(intent);
    }

    public void openSettings(View v) {
        Intent intent = new Intent(MenuActivity.this ,
                SettingsActivity.class);
        MenuActivity.this.startActivity(intent);
    }
}