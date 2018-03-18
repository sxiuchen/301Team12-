package com.example.dada;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //For requester listView testing
        Button RListTestButton = (Button) findViewById(R.id.goTo_RListTest);
        RListTestButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), com.example.dada.listViewRequester.RequesterTaskListActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
