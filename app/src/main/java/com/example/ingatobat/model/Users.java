package com.example.ingatobat.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Users implements Parcelable {
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

    protected Users(Parcel in) {
        id = in.readInt();
        namaPegawai = in.readString();
        nip = in.readString();
        password = in.readString();
        noTelp = in.readString();
        role = in.readString();
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };

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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(namaPegawai);
        dest.writeString(nip);
        dest.writeString(password);
        dest.writeString(noTelp);
        dest.writeString(role);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}


