package com.obe.quranid2.module.ah.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.obe.quranid2.R;
import com.obe.quranid2.module.ah.model.AsmaulHusna;

public class AhViewHolder extends RecyclerView.ViewHolder {
    CardView cvAh;
    TextView tvNumber, tvArabic, tvRead, tvIndo, tvDefinition, tvReference;
    public AhViewHolder(@NonNull View itemView) {
        super(itemView);
        cvAh = itemView.findViewById(R.id.cv_ah);
        tvNumber = itemView.findViewById(R.id.tv_number_ah);
        tvArabic = itemView.findViewById(R.id.tv_arabic_text_ah);
        tvRead = itemView.findViewById(R.id.tv_read_text_ah);
        tvIndo = itemView.findViewById(R.id.tv_indo_text_ah);
        tvDefinition = itemView.findViewById(R.id.tv_definition_ah);
        tvReference = itemView.findViewById(R.id.tv_ref_ah);
    }

    public void setDataAh(AsmaulHusna husna){
        tvNumber.setText(String.valueOf(husna.getNumber()));
        tvArabic.setText(husna.getArabicText());
        tvRead.setText(husna.getReadText());
        tvIndo.setText(husna.getIndoText());
        tvDefinition.setText(husna.getDefinitionText());
        tvReference.setText(husna.getReferenceText());
    }
}
