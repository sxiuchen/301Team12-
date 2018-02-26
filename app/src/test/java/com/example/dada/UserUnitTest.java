package com.example.dada;

import android.media.Image;

import com.example.dada.Model.User;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;


/**
 * Created by kq on 2/25/2018.
 */

public class UserUnitTest {
    protected User newUser;

    @Test
    public void testGetUser_name(){
        String testUser_name = "Test User";
        int testID = 123;
        int testType = 1;
        Image testProfile_photo = null;
        int testPhone_num = 1234567;

        User newUser = new User(testUser_name,testID, testType, testProfile_photo, testPhone_num);


    }
}
