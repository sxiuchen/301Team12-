package com.example.dada;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by kq on 2/25/2018.
 */

public class BiddedUnitTest {

    @Test
    public void testGetPrice(){
        int testPid = 123;
        double testPrice = 12.3;
        Bidded newBid = new Bidded(testPrice, testPid);
        assertThat(newBid.getPrice(),is(testPrice));
    }

    @Test
    public void testGetProvider_ID(){
        int testPid = 123;
        double testPrice = 12.3;
        Bidded newBid = new Bidded(testPrice, testPid);
        assertThat(newBid.getProvider_ID(),is(testPid));
    }

    @Test
    public void testSetPrice(){
        double testPrice = 12.3;
        int testPid = 123;
        Bidded newBid = new Bidded(testPrice, testPid);
        double testPrice2 = 12.34;
        newBid.setPrice(testPrice2);
        assertThat(newBid.getPrice(),is(testPrice2));

        try{
            double testPrice3 = -12.3;
            newBid.setPrice(testPrice3);
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testSetProvider_ID(){
        double testPrice = 12.3;
        int testPid = 123;
        Bidded newBid = new Bidded(testPrice, testPid);
        int testPid2 = 1234;
        newBid.setProvider_ID(testPid2);
        assertThat(newBid.getProvider_ID(),is(testPid2));

        try{
            int testPid3 = -123;
            newBid.setProvider_ID(testPid3);
        }catch(Exception e){
            assertTrue(true);
        }
    }
}
