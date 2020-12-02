package com.cosc3506.ccms.data.model;

import android.widget.Toast;

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




    public User(String studentNumber, String name, String phone, String email,
                ArrayList<String> enrolledClubs, String password, ArrayList<String> managedClubs) {
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
        DatabaseReference reference;
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        enrolledClubs.remove(clubID);
        reference = rootNode.getReference("Users/"+ user.getStudentNumber() + "/enrolled/" + clubID);
        reference.removeValue();
        reference = rootNode.getReference("Clubs/"+ clubID + "/members/" + user.getStudentNumber());
        reference.removeValue();
    }

    public void joinClub(String clubID, User user){
        DatabaseReference reference;
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        enrolledClubs.add(clubID);
        reference = rootNode.getReference("Users/"+ user.getStudentNumber() + "/enrolled");
        reference.child(clubID).setValue(clubID);
        reference = rootNode.getReference("Clubs/"+ clubID + "/members");
        reference.child(user.getStudentNumber()).setValue(user.getStudentNumber());
    }

    public Club joinCreatedClub(Club club, User user){
        DatabaseReference reference;
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        enrolledClubs.add(club.getID());
        reference = rootNode.getReference("Users/"+ user.getStudentNumber() + "/enrolled");
        reference.child(club.getID()).setValue(club.getID());
        reference = rootNode.getReference("Clubs/"+ club.getID() + "/members");
        reference.child(user.getStudentNumber()).setValue(user.getStudentNumber());
        ArrayList<String> members = club.getMembers();
        members.add(user.getStudentNumber());
        club.setMembers(members);

        return club;
    }


    public void createClub(Club club, User user, String newBudget){
        DatabaseReference reference;
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        club = joinCreatedClub(club, user);
        reference = rootNode.getReference("Clubs");
        reference.child(club.getID()).setValue(club);
        club.addFunds(Double.parseDouble(newBudget), "Initial funds");
        //Make the creator a manager
        club.promoteToManager(user);
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
