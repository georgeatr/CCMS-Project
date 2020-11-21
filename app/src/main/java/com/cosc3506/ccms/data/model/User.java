package com.cosc3506.ccms.data.model;

import android.os.Parcel;
import android.widget.Toast;

import com.cosc3506.ccms.ClubActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    String studentNumber;
    String name;
    String phone;
    String email;
    String password;
    ArrayList<String> enrolledClubs;
    ArrayList<String> managedClubs;

    DatabaseReference reference;


    public User(String studentNumber, String name, String phone, String email, ArrayList<String> enrolledClubs, String password, ArrayList<String> managedClubs) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.enrolledClubs = enrolledClubs;
        this.password = password;
        this.managedClubs = managedClubs;
    }

    public User(String studentNumber, String name, String phone, String email, String password) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.enrolledClubs = enrolledClubs;
        this.password = password;
    }

    public void leaveClub(String clubID, User user){
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        enrolledClubs.remove(clubID);
        reference = rootNode.getReference("Users/"+ user.getStudentNumber() + "/Enrolled" + clubID);
        reference.removeValue();
    }

    public void joinClub(String clubID, User user){
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        enrolledClubs.add(clubID);
        reference = rootNode.getReference("Users/"+ user.getStudentNumber() + "/Enrolled");
        reference.child(clubID).setValue(clubID);
    }

    public void createClub(Club club, User user){
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        joinClub(club.getID(), user);
        reference = rootNode.getReference("Clubs");
        reference.child(String.valueOf(club.getID())).setValue(club);
        //Make the creator a manager
        promoteToManager(club,user);
    }


    public boolean dropFromManager(Club club, User manager){
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        ArrayList<String> managers = club.getManagers();
        if (managers.size()>1) { //check if there are other managers for that club
            managers.remove(manager);
            club.setManagers(managers);
            reference = rootNode.getReference("Clubs/" + club.getID() + "/Managers/" + manager.getStudentNumber());
            reference.removeValue();
            return true;
        }
        return false;

    }

    public void promoteToManager(Club club, User user){
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Clubs/" + club.getID() + "/Managers");
        reference.child(String.valueOf(user.getStudentNumber())).setValue(user.getStudentNumber());
        ArrayList<String> managers = club.getManagers();
        managers.add(user.getStudentNumber());
        club.setManagers(managers);
    }

    //------------getters and setters

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
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

    public ArrayList<String> getEnrolledClubs() {
        return enrolledClubs;
    }

    public void setEnrolledClubs(ArrayList<String> enrolledClubs) {
        this.enrolledClubs = enrolledClubs;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getManagedClubs() {
        return managedClubs;
    }

    public void setManagedClubs(ArrayList<String> managedClubs) {
        this.managedClubs = managedClubs;
    }
}
