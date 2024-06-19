package com.example.ingatobat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

public class DetailObat extends AppCompatActivity {

    private ImageButton buttonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_obat);

        buttonSettings = findViewById(R.id.btn_settings);
        ImageButton back = findViewById(R.id.back);

        // Menambahkan listener onClick ke ImageButton back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menjalankan aktivitas MainActivity ketika ImageButton backimg diklik
                Intent intent = new Intent(DetailObat.this, HalamanObat.class);
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
                    Intent intent = new Intent(DetailObat.this, EditObat.class);
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