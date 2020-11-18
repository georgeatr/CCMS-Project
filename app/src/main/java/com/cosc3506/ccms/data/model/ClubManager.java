package com.cosc3506.ccms.data.model;



import java.util.ArrayList;

class ClubManager extends User {
    ArrayList<Club> managedClubs;

    public ClubManager(int studentNumber, String name, String phone, String email, ArrayList<Club> enrolledClubs, ArrayList<Club> managedClubs, String password) {
        super(studentNumber, name, phone, email, enrolledClubs, password);
        this.managedClubs = managedClubs;
    }

    public void newEvent(int ID, String name, Club club, String text, String address, String startDate, String endDate, int cost, int capacity){
        Event event = new Event(ID, name, club, text, address, startDate, endDate, cost, capacity);
        ArrayList<Event> events = club.getEvents();
        events.add(event);
        club.setEvents(events);
    }

    public void deleteEvent(Event event, Club club){
        ArrayList<Event> events = club.getEvents();
        events.remove(event);
        club.setEvents(events);
    }

    public void dropFromManager(Club club, ClubManager manager){
        //if there are other managers: TODO
        ArrayList<ClubManager> managers = club.getManagers();
        managers.remove(manager);
        club.setManagers(managers);
    }

    public void promoteToManager(Club club, ClubManager clubManager){
        ArrayList<ClubManager> managers = club.getManagers();
        managers.add(clubManager);
        club.setManagers(managers);
    }


    public ArrayList<Club> getManagedClubs() {
        return managedClubs;
    }

    public void setManagedClubs(ArrayList<Club> managedClubs) {
        this.managedClubs = managedClubs;
    }

}