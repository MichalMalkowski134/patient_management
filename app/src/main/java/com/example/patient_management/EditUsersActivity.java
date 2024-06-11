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
public class EditUsersActivity extends AppCompatActivity {
    EditText username, password, name;
    Button insert, delete, view, back;
    DatabaseHelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_users);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        insert = findViewById(R.id.btnInsert);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        back = findViewById(R.id.btnBack);
        DB = new DatabaseHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameTXT = username.getText().toString();
                String passwordTXT = password.getText().toString();
                String nameTXT = name.getText().toString();

                DB.insertdoctor(nameTXT, "", 0);
                Cursor res = DB.getlastdoctor();
                res.moveToNext();
                Boolean checkinsertdata = DB.insertuser(usernameTXT, passwordTXT, res.getString(0));
                if(checkinsertdata==true)
                {
                    Toast.makeText(EditUsersActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(EditUsersActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameTXT = username.getText().toString();
                Boolean checkudeletedata = DB.deleteuser(usernameTXT);
                if(checkudeletedata==true)
                    Toast.makeText(EditUsersActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(EditUsersActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getuser();
                if(res.getCount()==0){
                    Toast.makeText(EditUsersActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Nazwa użytkownika :"+res.getString(0)+"\n");
                    buffer.append("Lekarz :"+res.getString(2)+"\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(EditUsersActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditUsersActivity.this ,
                        MainActivity.class);
                EditUsersActivity.this.startActivity(intent);
            }        });
    }}













