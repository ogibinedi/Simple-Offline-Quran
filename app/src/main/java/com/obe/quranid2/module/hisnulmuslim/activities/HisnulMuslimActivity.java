package com.obe.quranid2.module.hisnulmuslim.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.obe.quranid2.R;
import com.obe.quranid2.module.hisnulmuslim.adapter.HmAdapter;
import com.obe.quranid2.module.hisnulmuslim.model.HisnulMuslim;
import com.obe.quranid2.module.hisnulmuslim.model.ItemHm;

import java.util.ArrayList;

public class HisnulMuslimActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hisnul_muslim);
        setTitle("Hisnul Muslim");
        initView();
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.rv_hm);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(HisnulMuslimActivity.this){
            @Override
            protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };
        smoothScroller.setTargetPosition(0);
        layoutManager.startSmoothScroll(smoothScroller);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        ArrayList<HisnulMuslim> hisnulMuslims = new ArrayList<>();
        for (int i = 0; i < ItemHm.imageDua.length; i++){
            hisnulMuslims.add(new HisnulMuslim(
                    ItemHm.imageDua[i],
                    ItemHm.shortNumber[i],
                    ItemHm.title[i],
                    ItemHm.category[i]
            ));
        }

        HmAdapter adapter = new HmAdapter(hisnulMuslims, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail_activity, menu);
        return true;
    }

    @SuppressLint("InflateParams")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /*
        if (item.getItemId() == R.id.action_rate){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            View rating = LayoutInflater.from(this).inflate(R.layout.rating_bar_layout, null);
            rating.setPadding(20,20,20,20);
            alert.setView(rating);
            alert.setTitle("Beri Ulasam Aplikasi ini");
            alert.setPositiveButton("Beri Rating", (dialogInterface, i) -> {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=com.obe.quranid2"));
                try {
                    startActivity(intent);
                }catch (Exception e){
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.obe.quranid2"));
                }
            });
            alert.setNegativeButton("Tutup", (dialogInterface, i) -> dialogInterface.dismiss());
            alert.show();
        }
         */
        if (item.getItemId() == R.id.action_share){
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            String body = "https://play.google.com/store/apps/details?id=com.obe.quranid2";
            String sub = "Silahkan unduh aplikasi Dzhikir dan Do'a Indonesia";
            share.putExtra(Intent.EXTRA_SUBJECT, sub);
            share.putExtra(Intent.EXTRA_TEXT, body);
            startActivity(Intent.createChooser(share, "Bagikan Menggunakan"));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSwipeLeft(this);
    }
}