package com.example.menghitungberatbadanideal2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import db.DbHelper;

public class Create extends AppCompatActivity {
    EditText create_name, create_bb, create_tinggi;
    TextView create_imt, create_ket;
    Button btnhitung, create_btnsimpan, create_btncancel;
    Double vbb, vtinggi, vimt ;
    String vket;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        //FIND VIEW
        create_name = findViewById(R.id.create_name);
        create_bb = findViewById(R.id.create_bb);
        create_tinggi = findViewById(R.id.create_tinggi);
        create_imt = findViewById(R.id.create_imt);
        create_ket = findViewById(R.id.create_ket);
        btnhitung = findViewById(R.id.btnhitungimt);
        create_btnsimpan = findViewById(R.id.create_btnsimpan);
        create_btncancel = findViewById(R.id.create_btncancel);

        dbHelper = new DbHelper(this);

        //BUTTON HITUNG


        //INSERT BUTTON CREATE
        create_btnsimpan.setOnClickListener(v -> {
            if (create_name.getText().toString().isEmpty()) {
                Toast.makeText(Create.this, "Error: Nama harus diisi!", Toast.LENGTH_SHORT).show();
            } else if (create_bb.getText().toString().isEmpty()) {
                Toast.makeText(Create.this, "Error: Berat badan harus diisi!", Toast.LENGTH_SHORT).show();
            } else if (create_tinggi.getText().toString().isEmpty()) {
                Toast.makeText(Create.this, "Error: Tinggi badan harus diisi!", Toast.LENGTH_SHORT).show();
            } else if (create_imt.getText().toString().isEmpty()) {
                Toast.makeText(Create.this, "Error: Anda harus menghitung IMT!", Toast.LENGTH_SHORT).show();
            } else {
                dbHelper.createData(create_name.getText().toString(),
                        create_bb.getInputType(), create_tinggi.getInputType(), create_imt.getInputType(),
                        create_ket.getText().toString());

                create_name.setText("");
                create_bb.setText("");
                create_tinggi.setText("");
                create_imt.setText("");
                create_ket.setText("");

                Toast.makeText(Create.this, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Create.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //INSERT BUTTON CANCEL
        create_btncancel.setOnClickListener(v -> {
            Intent intent = new Intent(Create.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void hitung(View view) {
        if (create_bb.getText().toString().isEmpty()) {
            Toast.makeText(Create.this, "Error: Berat badan harus diisi!", Toast.LENGTH_SHORT).show();
        } else if (create_tinggi.getText().toString().isEmpty()) {
            Toast.makeText(Create.this, "Error: Tinggi badan harus diisi!", Toast.LENGTH_SHORT).show();
        } else {
            vbb = Double.parseDouble(create_bb.getText().toString());
            vtinggi = Double.parseDouble(create_tinggi.getText().toString());

            vimt = vbb / ((vtinggi/100)*(vtinggi/100));

            if (vimt < 18.5){
                vket = "Berat Badan Kurang";
            }
            else if (vimt >= 18.5 && vimt <25){
                vket = "Berat Badan Ideal";
            }
            else if (vimt >= 25 && vimt <30){
                vket = "Berat Badan Berlebih";
            }
            else {
                vket = "Sangat Gemuk, Anda Harus Diet";
            }
            create_imt.setText(""+vimt);
            create_ket.setText(""+vket);
        }
    }
}