package com.cosc3506.ccms;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.cosc3506.ccms.data.model.Club;
import com.cosc3506.ccms.data.model.Event;
import com.cosc3506.ccms.data.model.User;

import java.util.ArrayList;

public class JoinCreateClubActivity extends AppCompatActivity {

    User user;
    EditText joinClubEditText;

    Club club;
    EditText nCN;
    EditText nCD;
    EditText nCID;
    EditText nCR;
    EditText nCB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_create_club);

        joinClubEditText = findViewById(R.id.joinClubEditText);

        nCN = findViewById(R.id.newClubNameEditText);
        nCD = findViewById(R.id.newClubDescriptionEditText);
        nCID = findViewById(R.id.newClubIDEditText);
        nCR = findViewById(R.id.newClubRoomEditText);
        nCB = findViewById(R.id.newClubBudgetEditText);

        user = (User) getIntent().getExtras().getSerializable("user");
    }

    public void joinClub(View view){
        String clubID = joinClubEditText.getText().toString();
        user.joinClub(clubID, user);
    }

    public void createClub(View view){

        String newClubName = nCN.getText().toString();
        String newClubDesc = nCD.getText().toString();
        String newClubID = nCID.getText().toString();
        String newClubRoom = nCR.getText().toString();
        String newClubBudget = String.format("%.2f", Double.parseDouble(nCB.getText().toString()));

        club = new Club(newClubID, "0", new ArrayList<String>(), newClubRoom, newClubName, new ArrayList<Event>(), newClubDesc, new ArrayList<String>(), new ArrayList<String>());
        club.addFunds(Double.parseDouble(newClubBudget), "Initial Funds");
        user.createClub(club, user);

    }
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
}