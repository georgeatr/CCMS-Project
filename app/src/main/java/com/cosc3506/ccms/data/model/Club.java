package com.cosc3506.ccms.data.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class Club implements Serializable {
        String ID;
        String budget;
        ArrayList<String> transactions;
        String room;
        String name;
        ArrayList<Event> events;
        String description;
        ArrayList<String> managers;

    DatabaseReference reference;

    public Club(String ID, String budget, ArrayList<String> transactions, String room, String name,
                ArrayList<Event> events, String description, ArrayList<String> managers) {
        this.ID = ID;
        this.budget = budget;
        this.transactions = transactions;
        this.room = room;
        this.name = name;
        this.events = events;
        this.description = description;
        this.managers = managers;
    }

    public Club(String ID, String budget, String room, String name, String description) {
        this.ID = ID;
        this.budget = budget;
        this.transactions = transactions;
        this.room = room;
        this.name = name;
        this.description = description;
        this.managers = managers;
    }

    public void addFunds(double money, String transactionName) {
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        double budgetInt = Double.parseDouble(budget);
        budgetInt = budgetInt + money;
        setBudget(String.valueOf(budgetInt));
        reference = rootNode.getReference("Clubs/" + ID + "/Transactions");
        reference.child(transactionName).setValue("+ $" + money);
        transactions.add(transactionName + ": + $" + money);
        reference = rootNode.getReference("Clubs/" + ID );
        reference.child("budget").setValue(String.valueOf(budgetInt));
    }

    public void subtractFunds(double money, String transactionName) {
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        double budgetInt = Double.parseDouble(budget);
        budgetInt = budgetInt - money;
        setBudget(String.valueOf(budgetInt));
        reference = rootNode.getReference("Clubs/" + ID + "/Transactions");
        reference.child(transactionName).setValue("- $" + money);
        transactions.add(transactionName + ": - $" + money);
        reference = rootNode.getReference("Clubs/" + ID );
        reference.child("budget").setValue(String.valueOf(budgetInt));
    }

    public void newEvent(Event event){
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        ArrayList<Event> events = getEvents();
        events.add(event);
        setEvents(events);
        reference = rootNode.getReference("Clubs/" + getID() + "/Events");
        reference.child(String.valueOf(event.getID())).setValue(event);
        String cost = String.format("%.2f", Double.parseDouble(event.getCost()));
        subtractFunds(Double.parseDouble(cost), "Event: " + event.getName());
    }

    public void deleteEvent(Event event){
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        ArrayList<Event> events = getEvents();
        events.remove(event);
        setEvents(events);
        reference = rootNode.getReference("Clubs/" + getID() + "/Events/" + event.getID());
        reference.removeValue();
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

    public ArrayList<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<String> transactions) {
        this.transactions = transactions;
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

    public ArrayList<String> getManagers() {
        return managers;
    }

    public void setManagers(ArrayList<String> managers) {
        this.managers = managers;
    }
}
