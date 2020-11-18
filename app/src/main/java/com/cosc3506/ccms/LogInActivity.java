package com.cosc3506.ccms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {

    Button login = findViewById(R.id.login);
    EditText username = findViewById(R.id.username);
    EditText password = findViewById(R.id.password);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    private Boolean validateUsername() {
        String val = username.getText().toString();
        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        } else {
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            return true;
        }
    }

//    public void loginUser(View view) {
//        //Validate Login Info
//        if (!validateUsername() | !validatePassword()) {
//            return;
//        } else {
//            isUser();
//        }
//    }
//
//    private void isUser() {
//        final String userEnteredUsername = username.getEditText().getText().toString().trim();
//        final String userEnteredPassword = password.getEditText().getText().toString().trim();
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    username.setError(null);
//                    username.setErrorEnabled(false);
//                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);
//                    if (passwordFromDB.equals(userEnteredPassword)) {
//                        username.setError(null);
//                        username.setErrorEnabled(false);
//                        String nameFromDB = dataSnapshot.child(userEnteredUsername).child("name").getValue(String.class);
//                        String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
//                        String phoneNoFromDB = dataSnapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
//                        String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);
//                        Intent intent = new Intent(getApplicationContext(), UserProfile.class);
//                        intent.putExtra("name", nameFromDB);
//                        intent.putExtra("username", usernameFromDB);
//                        intent.putExtra("email", emailFromDB);
//                        intent.putExtra("phoneNo", phoneNoFromDB);
//                        intent.putExtra("password", passwordFromDB);
//                        startActivity(intent);
//                    } else {
//                        progressBar.setVisibility(View.GONE);
//                        password.setError("Wrong Password");
//                        password.requestFocus();
//                    }
//                } else {
//                    progressBar.setVisibility(View.GONE);
//                    username.setError("No such User exist");
//                    username.requestFocus();
//                }
//            }

}