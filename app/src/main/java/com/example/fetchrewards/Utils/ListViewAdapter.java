package com.example.fetchrewards.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.example.fetchrewards.Models.Result;
import com.example.fetchrewards.R;


import java.util.HashMap;
import java.util.List;

public class ListViewAdapter extends BaseExpandableListAdapter {

    private List<String> itemGroups;
    private HashMap<String, List<Result>> results;
    private Context context;

    public ListViewAdapter(Context context, List<String> itemIds, HashMap<String, List<Result>> data) {
        this.context = context;
        itemGroups = itemIds;
        results = data;
    }

    @Override
    public int getGroupCount() {
        return itemGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition){
        return results.get(getGroup(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition){
        return itemGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return results.get(getGroup(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition){
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent){
        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }

        TextView itemIds = (TextView) convertView.findViewById(R.id.item_id_group);

        itemIds.setText((String) getGroup(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent){
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item_group, null);
        }
        TextView itemNames = (TextView) convertView.findViewById(R.id.item_name_group);
        Result result = (Result) getChild(groupPosition, childPosition);
        itemNames.setText(result.getName());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition){
        return true;
    }

}

