package com.cosc3506.ccms.data.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Event{
        int ID;
        String name;
        String description;
        String address;
        String startDate;
        String endDate;
        double cost;
        int capacity;

    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    DatabaseReference reference;

    public Event(int ID, String name,  String description, String address, String startDate,
                 String endDate, int cost, int capacity) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
        this.capacity = capacity;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
