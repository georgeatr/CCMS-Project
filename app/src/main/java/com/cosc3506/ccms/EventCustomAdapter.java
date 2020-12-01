package com.cosc3506.ccms;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cosc3506.ccms.data.model.Event;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class EventCustomAdapter extends RecyclerView.Adapter<EventCustomAdapter.ViewHolder> {

    private ArrayList<Event> localDataSet;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView eventName;
        private final TextView eventAddress;
        private final TextView eventStart;
        private final TextView eventEnd;
        private final LinearLayout linearLayout;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            eventName = (TextView) view.findViewById(R.id.event_name);
            eventAddress = (TextView) view.findViewById(R.id.event_address);
            eventStart = (TextView) view.findViewById(R.id.event_start);
            eventEnd = (TextView) view.findViewById(R.id.event_end);
            linearLayout = (LinearLayout) view.findViewById(R.id.event_rowLayout_linear);
        }

        public TextView getEventName() {
            return eventName;
        }
        public TextView getEventAddress() { return eventAddress; }
        public TextView getEventEnd() { return eventEnd; }
        public TextView getEventStart() { return eventStart; }
        public LinearLayout getLinearLayout() { return linearLayout; }

    }

    public EventCustomAdapter(Context context, ArrayList<Event> dataSet) {
        this.context = context;
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.eventrowlayout, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getEventName().setText((String)localDataSet.get(position).getName());
        viewHolder.getEventAddress().setText((String)localDataSet.get(position).getAddress());
        viewHolder.getEventStart().setText((String)localDataSet.get(position).getStartDate());
        viewHolder.getEventEnd().setText((String)localDataSet.get(position).getEndDate());

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