package com.obe.quranid2.module.dzikir.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.obe.quranid2.R;
import com.obe.quranid2.module.dzikir.model.DzikirSholat;

public class DzikirSholatViewHolder extends RecyclerView.ViewHolder {
    public CardView cvDzikirSholat;
    public TextView tvTitle, tvNumber, tvArabic, tvLatin, tvIndo, tvInfo;
    public DzikirSholatViewHolder(@NonNull View itemView) {
        super(itemView);
        cvDzikirSholat = itemView.findViewById(R.id.cardViewDSS);
        tvNumber = itemView.findViewById(R.id.tv_number_d);
        tvTitle = itemView.findViewById(R.id.tv_title_dzikir);
        tvArabic = itemView.findViewById(R.id.tv_ayah_d);
        tvLatin = itemView.findViewById(R.id.tv_latin_d);
        tvIndo = itemView.findViewById(R.id.tv_indo_d);
        tvInfo = itemView.findViewById(R.id.tv_ref_d);
    }

    public void setDzikirShalat(DzikirSholat sholat){
        tvNumber.setText(String.valueOf(sholat.getNumber()));
        tvTitle.setText(sholat.getTitle());
        tvArabic.setText(sholat.getArabic());
        tvLatin.setText(sholat.getLatin());
        tvIndo.setText(sholat.getIndo());
        tvInfo.setText(sholat.getInfo());
    }
}
