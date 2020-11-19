package com.cosc3506.ccms.data.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Event{
        Club club;
        String name;
        String description;
        int ID;
        int capacity;
        String startTime;
        String endTime;
        String Location;
        String date;
        int cost;

    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    DatabaseReference reference;

    public Event(String name,  String description, int ID, int capacity, String startTime, String endTime, String Location, String date, int cost) {

        this.name = name;
        this.description = description;
        this.ID = ID;
        this.capacity = capacity;
        this.startTime = startTime;
        this.endTime = endTime;
        this.Location = Location;
        this.date = date;
        this.cost = cost;
    }

    public void newEvent(Event event, Club club){
        ArrayList<Event> events = club.getEvents();
        events.add(event);
        club.setEvents(events);
        reference = rootNode.getReference("Clubs/" + club.getID() + "/Events");
        reference.child(String.valueOf(event.getID())).setValue(event);
    }

    public void deleteEvent(Event event, Club club){
        ArrayList<Event> events = club.getEvents();
        events.remove(event);
        club.setEvents(events);
        reference = rootNode.getReference("Clubs/" + club.getID() + "/Events/" + event.getID());
        reference.removeValue();
    }

    //----------getters and setters

    public String getDate() {return date; }

    public void setDate(String date) {this.date = date;}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        this.Location = location;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
