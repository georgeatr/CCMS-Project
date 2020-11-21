package com.cosc3506.ccms;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        EditText nEN = findViewById(R.id.newEventNameEditText);
        EditText nEDesc = findViewById(R.id.newEventDescriptionEditText);
        EditText nEID = findViewById(R.id.newEventIDEditText);
        EditText nEC = findViewById(R.id.newEventCapacityEditText);
        EditText nEST = findViewById(R.id.newEventStartTimeEditText);
        EditText nEET = findViewById(R.id.newEventEndTimeEditText);
        EditText nEL = findViewById(R.id.newEventLocationEditText);
        EditText nEDate = findViewById(R.id.newEventDateEditText);
        EditText nEB = findViewById(R.id.newEventBudgetEditTextDec);

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

        

        Toast.makeText(this,"!!Please Fill in All the Fields!!", Toast.LENGTH_SHORT).show();

    }
}