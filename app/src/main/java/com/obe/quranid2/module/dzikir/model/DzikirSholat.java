package com.obe.quranid2.module.dzikir.model;

public class DzikirSholat {
    private int number;
    private String title, arabic, latin, indo, info;

    public DzikirSholat(){}

    public DzikirSholat(int number, String title, String arabic, String latin, String indo, String info) {
        this.number = number;
        this.title = title;
        this.arabic = arabic;
        this.latin = latin;
        this.indo = indo;
        this.info = info;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public String getLatin() {
        return latin;
    }

    public void setLatin(String latin) {
        this.latin = latin;
    }

    public String getIndo() {
        return indo;
    }

    public void setIndo(String indo) {
        this.indo = indo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
