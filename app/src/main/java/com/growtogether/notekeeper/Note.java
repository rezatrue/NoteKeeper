package com.growtogether.notekeeper;

import java.util.ArrayList;

public class Note {

    private String title;
    private String note;
    private String date;
    private String uid;


    public Note() {
    }

    public Note(String title, String note, String date, String uid) {
        this.title = title;
        this.note = note;
        this.date = date;
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
