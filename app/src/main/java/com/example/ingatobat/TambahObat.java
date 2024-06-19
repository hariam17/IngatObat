package com.example.ingatobat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class TambahObat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_obat);

        ImageButton back = findViewById(R.id.back);
        Button btnEditObat = findViewById(R.id.btn_tambah_obat);

        // Menambahkan listener onClick ke ImageButton ic_back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menjalankan aktivitas MainActivity ketika ImageButton backimg diklik
                Intent intent = new Intent(TambahObat.this, HalamanObat.class);
                startActivity(intent);
            }
        });

        // Menambahkan listener onClick ke button Edit Obat
        btnEditObat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate layout
                View dialogView = LayoutInflater.from(TambahObat.this)
                        .inflate(R.layout.activity_success_modal_tobat, null);

                // Buat AlertDialog tanpa judul dan tombol negatif
                AlertDialog.Builder builder = new AlertDialog.Builder(TambahObat.this);
                builder.setView(dialogView);

                // Ambil referensi tombol "Kembali ke Halaman Utama" dari dialogView
                Button btnHomePage = dialogView.findViewById(R.id.homepageTO);
                btnHomePage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Memulai aktivitas MainActivity
                        Intent intent = new Intent(TambahObat.this, HalamanObat.class);
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