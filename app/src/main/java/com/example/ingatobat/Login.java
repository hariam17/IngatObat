package com.example.ingatobat;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ingatobat.db.dbHelper;
import com.example.ingatobat.model.Users;
public class Login extends AppCompatActivity {

    private EditText editTextNip, editTextPassword;
    private Button buttonLogin;
    private dbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new dbHelper(this);

        editTextNip = findViewById(R.id.nip);
        editTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.btn_login);

        buttonLogin.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        String nip = editTextNip.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Validate inputs
        if (nip.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "NIP dan password harus diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check user in database
        Users user = dbHelper.getUser(nip, password);
        if (user != null) {
            // Successful login
            Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show();

            // Save user data to SharedPreferences (optional)
            SessionManager sessionManager = SessionManager.getInstance(this);
            sessionManager.saveUser(user);

            Intent intent = new Intent(Login.this, HalamanJadwal.class);
            startActivity(intent);
            finish(); // Close login activity
        } else {
            // Failed login
            Toast.makeText(this, "NIP atau password salah", Toast.LENGTH_SHORT).show();
        }
    }
}

