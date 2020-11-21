package com.cosc3506.ccms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    Button createAccount;
    EditText newStudentNumber;
    EditText newName;
    EditText newEmail ;
    EditText newPhoneNumber;
    EditText newPassword;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

    }

    public void createUser(View view){
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("Users");

        //TODO change the Strings to getting text from the EditTexts
        String studentNumber = newStudentNumber.getText().toString();
        String name = newName.getText().toString();
        String phone = newPhoneNumber.getText().toString();
        String email = newEmail.getText().toString();
        String password = newPassword.getText().toString();

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
