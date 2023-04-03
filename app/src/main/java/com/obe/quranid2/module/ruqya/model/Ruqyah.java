package com.obe.quranid2.module.ruqya.model;

public class Ruqyah {
    private  String number, ayahText, readText, indoText;

    public Ruqyah(){
    }

    public Ruqyah(String number, String ayahText, String readText, String indoText) {
        this.number = number;
        this.ayahText = ayahText;
        this.readText = readText;
        this.indoText = indoText;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAyahText() {
        return ayahText;
    }

    public void setAyahText(String ayahText) {
        this.ayahText = ayahText;
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
}
