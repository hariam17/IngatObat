package com.example.ingatobat.model;

public class Obat {
    private int id;
    private String namaObat;
    private String dosis;
    private String deskripsiObat;

    public Obat(int id, String namaObat, String dosis,  String deskripsiObat) {
        this.id = id;
        this.namaObat = namaObat;
        this.dosis = dosis;
        this.deskripsiObat = deskripsiObat;
    }

    public int getId() {
        return id;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public String getDosis() {
        return dosis;
    }

    public String getDeskripsiObat() {
        return deskripsiObat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public void setDeskripsiObat(String deskripsiObat) {
        this.deskripsiObat = deskripsiObat;
    }
}

