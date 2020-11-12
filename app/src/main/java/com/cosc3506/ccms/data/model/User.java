package com.cosc3506.ccms.data.model;

import java.util.ArrayList;

class User {
    int studentNumber;
    String name;
    String phone;
    String email;
    ArrayList<Club> enrolledClubs;
    String password;

    public User(int studentNumber, String name, String phone, String email, ArrayList<Club> enrolledClubs, String password) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.enrolledClubs = enrolledClubs;
        this.password = password;
    }

    public void leaveClub(Club club){
        enrolledClubs.remove(club);
    }

    public void joinClub(Club club){
        enrolledClubs.add(club);
    }

    public void createClub(int ID, int budget, int remainingFunds, String room, String name, String description, ClubManager manager){
        ArrayList<ClubManager> managers = null;
        managers.add(manager);
        Club club = new Club(ID, budget, remainingFunds, room, name, description, managers);
        joinClub(club);
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
}
