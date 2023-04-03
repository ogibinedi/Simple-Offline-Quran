package com.obe.quranid2.module.bulughulmaram.model;

public class Bab {
    private int number;
    private String title, link, path;

    public Bab() {
    }

    public Bab(int number, String title, String link, String path) {
        this.number = number;
        this.title = title;
        this.link = link;
        this.path = path;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
