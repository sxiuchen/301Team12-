//package com.example.dada.IntentTest;
//
//import android.app.Activity;
//import android.test.ActivityInstrumentationTestCase2;
//import android.util.Log;
//
//import com.robotium.solo.Solo;
//import junit.framework.TestCase;
//import android.widget.EditText;
//import com.example.dada.SignupActivity;
//
///**
// * Intent Test for sign up
// */
//
//public class SignupTest extends ActivityInstrumentationTestCase2{
//    private Solo solo;
//
//    public SignupTest(){
//        super(com.example.dada.SignupActivity.class);
//    }
//
//    public void testStart() throws Exception {
//        Activity activity = getActivity();
//
//    }
//
//    @Override
//    public void setUp() throws Exception {
//        Log.d("TAG1", "setUp()");
//        solo = new Solo(getInstrumentation(), getActivity());
//    }
//
//
//    public void TestSignup(){
//        solo.assertCurrentActivity("Wrong Activity", SignupActivity.class);
//    }
//
//
//    @Override
//    protected void tearDown() throws Exception {
//        solo.finishOpenedActivities();
//    }
//}
