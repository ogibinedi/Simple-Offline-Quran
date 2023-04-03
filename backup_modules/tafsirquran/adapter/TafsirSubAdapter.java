package com.obe.quranid2.module.tafsirquran.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.obe.quranid2.R;
import com.obe.quranid2.module.tafsirquran.activities.TafsirSubDetailActivity;
import com.obe.quranid2.module.tafsirquran.model.TafsirSub;

import java.util.ArrayList;
import java.util.List;

public class TafsirSubAdapter extends RecyclerView.Adapter<TafsirSubViewHolder> {
    private final List<TafsirSub>tafsirSubs;
    private final List<TafsirSub>tafsirSubsFull;
    private final Context mContext;

    public TafsirSubAdapter(List<TafsirSub> tafsirSubs, Context mContext) {
        this.tafsirSubs = tafsirSubs;
        tafsirSubsFull = new ArrayList<>(tafsirSubs);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TafsirSubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title_list, parent, false);
        return new TafsirSubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TafsirSubViewHolder holder, int position) {
        holder.setDataSubTafsir(tafsirSubs.get(position));

        if (position %2 == 1){
            holder.llSub.setBackgroundColor(mContext.getResources().getColor(R.color.orange_200));
        }else {
            holder.llSub.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
        holder.llSub.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, TafsirSubDetailActivity.class);
            intent.putExtra("subTitle", tafsirSubs.get(position).getSubTitle());
            intent.putExtra("subName", tafsirSubs.get(position).getSubName());
            intent.putExtra("subNumber", String.valueOf(tafsirSubs.get(position).getSubNumber()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
            Animatoo.animateSwipeRight(mContext);
        });
    }

    @Override
    public int getItemCount() {
        return tafsirSubs.size();
    }

    public Filter getFilter(){ return tafsirFilter; }

    private final Filter tafsirFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<TafsirSub>tafsirSubList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0){
                tafsirSubList.addAll(tafsirSubsFull);
            }else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (TafsirSub sub : tafsirSubsFull){
                    if (String.valueOf(sub.getSubNumber()).toLowerCase().contains(filterPattern) ||sub.getSubTitle().toLowerCase().contains(filterPattern)){
                        tafsirSubList.add(sub);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = tafsirSubList;
            return results;
        }

        @SuppressWarnings("unchecked")
        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            tafsirSubs.clear();
            tafsirSubs.addAll((List<TafsirSub>) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
