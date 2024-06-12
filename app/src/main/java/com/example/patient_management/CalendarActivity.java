package com.example.patient_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CalendarActivity extends AppCompatActivity {

    DatabaseHelper DB;
    Singleton singleton;
    String[] vList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);
        singleton = Singleton.getInstance();
        ListView listView = findViewById(R.id.list_view);
        DB = new DatabaseHelper(this);
        Cursor res = DB.getvisits();
        ArrayList<String> visitsList = new ArrayList<>();
        if(res.getCount()!=0){
            while(res.moveToNext()){
                if (res.getString(1).equals(singleton.getValue())){
                    visitsList.add(res.getString(3) + " - " + res.getString(2));
                }
            }
        }

        vList = new String[visitsList.size()];
        vList = visitsList.toArray(vList);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, vList);

        listView.setAdapter(adapter);
    }

    public void addVisit(View v) {
        Intent intent = new Intent(CalendarActivity.this ,
                AddVisitActivity.class);
        CalendarActivity.this.startActivity(intent);
    }

    public void backToMenu(View v) {
        Intent intent = new Intent(CalendarActivity.this ,
                MenuActivity.class);
        CalendarActivity.this.startActivity(intent);
    }
}