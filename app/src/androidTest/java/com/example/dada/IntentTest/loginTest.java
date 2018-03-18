package com.example.dada.IntentTest;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.robotium.solo.Solo;
import junit.framework.TestCase;
import android.widget.EditText;
import com.example.dada.LoginActivity;

/**
 * Intent test for log in
 */

public class loginTest extends ActivityInstrumentationTestCase2{
    private Solo solo;

    public loginTest(){
        super(com.example.dada.LoginActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();

    }

    @Override
    public void setUp() throws Exception {
        Log.d("TAG1", "setUp()");
        solo = new Solo(getInstrumentation(), getActivity());
    }


    public void TestLogin(){
        solo.assertCurrentActivity("Wrong Activity", LoginActivity.class);
    }


    @Override
    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }



}
