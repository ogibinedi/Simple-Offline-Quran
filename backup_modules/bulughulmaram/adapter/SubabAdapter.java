package com.obe.quranid2.module.bulughulmaram.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.obe.quranid2.R;
import com.obe.quranid2.module.bulughulmaram.model.Subab;

import java.util.List;

public class SubabAdapter extends RecyclerView.Adapter<SubabAdapter.SubabViewHolder> {
    private final List<Subab>subabs;
    private final Context mContext;

    public SubabAdapter(List<Subab> subabs, Context mContext) {
        this.subabs = subabs;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public SubabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_bab_row, parent, false);
        return new SubabViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubabViewHolder holder, int position) {
        holder.setDataSubBab(subabs.get(position));

        if (position %2 == 1){
            holder.cvSubBab.setCardBackgroundColor(mContext.getResources().getColor(R.color.orange_200));
        }else {
            holder.cvSubBab.setCardBackgroundColor(mContext.getResources().getColor(R.color.white));
        }

        holder.cvSubBab.setOnClickListener(view -> {

        });
    }

    @Override
    public int getItemCount() {
        return subabs.size();
    }

    static class SubabViewHolder extends RecyclerView.ViewHolder {
        CardView cvSubBab;
        TextView tvNumber, tvTitle, tvHadits, tvIndo;
        public SubabViewHolder(@NonNull View itemView) {
            super(itemView);
            cvSubBab = itemView.findViewById(R.id.cv_sub_bab);
            tvNumber = itemView.findViewById(R.id.tv_number_sub_bab);
            tvTitle = itemView.findViewById(R.id.tv_title_sub_bab);
            tvHadits = itemView.findViewById(R.id.tv_hadits_sub_bab);
            tvIndo = itemView.findViewById(R.id.tv_indo_sub_bab);
        }

        public void setDataSubBab(Subab dataSubBab){
            tvNumber.setText(String.valueOf(dataSubBab.getNumber()));
            tvTitle.setText(dataSubBab.getTitle());
            tvHadits.setText(dataSubBab.getHadits());
            tvIndo.setText(dataSubBab.getIndo());
        }
    }
}
