package com.cosc3506.ccms;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class ClubCustomAdapter extends RecyclerView.Adapter<ClubCustomAdapter.ViewHolder> {

    private ArrayList localDataSet;
    Context context;
    Intent nextActivity;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final LinearLayout linearLayout;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            textView = (TextView) view.findViewById(R.id.club_name);
            linearLayout = (LinearLayout) view.findViewById(R.id.rowLayout_linear);
        }

        public TextView getTextView() {
            return textView;
        }
        public LinearLayout getLinearLayout() { return linearLayout; }

    }

    public ClubCustomAdapter(Context context, Intent intent, ArrayList dataSet) {
        this.context = context;
        localDataSet = dataSet;
        nextActivity = intent;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.clubrowlayout, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText((String)localDataSet.get(position));

        //set background for the layout
        //viewHolder.getLinearLayout().setBackgroundColor(getRandomColor());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start next activity with the information passed
                nextActivity.putExtra("keyname",(String)localDataSet.get(position));
                context.startActivity(nextActivity);
            }
        });
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