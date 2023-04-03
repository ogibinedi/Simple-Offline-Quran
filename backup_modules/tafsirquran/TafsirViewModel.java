package com.obe.quranid2.module.tafsirquran;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TafsirViewModel extends ViewModel {
    private final MutableLiveData<String> mArabicText, mIndoText, mAboutTafsir, mTafsirQuran, mBulughulMaram;

    public TafsirViewModel(){
        mArabicText = new MutableLiveData<>();
        mIndoText = new MutableLiveData<>();
        mAboutTafsir = new MutableLiveData<>();
        mTafsirQuran = new MutableLiveData<>();
        mBulughulMaram = new MutableLiveData<>();

        mArabicText.setValue("\u064a\u0670\u0653\u0627\u064e\u064a\u0651\u064f\u0647\u064e\u0627 \u0627\u0644\u0651\u064e\u0630\u0650\u064a\u0652\u0646\u064e \u0627\u0670\u0645\u064e\u0646\u064f\u0648\u0652\u0653\u0627 \u0627\u064e\u0637\u0650\u064a\u0652\u0639\u064f\u0648\u0627 \u0627\u0644\u0644\u0651\u0670\u0647\u064e \u0648\u064e\u0627\u064e\u0637\u0650\u064a\u0652\u0639\u064f\u0648\u0627 \u0627\u0644\u0631\u0651\u064e\u0633\u064f\u0648\u0652\u0644\u064e \u0648\u064e\u0627\u064f\u0648\u0644\u0650\u0649 \u0627\u0644\u0652\u0627\u064e\u0645\u0652\u0631\u0650 \u0645\u0650\u0646\u0652\u0643\u064f\u0645\u0652\u06da \u0641\u064e\u0627\u0650\u0646\u0652 \u062a\u064e\u0646\u064e\u0627\u0632\u064e\u0639\u0652\u062a\u064f\u0645\u0652 \u0641\u0650\u064a\u0652 \u0634\u064e\u064a\u0652\u0621\u064d \u0641\u064e\u0631\u064f\u062f\u0651\u064f\u0648\u0652\u0647\u064f \u0627\u0650\u0644\u064e\u0649 \u0627\u0644\u0644\u0651\u0670\u0647\u0650 \u0648\u064e\u0627\u0644\u0631\u0651\u064e\u0633\u064f\u0648\u0652\u0644\u0650 \u0627\u0650\u0646\u0652 \u0643\u064f\u0646\u0652\u062a\u064f\u0645\u0652 \u062a\u064f\u0624\u0652\u0645\u0650\u0646\u064f\u0648\u0652\u0646\u064e \u0628\u0650\u0627\u0644\u0644\u0651\u0670\u0647\u0650 \u0648\u064e\u0627\u0644\u0652\u064a\u064e\u0648\u0652\u0645\u0650 \u0627\u0644\u0652\u0627\u0670\u062e\u0650\u0631\u0650\u06d7 \u0630\u0670\u0644\u0650\u0643\u064e \u062e\u064e\u064a\u0652\u0631\u064c \u0648\u0651\u064e\u0627\u064e\u062d\u0652\u0633\u064e\u0646\u064f \u062a\u064e\u0623\u0652\u0648\u0650\u064a\u0652\u0644\u064b\u0627");
        mIndoText.setValue("Wahai orang-orang yang beriman! Taatilah Allah dan taatilah Rasul (Muhammad), dan Ulil Amri (pemegang kekuasaan) di antara kamu. Kemudian, jika kamu berbeda pendapat tentang sesuatu, maka kembalikanlah kepada Allah (Al-Qur'an) dan Rasul (sunnahnya), jika kamu beriman kepada Allah dan hari kemudian. Yang demikian itu lebih utama (bagimu) dan lebih baik akibatnya. (QS. AN-Nisa : 59)");
        mAboutTafsir.setValue("Tentang Tafsir");
        mTafsirQuran.setValue("Tafsir Qur'an");
        mBulughulMaram.setValue("Bulughul Maram");
    }

    public LiveData<String> getArabicText(){ return mArabicText; }

    public LiveData<String> getIndoText(){ return mIndoText; }

    public LiveData<String> getAboutTafsir(){ return  mAboutTafsir; }

    public LiveData<String> getTafsirQuran(){ return mTafsirQuran; }

    public LiveData<String> getBulughulMaram(){ return mBulughulMaram; }
}
