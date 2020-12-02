package com.cosc3506.ccms;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cosc3506.ccms.data.model.Club;

import java.util.ArrayList;
import java.util.Arrays;

public class TransactionActivity extends AppCompatActivity {

    Switch sign;
    TextView plus;
    TextView minus;
    ArrayList transactionList = new ArrayList(Arrays.asList("Transaction1","Transaction2","Transaction3","Transaction4","Transaction5","Transaction6"));

    Club club;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        sign = findViewById(R.id.signSwitch);
        plus = findViewById(R.id.plusTextView);
        minus = findViewById(R.id.minusTextView);

        club = (Club) getIntent().getExtras().getSerializable("club");

        //RecyclerView transactionsRV = findViewById(R.id.tran)




    }

    public void signChange(View view){

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
            club.subtractFunds(Double.parseDouble(amount.getText().toString()), "Funds Gained");
        }
        else{
            //Add Funds
            club.addFunds(Double.parseDouble(amount.getText().toString()), "Funds Lost");
        }
    }

}