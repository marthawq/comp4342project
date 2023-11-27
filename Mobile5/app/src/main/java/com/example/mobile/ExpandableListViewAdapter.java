package com.example.mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private List<HashMap<String,Object>> topics;
    private ArrayList<ArrayList<String>> posts;
    Context context;

    View.OnClickListener ivGoToChildClickListener;

    ExpandableListViewAdapter(){

    }

    ExpandableListViewAdapter(List<HashMap<String,Object>> topics, ArrayList<ArrayList<String>> posts, Context context){
        this.topics = topics;
        this.posts = posts;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return topics.size();
    }

    @Override
    public int getChildrenCount(int position) {
        return posts.get(position).size();
    }

    @Override
    public Object getGroup(int position) {
        return topics.get(position);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return posts.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        HashMap<String,Object> topic = (HashMap<String,Object>)getGroup(groupPosition);
        if(convertView== null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.topic, null);
        }
        TextView author = convertView.findViewById(R.id.author);
        TextView title = convertView.findViewById(R.id.title);
        TextView content = convertView.findViewById(R.id.content);
        //Button viewButton = convertView.findViewById(R.id.postButton);

        author.setText((String)topic.get("author"));
        title.setText((String)topic.get("title"));
        content.setText((String)topic.get("content"));
        //viewButton = (Button) topic.get("postButton");
        //viewButton.setText("Reply");
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View convertView, ViewGroup viewGroup) {
        String post = (String)getChild(groupPosition,childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.reply, null);
        }

            TextView reply = convertView.findViewById(R.id.replyTo);
            reply.setText(post);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
