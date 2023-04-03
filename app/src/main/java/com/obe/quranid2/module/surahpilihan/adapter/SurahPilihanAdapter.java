package com.obe.quranid2.module.surahpilihan.adapter;

import static android.content.Context.CLIPBOARD_SERVICE;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.obe.quranid2.R;
import com.obe.quranid2.module.surahpilihan.model.Surah;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SurahPilihanAdapter extends RecyclerView.Adapter<SurahPilihanViewHolder> {
    List<Surah>surahList;
    List<Surah> surahListFull;
    Context mContext;

    public SurahPilihanAdapter(List<Surah> surahList, Context mContext) {
        this.surahList = surahList;
        surahListFull = new ArrayList<>(surahList);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public SurahPilihanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ayah, parent, false);
        return new SurahPilihanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SurahPilihanViewHolder holder, int position) {
        holder.setSurahData(surahList.get(position));

        if (position %2 == 1){
            holder.cvSurahPilihan.setCardBackgroundColor(mContext.getResources().getColor(R.color.orange_200));
        }else {
            holder.cvSurahPilihan.setCardBackgroundColor(mContext.getResources().getColor(R.color.white));
        }

        holder.cvSurahPilihan.setOnClickListener(view -> {
            final BottomSheetDialog bt = new BottomSheetDialog(mContext);
            TextView tvSendReport, tvShareAyah, tvCopyAyah, tvSurahInfo;
            TextView tvPlayAyah;
            @SuppressLint("InflateParams") View v = LayoutInflater.from(mContext).inflate(R.layout.bottom_sheet_dialog, null);
            tvSendReport = v.findViewById(R.id.tv_send_report);
            tvShareAyah = v.findViewById(R.id.tv_share_ayah);
            tvCopyAyah = v.findViewById(R.id.tv_copy_ayah);
            tvPlayAyah = v.findViewById(R.id.tv_play_ayah);
            tvSurahInfo = v.findViewById(R.id.tv_surah_info);
            String tSurahInfo = holder.tvInfoSurah.getText().toString();
            tvSurahInfo.setText(tSurahInfo);
            bt.setContentView(v);
            bt.show();

            tvCopyAyah.setOnClickListener(view12 -> {
                ClipboardManager myClipboard = (ClipboardManager)mContext.getSystemService(CLIPBOARD_SERVICE);
                String text = holder.tvAyahText.getText().toString() + "\n "+ holder.tvIndoText.getText().toString()+" "+ holder.tvInfoSurah.getText().toString();
                ClipData myClip = ClipData.newPlainText("text", text);
                myClipboard.setPrimaryClip(myClip);
                Toast.makeText(mContext, "Ayat berhasil disalin", Toast.LENGTH_SHORT).show();
                bt.dismiss();
            });

            tvSendReport.setOnClickListener(view1 -> {
                String bodyEmail = holder.tvAyahText.getText().toString() + "\n" + holder.tvIndoText.getText().toString() + "\n" + holder.tvInfoSurah.getText().toString();
                sendEmail(mContext, new String[]{"ogibinedi@gmail.com"}, "Pilih Email Client", "Laporan Kesalahan Penulisan", bodyEmail);
                bt.dismiss();
            });

            tvShareAyah.setOnClickListener(view1 -> {
                try{
                    Intent iShare = new Intent(Intent.ACTION_SEND);
                    iShare.setType("text/plain");
                    iShare.putExtra(Intent.EXTRA_SUBJECT, "Dzikir dan Do'a Indonesia");
                    String textAyat = holder.tvAyahText.getText().toString() + "\n" + holder.tvIndoText.getText().toString() + holder.tvInfoSurah.getText().toString() + "\n" +"Silahkan unduh aplikasi melalui link dibawah \n "+ "https://play.google.com/store/apps/details?id=com.obe.kompaskiblat";
                    iShare.putExtra(Intent.EXTRA_TEXT, textAyat);
                    mContext.startActivity(Intent.createChooser(iShare, "Pilih salah satu"));
                    bt.dismiss();
                }catch (Exception e){
                    Toast.makeText(mContext, "Ada kesalahan saat membagikan "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            });

            tvPlayAyah.setOnClickListener(view1 -> {
                tvPlayAyah.setEnabled(false);
                tvPlayAyah.setTextColor(mContext.getResources().getColor(R.color.green));
                MediaPlayer mediaPlayer;
                String audioUrl = holder.tvAudio.getText().toString();
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try{
                    mediaPlayer.setDataSource(audioUrl);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Toast.makeText(mContext, "Audio diputar", Toast.LENGTH_SHORT).show();
                } catch (IOException e){
                    // Catch the exception
                    e.printStackTrace();
                }

                mediaPlayer.setOnCompletionListener(mediaPlayer1 -> {
                    Toast.makeText(mContext, "Audio selesai diputar", Toast.LENGTH_SHORT).show();
                    tvPlayAyah.setTextColor(mContext.getResources().getColor(R.color.black));
                    tvPlayAyah.setEnabled(true);
                });
            });
        });
    }

    @Override
    public int getItemCount() {
        return surahList.size();
    }

    public Filter getFilter(){
        return ayahFilter;
    }

    private final Filter ayahFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Surah> filterList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
                filterList.addAll(surahListFull);
            }else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Surah surah : surahListFull){
                    if (surah.getVerseId().toLowerCase().contains(filterPattern) || surah.getIndoText().toLowerCase().contains(filterPattern) || surah.getInfoSurah().toLowerCase().contains(filterPattern)){
                        filterList.add(surah);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filterList;
            return results;
        }
        @SuppressWarnings("unchecked")
        @SuppressLint("NotifyDataSetChanged")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            surahList.clear();
            surahList.addAll((List<Surah>) results.values);
            notifyDataSetChanged();
        }
    };

    public static void sendEmail(Context context, String[] recipientList, String title, String subject, String body){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, recipientList);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        context.startActivity(Intent.createChooser(emailIntent, title));
    }
}
