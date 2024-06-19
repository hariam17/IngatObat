package com.example.ingatobat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ingatobat.db.dbHelper;
import com.example.ingatobat.model.Pasien;

public class DetailPasien extends AppCompatActivity {

    private ImageButton buttonSettings;
    private TextView textViewNamaPasien;
    private TextView textViewUsia;
    private TextView textViewJenisKelamin;
    private TextView textViewRiwayatPenyakit;

    private dbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pasien);

        buttonSettings = findViewById(R.id.btn_settings);
        ImageButton backimg = findViewById(R.id.back);

        textViewNamaPasien = findViewById(R.id.viewNamaPasien);
        textViewUsia = findViewById(R.id.viewUsia);
        textViewJenisKelamin = findViewById(R.id.jenis_kelamin);
        textViewRiwayatPenyakit = findViewById(R.id.riwayat_penyakit);

        dbHelper = new dbHelper(this);

        // Get pasien_id from Intent
        int pasienId = getIntent().getIntExtra("pasien_id", -1);

        // Retrieve pasien details from database
        Pasien pasien = dbHelper.getPasienById(pasienId);

        // Display pasien details
        if (pasien != null) {
            textViewNamaPasien.setText(pasien.getNamaPasien());
            textViewUsia.setText(String.valueOf(pasien.getUsia()));
            textViewJenisKelamin.setText(pasien.getJenisKelamin());
            textViewRiwayatPenyakit.setText(pasien.getRiwayatPenyakit());
        } else {
            // Handle case where pasien is not found
            textViewNamaPasien.setText("Data pasien tidak ditemukan");
        }

        // Menambahkan listener onClick ke ImageButton back
        backimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menjalankan aktivitas MainActivity ketika ImageButton backimg diklik
                Intent intent = new Intent(DetailPasien.this, HalamanPasien.class);
                startActivity(intent);
            }
        });

        // Tambahkan onClickListener untuk image button settings
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Munculkan popup menu
                showPopupMenu(v);
            }
        });
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        // Tambahkan listener untuk menangani item yang dipilih
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_edit) {
                    // Memulai EditActivity ketika opsi edit dipilih
                    Intent intent = new Intent(DetailPasien.this, EditPasien.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.menu_delete) {
                    // Lakukan sesuatu ketika opsi delete dipilih
                    deleteData();
                    return true;
                } else {
                    return false;
                }
            }
        });

        // Tampilkan popup menu
        popupMenu.show();
    }

    private void deleteData() {
        // Implementasi fungsi untuk menghapus data
        Toast.makeText(this, "Delete data", Toast.LENGTH_SHORT).show();
    }
}
