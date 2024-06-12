package com.example.patient_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView;
import android.database.Cursor;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PatientsActivity extends AppCompatActivity {
    Singleton singleton;
    DatabaseHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patients);

        DB = new DatabaseHelper(this);
        singleton = Singleton.getInstance();
        Cursor res = DB.getPatient();
        if (res.getCount() == 0) {
            Toast.makeText(PatientsActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }

        List<String> patientsList = new ArrayList<>();
        Map<String, String> patientMap = new HashMap<>();
        while (res.moveToNext()) {
            String pesel0 = res.getString(0); // Assuming PESEL is in the first column
            String name1 = res.getString(1);  // Assuming name is in the second column
            String displayText = pesel0 + " --- " + name1;

            patientsList.add(displayText);
            patientMap.put(displayText, pesel0);
        }

        String[] patientArray = patientsList.toArray(new String[0]);

        ListView listView = findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, patientArray);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedText = patientArray[position];
                String patientPesel = patientMap.get(selectedText);

                Intent intent = new Intent(PatientsActivity.this, PatientDetailsActivity.class);
                intent.putExtra("patientPesel", patientPesel);
                startActivity(intent);
            }
        });
    }


    public void addPatient(View v) {
        Intent intent = new Intent(PatientsActivity.this ,
                AddPatientActivity.class);
        PatientsActivity.this.startActivity(intent);
    }

    public void backToMenu(View v) {
        Intent intent = new Intent(PatientsActivity.this ,
                MenuActivity.class);
        PatientsActivity.this.startActivity(intent);
    }
}