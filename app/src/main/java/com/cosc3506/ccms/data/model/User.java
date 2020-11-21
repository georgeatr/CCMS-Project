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
    ArrayList<Club> enrolledClubs;
    ArrayList<Club> managedClubs;

    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    DatabaseReference reference;


    public User(String studentNumber, String name, String phone, String email, ArrayList<Club> enrolledClubs, String password, ArrayList<Club> managedClubs) {
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

    public void leaveClub(Club club, User user){
        enrolledClubs.remove(club);
        reference = rootNode.getReference("Users/"+ user.getStudentNumber() + "/Enrolled" + club.getID());
        reference.removeValue();
    }

    public void joinClub(Club club, User user){
        enrolledClubs.add(club);
        reference = rootNode.getReference("Users/"+ user.getStudentNumber() + "/Enrolled");
        reference.child(club.getID()).setValue(club.getID());
    }

    public void createClub(Club club, User user){
        joinClub(club, user);
        reference = rootNode.getReference("Clubs");
        reference.child(String.valueOf(club.getID())).setValue(club);
        //Make the creator a manager
        promoteToManager(club,user);
    }


    public boolean dropFromManager(Club club, User manager){
        ArrayList<User> managers = club.getManagers();
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
        reference = rootNode.getReference("Clubs/" + club.getID() + "/Managers");
        reference.child(String.valueOf(user.getStudentNumber())).setValue(user.getStudentNumber());
        ArrayList<User> managers = club.getManagers();
        managers.add(user);
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
