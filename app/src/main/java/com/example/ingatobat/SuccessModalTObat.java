package com.example.ingatobat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuccessModalTObat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_modal_tobat);

        Button btnHomePage = findViewById(R.id.homepageTO);

        // Menambahkan listener onClick ke tombol "Kembali ke Halaman Utama"
        btnHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Memulai aktivitas MainActivity
                Intent intent = new Intent(SuccessModalTObat.this, HalamanJadwal.class);
                startActivity(intent);
                // Menutup activity saat ini
                finish();
            }
        });
    }
}