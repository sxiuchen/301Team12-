package com.example.dada;

import android.media.Image;
import android.test.ActivityInstrumentationTestCase2;
import android.location.Location;

import com.example.dada.Model.Bidded;
import com.example.dada.Model.Task;

import junit.framework.TestCase;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
/**
 * Created by rick on 25/02/2018.
 */

public class TaskTest extends ActivityInstrumentationTestCase2{
    public static final String UserName = "User1";
    public static final String Status = "Done";
    public static final String Description = "good";
    public static final Location slocation = new Location("");
    public static final Location elocation = new Location("");
    public static final Image picture = null;
    public static final int requester = 0;
    public static final int assigned_requester = 0;
    public static final double assigned_pri = 10.50;
    public static final float distance = 2.345f;
    public static final ArrayList<Bidded> Bidded_History = new ArrayList<Bidded>();
    public TaskTest(){
        super(Task.class);
    }

    public void testGetName(){

    }
}
