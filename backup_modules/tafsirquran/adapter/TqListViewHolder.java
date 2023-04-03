package com.obe.quranid2.module.tafsirquran.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.obe.quranid2.R;
import com.obe.quranid2.module.tafsirquran.model.TafsirList;

public class TqListViewHolder extends RecyclerView.ViewHolder {
    LinearLayout lLTqList;
    TextView tvTqNumberList, tvTqTitleList;
    public TqListViewHolder(@NonNull View itemView) {
        super(itemView);
        lLTqList = itemView.findViewById(R.id.ll_sub);
        tvTqNumberList = itemView.findViewById(R.id.tv_number_sub);
        tvTqTitleList = itemView.findViewById(R.id.tv_title_sub);
    }

    public void setDataTqList(TafsirList dataTqList){
        tvTqNumberList.setText(String.valueOf(dataTqList.getSubNumber()));
        tvTqTitleList.setText(dataTqList.getSubTitle());
    }
}
