package com.example.menghitungberatbadanideal2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

import adapter.DataAdapter;
import db.DbHelper;
import model.Data;

public class History extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private ArrayList<Data> dataArrayList;
    private DbHelper dbHelper;

    private Button BtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //FIND VIEW
        BtnBack = findViewById(R.id.history_back);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new DataAdapter(this);

        dbHelper = new DbHelper(this);
        dataArrayList = dbHelper.readData();
        adapter.setListData(dataArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(History.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        BtnBack.setOnClickListener(v -> {
            Intent intenteditcancel = new Intent(History.this, MainActivity.class);
            startActivity(intenteditcancel);
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        dataArrayList = dbHelper.readData();
        adapter.setListData(dataArrayList);
        adapter.notifyDataSetChanged();
    }
}