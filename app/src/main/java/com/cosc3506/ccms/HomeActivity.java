package com.cosc3506.ccms;

import android.content.Intent;
import android.os.Bundle;

import com.cosc3506.ccms.data.model.Club;
import com.cosc3506.ccms.data.model.Event;
import com.cosc3506.ccms.data.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("Clubs");
        int ID = 123;
        double budget = 1.23;
        double remainingFunds = .69;
        String room = "4h";
        String name = "FloorGang";
        ArrayList<Event> events = new ArrayList<>();
        String description = "19 year olds";
        ArrayList<User> managers = new ArrayList<>();

        Club club = new Club(ID, budget, remainingFunds, room, name, events, description, managers);

        //Event event = new Event(3, "name", club, "ree", "uno", "dos", "cuatro", 50, 4);
        //events.add(event);
        //club.setEvents(events);
        reference.child(String.valueOf(ID)).setValue(club);

        //TODO have the logged in User object

        //--PUT THIS IN CREATE CLUB
        /*
        int ID = XtextField.getText();
        float budget = XtextField.getText();
        String room = XtextField.getText();
        String name = XtextField.getText();
        ArrayList<Event> events = XtextField.getText();
        String description = XtextField.getText();

        Club club = new Club(ID, budget, room, name, description);
        user.createClub(club, user);
        */

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void onClickSettings(View view) {
        startActivity(new Intent(HomeActivity.this,SettingsActivity.class));
    }
}