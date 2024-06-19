package com.example.ingatobat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ingatobat.R;
import com.example.ingatobat.model.Pasien;

import java.util.List;

public class PasienFullAdapter extends RecyclerView.Adapter<PasienFullAdapter.PasienViewHolder> {

    private Context context;
    private List<Pasien> pasienList;

    public PasienFullAdapter(Context context, List<Pasien> pasienList) {
        this.context = context;
        this.pasienList = pasienList;
    }

    @NonNull
    @Override
    public PasienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_detail_pasien, parent, false);
        return new PasienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PasienViewHolder holder, int position) {
        Pasien pasien = pasienList.get(position);

        // Bind data to UI components
        if (holder.namaPasien != null) {
            holder.namaPasien.setText(pasien.getNamaPasien());
        }
        if (holder.jenisKelamin != null) {
            holder.jenisKelamin.setText(pasien.getJenisKelamin());
        }
        if (holder.riwayatPenyakit != null) {
            holder.riwayatPenyakit.setText(pasien.getRiwayatPenyakit());
        }
        if (holder.usia != null) {
            holder.usia.setText(String.valueOf(pasien.getUsia()));
        }
    }

    @Override
    public int getItemCount() {
        return pasienList.size();
    }

    public class PasienViewHolder extends RecyclerView.ViewHolder {

        TextView namaPasien, usia, jenisKelamin, riwayatPenyakit;

        public PasienViewHolder(@NonNull View itemView) {
            super(itemView);

            namaPasien = itemView.findViewById(R.id.viewNamaPasien);
            usia = itemView.findViewById(R.id.viewUsia);
            jenisKelamin = itemView.findViewById(R.id.jenis_kelamin);
            riwayatPenyakit = itemView.findViewById(R.id.riwayat_penyakit);
        }
    }
}
