package com.cosc3506.ccms;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cosc3506.ccms.data.model.Club;

import java.util.ArrayList;

public class TransactionActivity extends AppCompatActivity {

    Switch sign;
    TextView plus;
    TextView minus;
    EditText transName;
    EditText amount;
    ArrayList transactionList = new ArrayList();

    Club club;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        sign = findViewById(R.id.signSwitch);
        plus = findViewById(R.id.plusTextView);
        minus = findViewById(R.id.minusTextView);
        transName = findViewById(R.id.transactionNameEditText);
        amount = findViewById(R.id.amountEditText);

        club = (Club) getIntent().getExtras().getSerializable("club");

        transactionList.addAll(club.getTransactions());

        RecyclerView transactionsRV = findViewById(R.id.transactions_RV);

        LinearLayoutManager transactionLayoutManager = new LinearLayoutManager(transactionsRV.getContext());

        transactionsRV.setLayoutManager(transactionLayoutManager);

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
            club.subtractFunds(Double.parseDouble(amount.getText().toString()), transName.getText().toString());
        }
        else{
            //Add Funds
            club.addFunds(Double.parseDouble(amount.getText().toString()), transName.getText().toString());
        }

        transName.setText("");
        amount.setText("");
    }

}