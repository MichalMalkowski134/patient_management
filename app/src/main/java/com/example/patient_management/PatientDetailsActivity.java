package com.example.patient_management;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PatientDetailsActivity extends AppCompatActivity {
    DatabaseHelper DB;
    TextView patient_details_pesel, patient_details_nameSurname, patient_details_treatmentHistory;
    Button update, delete;
    Singleton singleton;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        patient_details_pesel = findViewById(R.id.patient_details_pesel);
        patient_details_nameSurname = findViewById(R.id.patient_details_nameSurname);
        patient_details_treatmentHistory = findViewById(R.id.patient_details_treatmentHistory);
        update = findViewById(R.id.update_button);
        delete = findViewById(R.id.delete_button);
        singleton = Singleton.getInstance();


        DB = new DatabaseHelper(this);

        // Pobranie nazwy pacjenta przekazanej z MainActivity
        String patientPesel = getIntent().getStringExtra("patientPesel");


        Cursor res = DB.getCurrentPatient(patientPesel);
        res.moveToNext();
        patient_details_pesel.setText(res.getString(0));
        patient_details_nameSurname.setText(res.getString(1));
        patient_details_treatmentHistory.setText(res.getString(2));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String patient_details_peselTXT = patient_details_pesel.getText().toString();
                String patient_details_nameSurnameTXT = patient_details_nameSurname.getText().toString();
                String patient_details_treatmentHistoryTXT = patient_details_treatmentHistory.getText().toString();
                Boolean checkinsertdata = DB.updatePatient(
                        patient_details_peselTXT, patient_details_nameSurnameTXT,
                        patient_details_treatmentHistoryTXT, singleton.getValue());
                if(checkinsertdata==true)
                    Toast.makeText(PatientDetailsActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(PatientDetailsActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean checkudeletedata = DB.deletePatient(patient_details_pesel.getText().toString());
                if(checkudeletedata==true)
                {
                    Intent intent = new Intent(PatientDetailsActivity.this ,
                            PatientsActivity.class);
                    PatientDetailsActivity.this.startActivity(intent);
                    Toast.makeText(PatientDetailsActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(PatientDetailsActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });
    }

    public void backToPatients(View v) {
        Intent intent = new Intent(PatientDetailsActivity.this ,
                PatientsActivity.class);
        PatientDetailsActivity.this.startActivity(intent);
    }
}
