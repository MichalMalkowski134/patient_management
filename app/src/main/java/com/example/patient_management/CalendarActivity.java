package com.example.patient_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
    }

    public void backToMenu(View v) {
        Intent intent = new Intent(CalendarActivity.this ,
                MenuActivity.class);
        CalendarActivity.this.startActivity(intent);
    }
}