package com.example.dada;

import com.example.dada.Model.Bidded;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

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

    public void testGetProvider_ID(){
        int testPid = 123;
        double testPrice = 12.3;
        Bidded newBid = new Bidded(testPrice, testPid);
        assertThat(newBid.getProvider_ID(),is(testPid));
    }

    public void testSetPrice(){
        double testPrice = 12.3;
        Bidded newBid = new Bidded();
        newBid.setPrice(testPrice);
        assertThat(newBid.getPrice(),is(testPrice));

        try{
            double testPrice2 = -12.3;
            newBid.setPrice(testPrice2);
        }catch(Exception e){
            assertTrue(true);
        }
    }

    public void testSetProvider_ID(){
        int testPid = 123;
        Bidded newBid = new Bidded();
        newBid.setProvider_ID(testPid);
        assertThat(newBid.getProvider_ID(),is(testPid));

        try{
            int testPid2 = -123;
            newBid.setProvider_ID(testPid2);
        }catch(Exception e){
            assertTrue(true);
        }
    }
}
