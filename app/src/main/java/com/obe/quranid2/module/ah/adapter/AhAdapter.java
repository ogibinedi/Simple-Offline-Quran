package com.obe.quranid2.module.ah.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.obe.quranid2.R;
import com.obe.quranid2.module.ah.model.AsmaulHusna;

import java.util.ArrayList;

public class AhAdapter extends RecyclerView.Adapter<AhViewHolder> {
    private final ArrayList<AsmaulHusna> asmaulHusnaArrayList;
    private final Context mContext;

    public AhAdapter(ArrayList<AsmaulHusna> asmaulHusnaArrayList, Context mContext) {
        this.asmaulHusnaArrayList = asmaulHusnaArrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public AhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_ah, parent, false);
        return new AhViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AhViewHolder holder, int position) {
        holder.setDataAh(asmaulHusnaArrayList.get(position));

        if (position %2 == 1){
            holder.cvAh.setCardBackgroundColor(mContext.getResources().getColor(R.color.white));
        }else {
            holder.cvAh.setCardBackgroundColor(mContext.getResources().getColor(R.color.orange_200));
        }
    }

    @Override
    public int getItemCount() {
        return asmaulHusnaArrayList.size();
    }
}
