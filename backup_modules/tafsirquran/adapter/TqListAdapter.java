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

import com.obe.quranid2.R;
import com.obe.quranid2.module.tafsirquran.activities.DetailListTafsirActivity;
import com.obe.quranid2.module.tafsirquran.model.TafsirList;

import java.util.ArrayList;
import java.util.List;

public class TqListAdapter extends RecyclerView.Adapter<TqListViewHolder> {
    private final List<TafsirList> tafsirLists;
    private final List<TafsirList> tafsirListsFull;
    private final Context mContext;

    public TqListAdapter(List<TafsirList> tafsirLists, Context mContext) {
        this.tafsirLists = tafsirLists;
        tafsirListsFull = new ArrayList<>(tafsirLists);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TqListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title_list, parent, false);
        return new TqListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TqListViewHolder holder, int position) {
        holder.setDataTqList(tafsirLists.get(position));

       if (position %2 == 1){
           holder.lLTqList.setBackgroundColor(mContext.getResources().getColor(R.color.white));
       }else {
           holder.lLTqList.setBackgroundColor(mContext.getResources().getColor(R.color.orange_200));
       }

        holder.lLTqList.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, DetailListTafsirActivity.class);
            intent.putExtra("detailTitle", tafsirLists.get(position).getSubTitle());
            intent.putExtra("detailNumber", String.valueOf(tafsirLists.get(position).getSubNumber()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tafsirLists.size();
    }

    public Filter getFilter(){ return tafsirFilter; }

    private final Filter tafsirFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<TafsirList>list = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0){
                list.addAll(tafsirListsFull);
            }else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (TafsirList tafsirList : tafsirListsFull){
                    if (String.valueOf(tafsirList.getSubNumber()).toLowerCase().contains(filterPattern) || tafsirList.getSubTitle().toLowerCase().contains(filterPattern)){
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
            tafsirLists.clear();
            tafsirLists.addAll((List<TafsirList>)filterResults.values);
            notifyDataSetChanged();
        }
    };
}
