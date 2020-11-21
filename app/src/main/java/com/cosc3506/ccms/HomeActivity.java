package com.cosc3506.ccms;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cosc3506.ccms.data.model.User;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeActivity extends AppCompatActivity {

    ArrayList clubList = new ArrayList(Arrays.asList("Club1","Club2","Club3"));
    User user = (User) getIntent().getExtras().getSerializable("user");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get the recycler view
        RecyclerView clubsView = findViewById(R.id.club_list);

        //Create the linear layout manager
        LinearLayoutManager manager = new LinearLayoutManager(clubsView.getContext());
        clubsView.setLayoutManager(manager);

        //Create and apply Adapter
        CustomAdapter customAdapter = new CustomAdapter(this,new Intent(
                this,ClubActivity.class),clubList);
        clubsView.setAdapter(customAdapter);

    }

    public void onClickLogout(View view) {
        startActivity(new Intent(HomeActivity.this,LoginActivity.class));
        finish();
    }

    public void onClickAddClub(View view) {
        startActivity(new Intent(HomeActivity.this,JoinCreateClubActivity.class));
    }
    
}