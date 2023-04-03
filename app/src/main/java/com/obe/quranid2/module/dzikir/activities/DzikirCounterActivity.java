package com.obe.quranid2.module.dzikir.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.obe.quranid2.R;

public class DzikirCounterActivity extends AppCompatActivity {
    private int dzikr = 0;
    private CircularProgressIndicator pb;
    private TextView tvDzikir;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dzikir_counter);
        initStatusBar();
        initToolbar();
        initTasbih();
    }

    @SuppressLint("SetTextI18n")
    private void initTasbih(){
        pb = findViewById(R.id.progress_bar);
        tvDzikir = findViewById(R.id.tv_tasbih_progress);
        AppCompatButton apBReset = findViewById(R.id.reset);
        AppCompatButton apBTasbih = findViewById(R.id.tasbih);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        apBReset.setOnClickListener(view -> {
            dzikr = 0;
            display(dzikr);
            pb.setMax(dzikr);
            tvDzikir.setText("0");
        });

        apBTasbih.setOnClickListener(view -> {
            dzikr = dzikr + 1;
            display(dzikr);
            pb.setProgress(dzikr);
            pb.setMax(33);
            if (dzikr == 33){
                vibrator.vibrate(1000);
            }
            if (dzikr == 34){
                dzikr = 1;
                display(dzikr);
                pb.setProgress(dzikr);
            }
            vibrator.vibrate(100);
        });
    }

    @SuppressLint("SetTextI18n")
    private void display(int dz) {
        if (dzikr == 0){
            tvDzikir.setText("" + dz);
            tvDzikir.setTextColor(getResources().getColor(R.color.colorGrey));
        }else {
            tvDzikir.setText("" + dz);
            tvDzikir.setTextColor(getResources().getColor(R.color.green));
        }
    }


    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Tasbih");
        toolbar.setSubtitle("اللَّهُ أَكْبَرُ , الْحَمْدُ لِلَّهِ , سُبْحَانَ اللَّهِ  33x");
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        ActionBar actionBar= getSupportActionBar();
        ColorDrawable color = new ColorDrawable(Color.parseColor("#1B5E20"));
        assert actionBar != null;
        actionBar.setBackgroundDrawable(color);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initStatusBar() {
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.dark_green));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSwipeLeft(this);
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
}