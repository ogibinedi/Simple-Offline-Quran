package com.obe.quranid2.module.dzikir.model;

public class Dzikir {
    private String number, arabicText, indoText, refHadits;

    public Dzikir(){}

    public Dzikir(String number, String arabicText, String indoText, String refHadits) {
        this.number = number;
        this.arabicText = arabicText;
        this.indoText = indoText;
        this.refHadits = refHadits;
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

    public String getIndoText() {
        return indoText;
    }

    public void setIndoText(String indoText) {
        this.indoText = indoText;
    }

    public String getRefHadits() {
        return refHadits;
    }

    public void setRefHadits(String refHadits) {
        this.refHadits = refHadits;
    }
}
