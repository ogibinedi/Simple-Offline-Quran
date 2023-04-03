package com.obe.quranid2.module.hisnulmuslim.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.obe.quranid2.R;
import com.obe.quranid2.module.hisnulmuslim.activities.HmDetailActivity;
import com.obe.quranid2.module.hisnulmuslim.model.HisnulMuslim;

import java.util.ArrayList;

public class HmAdapter extends RecyclerView.Adapter<HmViewHolder> {
    private final ArrayList<HisnulMuslim> hisnulMuslims;
    private final Context mContext;

    public HmAdapter(ArrayList<HisnulMuslim> hisnulMuslims, Context mContext) {
        this.hisnulMuslims = hisnulMuslims;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public HmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hisnu_muslim, parent, false);
        return new HmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HmViewHolder holder, int position) {
        holder.setHmData(hisnulMuslims.get(position));

        holder.layout.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, HmDetailActivity.class);
            intent.putExtra("number", hisnulMuslims.get(position).getShortNumber());
            intent.putExtra("title", hisnulMuslims.get(position).getTitle());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
            Animatoo.animateSwipeRight(mContext);
        });
    }

    @Override
    public int getItemCount() {
        return hisnulMuslims.size();
    }
}
