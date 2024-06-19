package com.example.ingatobat;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

public class EditJadwal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_jadwal);

        ImageButton back = findViewById(R.id.back);
        Button btnEditJadwal = findViewById(R.id.submitEJ);

        // Menambahkan listener onClick ke ImageButton ic_back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menjalankan aktivitas MainActivity ketika ImageButton backimg diklik
                Intent intent = new Intent(EditJadwal.this, DetailJadwal.class);
                startActivity(intent);
            }
        });

        // Siapkan data
        String[] pasienArray = {"Hari Agung Merdeka", "Agung Krissanto", "Bima Putra Efendi", "Radityo Agraprana"};
        String[] obatArray = {"Panadol", "Excimer", "Oskadon"};

        // Buat ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pasienArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, obatArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Atur adapter ke Spinner
        Spinner spinner = findViewById(R.id.pasienSpinner);
        Spinner spinner2 = findViewById(R.id.obatSpinner);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter2);

        // Menambahkan listener onClick ke button Edit Pasien
        btnEditJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate layout
                View dialogView = LayoutInflater.from(EditJadwal.this)
                        .inflate(R.layout.activity_success_modal_ejadwal, null);

                // Buat AlertDialog tanpa judul dan tombol negatif
                AlertDialog.Builder builder = new AlertDialog.Builder(EditJadwal.this);
                builder.setView(dialogView);

                // Ambil referensi tombol "Kembali ke Halaman Utama" dari dialogView
                Button btnHomePage = dialogView.findViewById(R.id.homepageEJ);
                btnHomePage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Memulai aktivitas MainActivity
                        Intent intent = new Intent(EditJadwal.this, HalamanJadwal.class);
                        startActivity(intent);
                        // Menutup activity saat ini
                        finish();
                    }
                });

                // Tampilkan AlertDialog
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}