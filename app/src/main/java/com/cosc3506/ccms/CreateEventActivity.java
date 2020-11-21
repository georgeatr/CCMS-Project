package com.cosc3506.ccms;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

        //Toast.makeText(this,"!!Please Fill in All the Fields!!", Toast.LENGTH_SHORT).show();

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

            TextView fieldsNotFilledError = findViewById(R.id.errorTextView);
            fieldsNotFilledError.setVisibility(View.VISIBLE);

        }
        else {

            Event e = new Event(newEventID,
                                newEventName,
                                newEventDescription,
                                newEventLocation,
                                newEventStartDateTime,
                                newEventEndDateTime,
                                newEventBudget,
                                newEventCapacity);

            //Do something with the new Event

            finish();

        }


    }

}