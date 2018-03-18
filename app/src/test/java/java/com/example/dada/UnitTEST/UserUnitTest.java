package java.com.example.dada.UnitTEST;

import android.media.Image;

import com.example.dada.Model.User;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * user model unit tests
 */

public class UserUnitTest{

    String testUser_name = "Test User";
    String testID = "123";
    int testType = 1;
    Image testProfile_photo = null;
    int testPhone_num = 1234567;
    User newUser = new User(testUser_name,testID, testType, testProfile_photo, testPhone_num);


    @Test
    public void testGetUser_name(){
        assertThat(newUser.getUser_name(),is(testUser_name));
    }

    @Test
    public void testSetUser_name(){
        String testUser_name2 = "Test User 2";
        newUser.setUser_name(testUser_name2);
        assertThat(newUser.getUser_name(),is(testUser_name2));
    }

    @Test
    public void testGetID(){
        assertThat(newUser.getID(),is(testID));
    }

    @Test
    public void testSetID(){
        String testID2 = "1234";
        newUser.setID(testID2);
        assertThat(newUser.getID(),is(testID2));
    }

    @Test
    public void testGetType(){
        assertThat(newUser.getType(),is(testType));
    }

    @Test
    public void testSetType(){
        int testType2 = 2;
        newUser.setType(testType2);
        assertThat(newUser.getType(),is(testType2));
    }

    @Test
    public void testGetProfile_photo(){
        assertThat(newUser.getProfile_photo(),is(testProfile_photo));
    }

    @Test
    public void testSetProfile_photo(){
        Image testProfile_photo2 = null;
        newUser.setProfile_photo(testProfile_photo2);
        assertThat(newUser.getProfile_photo(),is(testProfile_photo2));
    }

    @Test
    public void testGetPhone_num(){
        assertThat(newUser.getPhone_num(),is(testPhone_num));
    }

    @Test
    public void testSetPhone_num(){
        int testPhone_num2 = 12345678;
        newUser.setPhone_num(testPhone_num2);
        assertThat(newUser.getPhone_num(),is(testPhone_num2));
    }

}
