package com.example.kulinerkita.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kulinerkita.Model.ModelKuliner;
import com.example.kulinerkita.R;

import java.util.List;

public class adapterKuliner extends RecyclerView.Adapter<adapterKuliner.VHkuliner>{
private Context ctx;
private List<ModelKuliner> listkuliner;

    public adapterKuliner(Context ctx, List<ModelKuliner> listkuliner) {
        this.ctx = ctx;
        this.listkuliner = listkuliner;
    }

    @NonNull
    @Override
    public adapterKuliner.VHkuliner onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varview= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new VHkuliner(varview);

    }

    @Override
    public void onBindViewHolder(@NonNull adapterKuliner.VHkuliner holder, int position) {
ModelKuliner mk=listkuliner.get(position);
        holder.tvid.setText(mk.getId());
        holder.tvnama.setText(mk.getNama());
        holder.tvasal.setText(mk.getAsal());
        holder.tvdeskripsisingkat.setText(mk.getDeskripsi_singkat());


    }

    @Override
    public int getItemCount() {
        return listkuliner.size();
    }
    public class VHkuliner extends RecyclerView.ViewHolder{
        TextView tvid,tvnama,tvasal,tvdeskripsisingkat;
        public VHkuliner(@NonNull View itemView) {
            super(itemView);

            tvid=  itemView.findViewById(R.id.tv_ID);
            tvnama=  itemView.findViewById(R.id.tv_nama);
            tvasal=  itemView.findViewById(R.id.tv_asal);
            tvdeskripsisingkat=  itemView.findViewById(R.id.tv_deskripsi_singkat);
        }
    }

}


