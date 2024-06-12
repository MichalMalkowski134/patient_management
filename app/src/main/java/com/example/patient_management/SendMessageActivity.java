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
public class SendMessageActivity extends AppCompatActivity {
    EditText to_, message;
    Button insert, back;
    DatabaseHelper DB;
    Singleton singleton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_message);
        to_ = findViewById(R.id.receiver);
        message = findViewById(R.id.messageText);
        insert = findViewById(R.id.btnInsert);
        back = findViewById(R.id.btnBack);
        DB = new DatabaseHelper(this);
        singleton = Singleton.getInstance();
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String to_TXT = to_.getText().toString();
                String messageTXT = message.getText().toString();

                Boolean checkinsertdata = DB.insertmessage(singleton.getValue(), to_TXT, messageTXT);
                if(checkinsertdata==true)
                    Toast.makeText(SendMessageActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(SendMessageActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SendMessageActivity.this ,
                        MessagesActivity.class);
                SendMessageActivity.this.startActivity(intent);
            }        });
    }}