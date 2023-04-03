package com.obe.quranid2.module.bulughulmaram.model;

public class BulughulMaram {
    int numberListBm;
    String titleListBm;

    public BulughulMaram(){}

    public BulughulMaram(int numberListBm, String titleListBm) {
        this.numberListBm = numberListBm;
        this.titleListBm = titleListBm;
    }

    public int getNumberListBm() {
        return numberListBm;
    }

    public void setNumberListBm(int numberListBm) {
        this.numberListBm = numberListBm;
    }

    public String getTitleListBm() {
        return titleListBm;
    }

    public void setTitleListBm(String titleListBm) {
        this.titleListBm = titleListBm;
    }
}
