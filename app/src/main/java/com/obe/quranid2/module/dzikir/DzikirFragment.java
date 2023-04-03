package com.obe.quranid2.module.dzikir;

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
import com.obe.quranid2.databinding.FragmentDzikirBinding;
import com.obe.quranid2.module.ah.activities.AhActivity;
import com.obe.quranid2.module.dzikir.activities.AboutDzikirActivity;
import com.obe.quranid2.module.dzikir.activities.AboutDzikirDoaActivity;
import com.obe.quranid2.module.dzikir.activities.DzikirCounterActivity;
import com.obe.quranid2.module.dzikir.activities.DzikirPagiActivity;
import com.obe.quranid2.module.dzikir.activities.DzikirPetangActivity;
import com.obe.quranid2.module.dzikir.activities.DzikirSetelahSholatActivity;
import com.obe.quranid2.module.hisnulmuslim.activities.HisnulMuslimActivity;
import com.obe.quranid2.module.qibla.QiblaActivity;


public class DzikirFragment extends Fragment {

    private FragmentDzikirBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DzikirViewModel dzikirViewModel = new ViewModelProvider(this).get(DzikirViewModel.class);

        binding = FragmentDzikirBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView tvAbtDzikir = binding.tvTentangDzikir;
        final TextView tvDzikirPagi = binding.tvDzikirPagi;
        final TextView tvDzikirPetang = binding.tvDzikirPetang;
        final TextView tvDzikirFirman = binding.tvDzikirFirman;
        final TextView tvDzikirIndo = binding.tvDzikirIndo;
        final TextView tvAbtDzikirDoa = binding.tvTentangDzikirDoa;
        final TextView tvDzikirSetelahSholat = binding.tvDzikirSetelahShalat;
        final TextView tvTasbih = binding.tvTasbih;
        final TextView tvHisnulMudlim = binding.tvHisnulMuslim;
        final TextView tvAsmaulHusna = binding.tvAh;
        final TextView tvKompasKiblat = binding.tvKk;
        final ImageButton ibAbtDzikir = binding.ibTentangDzikirPp;
        final ImageButton ibDzikirPagi = binding.ibDzikirPagi;
        final ImageButton ibDzikirPetang = binding.ibDzikirPetang;
        final ImageButton ibDzikirDoa = binding.ibTentangDzikir;
        final ImageButton ibDzikirSetelahSholat = binding.ibDzikirSetelahSholat;
        final ImageButton ibTasbih = binding.ibTasbih;
        final ImageButton ibHisnulMuslim = binding.ibHisnulMuslim;
        final ImageButton ibAh = binding.ibAh;
        final ImageButton ibJs = binding.ibJs;

        ibAbtDzikir.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), AboutDzikirActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });

        ibDzikirPagi.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), DzikirPagiActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });

        ibDzikirPetang.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), DzikirPetangActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });

        ibDzikirDoa.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), AboutDzikirDoaActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });

        ibDzikirSetelahSholat.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), DzikirSetelahSholatActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });

        ibTasbih.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), DzikirCounterActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });

        ibJs.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), QiblaActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });

        ibHisnulMuslim.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), HisnulMuslimActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });

        ibAh.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), AhActivity.class);
            startActivity(intent);
            Animatoo.animateSwipeRight(requireContext());
        });

        dzikirViewModel.getAboutDzikir().observe(getViewLifecycleOwner(), tvAbtDzikir::setText);
        dzikirViewModel.getDzikirPagi().observe(getViewLifecycleOwner(), tvDzikirPagi::setText);
        dzikirViewModel.getDzikirPetang().observe(getViewLifecycleOwner(), tvDzikirPetang::setText);
        dzikirViewModel.getDzikirFirman().observe(getViewLifecycleOwner(), tvDzikirFirman::setText);
        dzikirViewModel.getDzikirIndo().observe(getViewLifecycleOwner(), tvDzikirIndo::setText);
        dzikirViewModel.getTentangDzikirDoa().observe(getViewLifecycleOwner(), tvAbtDzikirDoa::setText);
        dzikirViewModel.getDzikirSetelahSholat().observe(getViewLifecycleOwner(), tvDzikirSetelahSholat::setText);
        dzikirViewModel.getTasbih().observe(getViewLifecycleOwner(), tvTasbih::setText);
        dzikirViewModel.getHisnulMuslim().observe(getViewLifecycleOwner(), tvHisnulMudlim::setText);
        dzikirViewModel.getAsmaulHusna().observe(getViewLifecycleOwner(), tvAsmaulHusna::setText);
        dzikirViewModel.getKompasKiblat().observe(getViewLifecycleOwner(), tvKompasKiblat::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}