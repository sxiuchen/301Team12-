package com.example.dada.UnitTEST;

import com.example.dada.Model.Bidded;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Bidded Unit Test
 */

public class BiddedUnitTest {

    @Test
    public void testGetPrice(){
        int testPid = 123;
        int testPrice = 12;
        Bidded newBid = new Bidded(testPrice, testPid);
        assertThat(newBid.getPrice(),is(testPrice));
    }

    @Test
    public void testGetProvider_ID(){
        int testPid = 123;
        int testPrice = 12;
        Bidded newBid = new Bidded(testPrice, testPid);
        assertThat(newBid.getProvider_ID(),is(testPid));
    }

    @Test
    public void testPriceAmount(){
        int testPrice = 13;
        int testPid = 123;
        Bidded newBid = new Bidded(testPrice, testPid);
        int testPrice2 = 12;
        newBid.setPrice(testPrice2);
        assertThat(newBid.getPrice(),is(testPrice2));

        try{
            int testPrice3 = -12;
            newBid.setPrice(testPrice3);
        }catch(Exception e){
            assertTrue(true);
        }
    }

    @Test
    public void testProvider_ID(){
        int testPrice = 12;
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
