package com.example.dada.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dada.Controller.TaskController;
import com.example.dada.Exception.TaskException;
import com.example.dada.Model.OnAsyncTaskCompleted;
import com.example.dada.Model.Task.Task;
import com.example.dada.Model.User;
import com.example.dada.R;
import com.example.dada.Util.FileIOUtil;
import com.example.dada.Util.TaskUtil;
import com.novoda.merlin.Merlin;
import com.novoda.merlin.NetworkStatus;
import com.novoda.merlin.registerable.bind.Bindable;
import com.novoda.merlin.registerable.connection.Connectable;
import com.novoda.merlin.registerable.disconnection.Disconnectable;

import java.util.ArrayList;

public class ProviderMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Connectable, Disconnectable, Bindable {

    private User provider;

    protected Merlin merlin;

    private ListView requestedTaskListView;
    private ListView biddedTaskListView;
    private ListView assignedTaskListView;
    private ListView completedTaskListView;

    private ArrayAdapter<Task> requestedTaskAdapter;
    private ArrayAdapter<Task> biddedTaskAdapter;
    private ArrayAdapter<Task> assignedTaskAdapter;
    private ArrayAdapter<Task> completedTaskAdapter;

    private ArrayList<Task> requestedTaskList = new ArrayList<>();
    private ArrayList<Task> biddedTaskList = new ArrayList<>();
    private ArrayList<Task> assignedTaskList = new ArrayList<>();
    private ArrayList<Task> completedTaskList = new ArrayList<>();

    private TaskController requestedTaskController = new TaskController(new OnAsyncTaskCompleted() {
        @Override
        public void onTaskCompleted(Object o) {
            requestedTaskList = (ArrayList<Task>) o;
            requestedTaskAdapter.clear();
            requestedTaskAdapter.addAll(requestedTaskList);
            requestedTaskAdapter.notifyDataSetChanged();
        }
    });

    private TaskController biddedTaskController = new TaskController(new OnAsyncTaskCompleted() {
        @Override
        public void onTaskCompleted(Object o) {
            biddedTaskList = (ArrayList<Task>) o;
            biddedTaskAdapter.clear();
            biddedTaskAdapter.addAll(biddedTaskList);
            biddedTaskAdapter.notifyDataSetChanged();
        }
    });

    private TaskController assignedTaskController = new TaskController(new OnAsyncTaskCompleted() {
        @Override
        public void onTaskCompleted(Object o) {
            assignedTaskList = (ArrayList<Task>) o;
            assignedTaskAdapter.clear();
            assignedTaskAdapter.addAll(assignedTaskList);
            assignedTaskAdapter.notifyDataSetChanged();
        }
    });

    private TaskController completedTaskController = new TaskController(new OnAsyncTaskCompleted() {
        @Override
        public void onTaskCompleted(Object o) {
            completedTaskList = (ArrayList<Task>) o;
            completedTaskAdapter.clear();
            completedTaskAdapter.addAll(completedTaskList);
            completedTaskAdapter.notifyDataSetChanged();
        }
    });

    private TaskController bidRequestedTaskController = new TaskController(new OnAsyncTaskCompleted() {
        @Override
        public void onTaskCompleted(Object o) {
            requestedTaskAdapter.remove((Task) o);
            biddedTaskAdapter.add((Task) o);
            requestedTaskAdapter.notifyDataSetChanged();
            biddedTaskAdapter.notifyDataSetChanged();
        }
    });

    private TaskController bidBiddedTaskController = new TaskController(new OnAsyncTaskCompleted() {
        @Override
        public void onTaskCompleted(Object o) {
            biddedTaskAdapter.notifyDataSetChanged();
        }
    });

    private TaskController completeAssignedTaskController = new TaskController(new OnAsyncTaskCompleted() {
        @Override
        public void onTaskCompleted(Object o) {
            assignedTaskAdapter.remove((Task) o);
            completedTaskAdapter.add((Task) o);
            assignedTaskAdapter.notifyDataSetChanged();
            completedTaskAdapter.notifyDataSetChanged();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_main);

        // monitor network connectivity
        merlin = new Merlin.Builder().withConnectableCallbacks().withDisconnectableCallbacks().withBindableCallbacks().build(this);
        merlin.registerConnectable(this);
        merlin.registerDisconnectable(this);
        merlin.registerBindable(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_provider_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navHeader = navigationView.getHeaderView(0);
        TextView username = navHeader.findViewById(R.id.nav_drawer_provider_username);
        TextView email = navHeader.findViewById(R.id.nav_drawer_provider_email);

        // Get user profile
        provider = FileIOUtil.loadUserFromFile(getApplicationContext());

        // Set drawer text
        username.setText(provider.getUserName());
        email.setText(provider.getEmail());

        // list view
        requestedTaskListView = findViewById(R.id.listView_requestedTask_ProviderMainActivity);
        requestedTaskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // open requested task dialog
                openRequestedTaskDialog(requestedTaskList.get(position));
            }
        });

        biddedTaskListView = findViewById(R.id.listView_biddedTask_ProviderMainActivity);
        biddedTaskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // open bidded task dialog
                openBiddedTaskDialog(biddedTaskList.get(position));
            }
        });

        assignedTaskListView = findViewById(R.id.listView_assignedTask_ProviderMainActivity);
        assignedTaskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // open assigned task dialog
                openAssignedTaskDialog(assignedTaskList.get(position));
            }
        });

        completedTaskListView = findViewById(R.id.listView_completedTask_ProviderMainActivity);
        completedTaskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // open completed task dialog
                openCompletedTaskDialog(completedTaskList.get(position));
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        requestedTaskAdapter = new ArrayAdapter<>(this, R.layout.task_list_item, requestedTaskList);
        biddedTaskAdapter = new ArrayAdapter<>(this, R.layout.task_list_item, biddedTaskList);
        assignedTaskAdapter = new ArrayAdapter<>(this, R.layout.task_list_item, assignedTaskList);
        completedTaskAdapter = new ArrayAdapter<>(this, R.layout.task_list_item, completedTaskList);
        requestedTaskListView.setAdapter(requestedTaskAdapter);
        biddedTaskListView.setAdapter(biddedTaskAdapter);
        assignedTaskListView.setAdapter(assignedTaskAdapter);
        completedTaskListView.setAdapter(completedTaskAdapter);
        updateTaskList();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.provider_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage) {

            // intent to UserEditProfileActivity
            Intent intentUserEditProfile = new Intent(getApplicationContext(), UserEditProfileActivity.class);
            startActivity(intentUserEditProfile);

        } else if (id == R.id.nav_logout) {

            // intent to login activity
            startActivity(new Intent(this, LoginActivity.class));
            finish();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_provider_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void updateTaskList() {
        requestedTaskController.getProviderRequestedTask();
        biddedTaskController.getProviderBiddedTask();
        assignedTaskController.getProviderAssignedTask(provider.getUserName());
        completedTaskController.getProviderCompletedTask(provider.getUserName());
    }

    /**
     * Dialog for Requested Task
     * @param task
     */
    private void openRequestedTaskDialog(final Task task) {

        // get task info, and show it on the dialog
        String title = task.getTitle();
        String description = task.getDescription();
        String requesterUserName = task.getRequesterUserName();

        final EditText input_price = new EditText(ProviderMainActivity.this);

        AlertDialog.Builder builder = new AlertDialog.Builder(ProviderMainActivity.this);

        builder.setTitle("Task Information")
                .setMessage("Title: " + title + "\n" + "Description: " + description + "\n" + "Requester: " + requesterUserName + "\n" + "Price: ")
                .setView(input_price)
                .setNeutralButton("view map", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intentProviderBrowse = new Intent(ProviderMainActivity.this, MapsActivity.class);

                        // http://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android
                        // Serialize the task object and pass it over through the intent
                        intentProviderBrowse.putExtra("task", TaskUtil.serializer(task));
                        startActivity(intentProviderBrowse);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setPositiveButton("bid", new DialogInterface.OnClickListener() {
                    @Override
                    // Driver confirms request
                    public void onClick(DialogInterface dialog, int which) {

                        double price = Double.parseDouble(input_price.getText().toString());

                        // Bid requested task
                        try {
                            bidRequestedTaskController.providerBidTask(task, provider.getUserName(), price);
                        } catch (TaskException e) {
                            e.printStackTrace();
                        }
                    }
                });

        // Create & Show the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Dialog for Bidded Task
     * @param task
     */
    private void openBiddedTaskDialog(final Task task) {

        // get task info, and show it on the dialog
        String title = task.getTitle();
        String description = task.getDescription();
        String requesterUserName = task.getRequesterUserName();
        ArrayList<ArrayList<String>> bidList = task.getBidList();
        String lowestPrice = task.getLowestPrice().toString();

        Boolean hasOldBiddedPrice = false;
        String oldBiddedPrice = "";
        for ( ArrayList<String> bid : bidList ){
            if ( bid.get(0).equals(provider.getUserName()) ){
                hasOldBiddedPrice = true;
                oldBiddedPrice = bid.get(1);
            }
        }

        String message;
        if ( hasOldBiddedPrice ){
            message = "Title: " + title + "\n" + "Description: " + description + "\n" +
                    "Requester: " + requesterUserName + "\n" + "Lowest Price: " + lowestPrice + "\n" +
                    "Old Bidded Price: " + oldBiddedPrice + "\n" + "Updated Price: " + "\n";
        }else{
            message = "Title: " + title + "\n" + "Description: " + description + "\n" +
                    "Requester: " + requesterUserName + "\n" + "Lowest Price: " + lowestPrice +
                    "\n" + "Price: " + "\n";
        }

        final EditText input_price = new EditText(ProviderMainActivity.this);

        AlertDialog.Builder builder = new AlertDialog.Builder(ProviderMainActivity.this);

        builder.setTitle("Task Information")
                .setMessage(message)
                .setView(input_price)
                .setNeutralButton("view map", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intentProviderBrowse = new Intent(ProviderMainActivity.this, ProviderBrowseTaskActivity.class);

                        // http://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android
                        // Serialize the task object and pass it over through the intent
                        intentProviderBrowse.putExtra("task", TaskUtil.serializer(task));
                        startActivity(intentProviderBrowse);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setPositiveButton("bid", new DialogInterface.OnClickListener() {
                    @Override
                    // Driver confirms request
                    public void onClick(DialogInterface dialog, int which) {

                        double price = Double.parseDouble(input_price.getText().toString());

                        // Bid bidded task
                        try {
                            bidBiddedTaskController.providerBidTask(task, provider.getUserName(), price);
                        } catch (TaskException e) {
                            e.printStackTrace();
                        }
                    }
                });

        // Create & Show the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Dialog for Assigned Task
     * @param task
     */
    private void openAssignedTaskDialog(final Task task) {

        // get task info, and show it on the dialog
        String title = task.getTitle();
        String description = task.getDescription();
        String price = task.getPrice().toString();
        String requesterUserName = task.getRequesterUserName();

        AlertDialog.Builder builder = new AlertDialog.Builder(ProviderMainActivity.this);

        builder.setTitle("Task Information")
                .setMessage("Title: " + title + "\n" + "Description: " + description + "\n" + "Requester: " + requesterUserName + "\n" + "Price: " + price + "\n")
                .setNeutralButton("view map", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intentProviderBrowse = new Intent(ProviderMainActivity.this, ProviderBrowseTaskActivity.class);

                        // http://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android
                        // Serialize the task object and pass it over through the intent
                        intentProviderBrowse.putExtra("task", TaskUtil.serializer(task));
                        startActivity(intentProviderBrowse);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .setPositiveButton("complete", new DialogInterface.OnClickListener() {
                    @Override
                    // Driver confirms request
                    public void onClick(DialogInterface dialog, int which) {

                        // Bid requested task
                        try {
                            completeAssignedTaskController.providerCompleteTask(task);
                        } catch (TaskException e) {
                            e.printStackTrace();
                        }
                    }
                });

        // Create & Show the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Dialog for Completed Task
     * @param task
     */
    private void openCompletedTaskDialog(final Task task) {

        // get task info, and show it on the dialog
        String title = task.getTitle();
        String description = task.getDescription();
        String requesterUserName = task.getRequesterUserName();
        String price = task.getPrice().toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(ProviderMainActivity.this);

        builder.setTitle("Task Information")
                .setMessage("Title: " + title + "\n" + "Description: " + description + "\n" + "Requester: " + requesterUserName + "\n" + "Price: " + price + "\n")
                .setNeutralButton("view map", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intentProviderBrowse = new Intent(ProviderMainActivity.this, ProviderBrowseTaskActivity.class);

                        // http://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android
                        // Serialize the task object and pass it over through the intent
                        intentProviderBrowse.putExtra("task", TaskUtil.serializer(task));
                        startActivity(intentProviderBrowse);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        // Create & Show the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Once the device went offline, try to get task list from internal storage
     */
    protected void offlineHandler() {
        requestedTaskController.getProviderOfflineRequestedTask(this);
        biddedTaskController.getProviderOfflineBiddedTask(this);
        assignedTaskController.getProviderOfflineAssignedTask(provider.getUserName(), this);
        completedTaskController.getProviderOfflineCompletedTask(provider.getUserName(), this);
    }

    @Override
    public void onBind(NetworkStatus networkStatus) {
        if (networkStatus.isAvailable()) {
            onConnect();
        } else if (!networkStatus.isAvailable()) {
            onDisconnect();
        }
    }

    @Override
    public void onConnect() {
        // try to update offline assigned request
//        requestedTaskController.updateDriverOfflineRequest(driver.getUserName(), this);
//        updateRequestList();
    }

    @Override
    public void onDisconnect() {
        offlineHandler();
    }
}
