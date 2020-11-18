package com.cosc3506.ccms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cosc3506.ccms.data.model.Club;
import com.cosc3506.ccms.data.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LogInActivity extends AppCompatActivity {

    Button login = findViewById(R.id.login);
    EditText studentNumber = findViewById(R.id.studentNumber);
    EditText password = findViewById(R.id.password);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginUser();
        //Testing
        /*
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("Users");

        int studentNumber = 90;
        String name = "no";
        String phone = "no";
        String email = "no";
        String password = "no";

        User user = new User(studentNumber, name, phone, email, password);

        reference.child(String.valueOf(studentNumber)).setValue(user);
        */
    }

    public void loginUser() {
        //Validate Login Info

        final String userEnteredstudentNumber = studentNumber.getText().toString().trim();
        final String userEnteredPassword = password.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("studentNumber").equalTo(userEnteredstudentNumber);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    studentNumber.setError(null);
                    String passwordFromDB = snapshot.child(userEnteredstudentNumber).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userEnteredPassword)) {
                        //TODO login and go to Home
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}
