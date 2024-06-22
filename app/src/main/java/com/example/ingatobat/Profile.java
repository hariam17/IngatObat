package com.example.ingatobat;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.ingatobat.db.dbHelper;
import com.example.ingatobat.model.Users;

public class Profile extends AppCompatActivity {

    private TextView tvNamaLengkap, tvRoleUser, tvNip, tvNoTelepon;
    private dbHelper dbHelper;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        dbHelper = new dbHelper(this);
        sessionManager = SessionManager.getInstance(this);

        tvNamaLengkap = findViewById(R.id.tvNamaPegawai);
        tvRoleUser = findViewById(R.id.tvRoleUser);
        tvNip = findViewById(R.id.tvNip);
        tvNoTelepon = findViewById(R.id.tvNoTelepon);

        // Retrieve user data from SQLite using DbHelper
        Users user = sessionManager.getUser();

        if (user != null) {
            // Display user data
            tvNamaLengkap.setText(user.getNamaPegawai());
            tvRoleUser.setText(user.getRole());
            tvNip.setText(user.getNip());
            tvNoTelepon.setText(user.getNoTelp());
        }

        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(Profile.this, HalamanJadwal.class);
            startActivity(intent);
        });

        // Temukan ImageButton Jadwal
        ImageButton homeIcon1 = findViewById(R.id.homeIcon);

        // Menambahkan listener onClick ke ImageButton homeIcon1
        homeIcon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menjalankan aktivitas MainActivity ketika ImageButton homeIcon1 diklik
                Intent intent = new Intent(Profile.this, HalamanJadwal.class);
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
                Intent intent = new Intent(Profile.this, HalamanObat.class);
                startActivity(intent);
            }
        });

        // Temukan ImageButton Pasien
        ImageButton homeIcon4 = findViewById(R.id.homeIcon4);

        // Menambahkan listener onClick ke ImageButton homeIcon4
        homeIcon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menjalankan aktivitas MainActivity ketika ImageButton homeIcon4 diklik
                Intent intent = new Intent(Profile.this, HalamanPasien.class);
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
                Intent intent = new Intent(Profile.this, Profile.class);
                startActivity(intent);
            }
        });
    }



    public void goToLoginPage(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}

