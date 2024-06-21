package com.example.ingatobat.model;

import java.sql.Time;

public class Jadwal {
    private int id;
    private int pasienId;
    private int obatId;
    private int jumlahPemakaian;
    private Time waktuPemakaian;

    public Jadwal() {
        this.id = id;
        this.pasienId = pasienId;
        this.obatId = obatId;
        this.jumlahPemakaian = jumlahPemakaian;
        this.waktuPemakaian = waktuPemakaian;
    }

    public int getId() {
        return id;
    }

    public int getPasienId() {
        return pasienId;
    }

    public int getObatId() {
        return obatId;
    }

    public int getJumlahPemakaian() {
        return jumlahPemakaian;
    }

    public Time getWaktuPemakaian() {
        return waktuPemakaian;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPasienId(int pasienId) {
        this.pasienId = pasienId;
    }

    public void setObatId(int obatId) {
        this.obatId = obatId;
    }

    public void setJumlahPemakaian(int jumlahPemakaian) {
        this.jumlahPemakaian = jumlahPemakaian;
    }

    public void setWaktuPemakaian(Time waktuPemakaian) {
        this.waktuPemakaian = waktuPemakaian;
    }
}

