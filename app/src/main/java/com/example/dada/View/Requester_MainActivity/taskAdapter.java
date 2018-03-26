/*
 * taskAdapter
 *
 *
 * Mar 19, 2018
 *
 * Copyright (c) 2018 Haotian Qi. CMPUT301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and condition of the Code of Student Behaviour at University of Alberta.
 * You can find a copy of the license in this project. Otherwise please contact me.
 */
package com.example.dada.View.Requester_MainActivity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dada.Model.Task.Task;
import com.example.dada.R;

import java.util.ArrayList;

/**
 * Created by kq on 3/19/2018.
 */

/**
 * the adapter for handling list of task objects
 */

public class taskAdapter extends BaseAdapter{
    private Activity context;
    private ArrayList<Task> tasks;
    private static LayoutInflater inflater = null;

    public taskAdapter(Activity context, ArrayList<Task> tasks){
        this.context = context;
        this.tasks = tasks;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * the function made to be call in other activity to load tasks into listView
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        itemView = (itemView == null) ? inflater.inflate(R.layout.content_requester_activity_main_listitem2,null):itemView;
        TextView textViewTitle = (TextView) itemView.findViewById(R.id.taskTitle);
        TextView textViewStatus = (TextView) itemView.findViewById(R.id.taskStatus);
        Task selectedTask = tasks.get(position);
        textViewTitle.setText(selectedTask.getTitle());
        textViewStatus.setText(selectedTask.getStatus());
        return itemView;
    }
}
