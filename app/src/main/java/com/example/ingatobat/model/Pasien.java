package com.example.ingatobat.model;

public class Pasien {
    private int id;
    private String namaPasien;
    private int usia;
    private String jenisKelamin;
    private String riwayatPenyakit;

    // Default constructor
    public Pasien() {
    }

    // Constructor with parameters
    public Pasien(int id, String namaPasien, int usia, String jenisKelamin, String riwayatPenyakit) {
        this.id = id;
        this.namaPasien = namaPasien;
        this.usia = usia;
        this.jenisKelamin = jenisKelamin;
        this.riwayatPenyakit = riwayatPenyakit;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public int getUsia() {
        return usia;
    }

    public void setUsia(int usia) {
        this.usia = usia;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getRiwayatPenyakit() {
        return riwayatPenyakit;
    }

    public void setRiwayatPenyakit(String riwayatPenyakit) {
        this.riwayatPenyakit = riwayatPenyakit;
    }
}
