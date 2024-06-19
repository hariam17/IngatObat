package com.example.ingatobat;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.ingatobat.db.dbHelper;
import com.example.ingatobat.model.Pasien;

public class TambahPasien extends AppCompatActivity {

    private RadioButton radioButtonLaki, radioButtonPerempuan;
    private Button buttonTambahPasien;
    private EditText editTextNama, editTextUsia, editTextRiwayatPenyakit;
    private dbHelper dbHelper;
    private AlertDialog successDialog; // Variabel instance untuk AlertDialog

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pasien);

        // Inisialisasi DBHelper
        dbHelper = new dbHelper(this);

        radioButtonLaki = findViewById(R.id.laki);
        radioButtonPerempuan = findViewById(R.id.perempuan);
        buttonTambahPasien = findViewById(R.id.btn_tambah_pasien);
        editTextNama = findViewById(R.id.nama);
        editTextUsia = findViewById(R.id.edit_usia);
        editTextRiwayatPenyakit = findViewById(R.id.riwayat_penyakit);
        ImageButton backimg = findViewById(R.id.back);

        // Menambahkan listener ke RadioButton
        radioButtonLaki.setOnClickListener(v -> {
            if (radioButtonLaki.isChecked()) {
                Toast.makeText(getApplicationContext(), "Laki-laki dipilih", Toast.LENGTH_SHORT).show();
            }
        });

        radioButtonPerempuan.setOnClickListener(v -> {
            if (radioButtonPerempuan.isChecked()) {
                Toast.makeText(getApplicationContext(), "Perempuan dipilih", Toast.LENGTH_SHORT).show();
            }
        });

        // Menambahkan listener onClick ke ImageButton ic_back
        backimg.setOnClickListener(v -> {
            Intent intent = new Intent(TambahPasien.this, HalamanObat.class);
            startActivity(intent);
        });

        // Menambahkan listener onClick ke button Tambah Pasien
        buttonTambahPasien.setOnClickListener(v -> tambahPasien());
    }

    private void tambahPasien() {
        String nama = editTextNama.getText().toString().trim();
        String usiaStr = editTextUsia.getText().toString().trim();
        String jenisKelamin = radioButtonLaki.isChecked() ? "Laki-laki" : "Perempuan";
        String riwayatPenyakit = editTextRiwayatPenyakit.getText().toString().trim();

        // Validasi input
        if (nama.isEmpty() || usiaStr.isEmpty() || riwayatPenyakit.isEmpty()) {
            Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        int usia;
        try {
            usia = Integer.parseInt(usiaStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Usia harus berupa angka", Toast.LENGTH_SHORT).show();
            return;
        }

        // Simpan data ke database
        Pasien pasien = new Pasien();
        pasien.setNamaPasien(nama);
        pasien.setUsia(usia);
        pasien.setJenisKelamin(jenisKelamin);
        pasien.setRiwayatPenyakit(riwayatPenyakit);
        dbHelper.addPasien(pasien);

        // Tampilkan dialog sukses
        showSuccessDialog();
    }

    private void showSuccessDialog() {
        // Pastikan dialog tidak bocor (WindowLeaked) dengan menutup dialog sebelum memulai aktivitas baru
        if (successDialog != null && successDialog.isShowing()) {
            successDialog.dismiss();
        }

        // Inflate layout
        View dialogView = LayoutInflater.from(TambahPasien.this)
                .inflate(R.layout.activity_success_modal_tpasien, null);

        // Buat AlertDialog tanpa judul dan tombol negatif
        AlertDialog.Builder builder = new AlertDialog.Builder(TambahPasien.this);
        builder.setView(dialogView);

        // Ambil referensi tombol "Kembali ke Halaman Utama" dari dialogView
        Button btnHomePage = dialogView.findViewById(R.id.homepageTP);
        btnHomePage.setOnClickListener(v -> {
            // Memulai aktivitas HalamanPasien
            Intent intent = new Intent(TambahPasien.this, HalamanPasien.class);
            startActivity(intent);
            // Menutup activity saat ini
            finish();
        });

        // Tampilkan AlertDialog
        successDialog = builder.create();
        successDialog.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Pastikan dialog ditutup saat aktivitas sedang di-background
        if (successDialog != null && successDialog.isShowing()) {
            successDialog.dismiss();
        }
    }
}
