package com.example.dada.ActivityTest;


import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.robotium.solo.Solo;
import junit.framework.TestCase;
import android.widget.EditText;
import com.example.dada.View.LoginActivity;

/**
 * Intent test for log in
 */

public class LoginTest extends ActivityInstrumentationTestCase2{
    private Solo solo;

    public LoginTest(){
        super(com.example.dada.View.LoginActivity.class);
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
