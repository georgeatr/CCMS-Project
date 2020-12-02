package com.cosc3506.ccms;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cosc3506.ccms.data.model.Club;
import com.cosc3506.ccms.data.model.Event;
import com.cosc3506.ccms.data.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ClubActivity extends AppCompatActivity {

    int x = 0;
    Button refresh;
    TextView clubDescriptionTV;
    Club club;
    String clubID;
    ArrayList<Event> eventList = new ArrayList<>();
    ArrayList<String> memberList = new ArrayList<>();
    FloatingActionButton checkTransactionsFB;
    FloatingActionButton addEventFB2;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);

        refresh = findViewById(R.id.refreshButton);

        Intent intent = getIntent();
        clubID = intent.getStringExtra("keyname");
        user = (User) intent.getExtras().getSerializable("name");

        TextView clubTitle = findViewById(R.id.clubNameTV);
        clubTitle.setText(clubID);

        clubDescriptionTV = findViewById(R.id.clubDescription);
        checkTransactionsFB = findViewById(R.id.checkTransactionsFB);
        addEventFB2 = findViewById(R.id.addEventFB2);

        getClub(clubID);
        refresh(new View(this));

    }

    public void refresh(View view){
        //Fetch RecyclerViews
        RecyclerView memberListRV = findViewById(R.id.memberListRV);
        RecyclerView eventListRV = findViewById(R.id.eventListRV);

        //Create both linear layout managers
        LinearLayoutManager memberLayoutManager = new LinearLayoutManager(memberListRV.getContext());
        LinearLayoutManager eventLayoutManager = new LinearLayoutManager(eventListRV.getContext());
        //Apply layout managers
        memberListRV.setLayoutManager(memberLayoutManager);
        eventListRV.setLayoutManager(eventLayoutManager);
        try {
            eventList.addAll(club.getEvents());
            memberList.addAll(club.getMembers());
            clubDescriptionTV.setText("Club Description:\n" + club.getDescription());
        }catch (Exception e){
        }

        //Create Adapters
        MemberCustomAdapter memberAdapter = new MemberCustomAdapter(this,memberList);
        EventCustomAdapter eventAdapter = new EventCustomAdapter(this,eventList);

        //Set Adapters
        memberListRV.setAdapter(memberAdapter);
        eventListRV.setAdapter(eventAdapter);
        if (x != 0) {
            refresh.setVisibility(View.INVISIBLE);

            ArrayList<String> managed = user.getManagedClubs();
            int index = managed.indexOf(clubID);
            if (index != -1) //checks if manager
                checkTransactionsFB.setVisibility(View.VISIBLE);
                addEventFB2.setVisibility(View.VISIBLE);
        }
        x++;
    }

    public void onClickAddEvent(View view) {
        Intent intent = new Intent(ClubActivity.this, CreateEventActivity.class);
        intent.putExtra("club", club);
        startActivity(intent);
    }

    public void onClickCheckTransactions(View view) {
        Intent intent = new Intent(ClubActivity.this, TransactionActivity.class);
        intent.putExtra("club", club);
        startActivity(intent);
    }

    public void onClickPromoteMember(View view){

    }

    public void onClickRemoveMember(View view){

    }

    public void getClub(final String clubID){
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference clubsRef = rootRef.child("Clubs");
        Query checkClub = clubsRef;
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String budget = snapshot.child(clubID).child("budget").getValue(String.class);
                    String room = snapshot.child(clubID).child("room").getValue(String.class);
                    String name = snapshot.child(clubID).child("name").getValue(String.class);
                    String description = snapshot.child(clubID).child("description").getValue(String.class);
                    ArrayList<Event> events = new ArrayList<Event>();
                    ArrayList<String> managers = new ArrayList<String>();
                    ArrayList<String> transactions = new ArrayList<>();
                    ArrayList<String> members = new ArrayList<>();

                    Map<String, Object> managersMap = (HashMap<String, Object>) snapshot.child(clubID).child("managers").getValue();
                    Collection<Object> managersColl = managersMap.values();
                    for (Object value : managersColl) {
                        managers.add(value.toString());
                    }

                    Map<String, Object> transactionsMap = (HashMap<String, Object>) snapshot.child(clubID).child("transactions").getValue();
                    Object[] transactionsColl = transactionsMap.values().toArray();
                    Object[] transactionKeys = transactionsMap.keySet().toArray();

                    for (int j = 0; j < transactionKeys.length; j++) {
                        transactions.add(transactionKeys[j].toString() + "\n" + transactionsColl[j]);
                    }

                    try {
                        Map<String, Object> eventsMap = (HashMap<String, Object>) snapshot.child(clubID).child("events").getValue();
                        Collection<Object> eventsColl = eventsMap.values();
                        for (Object value : eventsColl) {
                            String eventString = value.toString().replace("{address=","")
                                    .replace("}","").replace(" description=", "")
                                    .replace(" name=","").replace(" id=","")
                                    .replace(" startDate=","").replace(" cost=","")
                                    .replace(" endDate=","").replace(" capacity=","");
                            String[] eventStringArray = eventString.split(",");
                            events.add(new Event(eventStringArray[0],eventStringArray[1],eventStringArray[2],eventStringArray[3],
                                    eventStringArray[4],eventStringArray[5],eventStringArray[6],eventStringArray[7]));
                        }
                    }catch (NullPointerException e){
                        Log.e("Club empty events", String.valueOf(e));

                    }try{
                        Map<String, Object> membersMap = (HashMap<String, Object>) snapshot.child(clubID).child("members").getValue();
                        Collection<Object> membersColl = membersMap.values();
                        for (Object value : membersColl) {
                            members.add(value.toString());
                        }
                    }catch (ClassCastException c){
                        Log.e("Weird members", String.valueOf(c));
                        ArrayList membersMap = (ArrayList) snapshot.child(clubID).child("members").getValue();
                        members = membersMap;
                    }

                    club = new Club(clubID, budget, transactions, room, name, events, description, managers, members);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("home", error.getMessage());
            }
        };
        checkClub.addValueEventListener(eventListener);
    }



}