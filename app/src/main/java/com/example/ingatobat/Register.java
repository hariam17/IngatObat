package com.example.ingatobat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    // Metode untuk pindah ke halaman login saat tombol diklik
    public void goToLoginPage(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}