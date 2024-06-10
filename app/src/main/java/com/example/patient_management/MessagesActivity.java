package com.example.patient_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MessagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages);
    }

    public void backToMenu(View v) {
        Intent intent = new Intent(MessagesActivity.this ,
                MenuActivity.class);
        MessagesActivity.this.startActivity(intent);
    }
}