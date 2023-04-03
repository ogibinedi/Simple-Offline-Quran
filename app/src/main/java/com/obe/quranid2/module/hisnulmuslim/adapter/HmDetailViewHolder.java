package com.obe.quranid2.module.hisnulmuslim.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.obe.quranid2.R;
import com.obe.quranid2.module.hisnulmuslim.model.Dua;

public class HmDetailViewHolder extends RecyclerView.ViewHolder {
    CardView cvDhm;
    TextView tvNumber, tvArabicText, tvReadText, tvIndoText, tvRefs;
    public HmDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        cvDhm = itemView.findViewById(R.id.cardViewDhm);
        tvNumber = itemView.findViewById(R.id.tv_number_dhm);
        tvArabicText = itemView.findViewById(R.id.tv_arabic_text_dhm);
        tvReadText = itemView.findViewById(R.id.tv_read_text_dhm);
        tvIndoText = itemView.findViewById(R.id.tv_indo_text_dhm);
        tvRefs = itemView.findViewById(R.id.tv_refs_dhm);
    }

    public void setDataHm(Dua dataHm){
        tvNumber.setText(dataHm.getNumber());
        tvArabicText.setText(dataHm.getArabicText());
        tvReadText.setText(dataHm.getReadText());
        tvIndoText.setText(dataHm.getIndoText());
        tvRefs.setText(dataHm.getRefs());
    }
}
