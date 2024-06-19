package com.example.ingatobat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HalamanJadwal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_jadwal);

        // Temukan ImageButton
        ImageButton arrowCard1 = findViewById(R.id.arrowCard1);

        // Tambahkan listener klik
        arrowCard1.setOnClickListener(v -> {
            // Buat Intent untuk memulai Activity DetailJadwal
            Intent intent = new Intent(HalamanJadwal.this, DetailJadwal.class);
            // Mulai Activity
            startActivity(intent);
        });

        // Temukan ImageButton Jadwal
        ImageButton homeIcon1 = findViewById(R.id.homeIcon);

        // Menambahkan listener onClick ke ImageButton homeIcon1
        homeIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menjalankan aktivitas MainActivity ketika ImageButton homeIcon1 diklik
                Intent intent = new Intent(HalamanJadwal.this, HalamanJadwal.class);
                startActivity(intent);
            }
        });

        // Temukan ImageButton Obat
        ImageButton homeIcon2 = findViewById(R.id.homeIcon2);

        // Menambahkan listener onClick ke ImageButton homeIcon2
        homeIcon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menjalankan aktivitas MainActivity ketika ImageButton homeIcon1 diklik
                Intent intent = new Intent(HalamanJadwal.this, HalamanObat.class);
                startActivity(intent);
            }
        });

        // Temukan ImageButton Add
        ImageButton homeIcon3 = findViewById(R.id.homeIcon3);

        // Tambahkan listener klik
        homeIcon3.setOnClickListener(v -> {
            // Buat Intent untuk memulai Activity TambahJadwal
            Intent intent = new Intent(HalamanJadwal.this, TambahJadwal.class);
            // Mulai Activity
            startActivity(intent);
        });

        // Temukan ImageButton Pasien
        ImageButton homeIcon4 = findViewById(R.id.homeIcon4);

        // Menambahkan listener onClick ke ImageButton homeIcon4
        homeIcon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menjalankan aktivitas MainActivity ketika ImageButton homeIcon4 diklik
                Intent intent = new Intent(HalamanJadwal.this, HalamanPasien.class);
                startActivity(intent);
            }
        });

        // Temukan ImageButton Profile
        ImageButton homeIcon5 = findViewById(R.id.homeIcon5);

        // Menambahkan listener onClick ke ImageButton homeIcon5
        homeIcon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menjalankan aktivitas MainActivity ketika ImageButton homeIcon5 diklik
                Intent intent = new Intent(HalamanJadwal.this, Profile.class);
                startActivity(intent);
            }
        });
    }

    public void onNameClicked(View view) {
        // Memulai aktivitas DetailPasien ketika nama pasien diklik
        Intent intent = new Intent(this, DetailJadwal.class);
        startActivity(intent);
    }
}