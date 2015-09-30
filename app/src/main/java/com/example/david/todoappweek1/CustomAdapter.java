package com.example.david.todoappweek1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<ListItem> {


    public CustomAdapter(Context context, ArrayList<ListItem> activities) {
        super(context, 0, activities);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ListItem activity = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_view_activity, parent, false);
        }
        // Lookup view for data population
        TextView editText = (TextView) convertView.findViewById(R.id.newItemText);

        // Populate the data into the template view using the data object
        editText.setText(activity.textItemName);
        String priority = activity.priority;
        if (priority.equals("High")){
            editText.setTextColor(Color.BLUE);
        }
        else if (priority.equals("Medium")){
            editText.setTextColor(Color.MAGENTA);
        }
        else
            editText.setTextColor(Color.GREEN);
        // Return the completed view to render on screen
        return convertView;
    }
}