package com.cosc3506.ccms.data.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Club {
        int ID;
        int Budget;
        int remainingFunds;
        String room;
        String name;
        ArrayList<Event> events;
        String description;
        ArrayList<ClubManager> managers;

    public Club(int ID, int budget, int remainingFunds, String room, String name, String description, ArrayList<ClubManager> managers) {
        this.ID = ID;
        Budget = budget;
        this.remainingFunds = remainingFunds;
        this.room = room;
        this.name = name;
        this.description = description;
        this.managers = managers;
    }

    //-----------getters and setters

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getBudget() {
        return Budget;
    }

    public void setBudget(int budget) {
        Budget = budget;
    }

    public int getRemainingFunds() {
        return remainingFunds;
    }

    public void setRemainingFunds(int remainingFunds) {
        this.remainingFunds = remainingFunds;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<ClubManager> getManagers() {
        return managers;
    }

    public void setManagers(ArrayList<ClubManager> managers) {
        this.managers = managers;
    }
}
