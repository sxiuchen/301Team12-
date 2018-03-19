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

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by kq on 3/19/2018.
 */

public class taskAdapter extends BaseAdapter{
    Activity context;
    ArrayList<Task> tasks;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        itemView = (itemView == null) ? inflater.inflate(R.layout.content_requester_activity_main_listitem,null):itemView;
        TextView textViewTitle = (TextView) itemView.findViewById(R.id.taskTitle);
        TextView textViewStatus = (TextView) itemView.findViewById(R.id.taskStatus);
        Task selectedTask = tasks.get(position);
        textViewTitle.setText(selectedTask.getTitle());
        textViewStatus.setText(selectedTask.getStatus());
        return itemView;
    }
}
