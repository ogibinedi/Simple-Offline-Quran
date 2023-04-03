package com.obe.quranid2.module.tafsirquran.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.obe.quranid2.R;
import com.obe.quranid2.module.tafsirquran.adapter.TqListAdapter;
import com.obe.quranid2.module.tafsirquran.model.ItemTq;
import com.obe.quranid2.module.tafsirquran.model.TafsirList;

import java.util.ArrayList;

public class ListTqActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tq);
        setTitle("Daftar Isi Tafsir Al-Qur'an");
        initView();
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.rv_list_tq);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<TafsirList> tafsirLists = new ArrayList<>();
        for (int i = 0; i < ItemTq.shortNumber.length; i++){
            tafsirLists.add(new TafsirList(ItemTq.shortNumber[i],ItemTq.itemTafsir[i]));
        }

        TqListAdapter adapter = new TqListAdapter(tafsirLists, ListTqActivity.this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSwipeLeft(this);
    }
}