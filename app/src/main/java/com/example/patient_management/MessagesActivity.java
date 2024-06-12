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

public class MessagesActivity extends AppCompatActivity {

    DatabaseHelper DB;
    Singleton singleton;
    String[] rList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages);

        ListView listView = findViewById(R.id.list_view);
        DB = new DatabaseHelper(this);
        singleton = Singleton.getInstance();

        Cursor res = DB.getmessages();
        ArrayList<String> receiversList = new ArrayList<>();
        if(res.getCount()!=0){
            while(res.moveToNext()){
                if (res.getString(0).equals(singleton.getValue()) || res.getString(1).equals(singleton.getValue())){
                    if (res.getString(0).equals(singleton.getValue())){
                        if (!receiversList.contains(res.getString(1))) {
                            receiversList.add(res.getString(1) + ":   " + res.getString(2));
                        }
                    }
                    else {
                        if (!receiversList.contains(res.getString(0))) {
                            receiversList.add(res.getString(0) + ":   " + res.getString(2));
                        }
                    }

                }
            }
        }

        rList = new String[receiversList.size()];
        rList = receiversList.toArray(rList);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, rList);

        listView.setAdapter(adapter);


        // Dodanie nasłuchiwacza na kliknięcia w kafelki
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Pobranie nazwy pacjenta z tablicy na podstawie pozycji kliknięcia
                String msg = rList[position];
                // Przygotowanie Intentu dla PatientDetailsActivity
                Intent intent = new Intent(MessagesActivity.this, MessageDetailsActivity.class);
                // Dodanie nazwy pacjenta jako dodatkowej informacji do Intentu
                intent.putExtra("msg", msg);
                // Rozpoczęcie nowej aktywności
                startActivity(intent);
            }
        });
    }

    public void sendMessage(View v) {
        Intent intent = new Intent(MessagesActivity.this ,
                SendMessageActivity.class);
        MessagesActivity.this.startActivity(intent);
    }

    public void backToMenu(View v) {
        Intent intent = new Intent(MessagesActivity.this ,
                MenuActivity.class);
        MessagesActivity.this.startActivity(intent);
    }
}