package com.cosc3506.ccms.data.model;


import java.lang.reflect.Array;

class ClubManager extends User {
    Array managedClubs;

    public ClubManager(int studentNumber, String name, String phone, String email, Array enrolledClubs, Array managedClubs) {
        super(studentNumber, name, phone, email, enrolledClubs);
        this.managedClubs = managedClubs;
    }

    public Array getManagedClubs() {
        return managedClubs;
    }

    public void setManagedClubs(Array managedClubs) {
        this.managedClubs = managedClubs;
    }
}
