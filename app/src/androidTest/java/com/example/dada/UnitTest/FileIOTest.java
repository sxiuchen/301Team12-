package com.example.dada.UnitTest;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.example.dada.Model.Task.RequestedTask;
import com.example.dada.Model.Task.Task;
import com.example.dada.Model.User;
import com.example.dada.Util.FileIOUtil;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * Testing suite for the FileIO system
 */
public class FileIOTest extends ApplicationTestCase<Application> {

    /**
     * The Signal.
     */
    CountDownLatch signal = null;

    /**
     * Instantiates a new File io test.
     */
    public FileIOTest() {
        super(Application.class);
    }

    @Override
    public void setUp() throws Exception {
        signal = new CountDownLatch(1);
    }

    @Override
    public void tearDown() throws Exception {
        signal.countDown();
    }

    public void testSaveUserInFile() {
        User testUser = new User("test", "000", "test@test.com");
        try {
            FileIOUtil.saveUserInFile(testUser, getContext());
        } catch (Exception e) {
            throw new RuntimeException("Failed to save user");
        }
    }

    public void testLoadUserFromFile() {
        User testUser = new User("test", "000", "test@test.com");
        FileIOUtil.saveUserInFile(testUser, getContext());

        User user;
        user = FileIOUtil.loadUserFromFile(getContext());
        assertFalse(user.equals(null));
    }

    public void testSaveRequestInFile() {
        RequestedTask test = new RequestedTask("title1", "description1", "sfeng3");

        try {
            FileIOUtil.saveTaskInFile(test, "test.json", getContext());
        } catch (Exception e) {
            throw new RuntimeException("Failed to save request");
        }

    }

    public void testSaveOfflineRequestInFile() {
        RequestedTask test = new RequestedTask("title1", "description1", "sfeng3");

        try {
            FileIOUtil.saveOfflineTaskInFile(test, getContext());
        } catch (Exception e) {
            throw new RuntimeException("Failed to save request");

        }
    }

    public void testSaveRequesterTaskInFile() {
        RequestedTask test = new RequestedTask("title1", "description1", "sfeng3");

        try {
            FileIOUtil.saveRequesterTaskInFile(test, getContext());
        } catch (Exception e) {
            throw new RuntimeException("Failed to save request");
        }
    }

    public void testLoadTaskFromFile() {
        ArrayList<Task> testArray;
        ArrayList<String> strArray = new ArrayList<>();
        strArray.add("test.json");

        testArray = FileIOUtil.loadTaskFromFile(getContext(),strArray);
        assertFalse(testArray.isEmpty());
    }

    public void testLoadSingleRequestFromFile() {
        Task test;

        test = FileIOUtil.loadSingleTaskFromFile("test.json", getContext());
        assertFalse(test.equals(null));
    }
}
