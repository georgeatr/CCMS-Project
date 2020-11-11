package com.cosc3506.ccms.data.model;

public class Event extends Announcement{
        String address;
        String startDate;
        String endDate;
        int cost;
        int capacity;

    public Event(int ID, String name, Club club, String text, String address, String startDate, String endDate, int cost, int capacity) {
        super(ID, name, club, text);
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
        this.capacity = capacity;
    }
}
