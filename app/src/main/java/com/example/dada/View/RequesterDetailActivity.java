package com.example.dada.View;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dada.Controller.TaskController;
import com.example.dada.Model.OnAsyncTaskCompleted;
import com.example.dada.Model.OnAsyncTaskFailure;
import com.example.dada.Model.Task.NormalTask;
import com.example.dada.Model.Task.Task;
import com.example.dada.Model.User;
import com.example.dada.R;
import com.example.dada.Util.FileIOUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequesterDetailActivity extends ListActivity {
    private NormalTask task;
    private String statusRequested = "REQUSTED";
    private String statusAssigned = "ASSIGNED";
    private String statusBidded = "BIDDED";
    private String statusDone = "DONE";
    private String providerName;
    private TaskController taskController = new TaskController(new OnAsyncTaskCompleted() {
        @Override
        public void onTaskCompleted(Object o) {
            Task task = (Task) o;
            FileIOUtil.saveTaskInFile(task, "temp", getApplicationContext()); //    //^_^//
        }
    }, new OnAsyncTaskFailure() {
        @Override
        public void onTaskFailed(Object o) {
            Task task = (Task) o;
            FileIOUtil.saveOfflineTaskInFile(task, getApplicationContext());
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requester_detail);
        //more intent part need                                                              //^_^//
        Intent intent = getIntent();
        task = (NormalTask)intent.getSerializableExtra("task");

        setViews();

        /**
         * listener of listview click action
         */

        if (task.getStatus().equals(statusBidded)) {
            final ListView listView = (ListView)findViewById(R.id.listView);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    providerName = task.getProviderList().get(position);
                    AlertDialog.Builder builder = new AlertDialog.Builder(RequesterDetailActivity.this);
                    builder.setMessage("What do you due with" + providerName).setTitle("Notofocation");

                    builder.setPositiveButton("Is Him", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            try {
                                task.requesterAssignProvider(providerName);
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), e.toString(),
                                        Toast.LENGTH_SHORT).show();
                            }
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
                            ArrayList<String> providerList = task.getProviderList();
                            providerList.remove(i);
                            task.setProviderList(providerList);
                            setViews();
                        }
                    });
                    taskController.updateTask(task);
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


        getActionBar().setTitle(task.getTitle());

        // Get from https://stackoverflow.com/questions/19452269/android-set-text-to-textview
        // Consider about 2 sides gravity                                                    //^_^//
        TextView textViewDescription = (TextView)findViewById(R.id.textViewDescription);
        textViewDescription.setText(task.getTaskDescription());

        int lowestPrice = 1;
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
            // temp test                                                                     //^_^//
            imageViewHead.setImageResource(R.drawable.temp_head);
            imageViewHead.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            textViewName.setVisibility(View.VISIBLE);
            textViewName.setText(task.getRequesterUserName());
            textViewPhone.setVisibility(View.VISIBLE);
            textViewPhone.setText(task.getRequesterUserName());
            buttonDone.setVisibility(View.VISIBLE);
            buttonNotComplete.setVisibility(View.VISIBLE);
        }

        if (task.getStatus().equals(statusDone)) {
            imageViewStatus.setColorFilter(Color.GREEN);
            imageViewHead.setVisibility(View.VISIBLE);
            // temp test                                                                     //^_^//
            imageViewHead.setImageResource(R.drawable.temp_head);
            imageViewHead.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            textViewName.setVisibility(View.VISIBLE);
            textViewName.setText(task.getRequesterUserName());
            textViewPhone.setVisibility(View.VISIBLE);
            textViewPhone.setText(task.getRequesterUserName());
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
        ArrayList<String> providerNames = task.getProviderList();
        for (int i=0; i < providerNames.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();

            // set picture                                                                   //^_^//
            map.put("img", R.drawable.temp_head);
            String name = providerNames.get(i);
            map.put("title", name);
                                                                                             //^_^//
            map.put("price", 1);
            itemList.add(map);
        }
        return itemList;
    }

    public void doneOnClick(View view) {
        if (task.getStatus().equals(statusAssigned)) {
            task.setStatus(statusDone);
            taskController.updateTask(task);
            setViews();
        }
    }

    public void notCompleteOnClick(View view) {
        if (task.getStatus().equals(statusAssigned)) {
            task.setStatus(statusRequested);
            taskController.updateTask(task);
            setViews();
        }
    }
}
