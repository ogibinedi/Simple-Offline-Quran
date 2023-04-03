package com.obe.quranid2.module.tafsirquran.model;

public class TafsirList {
    private int subNumber;
    private String subTitle;

    public TafsirList(int subNumber, String subTitle) {
        this.subNumber = subNumber;
        this.subTitle = subTitle;
    }

    public int getSubNumber() {
        return subNumber;
    }

    public void setSubNumber(int subNumber) {
        this.subNumber = subNumber;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
