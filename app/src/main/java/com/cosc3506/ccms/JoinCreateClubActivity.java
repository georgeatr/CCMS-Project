package com.cosc3506.ccms;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cosc3506.ccms.data.model.Club;
import com.cosc3506.ccms.data.model.User;

import java.util.ArrayList;

public class JoinCreateClubActivity extends AppCompatActivity {

    //Join Club
    User user;
    String clubID;
    EditText joinClubEditText;
    //Create Club
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
        clubID = joinClubEditText.getText().toString().trim();
        user.joinClub(clubID, user);
    }

    public void createClub(View view){

        String newClubName = nCN.getText().toString();
        String newClubDescription = nCD.getText().toString();
        String newClubID = nCID.getText().toString();
        String newClubRoom = nCR.getText().toString();
        String newClubBudget = nCB.getText().toString();

        ArrayList<String> managers = new ArrayList<>();

        if( newClubName.isEmpty() ||
            newClubDescription.isEmpty() ||
            newClubID.isEmpty() ||
            newClubRoom.isEmpty() ||
            newClubBudget.isEmpty()){

            Toast.makeText(this, "Please Fill in All the Fields!!!", Toast.LENGTH_SHORT).show();

        }
        else{

            Club c = new Club(newClubID, newClubBudget, newClubRoom, newClubName, newClubDescription, managers);

            user.createClub(c, user);
            //Do something with new Club

            finish();
        }

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