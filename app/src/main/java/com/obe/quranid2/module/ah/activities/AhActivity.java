package com.obe.quranid2.module.ah.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.obe.quranid2.R;
import com.obe.quranid2.module.ah.adapter.AhAdapter;
import com.obe.quranid2.module.ah.model.AsmaulHusna;

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

public class AhActivity extends AppCompatActivity {
    private ArrayList<AsmaulHusna> asmaulHusnas;
    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private FloatingActionButton fb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ah);
        setTitle("Asmaul Husna");
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

    private void getTheData(){
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> {
            // onPreExecute Method
            runOnUiThread(() ->{
                pDialog = new ProgressDialog(this);
                pDialog.setTitle("Pemproses");
                pDialog.setMessage("Silahkan tunggu...");
                pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pDialog.show();
            });

            // Getting JSON OBJECT from JSON File
            try {
                JSONObject obj = new JSONObject(Objects.requireNonNull(loadJSONfromAssets()));

                // Fetch JSOnArray named Asmaul Husna
                JSONArray asmaulHusnaArray = obj.getJSONArray("ah");
                asmaulHusnas = new ArrayList<>();
                // Getting ah list data
                for (int i=0; i < asmaulHusnaArray.length(); i++){
                    AsmaulHusna asmaulHusna = new AsmaulHusna();
                    // Fetch single asmaul husna data
                    JSONObject asmaulHusnaDetail = asmaulHusnaArray.getJSONObject(i);
                    asmaulHusna.setNumber(asmaulHusnaDetail.getInt("number"));
                    asmaulHusna.setArabicText(asmaulHusnaDetail.getString("arab"));
                    asmaulHusna.setReadText(asmaulHusnaDetail.getString("latin"));
                    asmaulHusna.setIndoText(asmaulHusnaDetail.getString("indo"));
                    asmaulHusna.setDefinitionText(asmaulHusnaDetail.getString("def"));
                    asmaulHusna.setReferenceText(asmaulHusnaDetail.getString("ref"));
                    asmaulHusnas.add(asmaulHusna);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

            // onPostExecute Method
            runOnUiThread(() ->{
                recyclerView = findViewById(R.id.rv_asmaul_husna);
                recyclerView.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(AhActivity.this){
                    @Override
                    protected int getVerticalSnapPreference() {
                        return LinearSmoothScroller.SNAP_TO_START;
                    }
                };
                smoothScroller.setTargetPosition(0);
                layoutManager.startSmoothScroll(smoothScroller);
                AhAdapter asmaulHusnaAdapter = new AhAdapter(asmaulHusnas, this);
                recyclerView.setAdapter(asmaulHusnaAdapter);
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
            InputStream is = getAssets().open("ah/asmaul_husna.json");
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
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSwipeLeft(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ah, menu);
        return true;
    }

    @SuppressLint("InflateParams")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_help){
            Intent intent = new Intent(this, AbAhActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(this);
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