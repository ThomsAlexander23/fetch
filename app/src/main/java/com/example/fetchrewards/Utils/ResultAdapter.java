package com.example.fetchrewards.Utils;

import com.example.fetchrewards.Models.Result;
import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.fetchrewards.R;

import java.util.ArrayList;

public class ResultAdapter  extends ArrayAdapter<Result> {

    public ResultAdapter(Application context, ArrayList<Result> results) {
        super(context, 0, results);
    }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View listItemView = convertView;
            if (listItemView == null){
                listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            }
            Result currentResult = getItem(position);
            TextView itemIdTextView = (TextView) listItemView.findViewById(R.id.item_id);
            itemIdTextView.setText(String.valueOf(currentResult.getItemId()));

            TextView nameTextView = (TextView) listItemView.findViewById(R.id.item_name);
            nameTextView.setText(currentResult.getName());
            return listItemView;
        }
    }

