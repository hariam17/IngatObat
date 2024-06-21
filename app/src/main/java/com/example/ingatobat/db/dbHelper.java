package com.example.ingatobat.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ingatobat.model.Jadwal;
import com.example.ingatobat.model.Obat;
import com.example.ingatobat.model.Pasien;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class dbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "IngatObatNew.db";
    private static final int DATABASE_VERSION = 2; // Increment the version to force onUpgrade

    // Tabel users
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_NAMA_PEGAWAI = "nama_pegawai";
    public static final String COLUMN_USER_NIP = "nip";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_NO_TELP = "no_telp";
    public static final String COLUMN_USER_ROLE = "role";

    // Tabel pasien
    public static final String TABLE_PASIEN = "pasien";
    public static final String COLUMN_PASIEN_ID = "id";
    public static final String COLUMN_PASIEN_NAMA_PASIEN = "nama_pasien";
    public static final String COLUMN_PASIEN_USIA = "usia";
    public static final String COLUMN_PASIEN_JENIS_KELAMIN = "jenis_kelamin";
    public static final String COLUMN_PASIEN_RIWAYAT_PENYAKIT = "riwayat_penyakit";

    // Tabel obat
    public static final String TABLE_OBAT = "obat";
    public static final String COLUMN_OBAT_ID = "id";
    public static final String COLUMN_OBAT_NAMA_OBAT = "nama_obat";
    public static final String COLUMN_OBAT_DOSIS = "dosis";
    public static final String COLUMN_OBAT_DESKRIPSI_OBAT = "deskripsi_obat";

    // Tabel jadwal
    public static final String TABLE_JADWAL = "jadwal";
    public static final String COLUMN_JADWAL_ID = "id";
    public static final String COLUMN_JADWAL_PASIEN_ID = "pasien_id";
    public static final String COLUMN_JADWAL_OBAT_ID = "obat_id";
    public static final String COLUMN_JADWAL_JUMLAH_PEMAKAIAN = "jumlah_pemakaian";
    public static final String COLUMN_JADWAL_WAKTU_PEMAKAIAN = "waktu_pemakaian";

    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Membuat tabel users
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + " ("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USER_NAMA_PEGAWAI + " VARCHAR, "
                + COLUMN_USER_NIP + " VARCHAR, "
                + COLUMN_USER_PASSWORD + " VARCHAR, "
                + COLUMN_USER_NO_TELP + " VARCHAR, "
                + COLUMN_USER_ROLE + " VARCHAR)";
        db.execSQL(CREATE_USERS_TABLE);

        // Membuat tabel pasien
        String CREATE_PASIEN_TABLE = "CREATE TABLE " + TABLE_PASIEN + " ("
                + COLUMN_PASIEN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_PASIEN_NAMA_PASIEN + " VARCHAR, "
                + COLUMN_PASIEN_USIA + " INTEGER, "
                + COLUMN_PASIEN_JENIS_KELAMIN + " VARCHAR, "
                + COLUMN_PASIEN_RIWAYAT_PENYAKIT + " VARCHAR)";
        db.execSQL(CREATE_PASIEN_TABLE);

        // Membuat tabel obat
        String CREATE_OBAT_TABLE = "CREATE TABLE " + TABLE_OBAT + " ("
                + COLUMN_OBAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_OBAT_NAMA_OBAT + " VARCHAR, "
                + COLUMN_OBAT_DOSIS + " VARCHAR, "
                + COLUMN_OBAT_DESKRIPSI_OBAT + " TEXT)";
        db.execSQL(CREATE_OBAT_TABLE);

        // Membuat tabel jadwal
        String CREATE_JADWAL_TABLE = "CREATE TABLE " + TABLE_JADWAL + " ("
                + COLUMN_JADWAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_JADWAL_PASIEN_ID + " INTEGER, "
                + COLUMN_JADWAL_OBAT_ID + " INTEGER, "
                + COLUMN_JADWAL_JUMLAH_PEMAKAIAN + " INTEGER, "
                + COLUMN_JADWAL_WAKTU_PEMAKAIAN + " TIME, "
                + "FOREIGN KEY(" + COLUMN_JADWAL_PASIEN_ID + ") REFERENCES " + TABLE_PASIEN + "(" + COLUMN_PASIEN_ID + "), "
                + "FOREIGN KEY(" + COLUMN_JADWAL_OBAT_ID + ") REFERENCES " + TABLE_OBAT + "(" + COLUMN_OBAT_ID + "))";
        db.execSQL(CREATE_JADWAL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //OPERASI CRUD
    // Create: Menambahkan pasien baru
    public void addPasien(Pasien pasien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PASIEN_NAMA_PASIEN, pasien.getNamaPasien());
        values.put(COLUMN_PASIEN_USIA, pasien.getUsia());
        values.put(COLUMN_PASIEN_JENIS_KELAMIN, pasien.getJenisKelamin());
        values.put(COLUMN_PASIEN_RIWAYAT_PENYAKIT, pasien.getRiwayatPenyakit());
        db.insert(TABLE_PASIEN, null, values);
        db.close();
    }

    // Read: Mendapatkan pasien berdasarkan ID
    @SuppressLint("Range")
    public Pasien getPasienById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Pasien pasien = null;
        Cursor cursor = db.query(TABLE_PASIEN,
                new String[]{COLUMN_PASIEN_ID, COLUMN_PASIEN_NAMA_PASIEN, COLUMN_PASIEN_USIA, COLUMN_PASIEN_JENIS_KELAMIN, COLUMN_PASIEN_RIWAYAT_PENYAKIT},
                COLUMN_PASIEN_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null);

        try {
            if (cursor != null && cursor.moveToFirst()) {
                pasien = new Pasien();
                pasien.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_PASIEN_ID)));
                pasien.setNamaPasien(cursor.getString(cursor.getColumnIndex(COLUMN_PASIEN_NAMA_PASIEN)));
                pasien.setUsia(cursor.getInt(cursor.getColumnIndex(COLUMN_PASIEN_USIA)));
                pasien.setJenisKelamin(cursor.getString(cursor.getColumnIndex(COLUMN_PASIEN_JENIS_KELAMIN)));
                pasien.setRiwayatPenyakit(cursor.getString(cursor.getColumnIndex(COLUMN_PASIEN_RIWAYAT_PENYAKIT)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return pasien;
    }


    // Read: Mendapatkan semua pasien
    public List<Pasien> getAllPasien() {
        List<Pasien> pasienList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PASIEN;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Pasien pasien = new Pasien();
                pasien.setId(cursor.getInt(0));
                pasien.setNamaPasien(cursor.getString(1));
                pasien.setUsia(cursor.getInt(2));
                pasien.setJenisKelamin(cursor.getString(3));
                pasien.setRiwayatPenyakit(cursor.getString(4));
                pasienList.add(pasien);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return pasienList;
    }

    public List<String> getAllPasienNames() {
        List<String> pasienNames = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT nama_pasien FROM pasien", null);
        if (cursor.moveToFirst()) {
            do {
                pasienNames.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return pasienNames;
    }

    // Update: Memperbarui data pasien
    public int updatePasien(Pasien pasien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PASIEN_NAMA_PASIEN, pasien.getNamaPasien());
        values.put(COLUMN_PASIEN_USIA, pasien.getUsia());
        values.put(COLUMN_PASIEN_JENIS_KELAMIN, pasien.getJenisKelamin());
        values.put(COLUMN_PASIEN_RIWAYAT_PENYAKIT, pasien.getRiwayatPenyakit());
        return db.update(TABLE_PASIEN, values, COLUMN_PASIEN_ID + " = ?",
                new String[]{String.valueOf(pasien.getId())});
    }

    // Delete: Menghapus data pasien
    public void deletePasien(Pasien pasien) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            int rowsDeleted = db.delete(TABLE_PASIEN, COLUMN_PASIEN_ID + " = ?", new String[]{String.valueOf(pasien.getId())});
            if (rowsDeleted > 0) {
                Log.d("dbHelper", "Pasien dengan ID " + pasien.getId() + " berhasil dihapus.");
            } else {
                Log.d("dbHelper", "Pasien dengan ID " + pasien.getId() + " tidak ditemukan.");
            }
        } catch (Exception e) {
            Log.e("dbHelper", "Error deleting pasien", e);
        } finally {
            db.close();
        }
    }

    //Add : Menambah data jadwal
    public boolean addJadwal(int pasienId, int obatId, int jumlahPemakaian, String waktuPemakaian) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_JADWAL_PASIEN_ID, pasienId);
        values.put(COLUMN_JADWAL_OBAT_ID, obatId);
        values.put(COLUMN_JADWAL_JUMLAH_PEMAKAIAN, jumlahPemakaian);
        values.put(COLUMN_JADWAL_WAKTU_PEMAKAIAN, waktuPemakaian);

        long result = db.insert(TABLE_JADWAL, null, values);
        db.close();
        return result != -1;
    }

    //Mengambil semua data jadwal
    @SuppressLint("Range")
    public List<Jadwal> getAllJadwal() {
        List<Jadwal> jadwalList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_JADWAL;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Jadwal jadwal = new Jadwal();
                jadwal.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_JADWAL_ID)));
                jadwal.setPasienId(cursor.getInt(cursor.getColumnIndex(COLUMN_JADWAL_PASIEN_ID)));
                jadwal.setObatId(cursor.getInt(cursor.getColumnIndex(COLUMN_JADWAL_OBAT_ID)));
                jadwal.setJumlahPemakaian(cursor.getInt(cursor.getColumnIndex(COLUMN_JADWAL_JUMLAH_PEMAKAIAN)));
                jadwal.setWaktuPemakaian(Time.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_JADWAL_WAKTU_PEMAKAIAN))));
                jadwalList.add(jadwal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return jadwalList;
    }

    //UPDATE : Edit jadwal
    public boolean updateJadwal(int id, int pasienId, int obatId, int jumlahPemakaian, String waktuPemakaian) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_JADWAL_PASIEN_ID, pasienId);
        values.put(COLUMN_JADWAL_OBAT_ID, obatId);
        values.put(COLUMN_JADWAL_JUMLAH_PEMAKAIAN, jumlahPemakaian);
        values.put(COLUMN_JADWAL_WAKTU_PEMAKAIAN, waktuPemakaian);

        int result = db.update(TABLE_JADWAL, values, COLUMN_JADWAL_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }

    public boolean deleteJadwal(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_JADWAL, COLUMN_JADWAL_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }
}
