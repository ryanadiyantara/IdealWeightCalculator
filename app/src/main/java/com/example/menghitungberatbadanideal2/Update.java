package com.example.menghitungberatbadanideal2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import db.DbHelper;
import model.Data;

public class Update extends AppCompatActivity {

    EditText edit_name;
    TextView edit_bb, edit_tinggi, edit_imt, edit_ket;
    Button editbtncancel, editbtndelete, editbtnsimpan;
    private Data data;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        dbHelper = new DbHelper(this);

        //FIND VIEW
        edit_name = findViewById(R.id.edit_name);
        edit_bb = findViewById(R.id.edit_bb);
        edit_tinggi = findViewById(R.id.edit_tinggi);
        edit_imt = findViewById(R.id.edit_imt);
        edit_ket = findViewById(R.id.edit_ket);

        editbtncancel = findViewById(R.id.edit_btncancel);
        editbtndelete = findViewById(R.id.edit_btndelete);
        editbtnsimpan = findViewById(R.id.edit_btnsimpan);

        //SET
        Intent intent = getIntent();
        data = (Data) intent.getSerializableExtra("user");

        edit_name.setText(data.getData_name());
        edit_bb.setText(""+ data.getData_bb());
        edit_tinggi.setText(""+ data.getData_tinggi());
        edit_imt.setText(""+ data.getData_IMT());
        edit_ket.setText(data.getData_ket());

        editbtncancel.setOnClickListener(v -> {
            Intent intentbtncancel = new Intent(Update.this, History.class);
            startActivity(intentbtncancel);
        });

        editbtndelete.setOnClickListener((View v)-> {
            dbHelper.deleteData(data.getData_id());
            Toast.makeText(Update.this, "Delete Sukses!", Toast.LENGTH_SHORT).show();
            Intent intenteditdelete = new Intent(Update.this, History.class);
            startActivity(intenteditdelete);
        });

        editbtnsimpan.setOnClickListener((View v) -> {
            if (edit_name.getText().toString().isEmpty()) {
                Toast.makeText(Update.this, "Error: Nama harus diisi!", Toast.LENGTH_SHORT).show();
            } else {
                dbHelper.updateData(data.getData_id(), edit_name.getText().toString(),
                        edit_bb.getInputType(), edit_tinggi.getInputType(), edit_imt.getInputType(),
                        edit_ket.getText().toString());

                edit_name.setText("");
                edit_bb.setText("");
                edit_tinggi.setText("");
                edit_imt.setText("");
                edit_ket.setText("");

                Toast.makeText(Update.this, "Update Sukses!", Toast.LENGTH_SHORT).show();
                Intent intentupdate = new Intent(Update.this, History.class);
                startActivity(intentupdate);
            }
        });
    }
}