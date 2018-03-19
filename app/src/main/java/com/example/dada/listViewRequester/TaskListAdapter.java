package com.example.dada.listViewRequester;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dada.Model.Task;
import com.example.dada.R;

import java.util.ArrayList;

/**
 * Created by kq on 3/18/2018.
 */

public class TaskListAdapter extends BaseAdapter {
    public Activity context;
    public ArrayList<Task> taskSubList;
    private static LayoutInflater inflater = null;

    public TaskListAdapter(Activity context, ArrayList<Task> taskSubList){
        this.context = context;
        this.taskSubList = taskSubList;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return taskSubList.size();
    }

    @Override
    public Object getItem(int position) {
        return taskSubList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        itemView = (itemView == null)? inflater.inflate(R.layout.requester_tasklist_listitem,null):itemView;
        TextView textViewTitle = (TextView) itemView.findViewById(R.id.taskTitle);
        TextView textViewStatus = (TextView) itemView.findViewById(R.id.taskStatus);
        Task  selectedTask = taskSubList.get(position);
        textViewTitle.setText(selectedTask.getName());
        textViewStatus.setText(selectedTask.getStatus());

    }
}
