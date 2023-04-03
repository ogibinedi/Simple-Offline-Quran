package com.obe.quranid2.module.dzikir.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.obe.quranid2.R;
import com.obe.quranid2.module.dzikir.model.Dzikir;

public class DzikirViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNumberD, tvAyahD, tvIndoD, tvRefD;
    public CardView cvD;

    public DzikirViewHolder(@NonNull View itemView) {
        super(itemView);
        cvD = itemView.findViewById(R.id.cardViewD);
        tvNumberD = itemView.findViewById(R.id.tv_number_d);
        tvAyahD = itemView.findViewById(R.id.tv_ayah_d);
        tvIndoD = itemView.findViewById(R.id.tv_indo_d);
        tvRefD =  itemView.findViewById(R.id.tv_ref_d);
    }

    public void setDzikirData(Dzikir dzikir){
        tvNumberD.setText(dzikir.getNumber());
        tvAyahD.setText(dzikir.getArabicText());
        tvIndoD.setText(dzikir.getIndoText());
        tvRefD.setText(dzikir.getRefHadits());
    }
}
