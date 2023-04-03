package com.obe.quranid2.module.bulughulmaram.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.obe.quranid2.R;
import com.obe.quranid2.module.bulughulmaram.adapter.SubabAdapter;
import com.obe.quranid2.module.bulughulmaram.model.Subab;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class SubabActivity extends AppCompatActivity {
    private ArrayList<Subab> subabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subab);
        setTitle(getIntent().getStringExtra("title"));
        getTheData();
        initView();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.rv_sub_bab);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(SubabActivity.this){
            @Override
            protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };
        smoothScroller.setTargetPosition(0);
        layoutManager.startSmoothScroll(smoothScroller);
        SubabAdapter subabAdapter = new SubabAdapter(subabs, this);
        recyclerView.setAdapter(subabAdapter);
        subabAdapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getTheData() {

            // Getting JSON OBJECT from JSON File
            try {
                JSONObject obj = new JSONObject(Objects.requireNonNull(loadJSONFromAssets()));
                // Fetch JSOnArray named Bulughul Maram
                String link = getIntent().getStringExtra("link");
                JSONArray subBabBmArray = obj.getJSONArray(link);
                subabs = new ArrayList<>();
                // Getting ah list data
                for (int i=0; i < subBabBmArray.length(); i++){
                    Subab subBabBm = new Subab();
                    // Fetch single sub bab bulughul maram data
                    JSONObject subBabBmDetail = subBabBmArray.getJSONObject(i);
                    subBabBm.setNumber(subBabBmDetail.getInt("number"));
                    subBabBm.setTitle(subBabBmDetail.getString("title"));
                    subBabBm.setHadits(subBabBmDetail.getString("hadits"));
                    subBabBm.setIndo(subBabBmDetail.getString("indo"));
                    subabs.add(subBabBm);
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
    }

    private String loadJSONFromAssets() {
        String json;

        try{
            String file = getIntent().getStringExtra("number");
            String path = getIntent().getStringExtra("path");
            InputStream is = getAssets().open("bm/sub/"+path+"/"+file+".json");
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