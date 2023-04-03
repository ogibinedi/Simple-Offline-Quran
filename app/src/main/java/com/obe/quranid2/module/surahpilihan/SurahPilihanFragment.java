package com.obe.quranid2.module.surahpilihan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.obe.quranid2.module.surahpilihan.activities.AlBaqarahActivity;
import com.obe.quranid2.module.surahpilihan.activities.AlKahfActivity;
import com.obe.quranid2.module.surahpilihan.activities.AlMulkActivity;
import com.obe.quranid2.module.surahpilihan.activities.AlMuzammilActivity;
import com.obe.quranid2.module.surahpilihan.activities.AlWaqiahActivity;
import com.obe.quranid2.module.surahpilihan.activities.AlhajjActivity;
import com.obe.quranid2.module.surahpilihan.activities.ArRahmanActivity;
import com.obe.quranid2.module.surahpilihan.activities.AsSajadahActivity;
import com.obe.quranid2.module.surahpilihan.activities.YaseenActivity;

import com.obe.quranid2.databinding.FragmentSurahpilihanBinding;

public class SurahPilihanFragment extends Fragment {

    private FragmentSurahpilihanBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SurahPilihanViewModel surahPilihanViewModel = new ViewModelProvider(this).get(SurahPilihanViewModel.class);

        binding = FragmentSurahpilihanBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ImageButton ibAlBaqarah = binding.ibAlBaqarah;
        ImageButton ibAlHajj = binding.ibAlHajj;
        ImageButton ibAsSajadah = binding.ibAsSajadah;
        ImageButton ibArRahman = binding.ibArRahman;
        ImageButton ibAlKahf = binding.ibAlKahf;
        ImageButton ibYaseen = binding.ibYaseen;
        ImageButton ibAlMuzammil = binding.ibAlMuzammil;
        ImageButton ibAlMulk = binding.ibAlMulk;
        ImageButton ibAlWaqiah = binding.ibAlWaqiah;

        TextView tvFirmanAyah = binding.tvDzikirFirman;
        TextView tvFirmanIndo = binding.tvDzikirIndo;
        TextView tvAlBaqarah = binding.tvAlBaqarah;
        TextView tvAlHajj = binding.tvAlHajj;
        TextView tvAsSajadah = binding.tvAsSajadah;
        TextView tvArRahman = binding.tvArRahman;
        TextView tvAlKahf = binding.tvAlKahf;
        TextView tvYaseen = binding.tvYaseen;
        TextView tvAlMuzammil = binding.tvAlMuzammil;
        TextView tvAlMulk = binding.tvAlMulk;
        TextView tvAlWaqiah = binding.tvAlWaqiah;

        ibAlBaqarah.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), AlBaqarahActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });
        ibAlHajj.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), AlhajjActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });
        ibAsSajadah.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), AsSajadahActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });
        ibArRahman.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), ArRahmanActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });
        ibAlKahf.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), AlKahfActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });
        ibYaseen.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), YaseenActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });
        ibAlMuzammil.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), AlMuzammilActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });
        ibAlMulk.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), AlMulkActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });
        ibAlWaqiah.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), AlWaqiahActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });

        surahPilihanViewModel.getFirmanAyah().observe(getViewLifecycleOwner(), tvFirmanAyah::setText);
        surahPilihanViewModel.getFirmanIndo().observe(getViewLifecycleOwner(), tvFirmanIndo::setText);
        surahPilihanViewModel.getAlBaqarah().observe(getViewLifecycleOwner(), tvAlBaqarah::setText);
        surahPilihanViewModel.getAlHajj().observe(getViewLifecycleOwner(), tvAlHajj::setText);
        surahPilihanViewModel.getAsSajadah().observe(getViewLifecycleOwner(), tvAsSajadah::setText);
        surahPilihanViewModel.getArRahman().observe(getViewLifecycleOwner(), tvArRahman::setText);
        surahPilihanViewModel.getAlKahf().observe(getViewLifecycleOwner(), tvAlKahf::setText);
        surahPilihanViewModel.getYaseen().observe(getViewLifecycleOwner(), tvYaseen::setText);
        surahPilihanViewModel.getAlMuzammil().observe(getViewLifecycleOwner(), tvAlMuzammil::setText);
        surahPilihanViewModel.getAlMulk().observe(getViewLifecycleOwner(), tvAlMulk::setText);
        surahPilihanViewModel.getAlWaqiah().observe(getViewLifecycleOwner(), tvAlWaqiah::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}