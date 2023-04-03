package com.obe.quranid2.module.bulughulmaram.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.obe.quranid2.R;
import com.obe.quranid2.module.bulughulmaram.adapter.BabAdapter;
import com.obe.quranid2.module.bulughulmaram.model.Bab;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class BabActivity extends AppCompatActivity {
    private ArrayList<Bab> babs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bab);
        setTitle(getIntent().getStringExtra("title"));
        getTheData();
        initView();
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.rv_list_bab);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(BabActivity.this){
            @Override
            protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };
        smoothScroller.setTargetPosition(0);
        layoutManager.startSmoothScroll(smoothScroller);
        BabAdapter babAdapter = new BabAdapter(babs, this);
        recyclerView.setAdapter(babAdapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getTheData() {
            // Getting JSON OBJECT from JSON File
            try {
                JSONObject obj = new JSONObject(loadJSONFromAssets());
                // Fetch JSOnArray named Bulughul Maram
                JSONArray babBmArray = obj.getJSONArray("bab");
                babs = new ArrayList<>();
                // Getting ah list data
                for (int i=0; i < babBmArray.length(); i++){
                    Bab babBm = new Bab();
                    // Fetch single bab bulughul maram data
                    JSONObject babBmDetail = babBmArray.getJSONObject(i);
                    babBm.setNumber(babBmDetail.getInt("number"));
                    babBm.setTitle(babBmDetail.getString("title"));
                    babBm.setLink(babBmDetail.getString("link_to"));
                    babBm.setPath(babBmDetail.getString("path"));
                    babs.add(babBm);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
    }

    private String loadJSONFromAssets() {
        String json;

        try{
            String file = getIntent().getStringExtra("number");
            InputStream is = getAssets().open("bm/"+file+".json");
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
}