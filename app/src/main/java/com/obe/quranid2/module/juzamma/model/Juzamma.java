package com.obe.quranid2.module.juzamma.model;

public class Juzamma {
    private int imageUrl;
    private String surahNumber,  surahText, totalAyah, type;

    public Juzamma(int imageUrl, String surahNumber, String surahText, String totalAyah, String type) {
        this.imageUrl = imageUrl;
        this.surahNumber = surahNumber;
        this.surahText = surahText;
        this.totalAyah = totalAyah;
        this.type = type;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSurahNumber() {
        return surahNumber;
    }

    public void setSurahNumber(String surahNumber) {
        this.surahNumber = surahNumber;
    }

    public String getSurahText() {
        return surahText;
    }

    public void setSurahText(String surahText) {
        this.surahText = surahText;
    }

    public String getTotalAyah() {
        return totalAyah;
    }

    public void setTotalAyah(String totalAyah) {
        this.totalAyah = totalAyah;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
