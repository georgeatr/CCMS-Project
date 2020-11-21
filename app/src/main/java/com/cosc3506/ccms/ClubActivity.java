package com.cosc3506.ccms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class ClubActivity extends AppCompatActivity {

    String clubName;
    String clubDescription = "But Can you do this??????";
    ArrayList eventList = new ArrayList(Arrays.asList("Event1","Event2","Event3","Event4","Event5","Event6","Event7"));
    ArrayList memberList = new ArrayList(Arrays.asList("Member1","Member2","Member3","Member4","Member5"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);

        Intent intent = getIntent();
        clubName = intent.getStringExtra("keyname");

        TextView clubTitle = findViewById(R.id.clubNameTV);
        clubTitle.setText(clubName);

        TextView clubDescriptionTV = findViewById(R.id.clubDescription);
        clubDescriptionTV.setText(clubDescription);

        //Fetch RecyclerViews
        RecyclerView memberListRV = findViewById(R.id.memberListRV);
        RecyclerView eventListRV = findViewById(R.id.eventListRV);

        //Create both linear layout managers
        LinearLayoutManager memberLayoutManager = new LinearLayoutManager(memberListRV.getContext());
        LinearLayoutManager eventLayoutManager = new LinearLayoutManager(eventListRV.getContext());

        //Apply layout managers
        memberListRV.setLayoutManager(memberLayoutManager);
        eventListRV.setLayoutManager(eventLayoutManager);

        //Create Adapters
        MemberCustomAdapter memberAdapter = new MemberCustomAdapter(this,memberList);
        EventCustomAdapter eventAdapter = new EventCustomAdapter(this,eventList);

        //Set Adapters
        memberListRV.setAdapter(memberAdapter);
        eventListRV.setAdapter(eventAdapter);

    }

    public void onClickAddEvent(View view) {
        startActivity(new Intent(ClubActivity.this,CreateEventActivity.class));
    }
}