package com.example.patient_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;




public class PatientsActivity extends AppCompatActivity {

    private String[] patients = {"arnold", "jan", "marcin", "kuba"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patients);

        ListView listView = findViewById(R.id.list_view);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, patients);

        listView.setAdapter(adapter);

        // Dodanie nasłuchiwacza na kliknięcia w kafelki
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Pobranie nazwy pacjenta z tablicy na podstawie pozycji kliknięcia
                String patientName = patients[position];
                // Przygotowanie Intentu dla PatientDetailsActivity
                Intent intent = new Intent(PatientsActivity.this, PatientDetailsActivity.class);
                // Dodanie nazwy pacjenta jako dodatkowej informacji do Intentu
                intent.putExtra("patientName", patientName);
                // Rozpoczęcie nowej aktywności
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