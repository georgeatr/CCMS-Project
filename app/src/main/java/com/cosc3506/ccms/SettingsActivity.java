package com.cosc3506.ccms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        /*
        Button toLoginActivityBtn = (Button)findViewById(R.id.logOutButton);
        toLoginActivityBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), LogInActivity.class);
                startActivity(startIntent);
            }
        }); */
    }
    public void logout(View view){
        startActivity(new Intent(SettingsActivity.this,LoginActivity.class));
    }
}