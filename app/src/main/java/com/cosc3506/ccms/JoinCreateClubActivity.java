package com.cosc3506.ccms;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cosc3506.ccms.data.model.Club;
import com.cosc3506.ccms.data.model.User;

public class JoinCreateClubActivity extends AppCompatActivity {

    User user;
    String clubID;
    EditText joinClubEditText;

    EditText nCN;
    EditText nCD;
    EditText nCID;
    EditText nCR;
    EditText nCB;
    TextView clubErrorTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_create_club);

        nCN = findViewById(R.id.newClubNameEditText);
        nCD = findViewById(R.id.newClubIDEditText);
        nCID = findViewById(R.id.newClubIDEditText);
        nCR = findViewById(R.id.newClubRoomEditText);
        nCB = findViewById(R.id.newClubBudgetEditText);

        clubErrorTV = findViewById(R.id.clubErrorTextView);


        joinClubEditText = findViewById(R.id.joinClubEditText);

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

        if( newClubName.isEmpty() ||
            newClubDescription.isEmpty() ||
            newClubID.isEmpty() ||
            newClubRoom.isEmpty() ||
            newClubBudget.isEmpty()){

            clubErrorTV.setVisibility(View.VISIBLE);

        }
        else{

            Club c = new Club(  newClubID,
                                newClubBudget,
                                newClubRoom,
                                newClubName,
                                newClubDescription);

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