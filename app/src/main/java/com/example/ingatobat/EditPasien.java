package com.example.ingatobat;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.ingatobat.db.dbHelper;
import com.example.ingatobat.model.Pasien;

public class EditPasien extends AppCompatActivity {

    private RadioButton radioButtonLaki, radioButtonPerempuan;
    private Button buttonEditPasien;
    private EditText editTextNama, editTextUsia, editTextRiwayatPenyakit;
    private dbHelper dbHelper;
    private int pasienId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pasien);

        // Initialize dbHelper
        dbHelper = new dbHelper(this);

        // Bind UI components
        radioButtonLaki = findViewById(R.id.laki);
        radioButtonPerempuan = findViewById(R.id.perempuan);
        buttonEditPasien = findViewById(R.id.btn_edit_pasien);
        editTextNama = findViewById(R.id.nama);
        editTextUsia = findViewById(R.id.edit_usia);
        editTextRiwayatPenyakit = findViewById(R.id.riwayat_penyakit);
        ImageButton backimg = findViewById(R.id.back);

        // Retrieve pasien ID from Intent
        pasienId = getIntent().getIntExtra("PASIEN_ID", -1);
        Log.d("EditPasien", "Pasien ID dari Intent: " + pasienId); // Log for debugging

        // Check if pasienId is valid
        if (pasienId == -1) {
            Toast.makeText(this, "ID pasien tidak ditemukan di intent", Toast.LENGTH_SHORT).show();
            finish(); // Close activity if no valid ID is found
            return;
        }

        // Display pasien data
        tampilkanDataPasien(pasienId);

        // Handle back button click
        backimg.setOnClickListener(v -> {
            // Navigate back to DetailPasien activity
            Intent intent = new Intent(EditPasien.this, DetailPasien.class);
            intent.putExtra("pasien_id", pasienId);
            startActivity(intent);
            finish(); // Close current activity
        });

        // Handle edit button click
        buttonEditPasien.setOnClickListener(v -> updatePasien());
    }

    // Method to display pasien data
    private void tampilkanDataPasien(int id) {
        Pasien pasien = dbHelper.getPasienById(id);
        if (pasien != null) {
            // Display pasien details
            editTextNama.setText(pasien.getNamaPasien());
            editTextUsia.setText(String.valueOf(pasien.getUsia()));
            if (pasien.getJenisKelamin().equalsIgnoreCase("Laki-laki")) {
                radioButtonLaki.setChecked(true);
            } else {
                radioButtonPerempuan.setChecked(true);
            }
            editTextRiwayatPenyakit.setText(pasien.getRiwayatPenyakit());
        } else {
            // Show a message and close the activity if the pasien is not found
            Toast.makeText(this, "Pasien tidak ditemukan di database", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    // Method to update pasien data
    private void updatePasien() {
        String nama = editTextNama.getText().toString().trim();
        String usiaStr = editTextUsia.getText().toString().trim();
        String jenisKelamin = radioButtonLaki.isChecked() ? "Laki-laki" : "Perempuan";
        String riwayatPenyakit = editTextRiwayatPenyakit.getText().toString().trim();

        // Validate input fields
        if (nama.isEmpty() || usiaStr.isEmpty() || riwayatPenyakit.isEmpty()) {
            Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        int usia;
        try {
            usia = Integer.parseInt(usiaStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Usia harus berupa angka yang valid", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Update pasien data
            Pasien pasien = new Pasien();
            pasien.setId(pasienId);
            pasien.setNamaPasien(nama);
            pasien.setUsia(usia);
            pasien.setJenisKelamin(jenisKelamin);
            pasien.setRiwayatPenyakit(riwayatPenyakit);
            dbHelper.updatePasien(pasien);

            // Show success dialog
            showSuccessDialog();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Gagal memperbarui data", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to show success dialog
    private void showSuccessDialog() {
        View dialogView = LayoutInflater.from(EditPasien.this)
                .inflate(R.layout.activity_success_modal_epasien, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(EditPasien.this);
        builder.setView(dialogView);

        Button btnHomePage = dialogView.findViewById(R.id.homepageEP);
        btnHomePage.setOnClickListener(v -> {
            Intent intent = new Intent(EditPasien.this, HalamanPasien.class);
            startActivity(intent);
            finish(); // Close current activity
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
