/* SignUpTest
 *
 * Version 1.0
 *
 * March 15, 2018
 *
 * Copyright (c) 2018 Team 12 CMPUT 301. University of Alberta - All Rights Reserved.
 * You may use distribute or modify this code under terms and condition of the Code of Student Behaviour at University of Alberta.
 * You can find a copy of licence in this project. Otherwise please contact contact sfeng3@ualberta.ca.
 */

package com.example.dada.ActivityTest;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.example.dada.R;
import com.robotium.solo.Solo;
import junit.framework.TestCase;
import android.widget.EditText;
import com.example.dada.View.SignupActivity;

/**
 * Intent Test for sign up
 */

public class SignUpTest extends ActivityInstrumentationTestCase2{
    private Solo solo;

    public SignUpTest(){
        super(com.example.dada.View.SignupActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();

    }

    @Override
    public void setUp() throws Exception {
        Log.d("TAG1", "setUp()");
        solo = new Solo(getInstrumentation(), getActivity());
    }

    /**
     * Test for sign up WITH valid and invalid format
     */
    public void TestSignup(){

        //Test for invalid sign up format

        solo.assertCurrentActivity("Wrong Activity", SignupActivity.class);
        solo.enterText((EditText) solo.getView(R.id.edit_text_signup_username), "longusername");
        solo.enterText((EditText) solo.getView(R.id.edit_text_signup_email),
                "user@ualberta.ca");
        solo.enterText((EditText) solo.getView(R.id.edit_text_signup_phone), "911");
        solo.clickOnButton("Sign Up");
        assertTrue(solo.waitForText("Username/Email/Mobile is not valid."));


        // test for valid sign up

        solo.assertCurrentActivity("Wrong Activity", SignupActivity.class);
        // clear text
        solo.clearEditText((EditText) solo.getView(R.id.edit_text_signup_username));
        solo.clearEditText((EditText) solo.getView(R.id.edit_text_signup_email));
        solo.clearEditText((EditText) solo.getView(R.id.edit_text_signup_phone));

        solo.enterText((EditText) solo.getView(R.id.edit_text_signup_username), "user");
        solo.enterText((EditText) solo.getView(R.id.edit_text_signup_email),
                "user@ualberta.ca");
        solo.enterText((EditText) solo.getView(R.id.edit_text_signup_phone), "12345678");
        solo.clickOnButton("Sign Up");
    }


    @Override
    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}
