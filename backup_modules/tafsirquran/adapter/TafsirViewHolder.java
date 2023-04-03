package com.obe.quranid2.module.tafsirquran.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.obe.quranid2.R;
import com.obe.quranid2.module.tafsirquran.model.Tafsir;

public class TafsirViewHolder extends RecyclerView.ViewHolder {
    CardView cvTafsir;
    TextView tvSurahNumber, tvSurahName, tvArabicText;
    public TafsirViewHolder(@NonNull View itemView) {
        super(itemView);
        cvTafsir = itemView.findViewById(R.id.cv_tafsir);
        tvSurahNumber = itemView.findViewById(R.id.tv_surah_number);
        tvSurahName = itemView.findViewById(R.id.tv_surah_name);
        tvArabicText = itemView.findViewById(R.id.tv_arabic_name);
    }

    public void setDataTafsirSurah(Tafsir dataTafsirSurah){
        tvSurahNumber.setText(String.valueOf(dataTafsirSurah.getNumber()));
        tvSurahName.setText(dataTafsirSurah.getSurahName());
        tvArabicText.setText(dataTafsirSurah.getArabicText());
    }
}
