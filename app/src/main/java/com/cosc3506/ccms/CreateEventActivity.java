package com.cosc3506.ccms;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateEventActivity extends AppCompatActivity {

    EditText nEN;
    EditText nEDesc;
    EditText nEID;
    EditText nEC;
    EditText nEST;
    EditText nEET;
    EditText nEL;
    EditText nEDate;
    EditText nEB;

    Button createEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        nEN = findViewById(R.id.newEventNameEditText);
        nEDesc = findViewById(R.id.newEventDescriptionEditText);
        nEID = findViewById(R.id.newEventIDEditText);
        nEC = findViewById(R.id.newEventCapacityEditText);
        nEST = findViewById(R.id.newEventStartTimeEditText);
        nEET = findViewById(R.id.newEventEndTimeEditText);
        nEL = findViewById(R.id.newEventLocationEditText);
        nEDate = findViewById(R.id.newEventDateEditText);
        nEB = findViewById(R.id.newEventBudgetEditTextDec);

        String newEventName = nEN.getText().toString();
        String newEventDescription = nEDesc.getText().toString();
        String newEventID = nEID.getText().toString();
        String newEventCapacity = nEC.getText().toString();
        String newEventStartTime = nEST.getText().toString();
        String newEventEndTime = nEET.getText().toString();
        String newEventLocation = nEL.getText().toString();
        String newEventDate = nEDate.getText().toString();
        String newEventBudget = nEB.getText().toString();

        TextView fieldsNotFilledError = findViewById(R.id.errorTextView);
        fieldsNotFilledError.setVisibility(View.INVISIBLE);


        createEvent = findViewById(R.id.createNewEventButton);
        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });



        Toast.makeText(this,"!!Please Fill in All the Fields!!", Toast.LENGTH_SHORT).show();

    }
}