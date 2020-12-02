package com.cosc3506.ccms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cosc3506.ccms.data.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {

    Button createAccount;
    EditText newStudentNumber;
    EditText newName;
    EditText newEmail ;
    EditText newPhoneNumber;
    EditText newPassword;
    Button register;
    TextView clickme;

    private static final String TAG = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        clickme = findViewById(R.id.clickme);
        createAccount = findViewById(R.id.createNewAccountButton);
        newStudentNumber = findViewById(R.id.newStudentNumberEditText);
        newName = findViewById(R.id.newNameEditText);
        newEmail = findViewById(R.id.newEmailEditText);
        newPhoneNumber = findViewById(R.id.newPhoneNumberEditText);
        newPassword = findViewById(R.id.newPasswordEditText);
        register = findViewById(R.id.registerButton);


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

        //FOR TESTING
        clickme.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goHome(new User("","","","",""));
            }
        });

    }


    public void createUser(View view){
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("Users");

        String studentNumber = newStudentNumber.getText().toString();
        String name = newName.getText().toString();
        String phone = newPhoneNumber.getText().toString();
        String email = newEmail.getText().toString();
        String password = newPassword.getText().toString();
        ArrayList<String> clubs = new ArrayList<String>();
        ArrayList<String> events = new ArrayList<String>();

        User user = new User(studentNumber, name, phone, email, clubs, password, events);

        reference.child(studentNumber).setValue(user);

        goHome(user);
    }

    public void goHome(User user){
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }


    public void login(View view) {
        //Validate Login Info
        final EditText studentNumber = findViewById(R.id.studentNumber);
        final EditText password = findViewById(R.id.password);
        final String userEnteredStudentNumber = studentNumber.getText().toString().trim();
        final String userEnteredPassword = password.getText().toString().trim();
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users/" + userEnteredStudentNumber);
        Query checkUser = reference;
        final ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String passwordDB = snapshot.child("password").getValue(String.class);
                    if (passwordDB.equals(userEnteredPassword)) {
                        String studentNumberDB = snapshot.child("studentNumber").getValue(String.class);
                        String nameDB = snapshot.child("name").getValue(String.class);
                        String phoneDB = snapshot.child("phone").getValue(String.class);
                        String emailDB = snapshot.child("email").getValue(String.class);
                        ArrayList<String> clubs = new ArrayList<String>();
                        ArrayList<String> managed = new ArrayList<String>();
                        try {
                            Map<String, Object> clubsMap = (HashMap<String, Object>) snapshot.child("enrolled").getValue();
                            Collection clubsColl = clubsMap.values();

                            for (Object value : clubsColl){
                                clubs.add(value.toString());
                            }
                            Map<String, Object> managedMap = (HashMap<String, Object>) snapshot.child("managed").getValue();
                            Collection managedColl = managedMap.values();
                            for (Object value : managedColl){
                                managed.add(value.toString());
                            }
                        }catch (NullPointerException e){

                        }

                        User user = new User(studentNumberDB,nameDB,phoneDB,emailDB,clubs,passwordDB,managed);
                        reference.removeEventListener(this);
                        goHome(user);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        checkUser.addValueEventListener(eventListener);
    }


}
