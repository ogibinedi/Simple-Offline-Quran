package com.obe.quranid2.module.juzamma.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.obe.quranid2.R;
import com.obe.quranid2.module.juzamma.model.JuzammaSurah;

public class JaDetailViewHolder extends RecyclerView.ViewHolder {
    public CardView cvJaDetail;
    public TextView tvVerseId, tvAyahText, tvReadText, tvIndoText, tvInfoSurah, tvAudio;
    public JaDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        cvJaDetail = itemView.findViewById(R.id.cardViewSurah);
        tvVerseId = itemView.findViewById(R.id.tv_verse_id);
        tvAyahText = itemView.findViewById(R.id.tv_ayah_text);
        tvReadText = itemView.findViewById(R.id.tv_read_text);
        tvIndoText = itemView.findViewById(R.id.tv_indo_text);
        tvInfoSurah = itemView.findViewById(R.id.tv_surah_info);
        tvAudio = itemView.findViewById(R.id.tv_audio);
    }
    public void setJaData(JuzammaSurah surah){
        tvVerseId.setText(surah.getVerseId());
        tvAyahText.setText(surah.getAyahText());
        tvReadText.setText(surah.getReadText());
        tvIndoText.setText(surah.getIndoText());
        tvInfoSurah.setText(surah.getInfoSurah());
        tvAudio.setText(surah.getAudio());
    }
}
