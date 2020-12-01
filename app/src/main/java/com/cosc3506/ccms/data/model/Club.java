package com.cosc3506.ccms.data.model;

import java.util.ArrayList;

public class Club {
        String ID;
        String budget;
        String remainingFunds;
        String room;
        String name;
        ArrayList<Event> events;
        String description;
        ArrayList<User> managers;


    //Reconstruct club from database
    public Club(String ID, String budget, String remainingFunds, String room, String name, ArrayList<Event> events, String description, ArrayList<User> managers) {

    public Club(String ID, String budget, String remainingFunds, String room, String name,
                ArrayList<Event> events, String description, ArrayList<String> managers) {

        this.ID = ID;
        this.budget = budget;
        this.remainingFunds = remainingFunds;
        this.room = room;
        this.name = name;
        this.events = events;
        this.description = description;
        this.managers = managers;
    }

    //Create new club
    public Club(String ID, String budget, String room, String name, String description, ArrayList<User> managers) {
        this.ID = ID;
        this.budget = budget;
        this.remainingFunds = remainingFunds;
        this.room = room;
        this.name = name;
        this.description = description;
        this.managers = managers;
    }

    //-----------getters and setters

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getRemainingFunds() {
        return remainingFunds;
    }

    public void setRemainingFunds(String remainingFunds) {
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

    public ArrayList<User> getManagers() {
        return managers;
    }

    public void setManagers(ArrayList<User> managers) {
        this.managers = managers;
    }
}
