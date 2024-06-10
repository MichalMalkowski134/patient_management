package com.example.patient_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
    }

    public void backToMenu(View v) {
        Intent intent = new Intent(SettingsActivity.this ,
                MenuActivity.class);
        SettingsActivity.this.startActivity(intent);
    }
}