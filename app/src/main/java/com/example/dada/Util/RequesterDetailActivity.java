// //just want to make program runnable

// /package com.example.dada.Util;
//
//import android.content.Intent;
//import android.location.Location;
//import android.media.Image;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.TextView;
//
//import com.example.dada.Model.Bidded;
//import com.example.dada.Model.Task;
//import com.example.dada.R;
//
//import java.util.ArrayList;
//
//public class RequesterDetailActivity extends AppCompatActivity {
//
//    private Task task;
//    private String statusRequested = "REQUSTED";
//    private String statusAssigned = "ASSIGNED";
//    private String statusBidded = "BIDDED";
//    private String statusDone = "DONE";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_requester_detail);
//        //more intent part need                                                              //^_^//
//        Intent intent = getIntent();
//
//        setViews();
//    }
//
//    private void setViews(task){
//        getActionBar().setTitle(task.getName());
//
//        // Get from https://stackoverflow.com/questions/19452269/android-set-text-to-textview
//        // Consider about 2 sides gravity                                                    //^_^//
//        TextView textViewDescription = (TextView)findViewById(R.id.textViewDescription);
//        textViewDescription.setText(task.getDescription());
//
//        int lowestPrice = task.FetchLowestPrice();
//        TextView textViewLowestPrice = (TextView)findViewById(R.id.textViewLowestPrice);
//        textViewLowestPrice.setText("Lowest Price Right Now: $" + lowestPrice);
//        //Should not allow it since bid cannot with price 0
//        if (lowestPrice == 0 && statusRequested.equals(task.getStatus()) ) {
//            Log.i("MayBeError", "Lowest Price equal to 0 and status is not Request");
//            textViewLowestPrice.setText("Lowest Price Right Now: $" + "0.00");
//        }
//
//        TextView textViewStatues = (TextView)findViewById(R.id.textViewStatus);
//        textViewStatues.setText(task.getStatus());
//
//        //Wait to set Picture                                                                //^_^//
//
//    }
//}
