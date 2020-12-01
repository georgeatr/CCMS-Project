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

        ArrayList<Club> clubs = new ArrayList<>();

//        for (int i = 0; i < user.getEnrolledClubs().size(); i++) {
//            clubs.add(getClub(user.getEnrolledClubs().get(0)));
//        }

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
        intent.putExtra("user", user);
        //intent.putExtra("clubID", )
        startActivity(intent);
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




}



