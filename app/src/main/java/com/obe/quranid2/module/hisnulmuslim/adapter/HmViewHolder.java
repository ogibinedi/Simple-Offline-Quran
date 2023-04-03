package com.obe.quranid2.module.hisnulmuslim.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.obe.quranid2.R;
import com.obe.quranid2.module.hisnulmuslim.model.HisnulMuslim;

public class HmViewHolder extends RecyclerView.ViewHolder {
    LinearLayout layout;
    ImageView ivDuas;
    TextView tvShortNumber, tvTitle, tvCategory;
    public HmViewHolder(@NonNull View itemView) {
        super(itemView);
        layout = itemView.findViewById(R.id.ll_action_item_hm);
        ivDuas = itemView.findViewById(R.id.iv_hm);
        tvShortNumber = itemView.findViewById(R.id.short_number);
        tvTitle = itemView.findViewById(R.id.tv_title_hm);
        tvCategory = itemView.findViewById(R.id.tv_category_hm);
    }

    public void setHmData(HisnulMuslim hmData){
        ivDuas.setImageResource(hmData.getImageDua());
        tvShortNumber.setText(String.valueOf(hmData.getShortNumber()));
        tvTitle.setText(hmData.getTitle());
        tvCategory.setText(hmData.getCategory());
    }
}
