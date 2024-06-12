package com.example.patient_management;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    TextView editNameSurname, editSpecialization, editEmail, editPhone;
    Button update, delete;
    Singleton singleton;
    DatabaseHelper DB;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        editNameSurname = findViewById(R.id.editNameSurname);
        editSpecialization = findViewById(R.id.editSpecialization);
        editEmail = findViewById(R.id.editEmail);
        editPhone = findViewById(R.id.editPhone);
        update = findViewById(R.id.update_button);
        delete = findViewById(R.id.delete_button);


        singleton = Singleton.getInstance();
        DB = new DatabaseHelper(this);

        Cursor res = DB.getUser(singleton.getValue());
        res.moveToNext();
        editNameSurname.setText(res.getString(2));
        editSpecialization.setText(res.getString(3));
        editEmail.setText(res.getString(4));
        editPhone.setText(res.getString(5));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editNameSurnameTXT = editNameSurname.getText().toString();
                String editSpecializationTXT = editSpecialization.getText().toString();
                String editEmailTXT = editEmail.getText().toString();
                String editPhoneTXT = editPhone.getText().toString();
                Boolean checkinsertdata = DB.updateUser(singleton.getValue(),
                        editNameSurnameTXT, editSpecializationTXT,
                        editEmailTXT, editPhoneTXT);
                if(checkinsertdata==true)
                    Toast.makeText(SettingsActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(SettingsActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean checkudeletedata = DB.deleteUser(singleton.getValue());
                if(checkudeletedata==true)
                {
                    Intent intent = new Intent(SettingsActivity.this ,
                            MainActivity.class);
                    SettingsActivity.this.startActivity(intent);
                    Toast.makeText(SettingsActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(SettingsActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });

    }


//    public void update(View v) {
//        Intent intent = new Intent(SettingsActivity.this ,
//                UpdateSettingsActivity.class);
//        SettingsActivity.this.startActivity(intent);
//    }

    public void backToMenu(View v) {
        Intent intent = new Intent(SettingsActivity.this ,
                MenuActivity.class);
        SettingsActivity.this.startActivity(intent);
    }

    public void backToLoginPage(View v) {
        Intent intent = new Intent(SettingsActivity.this ,
                MainActivity.class);
        SettingsActivity.this.startActivity(intent);
    }
}