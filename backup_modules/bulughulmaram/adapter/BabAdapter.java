package com.obe.quranid2.module.bulughulmaram.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.obe.quranid2.R;
import com.obe.quranid2.module.bulughulmaram.activities.SubabActivity;
import com.obe.quranid2.module.bulughulmaram.model.Bab;

import java.util.List;

public class BabAdapter extends RecyclerView.Adapter<BabAdapter.BabViewHolder> {
    private final List<Bab> babList;
    private final Context mContext;

    public BabAdapter(List<Bab> babList, Context mContext) {
        this.babList = babList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public BabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bab_row, parent, false);
        return new BabViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BabViewHolder holder, int position) {
        holder.setDataBab(babList.get(position));
        if (position %2 == 1){
            holder.llBab.setBackgroundColor(mContext.getResources().getColor(R.color.orange_200));
        }else {
            holder.llBab.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
        holder.llBab.setOnClickListener(view -> {
            Intent i = new Intent(mContext, SubabActivity.class);
            i.putExtra("number", String.valueOf(babList.get(position).getNumber()));
            i.putExtra("title", babList.get(position).getTitle());
            i.putExtra("link", babList.get(position).getLink());
            i.putExtra("path", babList.get(position).getPath());
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(i);
            Animatoo.animateSwipeRight(mContext);
        });
    }

    @Override
    public int getItemCount() {
        return babList.size();
    }

    static class BabViewHolder extends RecyclerView.ViewHolder {
        LinearLayout llBab;
        TextView tvNumber, tvTitle, tvLink;
        public BabViewHolder(@NonNull View itemView) {
            super(itemView);
            llBab = itemView.findViewById(R.id.ll_bab);
            tvNumber = itemView.findViewById(R.id.tv_number_bab);
            tvTitle = itemView.findViewById(R.id.tv_title_bab);
            tvLink = itemView.findViewById(R.id.tv_link);
        }

        public void setDataBab(Bab dataBab){
            tvNumber.setText(String.valueOf(dataBab.getNumber()));
            tvTitle.setText(dataBab.getTitle());
            tvLink.setText(dataBab.getLink());
        }
    }
}
