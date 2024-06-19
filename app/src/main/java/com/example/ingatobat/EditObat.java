package com.example.ingatobat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class EditObat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_obat);

        ImageButton back = findViewById(R.id.back);
        Button btnEditObat = findViewById(R.id.btn_edit_obat);

        // Menambahkan listener onClick ke ImageButton ic_back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menjalankan aktivitas MainActivity ketika ImageButton backimg diklik
                Intent intent = new Intent(EditObat.this, DetailObat.class);
                startActivity(intent);
            }
        });

        // Menambahkan listener onClick ke button Edit Obat
        btnEditObat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate layout
                View dialogView = LayoutInflater.from(EditObat.this)
                        .inflate(R.layout.activity_success_modal_eobat, null);

                // Buat AlertDialog tanpa judul dan tombol negatif
                AlertDialog.Builder builder = new AlertDialog.Builder(EditObat.this);
                builder.setView(dialogView);

                // Ambil referensi tombol "Kembali ke Halaman Utama" dari dialogView
                Button btnHomePage = dialogView.findViewById(R.id.homepageEO);
                btnHomePage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Memulai aktivitas MainActivity
                        Intent intent = new Intent(EditObat.this, HalamanObat.class);
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