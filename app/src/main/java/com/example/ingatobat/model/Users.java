package com.example.ingatobat.model;

public class Users {
    private int id;
    private String namaPegawai;
    private String nip;
    private String password;
    private String noTelp;
    private String role;

    public Users(int id, String namaPegawai, String nip, String password, String noTelp, String role) {
        this.id = id;
        this.namaPegawai = namaPegawai;
        this.nip = nip;
        this.password = password;
        this.noTelp = noTelp;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public String getNip() {
        return nip;
    }

    public String getPassword() {
        return password;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

