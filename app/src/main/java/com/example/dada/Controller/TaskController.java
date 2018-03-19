package com.example.dada.Controller;

import android.content.Context;

import com.example.dada.Model.OnAsyncTaskCompleted;
import com.example.dada.Model.OnAsyncTaskFailure;
import com.example.dada.Model.Task.NormalTask;
import com.example.dada.Model.Task.Task;
import com.example.dada.Util.FileIOUtil;
import com.example.dada.Util.TaskUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

/**
 * Request model's controller, a glue between Activity and Model. Give access for activity(View) to
 * modify and update model.
 */
public class TaskController {

    /**
     * The Listener, callback method when the async task is done
     */
    public OnAsyncTaskCompleted listener;
    public OnAsyncTaskFailure offlineHandler;

    /**
     * Instantiates a new Request controller.
     *
     * @param listener the listener, custom callback method
     */
    public TaskController(OnAsyncTaskCompleted listener) {
        this.listener = listener;
    }

    public TaskController(OnAsyncTaskCompleted listener, OnAsyncTaskFailure offlineHandler) {
        this.listener = listener;
        this.offlineHandler = offlineHandler;
    }

    /**
     * Create a new task and send it to the server
     *
     * @param task The request to be created
     */
    public void createTask(Task task) {
        Task.CreateTaskTask t = new Task.CreateTaskTask(listener, offlineHandler);
        try {
//            request.setID(UUID.randomUUID().toString());
            t.execute(task);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Update a a request
     *
     * @param request The request to be updated
     */
    public void updateRequest(Task request) {
        Task.UpdateTaskTask task = new Task.UpdateTaskTask(listener, offlineHandler);
        task.execute(request);
    }

    /**
     * Cancle a request
     *
     * @param request The request to be deleted
     */
    public void deleteTask(Task request) {
        Task.DeleteTaskTask task = new Task.DeleteTaskTask(listener);
        task.execute(request);
    }

    /**
     * Get a list of all request
     *
     * @return An ArrayList of requests
     */
    public ArrayList<NormalTask> getAllTask() {
        Task.GetTasksListTask task = new Task.GetTasksListTask(listener);
        task.execute("");

        ArrayList<NormalTask> requests = new ArrayList<>();

        try {
            requests =  task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return requests;
    }

    /**
     * Get a list of request that match the geo-location
     *
     * @param location       the coordinate of the location
     * @param driverUserName the driver user name
     */
    // http://stackoverflow.com/questions/36805014/how-to-merge-geo-distance-filter-with-bool-term-query
    // Author: Val
    public void searchTaskByGeoLocation(GeoPoint location, String driverUserName) {
        String query = String.format(
                "{\n" +
                        "    \"filter\": {\n" +
                        "       \"bool\" : {\n" +
                        "           \"must_not\" : [\n" +
                        "               { \"term\": {\"isCompleted\": true} },\n" +
                        "               { \"term\": {\"driverList\": \"%s\"} }\n" +
                        "           ],\n" +
                        "           \"must\": [\n" +
                        "               {\n" +
                        "                   \"nested\": {\n" +
                        "                       \"path\": \"route\",\n" +
                        "                       \"filter\": {\n" +
                        "                           \"geo_distance\": {\n" +
                        "                               \"distance\": \"5km\",\n" +
                        "                               \"origin\": [%.6f, %.6f]\n" +
                        "                           }\n" +
                        "                       }\n" +
                        "                   }\n" +
                        "               }\n" +
                        "           ]\n" +
                        "       }\n" +
                        "    }\n" +
                        "}", driverUserName, location.getLongitude(), location.getLatitude());

        Task.GetTasksListTask task = new Task.GetTasksListTask(listener);
        task.execute(query);
    }

    /**
     * Get a list of request that match the keyword
     *
     * @param keyword        The keyword to match
     * @param driverUserName the driver user name
     * @return An arraylist of matching request.
     */
    public void searchTaskByKeyword(String keyword, String driverUserName) {
        String query = String.format(
                "{\n" +
                        "    \"query\": {\n" +
                        "       \"match\" : {\n" +
                        "           \"requestDescription\" : \"%s\" \n" +
                        "       }\n" +
                        "    },\n" +
                        "    \"filter\": {\n" +
                        "       \"bool\" : {\n" +
                        "           \"must_not\" : [" +
                        "               { \"term\": {\"isCompleted\": true} },\n" +
                        "               { \"term\": {\"driverList\": \"%s\"} }\n" +
                        "           ]\n" +
                        "       }\n" +
                        "    }\n" +
                        "}", keyword, driverUserName);

        Task.GetTasksListTask task = new Task.GetTasksListTask(listener);
        task.execute(query);
    }

    /**
     * Get a list of reuqest that has been accepted by the rider but the request is not completed
     * yet
     *
     * @param driverUserName the driver's username
     */
    public void getDriverAcceptedTask(String driverUserName) {
        String query = String.format(
                "{\n" +
                        "    \"filter\": {\n" +
                        "       \"bool\" : {\n" +
                        "           \"must_not\" : {" +
                        "               \"term\": {\"isCompleted\": true}\n" +
                        "           },\n" +
                        "           \"should\" : [\n " +
                        "               { \"term\": {\"driverUserName\": \"%s\"} }\n" +
                        "           ]\n" +
                        "       }\n" +
                        "    }\n" +
                        "}", driverUserName);
        Task.GetTasksListTask task = new Task.GetTasksListTask(listener);
        task.execute(query);
    }

    /**
     * Get a list of requests that driver accepts but still waiting for confirmation from the rider
     *
     * @param driverUserName the driver's username
     */
    public void getDriverPendingTask(String driverUserName) {
        String query = String.format(
                "{\n" +
                        "    \"filter\": {\n" +
                        "       \"bool\" : {\n" +
                        "           \"must_not\" : {" +
                        "               \"term\": {\"isCompleted\": true}\n" +
                        "           },\n" +
                        "           \"must\" : [\n " +
                        "               { \"term\": {\"driverList\": \"%s\"} }\n" +
                        "           ]\n" +
                        "       }\n" +
                        "    }\n" +
                        "}", driverUserName);
        Task.GetTasksListTask task = new Task.GetTasksListTask(listener);
        task.execute(query);
    }

    /**
     * Get a list of requests of driver's past request
     *
     * @param driverUserName the driver's user name
     */
    public void getDriverCompletedTask(String driverUserName) {
        String query = String.format(
                "{\n" +
                        "    \"filter\": {\n" +
                        "       \"bool\" : {\n" +
                        "           \"must\" : [\n " +
                        "               { \"term\": {\"driverUserName\": \"%s\"} },\n" +
                        "               { \"term\": {\"isCompleted\": true} }\n" +
                        "           ]\n" +
                        "       }\n" +
                        "    }\n" +
                        "}", driverUserName);
        Task.GetTasksListTask task = new Task.GetTasksListTask(listener);
        task.execute(query);
    }

    /**
     * Get a list of requests of driver's past request
     *
     * @param riderUserName the rider's user name
     */
    public void getRiderCompletedTask(String riderUserName) {
        String query = String.format(
                "{\n" +
                        "    \"filter\": {\n" +
                        "       \"bool\" : {\n" +
                        "           \"must\" : [\n " +
                        "               { \"term\": {\"riderUserName\": \"%s\"} },\n" +
                        "               { \"term\": {\"isCompleted\": true} }\n" +
                        "           ]\n" +
                        "       }\n" +
                        "    }\n" +
                        "}", riderUserName);
        Task.GetTasksListTask task = new Task.GetTasksListTask(listener);
        task.execute(query);
    }

    /**
     * Get a list of inprogress request of rider
     *
     * @param riderUserName the rider's username
     */
    public void getRiderInProgressTask(String riderUserName) {
        String query = String.format(
                "{\n" +
                        "    \"filter\": {\n" +
                        "       \"bool\" : {\n" +
                        "           \"must_not\" : {" +
                        "               \"term\": {\"isCompleted\": true}\n" +
                        "           },\n" +
                        "           \"must\" : [\n " +
                        "               { \"term\": {\"riderUserName\": \"%s\"} }\n" +
                        "           ]\n" +
                        "       }\n" +
                        "    }\n" +
                        "}", riderUserName);
        Task.GetTasksListTask task = new Task.GetTasksListTask(listener);
        task.execute(query);
    }

    public void getRiderOfflineTask(String riderUserName, Context context) {
        ArrayList<String> fileList = TaskUtil.getRiderTaskList(context);
        if (fileList == null) return;
        ArrayList<Task> requestsList = FileIOUtil.loadTaskFromFile(context, fileList);
        Iterator<Task> it = requestsList.iterator();
        while (it.hasNext()) {
            Task r = it.next();
            if (!r.getRiderUserName().equals(riderUserName)) {
                it.remove();
            }
        }
        if (requestsList.isEmpty()) return;
        listener.onTaskCompleted(requestsList);
    }

    /**
     * Get a list of driver's pending request while offline
     * @param driverUserName the driver's user name
     * @param context activity context
     */
    public void getDriverOfflinePendingTask(String driverUserName, Context context) {
        ArrayList<String> fileList = TaskUtil.getDriverTaskList(context);
        if (fileList == null) return;
        ArrayList<Task> requestsList = FileIOUtil.loadTaskFromFile(context, fileList);
        Iterator<Task> it = requestsList.iterator();
        while (it.hasNext()) {
            Task r = it.next();
            if (r.getDriverList() == null) continue;
            if (!r.getDriverList().contains(driverUserName)) {
                it.remove();
            }
        }
        if (requestsList.isEmpty()) return;
        listener.onTaskCompleted(requestsList);
    }

    /**
     * Get a list of driver's request while offline
     * @param driverUserName the driver's user name
     * @param context activity context
     */
    public void getDriverOfflineAcceptedTask(String driverUserName, Context context) {
        ArrayList<String> fileList = TaskUtil.getDriverTaskList(context);
        if (fileList == null) return;
        ArrayList<Task> requestsList = FileIOUtil.loadTaskFromFile(context, fileList);
        Iterator<Task> it = requestsList.iterator();
        while (it.hasNext()) {
            Task r = it.next();
            if (r.getDriverUserName() == null || !r.getDriverUserName().equals(driverUserName)) {
                it.remove();
            }
        }
        if (requestsList.isEmpty()) return;
        listener.onTaskCompleted(requestsList);
    }

    /**
     * Send driver's accepted request to the server once the device is back onelin
     * @param driverUserName the driver's user name
     * @param context activity context
     */
    public void updateDriverOfflineTask(String driverUserName, Context context) {
        ArrayList<String> fileList = TaskUtil.getAcceptedTaskList(context);
        if (fileList == null) return;
        ArrayList<Task> requestsList = FileIOUtil.loadTaskFromFile(context, fileList);
        for (Task r : requestsList) {
            if (r.getDriverList() == null || r.getDriverList().contains(driverUserName)) {
                updateTask(r);
                // Delete file after it has been upload
                context.deleteFile(TaskUtil.generateAcceptedReqestFileName(r));
            }
        }
    }

    /**
     * Driver confirm request.
     *
     * @param request        the request
     * @param driverUserName the driver user name
     */
    public void driverConfirmTask(Task request, String driverUserName) {
        request.driverAcceptTask(driverUserName);
        updateTask(request);
    }

    /**
     * Rider confirm request complete.
     *
     * @param request the request to be confirmed completed
     */
    public void riderConfirmTaskComplete(Task request) {
        request.riderConfirmTaskComplete();
        updateTask(request);
    }

    /**
     * Rider confirm driver.
     *
     * @param request        the request to be confirmed by the rider
     * @param driverUserName the driver user name
     * @throws TaskException the request exception
     */
    public void riderConfirmDriver(Task request, String driverUserName) throws TaskException {
        request.riderConfirmDriver(driverUserName);

        updateTask(request);
    }


    /**
     * Calculate estimated fare.
     *
     * @param request the request
     */
    public void calculateEstimatedFare(Task request) {
        double fare = request.getDistance() * 0.50;
        request.setEstimatedFare(fare);
    }

    /**
     * Sets distance.
     *
     * @param request  the request
     * @param distance the distance
     */
    public void setDistance(Task request, double distance) {
        request.setDistance(distance);
    }
}

