package com.cosc3506.ccms;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.cosc3506.ccms.data.model.Club;
import com.cosc3506.ccms.data.model.Event;
import com.cosc3506.ccms.data.model.User;
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


public class HomeActivity extends AppCompatActivity {

    User user;
    ArrayList<String> clubList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        user = (User) getIntent().getExtras().getSerializable("user");

        clubList.addAll(user.getEnrolledClubs());

        TextView welcomeText = findViewById(R.id.welcome_view);
        welcomeText.setText("Welcome " + user.getName());

        //Get the recycler view
        RecyclerView clubsView = findViewById(R.id.club_list);

        //Create the linear layout manager
        LinearLayoutManager manager = new LinearLayoutManager(clubsView.getContext());
        clubsView.setLayoutManager(manager);

        //Create and apply Adapter
        Intent intent = new Intent(this,ClubActivity.class);
        intent.putExtra("key1",user);
        ClubCustomAdapter clubCustomAdapter = new ClubCustomAdapter(this,intent,clubList);
        clubsView.setAdapter(clubCustomAdapter);



    }

    public void onClickLogout(View view) {
        startActivity(new Intent(HomeActivity.this,LoginActivity.class));
        finish();
    }

    public void onClickAddClub(View view) {
        Intent intent = new Intent(HomeActivity.this, JoinCreateClubActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

//    public Club getClub(final String clubID){
//        final Club[] club = new Club[1];
//        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference clubsRef = rootRef.child("Clubs");
//        Query checkClub = clubsRef;
//        ValueEventListener eventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NotNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    String budget = snapshot.child(clubID).child("budget").getValue(String.class);
//                    String remainingFunds = snapshot.child(clubID).child("remainingFunds").getValue(String.class);
//                    String room = snapshot.child(clubID).child("room").getValue(String.class);
//                    String name = snapshot.child(clubID).child("name").getValue(String.class);
//                    String description = snapshot.child(clubID).child("description").getValue(String.class);
//                    ArrayList<Event> events = new ArrayList<Event>();
//                    ArrayList<String> managers = new ArrayList<String>();
//                    Map<String, Object> managersMap = (HashMap<String, Object>) snapshot.child(clubID).child("Managers").getValue();
//                    Collection<Object> managersColl = managersMap.values();
//                    for (Object value : managersColl) {
//                        managers.add(value.toString());
//                    }
//                    Map<String, Object> eventsMap = (HashMap<String, Object>) snapshot.child(clubID).child("Events").getValue();
//                    Collection<Object> eventsColl = eventsMap.values();
////                    for (Object value : eventsColl) {
////                        events.add(getEvents(value, clubID));
////                    }
//
//
//                    club[0] = new Club(clubID, budget, remainingFunds, room, name, events, description, managers);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.d("home", error.getMessage());
//            }
//        };
//        checkClub.addListenerForSingleValueEvent(eventListener);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return club[0];
//    }
//
//    private Event getEvents(Object value, String clubID) {
//        final String eventID = value.toString();
//        final Event[] event = new Event[1];
//        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Clubs/" + clubID + "Events");
//        Query checkUser = reference.orderByChild(eventID).equalTo(eventID);
//        checkUser.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    String address = snapshot.child(eventID).child("address").getValue(String.class);
//                    String startDate = snapshot.child(eventID).child("startDate").getValue(String.class);
//                    String endDate = snapshot.child(eventID).child("endDate").getValue(String.class);
//                    String cost = snapshot.child(eventID).child("cost").getValue(String.class);
//                    String capacity = snapshot.child(eventID).child("capacity").getValue(String.class);
//                    String name = snapshot.child(eventID).child("name").getValue(String.class);
//                    String description = snapshot.child(eventID).child("description").getValue(String.class);
//                    event[0] = new Event(eventID,name,description,address,startDate,endDate,cost,capacity);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });return event[0];
//    }

}



