package com.cosc3506.ccms;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class TransactionActivity extends AppCompatActivity {

    Switch sign;
    TextView plus;
    TextView minus;
    ArrayList transactionList = new ArrayList(Arrays.asList("Transaction1","Transaction2","Transaction3","Transaction4"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        sign = findViewById(R.id.signSwitch);
        plus = findViewById(R.id.plusTextView);
        minus = findViewById(R.id.minusTextView);

        RecyclerView transactionsRV = findViewById(R.id.transactions_RV);

        LinearLayoutManager transactionsLayoutManager = new LinearLayoutManager(transactionsRV.getContext());

        transactionsRV.setLayoutManager(transactionsLayoutManager);

        TransactionsCustomAdapter transactionsAdapter = new TransactionsCustomAdapter(this,transactionList);

        transactionsRV.setAdapter(transactionsAdapter);

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
        }
        else{
            //Add Funds
        }
    }

}