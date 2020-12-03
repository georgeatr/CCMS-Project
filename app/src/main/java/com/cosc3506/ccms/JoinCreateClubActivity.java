package com.cosc3506.ccms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

        if(clubID.isEmpty()){
            Toast.makeText(this, "Please Fill in The ClubID!", Toast.LENGTH_SHORT).show();
        }
        else {
            user.joinClub(clubID, user);
            Intent intent = new Intent(JoinCreateClubActivity.this, HomeActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
            finish();
        }
    }

    public void createClub(View view){

        String newClubName = nCN.getText().toString();
        String newClubDesc = nCD.getText().toString();
        String newClubID = nCID.getText().toString();
        String newClubRoom = nCR.getText().toString();
        String newClubBudget = nCB.getText().toString();

        if (    newClubName.isEmpty() ||
                newClubDesc.isEmpty() ||
                newClubID.isEmpty() ||
                newClubRoom.isEmpty() ||
                newClubBudget.isEmpty()){

            Toast.makeText(this, "Please Fill in All New Club Fields!", Toast.LENGTH_SHORT).show();
        }
        else {
            newClubBudget = String.format("%.2f", Double.parseDouble(newClubBudget));
            club = new Club(newClubID, "0", new ArrayList<String>(), newClubRoom, newClubName,
                    new ArrayList<Event>(), newClubDesc, new ArrayList<String>(), new ArrayList<String>());
            user.createClub(club, user, newClubBudget);
            Intent intent = new Intent(JoinCreateClubActivity.this, HomeActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
            finish();
        }

    }
}