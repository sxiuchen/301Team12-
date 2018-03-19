package com.example.dada.ActivityTest;

import android.test.ActivityInstrumentationTestCase2;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.example.dada.R;
import com.robotium.solo.Solo;
import junit.framework.TestCase;
import android.widget.EditText;
import com.example.dada.View.ProviderBrowseTaskActivity;

public class ProviderBrowseTaskTest extends ActivityInstrumentationTestCase2{
    private Solo solo;

    public ProviderBrowseTaskTest(){
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
     * Test for lists click
     */
    public void TestProvider(){

        solo.assertCurrentActivity("Wrong Activity", ProviderBrowseTaskActivity.class);
        solo.clickInList(0);

    }


    @Override
    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}
