package com.obe.quranid2.module.dzikir;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DzikirViewModel extends ViewModel {

    private final MutableLiveData<String> mTentangDzikir, mDzikirPagi, mDzikirPetang;
    private final MutableLiveData<String> mDzikirFirman, mDzikirIndo, mAsmaulHusna, mKompasKiblat;
    private final MutableLiveData<String> mTentangDzikirDoa, mDzikirSetelahSholat, mTasbih, mHisnulMuslim;

    public DzikirViewModel() {
        mTentangDzikir = new MutableLiveData<>();
        mDzikirPagi = new MutableLiveData<>();
        mDzikirPetang = new MutableLiveData<>();
        mDzikirFirman = new MutableLiveData<>();
        mDzikirIndo = new MutableLiveData<>();
        mTentangDzikirDoa = new MutableLiveData<>();
        mDzikirSetelahSholat = new MutableLiveData<>();
        mTasbih = new MutableLiveData<>();
        mHisnulMuslim = new MutableLiveData<>();
        mAsmaulHusna = new MutableLiveData<>();
        mKompasKiblat = new MutableLiveData<>();

        mTentangDzikir.setValue("Dzikr Pagi Sore");
        mDzikirPagi.setValue("Dzikr Pagi");
        mDzikirPetang.setValue("Dzikr Petang");
        mDzikirFirman.setValue("يَا أَيُّهَا الَّذِينَ آمَنُوا اذْكُرُوا اللَّهَ ذِكْرًا كَثِيرًا , وَسَبِّحُوهُ بُكْرَةً وَأَصِيل");
        mDzikirIndo.setValue("“Hai orang-orang yang beriman, berdzikirlah (dengan menyebut nama) Allah, dzikir yang sebanyak-banyaknya. Dan bertasbihlah kepada-Nya di waktu pagi dan petang,” (QS. Al-Ahzab: 41-42).");
        mTentangDzikirDoa.setValue("Dzikr dan Do'a");
        mDzikirSetelahSholat.setValue("Dzikr Set.. Sholat");
        mTasbih.setValue("Dzikr Counter");
        mHisnulMuslim.setValue("Hisnul Muslim");
        mAsmaulHusna.setValue("Asma'ul Husna");
        mKompasKiblat.setValue("Kompas Kiblat");
    }

    public LiveData<String> getAboutDzikir() {
        return mTentangDzikir;
    }

    public LiveData<String> getDzikirPagi(){
        return mDzikirPagi;
    }

    public LiveData<String> getDzikirPetang(){
        return mDzikirPetang;
    }

    public LiveData<String> getDzikirFirman(){ return mDzikirFirman; }

    public LiveData<String> getDzikirIndo(){ return mDzikirIndo; }

    public LiveData<String> getTentangDzikirDoa(){ return  mTentangDzikirDoa; }

    public LiveData<String> getDzikirSetelahSholat(){ return mDzikirSetelahSholat; }

    public LiveData<String> getTasbih(){ return mTasbih; }

    public LiveData<String> getHisnulMuslim(){ return mHisnulMuslim; }

    public LiveData<String> getAsmaulHusna(){ return mAsmaulHusna; }

    public LiveData<String> getKompasKiblat(){ return mKompasKiblat; }
}