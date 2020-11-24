package com.cosc3506.ccms;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class TransactionsCustomAdapter extends RecyclerView.Adapter<TransactionsCustomAdapter.ViewHolder> {

    private ArrayList localDataSet;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView transactionTextView;
        private final TextView amountTextView;
        private final LinearLayout linearLayout;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            transactionTextView = (TextView) view.findViewById(R.id.transaction_number);
            amountTextView = (TextView) view.findViewById(R.id.transaction_amount);
            linearLayout = (LinearLayout) view.findViewById(R.id.transaction_rowLayout_linear);
        }

        public TextView getTransactionTextView() {
            return transactionTextView;
        }
        public TextView getAmountTextView(){ return amountTextView; }
        public LinearLayout getLinearLayout() { return linearLayout; }

    }

    public TransactionsCustomAdapter(Context context, ArrayList dataSet) {
        this.context = context;
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.transactionsrowlayout, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTransactionTextView().setText((String)localDataSet.get(position));
        viewHolder.getAmountTextView().setText("$ 1,000,000");

        //viewHolder.getLinearLayout().setBackgroundColor(getRandomColor());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }


    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255,rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt(256));
    }
}