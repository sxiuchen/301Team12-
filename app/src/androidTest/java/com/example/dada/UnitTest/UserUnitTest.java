package com.example.dada.UnitTest;

import android.os.AsyncTask;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.EditText;

import com.example.dada.Model.OnAsyncTaskCompleted;
import com.example.dada.Model.User;
import com.example.dada.R;
import com.example.dada.View.LoginActivity;
import com.robotium.solo.Solo;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


public class UserUnitTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    private Solo solo;

    /**
     * Mock callback method
     */
    OnAsyncTaskCompleted mockTask = new OnAsyncTaskCompleted() {
        @Override
        public void onTaskCompleted(Object o) {

        }
    };

    public UserUnitTest() {
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

    /*
    This method is only here for making
    Junit test method work on robotinum
    http://stackoverflow.com/questions/11390276/android-junit-tests-not-detecting-in-robotium
    Author: BlackHatSamurai
     */
    public void testClickButton() {
        solo.enterText((EditText) solo.getView(R.id.edit_text_login_username), "balabl");
        solo.clickOnButton("Requester");
        solo.clickOnButton("Login");
        assertTrue(solo.waitForText("User does not exist, please signup"));
    }

    // http://stackoverflow.com/questions/7588584/android-asynctask-check-status

    /**
     * Test case for creating new user
     * and get user profile method.
     *
     * Generally, the interaction with database or server should be mocked
     * up in the unittest (waste of resource, and possbility to mess up
     * the production environment). Also, this test is not guarantee to pass.
     */
    public void testCreateUser() {
        String userName = "sfeng3_tutu";
        String mobileNumber = "100-1000-2000";
        String emailAddress = userName + "@cs.ualberta.ca";
        User user = new User(userName, mobileNumber, emailAddress);
        user.setID(UUID.randomUUID().toString());

        User.CreateUserTask createUserTask = new User.CreateUserTask(mockTask);
        createUserTask.execute(user);
        // Hang around till is done
        AsyncTask.Status taskStatus;
        do {
            taskStatus = createUserTask.getStatus();
        } while (taskStatus != AsyncTask.Status.FINISHED);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String query = String.format(
                "{\n" +
                        "    \"query\": {\n" +
                        "       \"term\" : { \"userName\" : \"%s\" }\n" +
                        "    }\n" +
                        "}", userName);
        Log.d("Debug", query);

        User.GetUserProfileTask getUserTask = new User.GetUserProfileTask(mockTask);
        getUserTask.execute(query);
        User getUser = new User();
        // Hang around till is done
        AsyncTask.Status anotherStatus;
        do {
            anotherStatus = getUserTask.getStatus();
        } while (anotherStatus != AsyncTask.Status.FINISHED);

        try {
            getUser = getUserTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(user.getID(), getUser.getID());
        assertEquals(user, getUser);
    }

    /**
     * Test cases for search user exist task
     */
    public void testSearchUserExist() {
        String query = String.format(
                "{\n" +
                        "    \"query\": {\n" +
                        "       \"term\" : { \"userName\" : \"%s\" }\n" +
                        "    }\n" +
                        "}", "sfeng3");
        User.SearchUserExistTask task = new User.SearchUserExistTask();
        task.execute(query);
        // Hang around till it's done
        AsyncTask.Status taskStatus;
        do {
            taskStatus = task.getStatus();
        } while (taskStatus != AsyncTask.Status.FINISHED);

        try {
            assertTrue(task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
