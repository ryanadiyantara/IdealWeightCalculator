package com.example.menghitungberatbadanideal2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button BtnCreate, BtnHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FIND VIEW
        BtnCreate = findViewById(R.id.MAIN_CREATE);
        BtnHistory = findViewById(R.id.MAIN_HISTORY);

        BtnCreate.setOnClickListener(v -> {
            Intent intenteditcancel = new Intent(MainActivity.this, Create.class);
            startActivity(intenteditcancel);
        });
        BtnHistory.setOnClickListener(v -> {
            Intent intenteditcancel = new Intent(MainActivity.this, History.class);
            startActivity(intenteditcancel);
        });

    }
}