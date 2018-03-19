package com.example.dada.View.Requester_MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dada.Model.Task.NormalTask;
import com.example.dada.Model.Task.Task;
import com.example.dada.R;
import com.example.dada.View.RequesterAddTaskActivity;

import java.util.ArrayList;

public class RequesterMainActivity extends AppCompatActivity {
    private ListView rTaskList;
    private ArrayList<Task> tasks = new ArrayList<>();
    private taskAdapter adapter;


    ////////////////////////////////////// onCreate ////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester_main);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Spinner
        final Spinner RLspinner = (Spinner) findViewById(R.id.filter_button);
        ArrayAdapter<String> filterAdapter = new ArrayAdapter<String>(RequesterMainActivity.this,
                R.layout.nav_spinner_requester_activity_main,
                getResources().getStringArray(R.array.filterOption));
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        RLspinner.setAdapter(filterAdapter);

        RLspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(RequesterMainActivity.this,
                        RLspinner.getSelectedItem().toString(),
                        Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //add button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentRequesterAddTask = new Intent(getApplicationContext(), RequesterAddTaskActivity.class);
                startActivity(intentRequesterAddTask);
            }
        });

        //listView
        rTaskList = (ListView) findViewById(R.id.rListView);
        rTaskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        rTaskList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });
    }

    ////////////////////////////////onStart/////////////////////////////////////////////////

    protected void onStart() {
        super.onStart();

        loadIntoList();

    }

    ///////////////////////////////onDestroy////////////////////////////////////////////////////

    protected void onDestroy() {
        super.onDestroy();

    }

    ////////////////////////////////////// other ////////////////////////////////////////////////

    //toolBar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.requester_activity_main_toolbar,menu);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    //search button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        switch(res_id){
            case R.id.search_button:
                //
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void loadIntoList(){
        //For testing
        NormalTask t1 = new NormalTask("name1","des1", "r1");
        t1.setStatus("DONE");
        NormalTask t2 = new NormalTask("name2","des2", "r2");
        t2.setStatus("DONE");
        NormalTask t3 = new NormalTask("name3","des3", "r3");
        t3.setStatus("BIDDED");

        tasks.add(t1);
        tasks.add(t2);
        tasks.add(t3);

        /*

         */

        adapter = new taskAdapter(this, tasks);
        rTaskList.setAdapter(adapter);

    }

}
