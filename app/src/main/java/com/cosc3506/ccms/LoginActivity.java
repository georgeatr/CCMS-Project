package com.cosc3506.ccms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cosc3506.ccms.data.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button createAccount = findViewById(R.id.createNewAccountButton);
        final EditText newStudentNumber = findViewById(R.id.newStudentNumberEditText);
        final EditText newName = findViewById(R.id.newNameEditText);
        final EditText newEmail = findViewById(R.id.newEmailEditText);
        final EditText newPhoneNumber = findViewById(R.id.newPhoneNumberEditText);
        final EditText newPassword = findViewById(R.id.newPasswordEditText);
        final Button register = findViewById(R.id.registerButton);
        //Register Fields are invisible
        newStudentNumber.setVisibility(View.INVISIBLE);
        newName.setVisibility(View.INVISIBLE);
        newEmail.setVisibility(View.INVISIBLE);
        newPhoneNumber.setVisibility(View.INVISIBLE);
        newPassword.setVisibility(View.INVISIBLE);
        register.setVisibility(View.INVISIBLE);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newStudentNumber.setVisibility(View.VISIBLE);
                newName.setVisibility(View.VISIBLE);
                newEmail.setVisibility(View.VISIBLE);
                newPhoneNumber.setVisibility(View.VISIBLE);
                newPassword.setVisibility(View.VISIBLE);
                register.setVisibility(View.VISIBLE);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User u = new User(  newStudentNumber.getText().toString(),
                        newName.getText().toString(),
                        newPhoneNumber.getText().toString(),
                        newEmail.getText().toString(),
                        newPassword.getText().toString());

                //Do something with the new User
            }
        });

    }

    public void createUser(View view){
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("Users");

        //TODO change the Strings to getting text from the EditTexts
        String studentNumber = "90";
        String name = "no";
        String phone = "no";
        String email = "no";
        String password = "no";

        User user = new User(studentNumber, name, phone, email, password);

        reference.child(studentNumber).setValue(user);
    }


    public void login(View view) {
        //Validate Login Info
        final EditText studentNumber = findViewById(R.id.studentNumber);
        final EditText password = findViewById(R.id.password);
        final String userEnteredStudentNumber = studentNumber.getText().toString().trim();
        final String userEnteredPassword = password.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser = reference.orderByChild("studentNumber").equalTo(userEnteredStudentNumber);
        checkUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String passwordDB = snapshot.child(userEnteredStudentNumber).child("password").getValue(String.class);
                    if (passwordDB.equals(userEnteredPassword)) {
                        //TODO login and go to Home
                        String studentNumberDB = snapshot.child(userEnteredStudentNumber).child("name").getValue(String.class);
                        String nameDB = snapshot.child(userEnteredStudentNumber).child("phoneNo").getValue(String.class);
                        String phoneDB = snapshot.child(userEnteredStudentNumber).child("username").getValue(String.class);
                        String emailDB = snapshot.child(userEnteredStudentNumber).child("email").getValue(String.class);
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        User user = new User(studentNumberDB,nameDB,phoneDB,emailDB,passwordDB);
                        intent.putExtra("user", user);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}
