package com.obe.quranid2.module.hisnulmuslim.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.obe.quranid2.R;
import com.obe.quranid2.module.hisnulmuslim.model.Dua;

import java.util.ArrayList;

public class HmDetailAdapter extends RecyclerView.Adapter<HmDetailViewHolder> {
    private final ArrayList<Dua>duas;
    private final Context mContext;

    public HmDetailAdapter(ArrayList<Dua> duas, Context mContext) {
        this.duas = duas;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public HmDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hm_detail, parent, false);
        return new HmDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HmDetailViewHolder holder, int position) {
        holder.setDataHm(duas.get(position));

        if (position %2 == 1){
            holder.cvDhm.setCardBackgroundColor(mContext.getResources().getColor(R.color.orange_200));
        }else {
            holder.cvDhm.setCardBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return duas.size();
    }
}
