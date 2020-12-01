package com.cosc3506.ccms;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.cosc3506.ccms.data.model.Club;
import com.cosc3506.ccms.data.model.User;

public class JoinCreateClubActivity extends AppCompatActivity {

    User user;
    String clubID;
    EditText joinClubEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_create_club);

        joinClubEditText = findViewById(R.id.joinClubEditText);


        user = (User) getIntent().getExtras().getSerializable("user");
    }

    public void joinClub(View view){
        clubID = joinClubEditText.getText().toString().trim();
        user.joinClub(clubID, user);
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