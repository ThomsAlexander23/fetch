package com.example.fetchrewards.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.example.fetchrewards.Models.Result;
import com.example.fetchrewards.R;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

public class ListViewAdapter  extends BaseExpandableListAdapter {

    private List<Integer> itemGroups;
    private HashMap<Integer, List<Result>> model;
    private Context context;

    public ListViewAdapter(Context context, List<Integer> itemIds, HashMap<Integer, List<Result> results) {
        this.context = context;
        itemGroups = itemIds;
        model = results;
    }

    @Override
    public int getGroupCount() {
        return itemGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition){
        return itemGroups.get(getGroup(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition){
        return itemGroups.get(groupPosition);
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

        TextView itemIdGroup = (TextView) convertView.findViewById(R.id.item_id_group);

        itemIdGroup.setText((Integer) getGroup(groupPosition));
        return convertView;
    }

    @Override
    public View getCholdView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent){
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item_group, null);
        }
        TextView itemNameGroup = (TextView) convertView.findViewById(R.id.item_name_group);
        Result result = (Result) getChild(groupPosition, childPosition);
        itemNameGroup.setText(result.getName());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition){
        return true;
    }

}

