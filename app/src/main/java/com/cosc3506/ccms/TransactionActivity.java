package com.cosc3506.ccms;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TransactionActivity extends AppCompatActivity {

    Switch sign;
    TextView plus;
    TextView minus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

    }

    public void signChange(View view){

        sign = findViewById(R.id.signSwitch);
        plus = findViewById(R.id.plusTextView);
        minus = findViewById(R.id.minusTextView);

        if(sign.isChecked()){
            plus.setVisibility(View.INVISIBLE);
            minus.setVisibility(View.VISIBLE);
        }
        else{
            plus.setVisibility(View.VISIBLE);
            minus.setVisibility(View.INVISIBLE);
        }
    }

    public void submitChanges(View view){
        if(sign.isChecked()){
            //Subtract Funds
        }
        else{
            //Add Funds
        }
    }

}