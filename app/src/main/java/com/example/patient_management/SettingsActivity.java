package com.example.patient_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    TextView textView;
    Singleton singleton;
    DatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        textView = findViewById(R.id.welcome);
        singleton = Singleton.getInstance();
        DB = new DatabaseHelper(this);

        Cursor res = DB.getdoctor(singleton.getValue());
        res.moveToNext();
        textView.setText("Witaj " + res.getString(0));
    }

    public void update(View v) {
        Intent intent = new Intent(SettingsActivity.this ,
                UpdateSettingsActivity.class);
        SettingsActivity.this.startActivity(intent);
    }

    public void backToMenu(View v) {
        Intent intent = new Intent(SettingsActivity.this ,
                MenuActivity.class);
        SettingsActivity.this.startActivity(intent);
    }
}