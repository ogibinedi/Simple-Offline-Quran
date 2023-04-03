package com.obe.quranid2.module.tafsirquran.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.obe.quranid2.R;
import com.obe.quranid2.module.tafsirquran.adapter.TafsirSubAdapter;
import com.obe.quranid2.module.tafsirquran.model.TafsirSub;

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

public class TafsirSubActivity extends AppCompatActivity {
    private final ArrayList<TafsirSub> tafsirSubs = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private TafsirSubAdapter adapter;
    private ProgressDialog pDialog;
    private FloatingActionButton fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tafsir_sub);

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getIntent().getStringExtra("number")+" "+getIntent().getStringExtra("title"));
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
                JSONObject obj = new JSONObject(Objects.requireNonNull(loadJSONFromAssets()));

                // Fetch JSOnArray named Sub Title Tafsir
                JSONArray subTitleArray = obj.getJSONArray("tafsir_list");
                // Getting subtitle list data
                for (int i=0; i < subTitleArray.length(); i++){
                    TafsirSub tafsirSub = new TafsirSub();
                    // Fetch single subtitle data
                    JSONObject subTitleDetail = subTitleArray.getJSONObject(i);

                    // Fetch the data and storing them in arraylist
                    tafsirSub.setSubNumber(subTitleDetail.getInt("number"));
                    tafsirSub.setSubTitle(subTitleDetail.getString("title_list"));
                    tafsirSub.setSubName(subTitleDetail.getString("sub_name"));

                    /* Create an object for getting nested data from JSONObject
                    // JSONObject subDetail = subTitleDetail.getJSONObject("sub_detail");
                    // tafsirSub.setAsma(subDetail.getString("asma")); */

                    tafsirSubs.add(tafsirSub);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

            // onPostExecute Method
            runOnUiThread(() -> {
                recyclerView = findViewById(R.id.rv_tafsir_sub);
                recyclerView.setHasFixedSize(true);
                layoutManager  = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(TafsirSubActivity.this){
                    @Override
                    protected int getVerticalSnapPreference() {
                        return LinearSmoothScroller.SNAP_TO_START;
                    }
                };
                smoothScroller.setTargetPosition(0);
                layoutManager.startSmoothScroll(smoothScroller);
                // Call the custom adapter to send the reference and data to adapter
                adapter = new TafsirSubAdapter(tafsirSubs, TafsirSubActivity.this);
                recyclerView.setAdapter(adapter);
                Handler handler = new Handler();
                handler.postDelayed(() -> pDialog.cancel(), 1000);

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
                });


            });
        });
    }

    private String loadJSONFromAssets() {
        String json;
        String rawFile = "tq/data/json/"+getIntent().getStringExtra("number")+".json";

        try{
            InputStream is = getAssets().open(rawFile);
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
        Animatoo.animateSwipeLeft(this);
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

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQueryHint("Masukan no atau Daftar Judul Tafsir");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @SuppressLint("InflateParams")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
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