package com.obe.quranid2.module.tafsirquran.model;

public class TafsirSub {
    private Integer subNumber;
    private String subTitle, subName;

    public TafsirSub(Integer subNumber, String subTitle, String subName) {
        this.subNumber = subNumber;
        this.subTitle = subTitle;
        this.subName = subName;
    }

    public TafsirSub() {

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

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }
}
