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

import android.content.Intent;
import com.example.ingatobat.DetailPasien;

public class PasienAdapter extends RecyclerView.Adapter<PasienAdapter.PasienViewHolder> {

    private Context context;
    private List<Pasien> pasienList;

    public PasienAdapter(Context context, List<Pasien> pasienList) {
        this.context = context;
        this.pasienList = pasienList;
    }

    @NonNull
    @Override
    public PasienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nama_pasien, parent, false);
        return new PasienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PasienViewHolder holder, int position) {
        Pasien pasien = pasienList.get(position);
        holder.textViewNamaPasien.setText(pasien.getNamaPasien());

        // Menangani klik pada nama pasien
        holder.textViewNamaPasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Memulai DetailPasienActivity ketika nama pasien diklik
                Intent intent = new Intent(context, DetailPasien.class);
                // Sisipkan data tambahan ke Intent jika diperlukan (misalnya ID pasien)
                intent.putExtra("pasien_id", pasien.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pasienList.size();
    }

    public class PasienViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNamaPasien;

        public PasienViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNamaPasien = itemView.findViewById(R.id.nama_pasien);
        }
    }
}



