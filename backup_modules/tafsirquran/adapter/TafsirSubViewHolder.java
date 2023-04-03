package com.obe.quranid2.module.tafsirquran.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.obe.quranid2.R;
import com.obe.quranid2.module.tafsirquran.model.TafsirSub;

public class TafsirSubViewHolder extends RecyclerView.ViewHolder {
    LinearLayout llSub;
    TextView tvSubNumber, tvSubTitle, tvSubName;
    public TafsirSubViewHolder(@NonNull View itemView) {
        super(itemView);

        llSub = itemView.findViewById(R.id.ll_sub);
        tvSubNumber = itemView.findViewById(R.id.tv_number_sub);
        tvSubTitle = itemView.findViewById(R.id.tv_title_sub);
        tvSubName = itemView.findViewById(R.id.tv_sub_name);
    }

    public void setDataSubTafsir(TafsirSub dataSubTafsir){
        tvSubNumber.setText(String.valueOf(dataSubTafsir.getSubNumber()));
        tvSubTitle.setText(dataSubTafsir.getSubTitle());
        tvSubName.setText(dataSubTafsir.getSubName());
    }
}
