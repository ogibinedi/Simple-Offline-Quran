package com.obe.quranid2.module.ruqya;

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
import com.obe.quranid2.module.ruqya.activities.JenisJenisSihirActivity;
import com.obe.quranid2.module.ruqya.activities.RqLongActivity;
import com.obe.quranid2.module.ruqya.activities.RqMiddleActivity;
import com.obe.quranid2.module.ruqya.activities.RqShortActivity;
import com.obe.quranid2.module.ruqya.activities.TentangHatiActivity;
import com.obe.quranid2.module.ruqya.activities.TentangRuqyaActivity;

import com.obe.quranid2.databinding.FragmentRuqyaBinding;

public class RuqyaFragment extends Fragment {

    private FragmentRuqyaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RuqyaViewModel ruqyaViewModel = new ViewModelProvider(this).get(RuqyaViewModel.class);

        binding = FragmentRuqyaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView tvTentangPenyakitHati = binding.tvTentangPenyakitHati;
        final TextView tvDefinisiRuqya = binding.tvDefinisiRq;
        final TextView tvJenisJenisSihir = binding.tvJenisSihir;
        final TextView tvDalilTentangHati = binding.tvRqFirman;
        final TextView tvDalilTentangHatiIndo = binding.tvRqIndo;
        final TextView tvRqShort = binding.tvRqShort;
        final TextView tvRqMiddle = binding.tvTqMiddle;
        final TextView tvRqLong = binding.tvRqLong;
        final ImageButton ibRqShort = binding.ibRqShort;
        final ImageButton ibRqMiddle = binding.ibRqMiddle;
        final ImageButton ibRqLong = binding.ibRqLong;
        final ImageButton ibTentangPenyakitHati = binding.ibTentangPenyakitHati;
        final ImageButton ibDefinisiRuqya = binding.ibDefinisiRq;
        final ImageButton ibJenisJenisSihir = binding.ibJenisSihir;

        ibRqShort.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), RqShortActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });

        ibRqMiddle.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), RqMiddleActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });

        ibRqLong.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), RqLongActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });

        ibTentangPenyakitHati.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), TentangHatiActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });

        ibDefinisiRuqya.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), TentangRuqyaActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });

        ibJenisJenisSihir.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), JenisJenisSihirActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });

        ruqyaViewModel.getTentangPenyakitHati().observe(getViewLifecycleOwner(), tvTentangPenyakitHati::setText);
        ruqyaViewModel.getDefinisiRuqya().observe(getViewLifecycleOwner(), tvDefinisiRuqya::setText);
        ruqyaViewModel.getJenisJenisSihir().observe(getViewLifecycleOwner(), tvJenisJenisSihir::setText);
        ruqyaViewModel.getDalilTentangHati().observe(getViewLifecycleOwner(), tvDalilTentangHati::setText);
        ruqyaViewModel.getDalilTentangHatiIndo().observe(getViewLifecycleOwner(), tvDalilTentangHatiIndo::setText);
        ruqyaViewModel.getRuqyaShort().observe(getViewLifecycleOwner(), tvRqShort::setText);
        ruqyaViewModel.getRuqyaMiddle().observe(getViewLifecycleOwner(), tvRqMiddle::setText);
        ruqyaViewModel.getRuqyaLong().observe(getViewLifecycleOwner(), tvRqLong::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}