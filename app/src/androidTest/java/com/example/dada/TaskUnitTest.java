package com.example.dada;

import android.location.Location;
import android.media.Image;
import android.util.Log;

import com.example.dada.Model.Bidded;
import com.example.dada.Model.Task;

import org.junit.Test;

import java.util.ArrayList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by rick on 25/02/2018.
 */

public class TaskUnitTest {
    public static final String TaskName = "user2";
    public static final String Status = "Done";
    public static final String Description = "good";
    public static final Location slocation = new Location("");
    public static final Location elocation = new Location("");
    public static final Image picture = null;
    public static final int requester = 0;
    public static final double assigned_pri = 10.50;
    public static final int assigned_requester = 0;
    public static final float distance = 2.345f;
    Bidded newBid = new Bidded(2, 3);
    ArrayList<Bidded> Bidded_History = new ArrayList<Bidded>();
    Task newTask = new Task(TaskName, Status, Description, slocation, elocation, picture, requester, distance, assigned_pri, assigned_requester, Bidded_History);


    @Test
    public void testGetName() {
        assertThat(newTask.getName(), is(TaskName));
    }

    @Test
    public void testGetStatus() {
        assertThat(newTask.getStatus(), is(Status));
    }

    @Test
    public void testGetDescription() {
        assertThat(newTask.getDescription(), is(Description));
    }

    @Test
    public void testGetLocation() {
        assertThat(newTask.getLocation(), is(slocation));
    }

    @Test
    public void testGetPicture() {
        assertThat(newTask.getPicture(), is(picture));
    }

    @Test
    public void testGetRequestor() {
        assertThat(newTask.getRequester(), is(requester));
    }

    @Test
    public void testGetAssigned_Requester() {
        Bidded_History.add(newBid);
        assertThat(newTask.getAssigned_Requester(), is(assigned_requester));
    }

    @Test
    public void testGetAssigned_Pri() {
        assertThat(newTask.getAssigned_Pri(), is(assigned_pri));
    }

    @Test
    public void testGetELocation() {
        assertThat(newTask.getElocation(), is(elocation));
    }

    @Test
    public void testGetDistance() {
        assertThat(newTask.getDistance(), is(distance));
    }

    @Test
    public void testGetBidded_History() {
        assertThat(newTask.getBidded_History(), is(Bidded_History));
    }

    @Test
    public void testSetName() {
        // test for task name that is more than 30 characters
        try {
            assertThat(newTask.getName(), is(TaskName));
        } catch (Exception e) {
            Log.i("testSetName failed ", "Task name not set properly");
        }
        // test for task name that is shorter than 30 chars
        try {
            String name1 = "Task name cannot be more than 30 characters";
            newTask.setName(name1);
            assertNotEquals(newTask.getName(), name1);
        } catch (Exception e) {
            Log.i("testSetName failed: ", "task name need to be less than 30 char");
        }
    }

    @Test
    public void testSetStatus() {
        String status1 = "Not Done";
        newTask.setStatus(status1);
        assertThat(newTask.getStatus(), is(status1));
    }

    @Test
    public void testSetDescription() {

        try {
            assertThat(newTask.getDescription(), is(Description));
        } catch (Exception e) {
            Log.i("testSetDescription failed ", "Description not set properly");
        }
    }

    @Test
    public void testSetSlocation() {
        Location slocation1 = new Location("");
        newTask.setSlocation(slocation1);
        assertThat(newTask.getLocation(), is(slocation1));
    }

    @Test
    public void testSetPicture() {
        Image picture1 = null;
        newTask.setPicture(picture1);
        assertThat(newTask.getPicture(), is(picture1));
    }

    @Test
    public void testSetRequester() {
        int requester1 = 0;
        newTask.setRequester(requester1);
        assertThat(newTask.getRequester(), is(requester1));
    }

    @Test
    public void testSetAssigned_Requeste() {
        int assigned_requester1 = 0;
        newTask.setAssigned_Requester(assigned_requester1);
        assertThat(newTask.getAssigned_Requester(), is(assigned_requester1));
    }

    @Test
    public void testSetAssigned_Pri() {
        double assigned_pri1 = 10.50;
        newTask.setAssigned_Pri(assigned_pri1);
        assertThat(newTask.getAssigned_Pri(), is(assigned_pri1));
    }

    @Test
    public void testSetDistance() {
        float distance1 = 4.345f;
        newTask.setDistance(distance1);
        assertThat(newTask.getDistance(), is(distance1));
    }

    @Test
    public void testSetElocation() {
        Location elocation1 = new Location("");
        newTask.setElocation(elocation1);
        assertThat(newTask.getElocation(), is(elocation1));
    }

    @Test
    public void testSetBidded_History() {
        ArrayList<Bidded> Bidded_History1 = new ArrayList();
        newTask.setBidded_History(Bidded_History1);
        assertThat(newTask.getBidded_History(), is(Bidded_History1));
    }

    @Test
    public void test_add() {
        Bidded bid = new Bidded(2, 1);
        newTask.add_bid(bid);
        assertEquals(2, newTask.get_count());
    }

    @Test
    public void test_delete() {
        Task newTask = new Task(TaskName, Status, Description, slocation, elocation, picture, requester, distance, assigned_pri, assigned_requester, Bidded_History);
        Bidded Bid = new Bidded(2, 1);
        newTask.add_bid(Bid);
        newTask.delete_bid(Bid);
        assertEquals(0, newTask.get_count());
    }
}
