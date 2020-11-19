package com.cosc3506.ccms;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        final EditText newEventStartDate = findViewById(R.id.newEventStartDateEditText);
        final EditText newEventEndDate = findViewById(R.id.newEventEndDateEditText);
        final EditText newEventLocation = findViewById(R.id.newEventLocationEditText);
        final EditText newEventBudget = findViewById(R.id.newEventBudgetEditTextDec);
        final TextView fieldsNotFilledError = findViewById(R.id.errorTextView);
        fieldsNotFilledError.setVisibility(View.INVISIBLE);

        //Toast.makeText(this,"!!Please Fill in All the Fields!!", Toast.LENGTH_SHORT).show();

        Button createNewEventBtn = (Button)findViewById(R.id.createNewEventButton);

        createNewEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newEventName.getText().toString().isEmpty() ||
                newEventDescription.getText().toString().isEmpty() ||
                newEventID.getText().toString().isEmpty() ||
                newEventCapacity.getText().toString().isEmpty() ||
                newEventStartDate.getText().toString().isEmpty() ||
                newEventEndDate.getText().toString().isEmpty() ||
                newEventLocation.getText().toString().isEmpty() ||
                newEventBudget.getText().toString().isEmpty()) {

                    fieldsNotFilledError.setVisibility(View.VISIBLE);

                }
                else {

                    Event event = new Event(newEventName.getText().toString(), newEventDescription.getText().toString(), Integer.getInteger(newEventID.getText().toString()),
                            Integer.getInteger(newEventCapacity.getText().toString()), newEventStartDate.getText().toString(), newEventEndDate.getText().toString(),
                            newEventLocation.getText().toString(), Integer.getInteger(newEventBudget.getText().toString()));

                    finish();
                }
            }
        });



    }
}