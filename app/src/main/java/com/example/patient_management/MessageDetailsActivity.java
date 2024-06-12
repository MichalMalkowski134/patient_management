package com.example.patient_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MessageDetailsActivity extends AppCompatActivity {

    DatabaseHelper DB;
    Singleton singleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages);

        Intent intent = getIntent();
        String receivedString = intent.getStringExtra("EXTRA_STRING");

        ListView listView = findViewById(R.id.list_view);
        DB = new DatabaseHelper(this);
        singleton = Singleton.getInstance();


        String[] rList = {receivedString};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, rList);

        listView.setAdapter(adapter);
    }

    public void backToMenu(View v) {
        Intent intent = new Intent(MessageDetailsActivity.this ,
                MessagesActivity.class);
        MessageDetailsActivity.this.startActivity(intent);
    }
}