package com.obe.quranid2.module.ruqya.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.obe.quranid2.R;
import com.obe.quranid2.module.ruqya.model.Ruqyah;

import java.util.ArrayList;

public class RuqyahAdapter extends RecyclerView.Adapter<RuqyahViewHolder> {
    private final ArrayList<Ruqyah> ruqyahs;
    private final Context mContext;

    public RuqyahAdapter(ArrayList<Ruqyah> ruqyahs, Context mContext) {
        this.ruqyahs = ruqyahs;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RuqyahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rq, parent, false);
        return new RuqyahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RuqyahViewHolder holder, int position) {
        holder.setDataRq(ruqyahs.get(position));

        if (position %2 == 1){
            holder.cvRq.setCardBackgroundColor(mContext.getResources().getColor(R.color.orange_200));
        }else {
            holder.cvRq.setCardBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return ruqyahs.size();
    }
}
