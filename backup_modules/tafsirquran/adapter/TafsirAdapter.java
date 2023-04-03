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
import com.obe.quranid2.module.tafsirquran.activities.TafsirSubActivity;
import com.obe.quranid2.module.tafsirquran.model.Tafsir;

import java.util.ArrayList;
import java.util.List;

public class TafsirAdapter extends RecyclerView.Adapter<TafsirViewHolder> {
    private final List<Tafsir> tafsirs;
    private final List<Tafsir> tafsirsFull;
    private final Context mContext;

    public TafsirAdapter(List<Tafsir> tafsirs, Context mContext) {
        this.tafsirs = tafsirs;
        tafsirsFull = new ArrayList<>(tafsirs);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TafsirViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tafsir, parent, false);
        return new TafsirViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TafsirViewHolder holder, int position) {
        holder.setDataTafsirSurah(tafsirs.get(position));

        if (position %2 == 1){
            holder.cvTafsir.setCardBackgroundColor(mContext.getResources().getColor(R.color.orange_200));
        }else {
            holder.cvTafsir.setCardBackgroundColor(mContext.getResources().getColor(R.color.white));
        }
        holder.cvTafsir.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, TafsirSubActivity.class);
            intent.putExtra("number",String.valueOf(tafsirs.get(position).getNumber()));
            intent.putExtra("title",tafsirs.get(position).getSurahName());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
            Animatoo.animateSwipeRight(mContext);
        });
    }

    @Override
    public int getItemCount() {
        return tafsirs.size();
    }

    public Filter getFilter(){ return tafsirFilter; }

    private final Filter tafsirFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Tafsir>list = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0){
                list.addAll(tafsirsFull);
            }else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Tafsir tafsirList : tafsirsFull){
                    if (String.valueOf(tafsirList.getNumber()).toLowerCase().contains(filterPattern) || tafsirList.getSurahName().toLowerCase().contains(filterPattern)){
                        list.add(tafsirList);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = list;
            return results;
        }

        @SuppressWarnings("unchecked")
        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            tafsirs.clear();
            tafsirs.addAll((List<Tafsir>)filterResults.values);
            notifyDataSetChanged();
        }
    };
}
