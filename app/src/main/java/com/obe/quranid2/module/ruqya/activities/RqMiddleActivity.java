package com.obe.quranid2.module.ruqya.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.obe.quranid2.R;
import com.obe.quranid2.module.ruqya.adapter.RuqyahAdapter;
import com.obe.quranid2.module.ruqya.model.Ruqyah;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RqMiddleActivity extends AppCompatActivity {
    private final ArrayList<Ruqyah> ruqyahs = new ArrayList<>();
    private RecyclerView recyclerView;
    private RuqyahAdapter adapter;
    private ProgressDialog pDialog;
    private FloatingActionButton fb;
    private int lastPosition;
    private static final String RQ_MIDDLE_PREFS = "RqMiddle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rq_middle);

        initStatusBar();
        initToolbar();
        getTheData();
        initScrollToTop();
    }

    private void initScrollToTop() {
        fb = findViewById(R.id.fab_to_top);
        fb.setOnClickListener(view -> {
            recyclerView.scrollToPosition(0);
            recyclerView.smoothScrollToPosition(0);
        });
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_rq);
        toolbar.setTitle("Bacaan Ruqyah Sedang");
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

    private void getTheData(){
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> {
            // onPreExecute Method
            runOnUiThread(() ->{
                pDialog = new ProgressDialog(this);
                pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pDialog.setMessage("Silahkan ditunggu...");
                pDialog.show();
            });

            // Getting JSON OBJECT from JSON File
            try {
                JSONObject obj = new JSONObject(Objects.requireNonNull(loadJSONfromAssets()));

                // Fetch JSOnArray named Ruqyah Sedang
                JSONArray ruqyahMiddleArray = obj.getJSONArray("ayahs");
                // Getting ruqyah list data
                for (int i=0; i < ruqyahMiddleArray.length(); i++){
                    Ruqyah ruqyah = new Ruqyah();
                    // Fetch single ruqyah data
                    JSONObject ruqyahDetail = ruqyahMiddleArray.getJSONObject(i);

                    // Fetch the data and storing them in arraylist
                    ruqyah.setNumber(ruqyahDetail.getString("verseId"));
                    ruqyah.setAyahText(ruqyahDetail.getString("ayahText"));
                    ruqyah.setReadText(ruqyahDetail.getString("readText"));

                    // Create an object for getting ayah_tafsir data from JSONObject
                    // JSONObject hadits = ruqyahDetail.getJSONObject("hadits");

                    // Fetching hadits and storing it in arraylist
                    ruqyah.setIndoText(ruqyahDetail.getString("indoText"));

                    ruqyahs.add(ruqyah);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

            // onPostExecute Method
            runOnUiThread(() -> {
                recyclerView = findViewById(R.id.rv_rq_middle);
                recyclerView.setHasFixedSize(true);
                LinearLayoutManager layoutManager  = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(RqMiddleActivity.this){
                    @Override
                    protected int getVerticalSnapPreference() {
                        return LinearSmoothScroller.SNAP_TO_START;
                    }
                };
                smoothScroller.setTargetPosition(0);
                recyclerView.scrollToPosition(0);
                layoutManager.startSmoothScroll(smoothScroller);
                // Call the custom adapter to send the reference and data to adapter
                adapter = new RuqyahAdapter(ruqyahs, RqMiddleActivity.this);
                recyclerView.setAdapter(adapter);
                Handler handler = new Handler();
                handler.postDelayed(() -> pDialog.cancel(), 1000);
                // menampilkan posisi terakhir list pada saat dibuka kembali
                SharedPreferences getPrefs = getApplicationContext().getSharedPreferences(RQ_MIDDLE_PREFS, MODE_PRIVATE);
                lastPosition = getPrefs.getInt("lastPos", 0);
                recyclerView.scrollToPosition(lastPosition);
                layoutManager.scrollToPositionWithOffset(lastPosition, 0);

                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        // if the recyclerview is scrolled above hide fb
                        if (dy > 10 && fb.isShown()){
                            fb.hide();
                        }
                        // if the recyclerview is scrolled above show fb
                        if (dy < -10 && !fb.isShown()){
                            fb.show();
                        }
                        // if the recyclerview is at the first hide fb
                        if (!recyclerView.canScrollVertically(-1)){
                            fb.hide();
                        }
                    }

                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        lastPosition = layoutManager.findFirstVisibleItemPosition();
                    }
                });
            });
        });
    }

    private String loadJSONfromAssets() {
        String json;

        try{
            InputStream is = getAssets().open("rq/rq_midle.json");
            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, StandardCharsets.UTF_8);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

        return json;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // menyimpan posisi terakhir pada sharedpreferences di method destroy
        SharedPreferences getPrefs =  getApplicationContext().getSharedPreferences(RQ_MIDDLE_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor e = getPrefs.edit();
        e.putInt("lastPos", lastPosition);
        e.apply();
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