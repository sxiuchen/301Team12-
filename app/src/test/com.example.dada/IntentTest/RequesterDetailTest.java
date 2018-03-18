package com.example.dada.IntentTest;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.robotium.solo.Solo;
import junit.framework.TestCase;
import android.widget.EditText;
import com.example.dada.Util.RequesterDetailActivity;
/**
 * Intent test for detailed activity
 */

public class RequesterDetailTest extends ActivityInstrumentationTestCase2{
    private Solo solo;

    public RequesterDetailTest(){
        super(com.example.dada.Util.RequesterDetailActivity.class);
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
        solo.assertCurrentActivity("Wrong Activity", RequesterDetailActivity.class);

    }


    @Override
    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }
}
