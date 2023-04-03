package com.obe.quranid2.module.dzikir.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.obe.quranid2.R;
import com.obe.quranid2.module.dzikir.model.DzikirSholat;

import java.util.ArrayList;

public class DzikirSholatAdapter extends RecyclerView.Adapter<DzikirSholatViewHolder> {
    private final ArrayList<DzikirSholat> sholats;
    Context mContext;

    public DzikirSholatAdapter(ArrayList<DzikirSholat> sholats, Context mContext) {
        this.sholats = sholats;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public DzikirSholatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dzikir_setelah_sholat, parent, false);
        return new DzikirSholatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DzikirSholatViewHolder holder, int position) {
        holder.setDzikirShalat(sholats.get(position));

        if (position %2 == 1){
            holder.cvDzikirSholat.setCardBackgroundColor(mContext.getResources().getColor(R.color.orange_200));
        }else {
            holder.cvDzikirSholat.setCardBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return sholats.size();
    }
}
