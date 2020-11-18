package com.cosc3506.ccms.data.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class User {
    int studentNumber;
    String name;
    String phone;
    String email;
    ArrayList<Club> enrolledClubs;
    String password;
    ArrayList<Club> managedClubs;

    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    DatabaseReference reference;

    public User(int studentNumber, String name, String phone, String email, ArrayList<Club> enrolledClubs, String password, ArrayList<Club> managedClubs) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.enrolledClubs = enrolledClubs;
        this.password = password;
        this.managedClubs = managedClubs;
    }

    public User(int studentNumber, String name, String phone, String email, String password) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.enrolledClubs = enrolledClubs;
        this.password = password;
    }

    public void leaveClub(Club club){
        enrolledClubs.remove(club);
        reference = rootNode.getReference("Clubs/" + club.getID() + "/Managers");

    }

    public void joinClub(Club club, User user){
        enrolledClubs.add(club);
        reference = rootNode.getReference("Clubs/" + club.getID() + "/Managers");
        reference.child(String.valueOf(user.getStudentNumber())).setValue(user.getStudentNumber());
    }

    public void createClub(Club club, User user){
        ArrayList<User> managers = null;
        managers.add(user);
        joinClub(club, user);
        reference = rootNode.getReference("Clubs");
        reference.child(String.valueOf(club.ID)).setValue(club);
    }


    public void dropFromManager(Club club, User manager){
        //if there are other managers: TODO
        ArrayList<User> managers = club.getManagers();
        managers.remove(manager);
        club.setManagers(managers);
    }

    public void promoteToManager(Club club, User user){
        ArrayList<User> managers = club.getManagers();
        managers.add(user);
        club.setManagers(managers);
    }

    //------------getters and setters

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Club> getEnrolledClubs() {
        return enrolledClubs;
    }

    public void setEnrolledClubs(ArrayList<Club> enrolledClubs) {
        this.enrolledClubs = enrolledClubs;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Club> getManagedClubs() {
        return managedClubs;
    }

    public void setManagedClubs(ArrayList<Club> managedClubs) {
        this.managedClubs = managedClubs;
    }
}
