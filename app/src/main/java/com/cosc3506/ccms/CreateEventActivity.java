package com.cosc3506.ccms;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cosc3506.ccms.data.model.Event;

public class CreateEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        final EditText newEventName = findViewById(R.id.newEventNameEditText);
        final EditText newEventDescription = findViewById(R.id.newEventDescriptionEditText);
        final EditText newEventID = findViewById(R.id.newEventIDEditText);
        final EditText newEventCapacity = findViewById(R.id.newEventCapacityEditText);
        final EditText newEventStartTime = findViewById(R.id.newEventStartTimeEditText);
        final EditText newEventEndTime = findViewById(R.id.newEventEndTimeEditText);
        final EditText newEventLocation = findViewById(R.id.newEventLocationEditText);
        final EditText newEventDate = findViewById(R.id.newEventDateEditText);
        final EditText newEventBudget = findViewById(R.id.newEventBudgetEditTextDec);
        final TextView fieldsNotFilledError = findViewById(R.id.errorTextView);
        fieldsNotFilledError.setVisibility(View.INVISIBLE);

        Toast.makeText(this,"!!Please Fill in All the Fields!!", Toast.LENGTH_SHORT).show();

    }
}