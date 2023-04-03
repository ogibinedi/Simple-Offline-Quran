package com.obe.quranid2.module.juzamma.adapter;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.obe.quranid2.R;
import com.obe.quranid2.module.juzamma.model.Juzamma;

public class JuzammaViewHolder extends RecyclerView.ViewHolder {
    LinearLayout layoutDetail;
    ImageView ivSurah;
    TextView tvSurahNumber, tvSurahText, tvType, tvTotalAyah;
    public JuzammaViewHolder(@NonNull View itemView) {
        super(itemView);
        layoutDetail = itemView.findViewById(R.id.action_detail);
        ivSurah = itemView.findViewById(R.id.iv_surah);
        tvSurahNumber = itemView.findViewById(R.id.tv_surah_number);
        tvSurahText = itemView.findViewById(R.id.tv_surah_text);
        tvType = itemView.findViewById(R.id.tv_type);
        tvTotalAyah = itemView.findViewById(R.id.tv_total_ayah);
    }

    @SuppressLint("SetTextI18n")
    public void setDataJuzamma(Juzamma juzamma){
        ivSurah.setImageResource(juzamma.getImageUrl());
        tvSurahNumber.setText(juzamma.getSurahNumber());
        tvSurahText.setText(juzamma.getSurahText());
        tvType.setText(juzamma.getType());
        tvTotalAyah.setText(juzamma.getTotalAyah());
    }
}
