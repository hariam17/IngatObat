package com.example.ingatobat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SuccessModalEJadwal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_modal_ejadwal);

        Button btnHomePage = findViewById(R.id.homepageEJ);

        // Menambahkan listener onClick ke tombol "Kembali ke Halaman Utama"
        btnHomePage.setOnClickListener(v -> {
            // Memulai aktivitas MainActivity
            Intent intent = new Intent(SuccessModalEJadwal.this, HalamanJadwal.class);
            startActivity(intent);
            // Menutup activity saat ini
            finish();
        });
    }
}