package com.cosc3506.ccms;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button toLoginActivityBtn = (Button)findViewById(R.id.logOutButton);
        toLoginActivityBtn.setOnClickListener(new veiw.OnClickListener());
    }
}