package com.cosc3506.ccms;

import android.graphics.Color;
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
    TextView budget;
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

        //Edit Budget
        budget = findViewById(R.id.budget_amount);
        budget.setText(String.format("%.2f", Double.parseDouble(club.getBudget())));

        transactionList.addAll(club.getTransactions());

        //Set Everything for the recycler view of the transactions
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
            String subAmount = String.format("%.2f", Double.parseDouble(amount.getText().toString()));
            club.subtractFunds(Double.parseDouble(subAmount), transName.getText().toString());
        }
        else{
            //Add Funds
            String addAmount = String.format("%.2f", Double.parseDouble(amount.getText().toString()));
            club.addFunds(Double.parseDouble(addAmount), transName.getText().toString());
        }

        if(Double.parseDouble(club.getBudget()) < 0){
            budget.setText(String.format("%.2f", Double.parseDouble(club.getBudget())));
            budget.setTextColor(Color.RED);
        }else{
            budget.setText(String.format("%.2f", Double.parseDouble(club.getBudget())));
            budget.setTextColor(Color.BLACK);
        }

        transName.setText("");
        amount.setText("");

    }

}