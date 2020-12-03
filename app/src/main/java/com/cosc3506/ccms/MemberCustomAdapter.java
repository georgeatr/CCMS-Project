package com.cosc3506.ccms;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cosc3506.ccms.data.model.Club;
import com.cosc3506.ccms.data.model.User;

import java.util.ArrayList;
import java.util.Random;

public class MemberCustomAdapter extends RecyclerView.Adapter<MemberCustomAdapter.ViewHolder> {

    private ArrayList localDataSet;
    private ArrayList nameList;
    Context context;
    User user;
    Club club;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final Button removeButton;
        private final Button promoteButton;
        private final LinearLayout linearLayout;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            textView = (TextView) view.findViewById(R.id.member_name);
            removeButton = (Button) view.findViewById(R.id.remove_member);
            promoteButton = (Button) view.findViewById(R.id.promote_member);
            linearLayout = (LinearLayout) view.findViewById(R.id.member_rowLayout_linear);

        }

        public TextView getTextView() {
            return textView;
        }
        public Button getRemoveButton() { return removeButton;}
        public Button getPromoteButton() { return promoteButton; }
        public LinearLayout getLinearLayout() { return linearLayout; }

    }

    public MemberCustomAdapter(Context context, ArrayList dataSet, User user, Club club,ArrayList names) {
        this.context = context;
        localDataSet = dataSet;
        nameList = names;
        this.user = user;
        this.club = club;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.memberrowlayout, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText((String)nameList.get(position));
        viewHolder.getRemoveButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                club.kickUser((String)localDataSet.get(position));
                localDataSet.remove(position);
                notifyDataSetChanged();
            }
        });

        viewHolder.getPromoteButton().setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                club.promoteToManager((String) localDataSet.get(position));
            }
        });

        ArrayList<String> managed = user.getManagedClubs();
        int index = managed.indexOf(club.getID());
        if (index != -1) { //checks if manager
            viewHolder.getRemoveButton().setVisibility(View.VISIBLE);
            viewHolder.getPromoteButton().setVisibility(View.VISIBLE);
            for (int i = 0; i < club.getManagers().size(); i++) {
                if(club.getManagers().get(i) != localDataSet.get(position)){
                    viewHolder.getRemoveButton().setVisibility(View.INVISIBLE);
                    viewHolder.getPromoteButton().setVisibility(View.INVISIBLE);
                }
            }
        }

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