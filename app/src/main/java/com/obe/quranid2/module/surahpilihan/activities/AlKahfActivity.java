package com.obe.quranid2.module.surahpilihan.activities;

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
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SearchView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.obe.quranid2.R;
import com.obe.quranid2.module.surahpilihan.adapter.SurahPilihanAdapter;
import com.obe.quranid2.module.surahpilihan.model.Surah;

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

public class AlKahfActivity extends AppCompatActivity {
    private final ArrayList<Surah> surahs = new ArrayList<>();
    private RecyclerView recyclerView;
    private SurahPilihanAdapter adapter;
    private ProgressDialog pDialog;
    private FloatingActionButton fb;
    private int lastPosition;
    private static final String SURAH_PILIHAN_PREFS = "AlKahf";
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_al_kahf);

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
        Toolbar toolbar = findViewById(R.id.toolbar_sp);
        toolbar.setTitle("Surah Al-Kahf");
        toolbar.setSubtitle("Gua - 110 Ayat");
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

                // Fetch JSOnArray named SURAH_PILIHAN
                JSONArray surahPilihanArray = obj.getJSONArray("ayahs");
                // Getting surah list data
                for (int i=0; i < surahPilihanArray.length(); i++){
                    Surah surah = new Surah();
                    // Fetch single surah pilihan data
                    JSONObject surahPilihanDetail = surahPilihanArray.getJSONObject(i);

                    // Fetch the data and storing them in arraylist
                    surah.setVerseId(surahPilihanDetail.getString("verseId"));
                    surah.setAyahText(surahPilihanDetail.getString("ayahText"));
                    surah.setReadText(surahPilihanDetail.getString("readText"));

                    // Create an object for getting ayah_tafsir data from JSONObject
                    // JSONObject surahs = surahPilihanDetail.getJSONObject("surahs");

                    // Fetching surahs and storing it in arraylist
                    surah.setIndoText(surahPilihanDetail.getString("indoText"));
                    surah.setInfoSurah(surahPilihanDetail.getString("surah_info"));
                    surah.setAudio(surahPilihanDetail.getString("audio"));

                    surahs.add(surah);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }

            // onPostExecute Method
            runOnUiThread(() -> {
                recyclerView = findViewById(R.id.rv_sp);
                recyclerView.setHasFixedSize(true);
                layoutManager  = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);
                RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(AlKahfActivity.this){
                    @Override
                    protected int getVerticalSnapPreference() {
                        return LinearSmoothScroller.SNAP_TO_START;
                    }
                };
                smoothScroller.setTargetPosition(0);
                layoutManager.startSmoothScroll(smoothScroller);
                // Call the custom adapter to send the reference and data to adapter
                adapter = new SurahPilihanAdapter(surahs, AlKahfActivity.this);
                recyclerView.setAdapter(adapter);
                Handler handler = new Handler();
                handler.postDelayed(() -> pDialog.cancel(), 1000);

                // menampilkan posisi terakhir list pada saat dibuka kembali
                SharedPreferences getPrefs = getApplicationContext().getSharedPreferences(SURAH_PILIHAN_PREFS, MODE_PRIVATE);
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
            InputStream is = getAssets().open("sp/kahfi.json");
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_option_surah, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQueryHint("Masukan no, info surah, terjemahan");
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
        if (item.getItemId() == R.id.action_goto){
            final EditText edtGoto = new EditText(this);
            edtGoto.setWidth(50);
            edtGoto.setHint("1-110");
            edtGoto.setGravity(Gravity.CENTER_HORIZONTAL);
            edtGoto.setInputType(InputType.TYPE_CLASS_NUMBER);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(edtGoto)
                    .setTitle("Pergi ke spesifik ayat")
                    .setMessage("Masukan nomor ayat antara 1-110")
                    .setPositiveButton("Go", (dialog, id) -> {
                        String result = edtGoto.getText().toString();
                        //Scroll item 2 to 20 pixels from the top
                        int lastResult = Integer.parseInt(result) - 1;
                        recyclerView.scrollToPosition(0);
                        layoutManager.scrollToPositionWithOffset(lastResult, 0);
                    })
                    .setNegativeButton(R.string.cancel, (dialog, id) -> {
                        // User cancelled the dialog
                        dialog.dismiss();
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        if (item.getItemId() == R.id.action_help){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.help_info, null);
            builder.setView(dialogView)
                    .setNegativeButton("Tutup", (dialog, id) -> dialog.dismiss());
            AlertDialog alertDialog = builder.create();
            alertDialog.setTitle("Halaman Simpan Otomatis");
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // menyimpan posisi terakhir pada sharedpreferences di method destroy
        SharedPreferences getPrefs = getApplicationContext().getSharedPreferences(SURAH_PILIHAN_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor e = getPrefs.edit();
        e.putInt("lastPos", lastPosition);
        e.apply();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSwipeLeft(this);
    }
}