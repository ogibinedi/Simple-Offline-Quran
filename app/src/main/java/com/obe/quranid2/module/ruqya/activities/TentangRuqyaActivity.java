package com.obe.quranid2.module.ruqya.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.obe.quranid2.R;

public class TentangRuqyaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_ruqya);

        initToolbar();
        initStatusBar();
        initWebView();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Tentang Ruqya");
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

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        WebView webView = findViewById(R.id.wv_trq);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        ProgressDialog mProgress = ProgressDialog.show(this, null, "Tunggu Sebentar");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if(mProgress.isShowing()){
                    mProgress.dismiss();
                }
            }
        });

        webView.loadUrl("file:///android_asset/rq/public/tentang_ruqya.html");
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
}