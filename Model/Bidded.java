package com.example.dada.Model;

/**
 * Created by rick on 24/02/2018.
 */

public class Bidded {
    private int price;
    private int Requester_ID;

    public Bidded(int price, int requester_ID){
        this.price = price;
        this.Requester_ID = requester_ID;
    }

    public int getPrice(){
        return price;
    }

    public int getRequester_ID(){
        return Requester_ID;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public void setRequester_ID(int Requester_ID){
        this.Requester_ID = Requester_ID;
    }
}
