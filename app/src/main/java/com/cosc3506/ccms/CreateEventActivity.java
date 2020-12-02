package com.cosc3506.ccms;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cosc3506.ccms.data.model.Club;
import com.cosc3506.ccms.data.model.Event;

public class CreateEventActivity extends AppCompatActivity {

    EditText nEN;
    EditText nEDesc;
    EditText nEID;
    EditText nEC;
    EditText nEST;
    EditText nEET;
    EditText nEL;
    EditText nEB;

    Club club;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        nEN = findViewById(R.id.newEventNameEditText);
        nEDesc = findViewById(R.id.newEventDescriptionEditText);
        nEID = findViewById(R.id.newEventIDEditText);
        nEC = findViewById(R.id.newEventCapacityEditText);
        nEST = findViewById(R.id.newEventStartDateTimeEditText);
        nEET = findViewById(R.id.newEventEndDateTimeEditText);
        nEL = findViewById(R.id.newEventLocationEditText);
        nEB = findViewById(R.id.newEventBudgetEditTextDec);

        club = (Club) getIntent().getExtras().getSerializable("club");

    }


    public void createEvent(View view){

        String newEventName = nEN.getText().toString();
        String newEventDescription = nEDesc.getText().toString();
        String newEventID = nEID.getText().toString();
        String newEventCapacity = nEC.getText().toString();
        String newEventStartDateTime = nEST.getText().toString();
        String newEventEndDateTime = nEET.getText().toString();
        String newEventLocation = nEL.getText().toString();
        String newEventBudget = nEB.getText().toString();

        if(     newEventName.isEmpty() ||
                newEventDescription.isEmpty() ||
                newEventID.isEmpty() ||
                newEventCapacity.isEmpty() ||
                newEventStartDateTime.isEmpty() ||
                newEventEndDateTime.isEmpty() ||
                newEventLocation.isEmpty() ||
                newEventBudget.isEmpty()){

            Toast.makeText(this, "Please Fill in All the Fields!!!", Toast.LENGTH_SHORT).show();

        }
        else {

            Event e = new Event(newEventLocation, newEventDescription, newEventName, newEventID,
                    newEventStartDateTime, newEventBudget, newEventEndDateTime, newEventCapacity);

            club.newEvent(e);

            finish();

        }


    }

}