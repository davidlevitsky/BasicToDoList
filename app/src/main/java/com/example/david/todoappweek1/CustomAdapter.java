package com.example.david.todoappweek1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<CustomClass> {


    public CustomAdapter(Context context, ArrayList<CustomClass> activities) {
        super(context, 0, activities);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CustomClass activity = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_view_activity, parent, false);
        }
        // Lookup view for data population
        TextView editText = (TextView) convertView.findViewById(R.id.newItemText);
        RatingBar star = (RatingBar) convertView.findViewById(R.id.ratingBar);

        // Populate the data into the template view using the data object
        editText.setText(activity.textItemName);
        if (activity.textStar.equals("High"))
        {
            star.setVisibility(View.VISIBLE);
        }
        else
        star.setVisibility(View.INVISIBLE);
        // Return the completed view to render on screen
        return convertView;
    }
}