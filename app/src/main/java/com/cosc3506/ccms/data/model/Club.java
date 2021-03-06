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
        ArrayList<String> members;



    public Club(String ID, String budget, ArrayList<String> transactions, String room, String name,
                ArrayList<Event> events, String description, ArrayList<String> managers, ArrayList<String> members) {
        this.ID = ID;
        this.budget = budget;
        this.transactions = transactions;
        this.room = room;
        this.name = name;
        this.events = events;
        this.description = description;
        this.managers = managers;
        this.members = members;
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
        DatabaseReference reference;
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        double budgetInt = Double.parseDouble(budget);
        budgetInt = budgetInt + money;
        setBudget(String.valueOf(budgetInt));
        reference = rootNode.getReference("Clubs/" + ID + "/transactions");
        reference.child(transactionName).setValue("+ $" + money);
        transactions.add(transactionName + ": + $" + money);
        reference = rootNode.getReference("Clubs/" + ID );
        reference.child("budget").setValue(String.valueOf(budgetInt));
    }

    public void subtractFunds(double money, String transactionName) {
        DatabaseReference reference;
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        double budgetInt = Double.parseDouble(budget);
        budgetInt = budgetInt - money;
        setBudget(String.valueOf(budgetInt));
        reference = rootNode.getReference("Clubs/" + ID + "/transactions");
        reference.child(transactionName).setValue("- $" + money);
        transactions.add(transactionName + ": - $" + money);
        reference = rootNode.getReference("Clubs/" + ID );
        reference.child("budget").setValue(String.valueOf(budgetInt));
    }

    public void newEvent(Event event){
        DatabaseReference reference;
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        ArrayList<Event> events = getEvents();
        events.add(event);
        setEvents(events);
        reference = rootNode.getReference("Clubs/" + getID() + "/events");
        reference.child(String.valueOf(event.getID())).setValue(event);
        String cost = String.format("%.2f", Double.parseDouble(event.getCost()));
        subtractFunds(Double.parseDouble(cost), "Event: " + event.getID());
    }

    public void deleteEvent(Event event){
        DatabaseReference reference;
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        ArrayList<Event> events = getEvents();
        events.remove(event);
        setEvents(events);
        reference = rootNode.getReference("Clubs/" + getID() + "/events/" + event.getID());
        reference.removeValue();
    }

    public void kickUser(String userStudentNumber){
        DatabaseReference reference;
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        members.remove(userStudentNumber);
        reference = rootNode.getReference("Users/"+ userStudentNumber + "/enrolled/" + getID());
        reference.removeValue();
        reference = rootNode.getReference("Clubs/"+ getID() + "/members/" + userStudentNumber);
        reference.removeValue();
    }

    public void promoteToManager(String userStudentNumber){
        DatabaseReference reference;
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Users/"+ userStudentNumber + "/managed");
        reference.child(getID()).setValue(getID());
        reference = rootNode.getReference("Clubs/" + getID() + "/managers");
        reference.child(userStudentNumber).setValue(userStudentNumber);
        ArrayList<String> managers = getManagers();
        managers.add(userStudentNumber);
        setManagers(managers);
    }

    public boolean dropFromManager(String managerStudentNumber){
        DatabaseReference reference;
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        ArrayList<String> managers = getManagers();
        if (managers.size()>1) { //check if there are other managers for that club
            managers.remove(managerStudentNumber);
            setManagers(managers);
            reference = rootNode.getReference("Users/" + managerStudentNumber + "/managed/"
                    + ID);
            reference.removeValue();
            reference = rootNode.getReference("Clubs/" + getID() + "/managers/"
                    + managerStudentNumber);
            reference.removeValue();
            return true;
        }
        return false;
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

    public ArrayList<String> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }
}
