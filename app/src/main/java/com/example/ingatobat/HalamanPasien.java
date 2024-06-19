package com.example.ingatobat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ingatobat.adapter.PasienAdapter;
import com.example.ingatobat.model.Pasien;
import com.example.ingatobat.db.dbHelper;

import java.util.List;

public class HalamanPasien extends AppCompatActivity {

    private ImageButton buttonSearch;
    private RecyclerView recyclerViewPasien;
    private PasienAdapter pasienAdapter;
    private dbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_pasien);

        buttonSearch = findViewById(R.id.btn_search);
        recyclerViewPasien = findViewById(R.id.recyclerViewPasien);
        recyclerViewPasien.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new dbHelper(this);

        // Mengambil data pasien dari database
        List<Pasien> pasienList = dbHelper.getAllPasien();

        // Mengatur adapter untuk RecyclerView
        pasienAdapter = new PasienAdapter(this, pasienList);
        recyclerViewPasien.setAdapter(pasienAdapter);

        // Menambahkan listener ke ImageButton search
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menampilkan menu dropdown search
                showSearchDialog();
            }
        });

        // Temukan ImageButton Jadwal
        ImageButton homeIcon1 = findViewById(R.id.homeIcon);

        // Menambahkan listener onClick ke ImageButton homeIcon1
        homeIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menjalankan aktivitas MainActivity ketika ImageButton homeIcon1 diklik
                Intent intent = new Intent(HalamanPasien.this, HalamanJadwal.class);
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
                Intent intent = new Intent(HalamanPasien.this, HalamanObat.class);
                startActivity(intent);
            }
        });

        // Temukan ImageButton Add
        ImageButton homeIcon3 = findViewById(R.id.homeIcon3);

        // Tambahkan listener klik
        homeIcon3.setOnClickListener(v -> {
            // Buat Intent untuk memulai Activity TambahJadwal
            Intent intent = new Intent(HalamanPasien.this, TambahPasien.class);
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
                Intent intent = new Intent(HalamanPasien.this, HalamanPasien.class);
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
                Intent intent = new Intent(HalamanPasien.this, Profile.class);
                startActivity(intent);
            }
        });
    }

    public void onNameClicked(View view) {
        // Memeriksa apakah teks pada TextView sesuai dengan nama yang dicari
        TextView textView = (TextView) view;
        String clickedName = textView.getText().toString();
        String searchName = "Agung Krissanto";

        if (clickedName.equals(searchName)) {
            // Memulai aktivitas DetailPasien jika nama sesuai dengan yang dicari
            Intent intent = new Intent(this, DetailPasien.class);
            startActivity(intent);
        } else {
            // Menampilkan pesan jika nama tidak sesuai dengan yang dicari
            Toast.makeText(this, "Nama tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }

    private void showSearchDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Search");

        // Setup layout untuk dialog
        View view = getLayoutInflater().inflate(R.layout.dialog_search, null);
        builder.setView(view);

        EditText editTextSearch = view.findViewById(R.id.edit_text_search);

        builder.setPositiveButton("Search", (dialog, which) -> {
            // Lakukan sesuatu ketika tombol "Search" ditekan
            String searchText = editTextSearch.getText().toString();

            // Periksa apakah teks yang dimasukkan adalah "Agung Krissanto"
            if (searchText.equalsIgnoreCase("Agung Krissanto")) {
                // Memulai aktivitas DetailPasien jika nama sesuai dengan yang dicari
                Intent intent = new Intent(HalamanPasien.this, DetailPasien.class);
                startActivity(intent);
            } else {
                // Menampilkan pesan jika nama tidak sesuai dengan yang dicari
                Toast.makeText(HalamanPasien.this, "Nama tidak ditemukan atau coba tuliskan nama lengkapnya!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            // Tidak melakukan apa-apa saat tombol "Cancel" ditekan
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void performSearch(String searchText) {
        // Lakukan pencarian berdasarkan teks yang dimasukkan di sini
        Toast.makeText(this, "Searching for: " + searchText, Toast.LENGTH_SHORT).show();
    }
}