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
public class AddPatientActivity extends AppCompatActivity {
    EditText name, pesel;
    Button insert, delete, view, back;
    DatabaseHelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_patient);
        name = findViewById(R.id.username);
        pesel = findViewById(R.id.pesel);
        insert = findViewById(R.id.btnInsert);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        back = findViewById(R.id.btnBack);
        DB = new DatabaseHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String peselTXT = pesel.getText().toString();

                Boolean checkinsertdata = DB.insertpatient(peselTXT, nameTXT, 1);
                if(checkinsertdata==true)
                    Toast.makeText(AddPatientActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddPatientActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String peselTXT = pesel.getText().toString();
                Boolean checkudeletedata = DB.deletepatient(peselTXT);
                if(checkudeletedata==true)
                    Toast.makeText(AddPatientActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddPatientActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getpatient();
                if(res.getCount()==0){
                    Toast.makeText(AddPatientActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Imię i nazwisko :"+res.getString(1)+"\n");
                    buffer.append("Pesel :"+res.getString(0)+"\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(AddPatientActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPatientActivity.this ,
                        PatientsActivity.class);
                AddPatientActivity.this.startActivity(intent);
            }        });
    }}