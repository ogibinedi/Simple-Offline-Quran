package com.obe.quranid2.module.bulughulmaram.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.obe.quranid2.R;
import com.obe.quranid2.module.bulughulmaram.adapter.BmAdapter;
import com.obe.quranid2.module.bulughulmaram.model.BulughulMaram;
import com.obe.quranid2.module.bulughulmaram.model.ItemBulughulMaram;

import java.util.ArrayList;

public class BulughulMaramActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulughul_maram);
        setTitle("Daftar Isi Bulughul Maram");
        initView();
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.rv_list_bm);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<BulughulMaram> bulughulMarams = new ArrayList<>();
        for (int i = 0; i < ItemBulughulMaram.shortNumber.length; i++){
            bulughulMarams.add(new BulughulMaram(ItemBulughulMaram.shortNumber[i],ItemBulughulMaram.titleList[i] ));
        }
        BmAdapter adapter = new BmAdapter(bulughulMarams, BulughulMaramActivity.this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSwipeLeft(this);
    }
}