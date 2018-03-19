package com.example.dada.Util;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dada.Model.Bidded;
import com.example.dada.Model.Task;
import com.example.dada.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequesterDetailActivity extends ListActivity {

    private Task task;
    private String statusRequested = "REQUSTED";
    private String statusAssigned = "ASSIGNED";
    private String statusBidded = "BIDDED";
    private String statusDone = "DONE";
    private Bidded bid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester_detail);
        //more intent part need                                                              //^_^//
        Intent intent = getIntent();
        task = (Task)intent.getSerializableExtra("task");

        setViews();

        /**
         * listener of listview click action
         */

        if (task.getStatus().equals(statusBidded)) {
            final ListView listView = (ListView)findViewById(R.id.listView);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    bid = task.getBidded_History().get(position);
                    AlertDialog.Builder builder = new AlertDialog.Builder(RequesterDetailActivity.this);
                    builder.setMessage("What do you due with" + bid.getProvider_ID()).setTitle("Notofocation");

                    builder.setPositiveButton("Is Him", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            task.setStatus(statusAssigned);
                            setViews();
                        }
                    });
                    builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.setNeutralButton("Delete Him", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            task.delete_bid(bid);
                            setViews();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }

        
    }

    private void setViews(){
        // Hidden the view that will not in requested page
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setVisibility(View.GONE);
        TextView textViewName = (TextView)findViewById(R.id.textViewName);
        textViewName.setVisibility(View.GONE);
        TextView textViewPhone = (TextView)findViewById(R.id.textViewPhone);
        textViewPhone.setVisibility(View.GONE);
        ImageView imageViewHead = (ImageView)findViewById(R.id.circleImageView);
        imageViewHead.setVisibility(View.GONE);
        Button buttonDone = (Button)findViewById(R.id.buttonDone);
        buttonDone.setVisibility(View.GONE);
        Button buttonNotComplete = (Button)findViewById(R.id.buttonNotComplete);
        buttonNotComplete.setVisibility(View.GONE);


        getActionBar().setTitle(task.getName());

        // Get from https://stackoverflow.com/questions/19452269/android-set-text-to-textview
        // Consider about 2 sides gravity                                                    //^_^//
        TextView textViewDescription = (TextView)findViewById(R.id.textViewDescription);
        textViewDescription.setText(task.getDescription());

        int lowestPrice = task.FetchLowestPrice();
        TextView textViewLowestPrice = (TextView)findViewById(R.id.textViewLowestPrice);
        textViewLowestPrice.setText("Lowest Price Right Now: $" + lowestPrice);
        //Should not allow it since bid cannot with price 0
        if (lowestPrice == 0 && statusRequested.equals(task.getStatus()) ) {
            Log.i("MayBeError", "Lowest Price equal to 0 and status is not Request");
            textViewLowestPrice.setText("Lowest Price Right Now: $" + "0.00");
        }


        TextView textViewStatus = (TextView)findViewById(R.id.textViewStatus);
        ImageView imageViewStatus = (ImageView)findViewById(R.id.imageViewStatus);
        textViewStatus.setText(task.getStatus());
        if (task.getStatus().equals(statusBidded)) {
            imageViewStatus.setColorFilter(Color.MAGENTA);
            listView.setVisibility(View.VISIBLE);
            setListview();
        }
        if (task.getStatus().equals(statusAssigned)) {
            imageViewStatus.setColorFilter(Color.RED);
            imageViewHead.setVisibility(View.VISIBLE);
            imageViewHead.setImageResource(R.drawable.temp_head);
            imageViewHead.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            textViewName.setVisibility(View.VISIBLE);
            textViewName.setText(task.getRequester());
            textViewPhone.setVisibility(View.VISIBLE);
            textViewPhone.setText(task.getRequester());
            buttonDone.setVisibility(View.VISIBLE);
            buttonNotComplete.setVisibility(View.VISIBLE);
        }

        if (task.getStatus().equals(statusDone)) {
            imageViewStatus.setColorFilter(Color.GREEN);
            imageViewHead.setVisibility(View.VISIBLE);
            imageViewHead.setImageResource(R.drawable.temp_head);
            imageViewHead.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            textViewName.setVisibility(View.VISIBLE);
            textViewName.setText(task.getRequester());
            textViewPhone.setVisibility(View.VISIBLE);
            textViewPhone.setText(task.getRequester());
        }

        //Wait to set Picture                                                                //^_^//

        //wait to set location

    }

    // from https://www.cnblogs.com/allin/archive/2010/05/11/1732200.html
    private void setListview() {

        SimpleAdapter listViewAdapter = new SimpleAdapter(this, setListItem(),
                R.layout.activity_requester_detail_listitem, new String[]{"img", "title", "prive"},
                new int[]{R.id.img, R.id.title, R.id.price});
        setListAdapter(listViewAdapter);
    }

    private List<Map<String, Object>> setListItem() {
        List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
        ArrayList<Bidded> history = task.getBidded_History();
        for (int i=0; i < history.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();

            // set picture                                                                   //^_^//
            map.put("img", R.drawable.temp_head);
            Bidded bid = history.get(i);
            map.put("title", bid.getProvider_ID());
            map.put("price", bid.getPrice());
            itemList.add(map);
        }
        return itemList;
    }

    public void doneOnClick(View view) {
        if (task.getStatus().equals(statusAssigned)) {
            task.setStatus(statusDone);
            setViews();
        }
    }

    public void notCompleteOnClick(View view) {
        if (task.getStatus().equals(statusAssigned)) {
            task.setStatus(statusRequested);
            setViews();
        }
    }
}
