package com.obe.quranid2.module.tafsirquran;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.obe.quranid2.module.bulughulmaram.activities.BulughulMaramActivity;
import com.obe.quranid2.module.tafsirquran.activities.ListTqActivity;
import com.obe.quranid2.module.tafsirquran.activities.TafsirActivity;

import com.obe.quranid2.databinding.FragmentTafsirBinding;

public class TafsirFragment extends Fragment {
    private FragmentTafsirBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTafsirBinding.inflate(inflater, container,false);
        View root = binding.getRoot();
        initView();
        return root;
    }

    private void initView() {
        TafsirViewModel tafsirViewModel = new ViewModelProvider(this).get(TafsirViewModel.class);

        TextView tvArabicText = binding.tvArabicText;
        TextView tvIndoText = binding.tvIndoText;
        TextView tvAboutTafsir = binding.tvAboutTafsir;
        TextView tvTafsir = binding.tvTitleTafsir;
        TextView tvBulughulMaram = binding.tvTitleBulughulMaram;
        tafsirViewModel.getArabicText().observe(getViewLifecycleOwner(), tvArabicText::setText);
        tafsirViewModel.getIndoText().observe(getViewLifecycleOwner(), tvIndoText::setText);
        tafsirViewModel.getAboutTafsir().observe(getViewLifecycleOwner(), tvAboutTafsir::setText);
        tafsirViewModel.getTafsirQuran().observe(getViewLifecycleOwner(), tvTafsir::setText);
        tafsirViewModel.getBulughulMaram().observe(getViewLifecycleOwner(), tvBulughulMaram::setText);


        ImageButton ibAboutTafsir = binding.ibTentangTafsir;
        ImageButton ibTafsir = binding.ibTafsir;
        ImageButton ibBulghulMarom = binding.ibBulughulMarom;

        ibAboutTafsir.setOnClickListener(view2 -> {
            Intent intent = new Intent(getContext(), ListTqActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });
        ibTafsir.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), TafsirActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });
        ibBulghulMarom.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), BulughulMaramActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}