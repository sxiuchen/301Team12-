/* TaskController
 *
 * Version 1.0
 *
 * March 15, 2018
 *
 * Copyright (c) 2018 Team 12 CMPUT 301. University of Alberta - All Rights Reserved.
 * You may use distribute or modify this code under terms and condition of the Code of Student Behaviour at University of Alberta.
 * You can find a copy of licence in this project. Otherwise please contact contact sfeng3@ualberta.ca.
 */

package com.example.dada.Controller;

import android.content.Context;

import com.example.dada.Exception.TaskException;
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
            t.execute(task);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Update a task
     *
     * @param task The task to be updated
     */
    public void updateTask(Task task) {
        Task.UpdateTaskTask t = new Task.UpdateTaskTask(listener, offlineHandler);
        t.execute(task);
    }

    /**
     * Cancel a task
     *
     * @param task The task to be deleted
     */
    public void deleteTask(Task task) {
        Task.DeleteTaskTask t = new Task.DeleteTaskTask(listener);
        t.execute(task);
    }

    /**
     * Get a list of all task
     *
     * @return An ArrayList of tasks
     */
    public ArrayList<NormalTask> getAllTask() {
        Task.GetTasksListTask t = new Task.GetTasksListTask(listener);
        t.execute("");

        ArrayList<NormalTask> tasks = new ArrayList<>();

        try {
            tasks = t.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return tasks;
    }


    // TODO Get a list of task that match the geo-location
//    /**
//     * Get a list of task that match the geo-location
//     *
//     * @param location       the coordinate of the location
//     * @param driverUserName the driver user name
//     */
//    // http://stackoverflow.com/questions/36805014/how-to-merge-geo-distance-filter-with-bool-term-query
//    // Author: Val
//    public void searchTaskByGeoLocation(GeoPoint location, String driverUserName) {
//        String query = String.format(
//                "{\n" +
//                        "    \"filter\": {\n" +
//                        "       \"bool\" : {\n" +
//                        "           \"must_not\" : [\n" +
//                        "               { \"term\": {\"isCompleted\": true} },\n" +
//                        "               { \"term\": {\"driverList\": \"%s\"} }\n" +
//                        "           ],\n" +
//                        "           \"must\": [\n" +
//                        "               {\n" +
//                        "                   \"nested\": {\n" +
//                        "                       \"path\": \"route\",\n" +
//                        "                       \"filter\": {\n" +
//                        "                           \"geo_distance\": {\n" +
//                        "                               \"distance\": \"5km\",\n" +
//                        "                               \"origin\": [%.6f, %.6f]\n" +
//                        "                           }\n" +
//                        "                       }\n" +
//                        "                   }\n" +
//                        "               }\n" +
//                        "           ]\n" +
//                        "       }\n" +
//                        "    }\n" +
//                        "}", driverUserName, location.getLongitude(), location.getLatitude());
//
//        Task.GetTasksListTask task = new Task.GetTasksListTask(listener);
//        task.execute(query);
//    }

    /**
     * Get a list of tasks that match the keyword
     *
     * @param keyword           The keyword to match
     * @param providerUserName  The driver user name
     * @return An Arraylist of matching tasks.
     */
    public void searchTaskByKeyword(String keyword, String providerUserName) {
        String query = String.format(
                "{\n" +
                        "    \"query\": {\n" +
                        "       \"match\" : {\n" +
                        "           \"taskDescription\" : \"%s\" \n" +
                        "       }\n" +
                        "    },\n" +
                        "    \"filter\": {\n" +
                        "       \"bool\" : {\n" +
                        "           \"must_not\" : [" +
                        "               { \"term\": {\"isCompleted\": true} },\n" +
                        "               { \"term\": {\"providerList\": \"%s\"} }\n" +
                        "           ]\n" +
                        "       }\n" +
                        "    }\n" +
                        "}", keyword, providerUserName);

        Task.GetTasksListTask task = new Task.GetTasksListTask(listener);
        task.execute(query);
    }

    /**
     * Get a list of tasks that has been assigned by the requester but the task is not completed
     * yet
     *
     * @param providerUserName the driver's username
     */
    public void getProviderAcceptedTask(String providerUserName) {
        String query = String.format(
                "{\n" +
                        "    \"filter\": {\n" +
                        "       \"bool\" : {\n" +
                        "           \"must_not\" : {" +
                        "               \"term\": {\"isCompleted\": true}\n" +
                        "           },\n" +
                        "           \"should\" : [\n " +
                        "               { \"term\": {\"providerUserName\": \"%s\"} }\n" +
                        "           ]\n" +
                        "       }\n" +
                        "    }\n" +
                        "}", providerUserName);
        Task.GetTasksListTask task = new Task.GetTasksListTask(listener);
        task.execute(query);
    }

    /**
     * Get a list of tasks of requester's past tasks
     *
     * @param requesterUserName the requester's user name
     */
    public void getRequesterCompletedTask(String requesterUserName) {
        String query = String.format(
                "{\n" +
                        "    \"filter\": {\n" +
                        "       \"bool\" : {\n" +
                        "           \"must\" : [\n " +
                        "               { \"term\": {\"requesterUserName\": \"%s\"} },\n" +
                        "               { \"term\": {\"isCompleted\": true} }\n" +
                        "           ]\n" +
                        "       }\n" +
                        "    }\n" +
                        "}", requesterUserName);
        Task.GetTasksListTask task = new Task.GetTasksListTask(listener);
        task.execute(query);
    }

    /**
     * Get a list of in progress tasks of requester
     *
     * @param requesterUserName the requester's username
     */
    public void getRequesterInProgressTask(String requesterUserName) {
        String query = String.format(
                "{\n" +
                        "    \"filter\": {\n" +
                        "       \"bool\" : {\n" +
                        "           \"must_not\" : {" +
                        "               \"term\": {\"isCompleted\": true}\n" +
                        "           },\n" +
                        "           \"must\" : [\n " +
                        "               { \"term\": {\"requesterUserName\": \"%s\"} }\n" +
                        "           ]\n" +
                        "       }\n" +
                        "    }\n" +
                        "}", requesterUserName);
        Task.GetTasksListTask task = new Task.GetTasksListTask(listener);
        task.execute(query);
    }

    /**
     * Get a list of requested tasks for provider
     */
    public void getProviderRequestedTask(){
        String query = String.format(
                "{\n" +
                        "    \"query\": {\n" +
                        "       \"term\" : { \"status\" : \"requested\" }\n" +
                        "    }\n" +
                        "}");
        Task.GetTasksListTask task = new Task.GetTasksListTask(listener);
        task.execute(query);
    }

    /**
     * Get a list of bidded tasks for provider
     */
    public void getProviderBiddedTask(){
        String query = String.format(
                "{\n" +
                        "    \"query\": {\n" +
                        "       \"term\" : { \"status\" : \"bidded\" }\n" +
                        "    }\n" +
                        "}");
        Task.GetTasksListTask task = new Task.GetTasksListTask(listener);
        task.execute(query);
    }

    /**
     * Get a list of tasks of provider's assigned tasks
     *
     * @param providerUserName the provider's user name
     */
    public void getProviderAssignedTask(String providerUserName){
        String query = String.format(
                "{\n" +
                        "    \"filter\": {\n" +
                        "       \"bool\" : {\n" +
                        "           \"must\" : [\n " +
                        "               { \"term\": {\"providerUserName\": \"%s\"} },\n" +
                        "               { \"term\": {\"isCompleted\": false} }\n" +
                        "               { \"term\": {\"status\": \"assigned\"} }\n" +
                        "           ]\n" +
                        "       }\n" +
                        "    }\n" +
                        "}", providerUserName);
        Task.GetTasksListTask task = new Task.GetTasksListTask(listener);
        task.execute(query);
    }

    /**
     * Get a list of tasks of provider's past tasks
     *
     * @param providerUserName the provider's user name
     */
    public void getProviderCompletedTask(String providerUserName) {
        String query = String.format(
                "{\n" +
                        "    \"filter\": {\n" +
                        "       \"bool\" : {\n" +
                        "           \"must\" : [\n " +
                        "               { \"term\": {\"providerUserName\": \"%s\"} },\n" +
                        "               { \"term\": {\"isCompleted\": true} }\n" +
                        "               { \"term\": {\"status\": \"completed\"} }\n" +
                        "           ]\n" +
                        "       }\n" +
                        "    }\n" +
                        "}", providerUserName);
        Task.GetTasksListTask task = new Task.GetTasksListTask(listener);
        task.execute(query);
    }

    // TODO offline task
//    /**
//     * Get a list of requester's pending tasks while offline
//     * @param providerUserName   the driver's user name
//     * @param context            activity context
//     */
//    public void getRequesterOfflineTask(String riderUserName, Context context) {
//        ArrayList<String> fileList = TaskUtil.getRequesterTaskList(context);
//        if (fileList == null) return;
//        ArrayList<Task> requestsList = FileIOUtil.loadTaskFromFile(context, fileList);
//        Iterator<Task> it = requestsList.iterator();
//        while (it.hasNext()) {
//            Task r = it.next();
//            if (!r.getRiderUserName().equals(riderUserName)) {
//                it.remove();
//            }
//        }
//        if (requestsList.isEmpty()) return;
//        listener.onTaskCompleted(requestsList);
//    }

//    /**
//     * Get a list of provider's pending tasks while offline
//     * @param providerUserName   the driver's user name
//     * @param context            activity context
//     */
//    public void getDriverOfflinePendingTask(String providerUserName, Context context) {
//        ArrayList<String> fileList = TaskUtil.getDriverTaskList(context);
//        if (fileList == null) return;
//        ArrayList<Task> requestsList = FileIOUtil.loadTaskFromFile(context, fileList);
//        Iterator<Task> it = requestsList.iterator();
//        while (it.hasNext()) {
//            Task r = it.next();
//            if (r.getDriverList() == null) continue;
//            if (!r.getDriverList().contains(providerUserName)) {
//                it.remove();
//            }
//        }
//        if (requestsList.isEmpty()) return;
//        listener.onTaskCompleted(requestsList);
//    }

//    /**
//     * Get a list of driver's request while offline
//     * @param driverUserName the driver's user name
//     * @param context activity context
//     */
//    public void getDriverOfflineAcceptedTask(String driverUserName, Context context) {
//        ArrayList<String> fileList = TaskUtil.getDriverTaskList(context);
//        if (fileList == null) return;
//        ArrayList<Task> requestsList = FileIOUtil.loadTaskFromFile(context, fileList);
//        Iterator<Task> it = requestsList.iterator();
//        while (it.hasNext()) {
//            Task r = it.next();
//            if (r.getDriverUserName() == null || !r.getDriverUserName().equals(driverUserName)) {
//                it.remove();
//            }
//        }
//        if (requestsList.isEmpty()) return;
//        listener.onTaskCompleted(requestsList);
//    }

//    /**
//     * Send driver's accepted request to the server once the device is back onelin
//     * @param driverUserName the driver's user name
//     * @param context activity context
//     */
//    public void updateDriverOfflineTask(String driverUserName, Context context) {
//        ArrayList<String> fileList = TaskUtil.getAcceptedTaskList(context);
//        if (fileList == null) return;
//        ArrayList<Task> requestsList = FileIOUtil.loadTaskFromFile(context, fileList);
//        for (Task r : requestsList) {
//            if (r.getDriverList() == null || r.getDriverList().contains(driverUserName)) {
//                updateTask(r);
//                // Delete file after it has been upload
//                context.deleteFile(TaskUtil.generateAcceptedReqestFileName(r));
//            }
//        }
//    }

    /**
     * Provider bid task.
     *
     * @param task              the task
     * @param providerUserName  the provider user name
     */
    public void providerBidTask(Task task, String providerUserName) {
        task.providerBidTask(providerUserName);
        updateTask(task);
    }

    /**
     * Requester confirm task complete.
     *
     * @param task the task to be confirmed completed
     */
    public void requesterConfirmTaskComplete(Task task) {
        task.requesterConfirmTaskComplete();
        updateTask(task);
    }

    /**
     * Requester confirm provider.
     *
     * @param task                  the task to be confirmed by the requester
     * @param providerUserName      the provider user name
     * @throws TaskException        the task exception
     */
    public void requesterConfirmProvider(Task task, String providerUserName) throws TaskException {
        task.requesterAssignProvider(providerUserName);
        updateTask(task);
    }
}

