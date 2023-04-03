package com.obe.quranid2.module.bulughulmaram.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.obe.quranid2.R;
import com.obe.quranid2.module.bulughulmaram.activities.BabActivity;
import com.obe.quranid2.module.bulughulmaram.model.BulughulMaram;

import java.util.List;

public class BmAdapter extends RecyclerView.Adapter<BmViewHolder> {
    private final List<BulughulMaram>listBm;
    private final Context mContext;

    public BmAdapter(List<BulughulMaram> listBm, Context mContext) {
        this.listBm = listBm;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public BmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title_list, parent, false);
        return new BmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BmViewHolder holder, int position) {
        holder.setDataBm(listBm.get(position));

        if (position %2 == 1){
            holder.llBm.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }else {
            holder.llBm.setBackgroundColor(mContext.getResources().getColor(R.color.orange_200));
        }

        holder.llBm.setOnClickListener(view -> {
            Intent i = new Intent(mContext, BabActivity.class);
            i.putExtra("number", String.valueOf(listBm.get(position).getNumberListBm()));
            i.putExtra("title", listBm.get(position).getTitleListBm());
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(i);
            Animatoo.animateSwipeRight(mContext);
        });
    }

    @Override
    public int getItemCount() {
        return listBm.size();
    }
}
