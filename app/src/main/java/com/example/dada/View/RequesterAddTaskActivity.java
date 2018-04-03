/*
 * RequesterAddTaskActivity
 *
 *
 * Mar 19, 2018
 *
 * Copyright (c) 2018 Haotian Qi. CMPUT301, University of Alberta - All Rights Reserved.
 * You may use, distribute, or modify this code under terms and condition of the Code of Student Behaviour at University of Alberta.
 * You can find a copy of the license in this project. Otherwise please contact me.
 */
package com.example.dada.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dada.Controller.TaskController;
import com.example.dada.Exception.TaskException;
import com.example.dada.Model.OnAsyncTaskCompleted;
import com.example.dada.Model.Task.RequestedTask;
import com.example.dada.Model.Task.Task;
import com.example.dada.Model.User;
import com.example.dada.R;
import com.example.dada.Util.FileIOUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.UUID;

/**
 * activity to handle interface of adding new task from user
 */

public class RequesterAddTaskActivity extends AppCompatActivity implements OnMapReadyCallback {

    private EditText titleText;
    private EditText descriptionText;
    private User requester;

    private Button doneButton;

    private TaskController taskController = new TaskController(new OnAsyncTaskCompleted() {
        @Override
        public void onTaskCompleted(Object o) {
            Task t = (Task) o;
            FileIOUtil.saveRequesterTaskInFile(t, getApplicationContext());
        }
    });

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester_add_task);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        titleText = findViewById(R.id.editText_requester_add_task_title);
        descriptionText = findViewById(R.id.editText_requester_add_task_description);

        doneButton = findViewById(R.id.newTask_done_button);
        assert doneButton != null;
        doneButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                addTask();
            }
        });

        // Get user profile
        requester = FileIOUtil.loadUserFromFile(getApplicationContext());

        // map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_requester_add_task);
        mapFragment.getMapAsync(this);
    }

    public void addTask() {
        String title = titleText.getText().toString();
        String description = descriptionText.getText().toString();

        boolean validTitle = !(title.isEmpty() || title.trim().isEmpty());
        boolean validDescription = !(description.isEmpty() || description.trim().isEmpty());

        if (!(validTitle && validDescription)) {
            Toast.makeText(this, "Task Title/Description is not valid.", Toast.LENGTH_SHORT).show();
        } else {
            Task task = new RequestedTask(title, description, requester.getUserName());
            task.setID(UUID.randomUUID().toString());
            taskController.createTask(task);
            finish();
            Intent intentRequesterMain = new Intent(getApplicationContext(), RequesterMainActivity.class);
            startActivity(intentRequesterMain);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Move the camera to Edmonton
        LatLng edmonton = new LatLng(53.631611, -113.323975);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(edmonton,10));

    }
}
