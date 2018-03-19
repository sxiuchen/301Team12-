package com.example.dada.View.Requester_MainActivity;

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

import com.example.dada.Model.Task.Task;
import com.example.dada.R;

import java.util.ArrayList;

public class RequesterMainActivity extends AppCompatActivity {
    private ListView rTaskList;
    private ArrayList<Task> subList;


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
                Snackbar.make(view, "Go to new activity and create new task", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

//        getListContent();

//        ArrayAdapter<Task> adapter = new ArrayAdapter<Task>(this,
//                R.layout.content_requester_activity_main_listitem, subList);
//        rTaskList.setAdapter(adapter);

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

//    private void getListContent(){
//        Task t1 = new Task("name1","DONE","des1",null,null,
//                null,1,1f,1.1,1,null);
//        Task t2 = new Task("name2","DONE","des2",null,null,
//                null,2,2f,2.2,2,null);
//        Task t3 = new Task("name3","BIDDED","des3",null,null,
//                null,3,3f,3.3,3,null);
//
//        subList.add(t1);
//        subList.add(t2);
//        subList.add(t3);
//    }

//    private void loadIntoList(){
//
//    }
}
