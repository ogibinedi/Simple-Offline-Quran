package com.obe.quranid2.module.bulughulmaram.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.obe.quranid2.R;
import com.obe.quranid2.module.bulughulmaram.model.BulughulMaram;

public class BmViewHolder extends RecyclerView.ViewHolder {
    LinearLayout llBm;
    TextView tvNumberLbm, tvTitleLbm;
    public BmViewHolder(@NonNull View itemView) {
        super(itemView);
        llBm = itemView.findViewById(R.id.ll_sub);
        tvNumberLbm = itemView.findViewById(R.id.tv_number_sub);
        tvTitleLbm = itemView.findViewById(R.id.tv_title_sub);
    }

    public void setDataBm(BulughulMaram dataBm){
        tvNumberLbm.setText(String.valueOf(dataBm.getNumberListBm()));
        tvTitleLbm.setText(dataBm.getTitleListBm());
    }
}
