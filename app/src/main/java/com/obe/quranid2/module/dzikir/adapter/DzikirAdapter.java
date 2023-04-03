package com.obe.quranid2.module.dzikir.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.obe.quranid2.R;
import com.obe.quranid2.module.dzikir.model.Dzikir;

import java.util.List;

public class DzikirAdapter extends RecyclerView.Adapter<DzikirViewHolder> {
    private final List<Dzikir> dzikirs;
    Context mContext;

    public DzikirAdapter(List<Dzikir> dzikirs, Context mContext) {
        this.dzikirs = dzikirs;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public DzikirViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dzikir, parent, false);

        return new DzikirViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DzikirViewHolder holder, int position) {
        holder.setDzikirData(dzikirs.get(position));

        if (position %2 == 1){
            holder.cvD.setCardBackgroundColor(mContext.getResources().getColor(R.color.orange_200));
        }else {
            holder.cvD.setCardBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return dzikirs.size();
    }
}
