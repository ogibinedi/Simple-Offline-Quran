package com.obe.quranid2.module.juzamma;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.obe.quranid2.R;
import com.obe.quranid2.module.juzamma.adapter.JuzammaAdapter;
import com.obe.quranid2.module.juzamma.model.ItemJa;
import com.obe.quranid2.module.juzamma.model.Juzamma;

import java.util.ArrayList;

public class JuzammaFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = LayoutInflater.from(getContext()).inflate(R.layout.fragment_juzamma, container, false);
        initView(root);
        return root;
    }

    private void initView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.rv_juzamma);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(requireContext()){
            @Override
            protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };
        smoothScroller.setTargetPosition(0);
        layoutManager.startSmoothScroll(smoothScroller);
        // recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
        ArrayList<Juzamma> juzammas = new ArrayList<>();
        for (int i = 0; i < ItemJa.imageSurah.length; i++){
            juzammas.add(
                    new Juzamma(
                            ItemJa.imageSurah[i],
                            ItemJa.surahNumber[i],
                            ItemJa.surahText[i],
                            ItemJa.totalAyah[i],
                            ItemJa.type[i]
                                        ));
        }
        JuzammaAdapter adapter = new JuzammaAdapter(juzammas, getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}