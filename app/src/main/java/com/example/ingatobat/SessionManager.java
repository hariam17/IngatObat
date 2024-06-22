package com.example.ingatobat;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.ingatobat.model.Users;

public class SessionManager {
    private static final String SHARED_PREF_NAME = "user_prefs";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_NAMA_PEGAWAI = "nama_pegawai";
    private static final String KEY_NIP = "nip";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_NO_TELP = "no_telp";
    private static final String KEY_ROLE = "role";

    private static SessionManager instance;
    private SharedPreferences sharedPreferences;

    private SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized SessionManager getInstance(Context context) {
        if (instance == null) {
            instance = new SessionManager(context);
        }
        return instance;
    }

    public void saveUser(Users user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER_ID, user.getId());
        editor.putString(KEY_NAMA_PEGAWAI, user.getNamaPegawai());
        editor.putString(KEY_NIP, user.getNip());
        editor.putString(KEY_PASSWORD, user.getPassword());
        editor.putString(KEY_NO_TELP, user.getNoTelp());
        editor.putString(KEY_ROLE, user.getRole());
        editor.apply();
    }

    public Users getUser() {
        int id = sharedPreferences.getInt(KEY_USER_ID, -1);
        String namaPegawai = sharedPreferences.getString(KEY_NAMA_PEGAWAI, "");
        String nip = sharedPreferences.getString(KEY_NIP, "");
        String password = sharedPreferences.getString(KEY_PASSWORD, "");
        String noTelp = sharedPreferences.getString(KEY_NO_TELP, "");
        String role = sharedPreferences.getString(KEY_ROLE, "");

        if (id != -1 && !namaPegawai.isEmpty()) {
            return new Users(id, namaPegawai, nip, password, noTelp, role);
        }
        return null;
    }

    public void clearSession() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}

