package com.example.ingatobat;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ingatobat.db.dbHelper;
import com.example.ingatobat.model.Pasien;

public class DetailPasien extends AppCompatActivity {

    private dbHelper dbHelper;
    private Pasien pasien;  // Ubah dari final ke variabel biasa

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pasien);

        ImageButton buttonSettings = findViewById(R.id.btn_settings);
        ImageButton backimg = findViewById(R.id.back);

        TextView textViewNamaPasien = findViewById(R.id.viewNamaPasien);
        TextView textViewUsia = findViewById(R.id.viewUsia);
        TextView textViewJenisKelamin = findViewById(R.id.jenis_kelamin);
        TextView textViewRiwayatPenyakit = findViewById(R.id.riwayat_penyakit);

        dbHelper = new dbHelper(this);

        // Dapatkan pasien_id dari Intent
        int pasienId = getIntent().getIntExtra("pasien_id", -1);

        // Ambil detail pasien dari database
        pasien = dbHelper.getPasienById(pasienId);

        // Tampilkan detail pasien
        if (pasien != null) {
            textViewNamaPasien.setText(pasien.getNamaPasien());
            textViewUsia.setText(String.valueOf(pasien.getUsia()));
            textViewJenisKelamin.setText(pasien.getJenisKelamin());
            textViewRiwayatPenyakit.setText(pasien.getRiwayatPenyakit());
        } else {
            // Tangani kasus di mana pasien tidak ditemukan
            textViewNamaPasien.setText("Data pasien tidak ditemukan");
            buttonSettings.setEnabled(false);
        }

        // Menambahkan listener onClick ke ImageButton back
        backimg.setOnClickListener(v -> {
            // Menjalankan aktivitas MainActivity ketika ImageButton backimg diklik
            Intent intent = new Intent(DetailPasien.this, HalamanPasien.class);
            startActivity(intent);
        });

        // Tambahkan onClickListener untuk image button settings
        buttonSettings.setOnClickListener(this::showPopupMenu);
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        // Tambahkan listener untuk menangani item yang dipilih
        popupMenu.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_edit) {
                if (pasien != null) {
                    Intent intent = new Intent(DetailPasien.this, EditPasien.class);
                    intent.putExtra("PASIEN_ID", pasien.getId()); // Ubah dari "pasien_id" ke "PASIEN_ID"
                    startActivity(intent);
                } else {
                    Toast.makeText(DetailPasien.this, "Pasien tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
                return true;
            } else if (item.getItemId() == R.id.menu_delete) {
                if (pasien != null) {
                    showDeleteConfirmationDialog();
                } else {
                    Toast.makeText(DetailPasien.this, "Pasien tidak ditemukan", Toast.LENGTH_SHORT).show();
                }
                return true;
            } else {
                return false;
            }
        });

        // Tampilkan popup menu
        popupMenu.show();
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Konfirmasi Penghapusan");
        builder.setMessage("Apakah Anda yakin ingin menghapus pasien ini?");
        builder.setPositiveButton("Ya", (dialog, which) -> deleteData());
        builder.setNegativeButton("Tidak", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteData() {
        if (pasien != null) {
            dbHelper.deletePasien(pasien);
            Toast.makeText(this, "Data pasien telah dihapus", Toast.LENGTH_SHORT).show();
            // Redirect ke halaman utama atau daftar pasien
            Intent intent = new Intent(DetailPasien.this, HalamanPasien.class);
            startActivity(intent);
            finish(); // Mengakhiri aktivitas saat ini
        } else {
            Toast.makeText(this, "Data pasien tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }
}
