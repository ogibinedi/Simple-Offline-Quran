package com.obe.quranid2.module.bulughulmaram.model;

public class Subab {
    private int number;
    private String title, hadits, indo;

    public Subab() {
    }

    public Subab(int number, String title, String hadits, String indo) {
        this.number = number;
        this.title = title;
        this.hadits = hadits;
        this.indo = indo;
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

    public String getHadits() {
        return hadits;
    }

    public void setHadits(String hadits) {
        this.hadits = hadits;
    }

    public String getIndo() {
        return indo;
    }

    public void setIndo(String indo) {
        this.indo = indo;
    }

}
