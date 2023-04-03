package com.obe.quranid2.module.juzamma;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class JuzammaViewModel extends ViewModel {
    private final MutableLiveData<String>mFirmanAyah, mFirmanIndo;

    public JuzammaViewModel(){
        mFirmanAyah = new MutableLiveData<>();
        mFirmanAyah.setValue("\u0627\u0650\u0646\u0651\u064e \u0647\u0670\u0630\u064e\u0627 \u0627\u0644\u0652\u0642\u064f\u0631\u0652\u0627\u0670\u0646\u064e \u064a\u064e\u0647\u0652\u062f\u0650\u064a\u0652 \u0644\u0650\u0644\u0651\u064e\u062a\u0650\u064a\u0652 \u0647\u0650\u064a\u064e \u0627\u064e\u0642\u0652\u0648\u064e\u0645\u064f \u0648\u064e\u064a\u064f\u0628\u064e\u0634\u0651\u0650\u0631\u064f \u0627\u0644\u0652\u0645\u064f\u0624\u0652\u0645\u0650\u0646\u0650\u064a\u0652\u0646\u064e \u0627\u0644\u0651\u064e\u0630\u0650\u064a\u0652\u0646\u064e \u064a\u064e\u0639\u0652\u0645\u064e\u0644\u064f\u0648\u0652\u0646\u064e \u0627\u0644\u0635\u0651\u0670\u0644\u0650\u062d\u0670\u062a\u0650 \u0627\u064e\u0646\u0651\u064e \u0644\u064e\u0647\u064f\u0645\u0652 \u0627\u064e\u062c\u0652\u0631\u064b\u0627 \u0643\u064e\u0628\u0650\u064a\u0652\u0631\u064b\u0627\u06d9");
        mFirmanIndo = new MutableLiveData<>();
        mFirmanIndo.setValue("Sungguh, Al-Qur'an ini memberi petunjuk ke (jalan) yang paling lurus dan memberi kabar gembira kepada orang mukmin yang mengerjakan kebajikan, bahwa mereka akan mendapat pahala yang besar. (QS : Al - Israa : 9)");
    }

    public LiveData<String> getFiramnAyah(){
        return mFirmanAyah;
    }
    public LiveData<String> getFirmanIndo(){
        return mFirmanIndo;
    }
}
