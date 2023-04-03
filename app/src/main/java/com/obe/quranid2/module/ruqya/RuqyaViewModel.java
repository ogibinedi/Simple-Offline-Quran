package com.obe.quranid2.module.ruqya;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RuqyaViewModel extends ViewModel {

    private final MutableLiveData<String> mTentangPenyakitHati, mTentangRuqya, mJenisJenisSihir;
    private final MutableLiveData<String> mDalilTentangHati, mDalilIndo;
    private final MutableLiveData<String> mRqShort, mRqMiddle, mRqLong;

    public RuqyaViewModel() {
        mTentangPenyakitHati = new MutableLiveData<>();
        mTentangRuqya = new MutableLiveData<>();
        mJenisJenisSihir = new MutableLiveData<>();
        mDalilTentangHati = new MutableLiveData<>();
        mDalilIndo = new MutableLiveData<>();
        mRqShort = new MutableLiveData<>();
        mRqMiddle = new MutableLiveData<>();
        mRqLong = new MutableLiveData<>();

        mTentangPenyakitHati.setValue("Tentang Penyakit Hati");
        mTentangRuqya.setValue("Definisi Ruqya");
        mJenisJenisSihir.setValue("Jenis-jenis Sihir");
        mDalilTentangHati.setValue("أَلاَ وَإِنَّ فِى الْجَسَدِ مُضْغَةً إِذَا صَلَحَتْ صَلَحَ الْجَسَدُ كُلُّهُ ، وَإِذَا فَسَدَتْ فَسَدَ الْجَسَدُ كُلُّهُ . أَلاَ وَهِىَ الْقَلْبُ");
        mDalilIndo.setValue("“Ingatlah bahwa di dalam jasad itu ada segumpal daging. Jika ia baik, maka baik pula seluruh jasad. Jika ia rusak, maka rusak pula seluruh jasad. Ketahuilah bahwa ia adalah hati (jantung)” (HR. Bukhari no. 52 dan Muslim no. 1599).");
        mRqShort.setValue("Ruqya Pendek");
        mRqMiddle.setValue("Ruqya Sedang");
        mRqLong.setValue("Ruqya Panjang");
    }

    public LiveData<String> getTentangPenyakitHati() {
        return mTentangPenyakitHati;
    }

    public LiveData<String> getDefinisiRuqya(){
        return mTentangRuqya;
    }

    public LiveData<String> getJenisJenisSihir(){
        return mJenisJenisSihir;
    }

    public LiveData<String> getDalilTentangHati(){ return mDalilTentangHati; }

    public LiveData<String> getDalilTentangHatiIndo(){ return mDalilIndo; }

    public LiveData<String> getRuqyaShort(){ return  mRqShort; }

    public LiveData<String> getRuqyaMiddle(){ return mRqMiddle; }

    public LiveData<String> getRuqyaLong(){ return mRqLong; }
}