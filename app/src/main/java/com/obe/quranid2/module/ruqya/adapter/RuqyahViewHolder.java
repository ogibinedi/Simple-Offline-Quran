package com.obe.quranid2.module.ruqya.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.obe.quranid2.R;
import com.obe.quranid2.module.ruqya.model.Ruqyah;

public class RuqyahViewHolder extends RecyclerView.ViewHolder {
    CardView cvRq;
    TextView tvNumber, tvAyahText, tvReadText, tvIndoText;
    public RuqyahViewHolder(@NonNull View itemView) {
        super(itemView);
        cvRq = itemView.findViewById(R.id.cardViewRq);
        tvNumber = itemView.findViewById(R.id.tv_number_rq);
        tvAyahText = itemView.findViewById(R.id.tv_ayah_rq);
        tvReadText = itemView.findViewById(R.id.tv_read_rq);
        tvIndoText = itemView.findViewById(R.id.tv_indo_rq);
    }

    public void setDataRq(Ruqyah ruqyah){
        tvNumber.setText(ruqyah.getNumber());
        tvAyahText.setText(ruqyah.getAyahText());
        tvReadText.setText(ruqyah.getReadText());
        tvIndoText.setText(ruqyah.getIndoText());
    }
}
