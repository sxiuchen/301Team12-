/* TaskUnitTest
 *
 * Version 1.0
 *
 * March 15, 2018
 *
 * Copyright (c) 2018 Team 12 CMPUT 301. University of Alberta - All Rights Reserved.
 * You may use distribute or modify this code under terms and condition of the Code of Student Behaviour at University of Alberta.
 * You can find a copy of licence in this project. Otherwise please contact contact sfeng3@ualberta.ca.
 */

package com.example.dada.UnitTest;

import android.location.Location;
import android.media.Image;
import android.os.AsyncTask;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.EditText;

import com.example.dada.Exception.TaskException;
import com.example.dada.Model.OnAsyncTaskCompleted;
import com.example.dada.Model.Task.NormalTask;
import com.example.dada.Model.Task.RequestedTask;
import com.example.dada.Model.Task.Task;
import com.example.dada.R;
import com.example.dada.View.LoginActivity;
import com.robotium.solo.Solo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Unit test cases for task model Since the controller class are designed to be as thick as
 * possible, all business logic are inside the model class, which fits the MVC pattern. Therefore,
 * it's pretty much no need to test controller class.
 */
public class TaskUnitTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    private Solo solo;

    /**
     * Mock callback method
     */
    OnAsyncTaskCompleted mockTask = new OnAsyncTaskCompleted() {
        @Override
        public void onTaskCompleted(Object o) {

        }
    };

    /**
     * Instantiates a new Task test.
     */
    public TaskUnitTest() {
        super("com.example.dada.View", LoginActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        Log.d("TAG1", "setUp()");
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    /**
     * This method is only here for making Junit test method work on robotinum
     * http://stackoverflow.com/questions/11390276/android-junit-tests-not-detecting-in-robotium
     * Author: BlackHatSamurai
     */
    public void testClickButton() {
        solo.enterText((EditText) solo.getView(R.id.edit_text_login_username), "balabl");
        solo.clickOnButton("Requester");
        solo.clickOnButton("Login");
        assertTrue(solo.waitForText("User does not exist, please signup"));
    }

    /**
     * Test update task. Once the task is modified,
     * created, this method would be called to update task
     * to the server. This test covers all update method
     * for the rest of tests. In other word, no need to implement
     * UpdateRequestTask in the rest of the test cases.
     * Generally, the interaction with database or server should be mocked
     * up in the unittest (waste of resource, and possbility to mess up
     * the production environment). Also, this test is not guarantee to pass.
     */
    public void testUpdateRequest() {
        Task.CreateTaskTask createTaskTask = new Task.CreateTaskTask(null);
        String requesterUserName = "sfeng3_tutu";
        Task task = new RequestedTask("title1", "description1", requesterUserName);
        task.setID(UUID.randomUUID().toString());
        createTaskTask.execute(task);
        AsyncTask.Status taskStatus;
        do {
            taskStatus = createTaskTask.getStatus();
        } while (taskStatus != AsyncTask.Status.FINISHED);

        // Wait for task to finished
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String query = String.format(
                "{\n" +
                        "    \"filter\": {\n" +
                        "       \"bool\" : {\n" +
                        "           \"must\" : [\n " +
                        "               { \"term\": {\"requesterUserName\": \"%s\"} },\n" +
                        "           ]\n" +
                        "       }\n" +
                        "    }\n" +
                        "}", requesterUserName);

        Task.GetTasksListTask getTasksListTask = new Task.GetTasksListTask(null);
        getTasksListTask.execute(query);
        AsyncTask.Status anotherStatus;
        do {
            anotherStatus = getTasksListTask.getStatus();
        } while (anotherStatus != AsyncTask.Status.FINISHED);

        ArrayList<NormalTask> getTasks = new ArrayList<>();
        try {
            getTasks = getTasksListTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(task.getID(), getTasks.get(0).getID());
        assertEquals(task, getTasks.get(0));
    }

    /**
     * Test requester confirm task's completion.
     */
    public void testRequesterConfirmTaskComplete() {
        Task request = new RequestedTask("title1", "description1", "sfeng3");
        request.requesterConfirmTaskComplete();
        assertTrue(request.getIsCompleted());
    }

    /**
     * Test requester assign provider.
     */
    public void testRequesterAssignProvider() {
        Task request = new RequestedTask("title1", "description1", "sfeng3");
        request.providerBidTask("yz6_1");
        request.providerBidTask("yz6_2");
        try {
            request.requesterAssignProvider("yz6_1");
        } catch (TaskException e) {
            e.printStackTrace();
        }
        assertEquals(request.getProviderUserName(), "yz6_1");
        assertNull(request.getProviderList());
    }

    /**
     * Test provider bid task.
     */
    public void testRequesterBidTask() {
        Task request = new RequestedTask("title1", "description1", "sfeng3");
        request.providerBidTask("yz6");
        assertTrue(request.getProviderUserName().contains("yz6"));
    }

}
