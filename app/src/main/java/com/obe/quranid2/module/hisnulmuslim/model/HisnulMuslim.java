package com.obe.quranid2.module.hisnulmuslim.model;

public class HisnulMuslim {
    private int imageDua;
    private String shortNumber, title, category;

    public HisnulMuslim(int imageDua, String shortNumber, String title, String category) {
        this.imageDua = imageDua;
        this.shortNumber = shortNumber;
        this.title = title;
        this.category = category;
    }

    public int getImageDua() {
        return imageDua;
    }

    public void setImageDua(int imageDua) {
        this.imageDua = imageDua;
    }

    public String getShortNumber() {
        return shortNumber;
    }

    public void setShortNumber(String shortNumber) {
        this.shortNumber = shortNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
