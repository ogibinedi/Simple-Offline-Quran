package com.obe.quranid2.module.tafsirquran.model;

public class Tafsir {
    private int number;
    private String surahName, arabicText;

    public Tafsir(){}

    public Tafsir(int number, String surahName, String arabicText) {
        this.number = number;
        this.surahName = surahName;
        this.arabicText = arabicText;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSurahName() {
        return surahName;
    }

    public void setSurahName(String surahName) {
        this.surahName = surahName;
    }

    public String getArabicText() {
        return arabicText;
    }

    public void setArabicText(String arabicText) {
        this.arabicText = arabicText;
    }
}
