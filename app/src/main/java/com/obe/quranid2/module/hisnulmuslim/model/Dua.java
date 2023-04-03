package com.obe.quranid2.module.hisnulmuslim.model;

public class Dua {
    private String number, arabicText, readText, indoText, refs;

    public Dua(){}

    public Dua(String number, String arabicText, String readText, String indoText, String refs) {
        this.number = number;
        this.arabicText = arabicText;
        this.readText = readText;
        this.indoText = indoText;
        this.refs = refs;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getArabicText() {
        return arabicText;
    }

    public void setArabicText(String arabicText) {
        this.arabicText = arabicText;
    }

    public String getReadText() {
        return readText;
    }

    public void setReadText(String readText) {
        this.readText = readText;
    }

    public String getIndoText() {
        return indoText;
    }

    public void setIndoText(String indoText) {
        this.indoText = indoText;
    }

    public String getRefs() {
        return refs;
    }

    public void setRefs(String refs) {
        this.refs = refs;
    }
}
