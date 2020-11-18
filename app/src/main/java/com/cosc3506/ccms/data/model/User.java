package com.cosc3506.ccms.data.model;

import java.lang.reflect.Array;

class User {
    int studentNumber;
    String name;
    String phone;
    String email;
    Array enrolledClubs;
    String password;

    public User(int studentNumber, String name, String phone, String email, Array enrolledClubs, Array password) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.enrolledClubs = enrolledClubs;
    }

    public void leaveClub(Club club){

    }

    public void joinClub(Club club){

    }

    public void createClub(int ID, String name, String description, ClubManager manager){
        ClubManager[] managers = {manager};
        Club club = new Club(ID, name, description, managers);
        joinClub(club);
    }



}
