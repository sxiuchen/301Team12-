//package com.example.dada.ActivityTest;
//import android.app.Activity;
//import android.test.ActivityInstrumentationTestCase2;
//import android.util.Log;
//
//import com.example.dada.R;
//import com.robotium.solo.Solo;
//import android.widget.EditText;
//import com.example.dada.View.LoginActivity;
///**
// * Detail interface test
// */
//
//public class RequesterViewTest extends ActivityInstrumentationTestCase2 {
//    private Solo solo;
//    public RequesterViewTest(){
//        super(com.example.dada.Util.RequesterDetailActivity.class);
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
//    /**
//     * Detail interface test, assigned different tasks
//     */
//    public void Detail_test(){
//        // check current activity
//        solo.assertCurrentActivity("Wrong Activity", RequesterDetailActivity.class);
//        solo.getView(R.id.textViewStatus);
//
//    }
//
//    @Override
//    protected void tearDown() throws Exception {
//        solo.finishOpenedActivities();
//    }
//}
