package com.obe.quranid2.module.dzikir.activities;

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
import com.obe.quranid2.module.dzikir.adapter.DzikirAdapter;
import com.obe.quranid2.module.dzikir.model.Dzikir;

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

public class DzikirPetangActivity extends AppCompatActivity {
    private final ArrayList<Dzikir> dzikirs = new ArrayList<>();
    private RecyclerView recyclerView;
    private DzikirAdapter adapter;
    private ProgressDialog pDialog;
    private FloatingActionButton fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dzikir_petang);

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
        Toolbar toolbar = findViewById(R.id.toolbar_p);
        toolbar.setTitle("Dzikir Petang");
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

                // Fetch JSOnArray named DZIKIR_PETANG
                JSONArray dzikirPetangArray = obj.getJSONArray("petang");
                // Getting dzikir list data
                for (int i=0; i < dzikirPetangArray.length(); i++){
                    Dzikir dzikir = new Dzikir();
                    // Fetch single dzikir data
                    JSONObject dzikirDetail = dzikirPetangArray.getJSONObject(i);

                    // Fetch the data and storing them in arraylist
                    dzikir.setNumber(dzikirDetail.getString("order_number"));
                    dzikir.setArabicText(dzikirDetail.getString("ayah"));
                    dzikir.setIndoText(dzikirDetail.getString("translation"));

                    // Create an object for getting hadits data from JSONObject
                    // JSONObject hadits = dzikirDetail.getJSONObject("hadits");

                    // Fetching hadits and storing it in arraylist
                    dzikir.setRefHadits(dzikirDetail.getString("hadits"));

                    dzikirs.add(dzikir);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

            // onPostExecute Method
            runOnUiThread(() -> {
                recyclerView = findViewById(R.id.rv_dzikir_petang);
                recyclerView.setHasFixedSize(true);
                LinearLayoutManager layoutManager  = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(DzikirPetangActivity.this){
                    @Override
                    protected int getVerticalSnapPreference() {
                        return LinearSmoothScroller.SNAP_TO_START;
                    }
                };
                smoothScroller.setTargetPosition(0);
                recyclerView.scrollToPosition(0);
                layoutManager.startSmoothScroll(smoothScroller);
                // Call the custom adapter to send the reference and data to adapter
                adapter = new DzikirAdapter(dzikirs, DzikirPetangActivity.this);
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

    private String loadJSONfromAssets() {
        String json;

        try{
            InputStream is = getAssets().open("dzikir/dzikirpetang.json");
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