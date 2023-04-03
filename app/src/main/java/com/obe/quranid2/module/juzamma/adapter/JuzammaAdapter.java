package com.obe.quranid2.module.juzamma.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.obe.quranid2.R;
import com.obe.quranid2.module.juzamma.activities.JaDetailActivity;
import com.obe.quranid2.module.juzamma.model.Juzamma;

import java.util.ArrayList;

public class JuzammaAdapter extends RecyclerView.Adapter<JuzammaViewHolder> {
    private final ArrayList<Juzamma> juzammas;
    private final Context mContext;

    public JuzammaAdapter(ArrayList<Juzamma> juzammas, Context mContext) {
        this.juzammas = juzammas;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public JuzammaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ayah, parent, false);
        return new JuzammaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JuzammaViewHolder holder, int position) {
       holder.setDataJuzamma(juzammas.get(position));
       if (position %2 == 1){
           holder.layoutDetail.setBackgroundColor(mContext.getResources().getColor(R.color.white));
       }else{
           holder.layoutDetail.setBackgroundColor(mContext.getResources().getColor(R.color.orange_200));
       }
       holder.layoutDetail.setOnClickListener(view -> {
           Intent intent = new Intent(mContext, JaDetailActivity.class);
           intent.putExtra("surahNumber", juzammas.get(position).getSurahNumber());
           intent.putExtra("surahText", juzammas.get(position).getSurahText());
           intent.putExtra("totalAyah", juzammas.get(position).getTotalAyah());
           intent.putExtra("type", juzammas.get(position).getType());
           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           mContext.startActivity(intent);
           Animatoo.animateSwipeRight(mContext);
       });
    }

    @Override
    public int getItemCount() {
        return juzammas.size();
    }
}
