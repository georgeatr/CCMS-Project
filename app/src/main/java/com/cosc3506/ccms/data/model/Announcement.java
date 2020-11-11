package com.cosc3506.ccms.data.model;

class Announcement {
    int ID;
    String name;
    Club club;
    String text;
    public Announcement(int ID, String name, Club club, String text) {
        this.ID = ID;
        this.name = name;
        this.club = club;
        this.text = text;
    }
}
