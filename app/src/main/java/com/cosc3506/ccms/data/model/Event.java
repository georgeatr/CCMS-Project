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
        String startDate;
        String endDate;
        String Location;
        int cost;

    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    DatabaseReference reference;

    public Event(String name,  String description, int ID, int capacity, String startDate, String endDate, String Location, int cost) {

        this.name = name;
        this.description = description;
        this.ID = ID;
        this.capacity = capacity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.Location = Location;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
