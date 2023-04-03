package com.obe.quranid2.module.surahpilihan.model;

public class Surah {
    private String verseId, ayahText, readText, indoText, infoSurah, audio;

    public Surah(){}

    public Surah(String verseId, String ayahText, String readText, String indoText, String infoSurah, String audio) {
        this.verseId = verseId;
        this.ayahText = ayahText;
        this.readText = readText;
        this.indoText = indoText;
        this.infoSurah = infoSurah;
        this.audio = audio;
    }

    public String getVerseId() {
        return verseId;
    }

    public void setVerseId(String verseId) {
        this.verseId = verseId;
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

    public String getInfoSurah() {
        return infoSurah;
    }

    public void setInfoSurah(String infoSurah) {
        this.infoSurah = infoSurah;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}
