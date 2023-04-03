package com.obe.quranid2.module.ah.model;

public class AsmaulHusna {
    private int number;
    private String arabicText, readText, indoText, definitionText, referenceText;

    public AsmaulHusna(){}

    public AsmaulHusna(int number, String arabicText, String readText, String indoText, String definitionText, String referenceText) {
        this.number = number;
        this.arabicText = arabicText;
        this.readText = readText;
        this.indoText = indoText;
        this.definitionText = definitionText;
        this.referenceText = referenceText;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
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

    public String getDefinitionText() {
        return definitionText;
    }

    public void setDefinitionText(String definitionText) {
        this.definitionText = definitionText;
    }

    public String getReferenceText() {
        return referenceText;
    }

    public void setReferenceText(String referenceText) {
        this.referenceText = referenceText;
    }
}
