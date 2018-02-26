package com.example.dada.Model;

/**
 * Created by rick on 24/02/2018.
 */

public class Bidded {
    private double price;
    private int Provider_ID;


    public Bidded(double price, int Provider_ID){
        this.price = price;
        this.Provider_ID = Provider_ID;
    }

    public double getPrice(){
        return price;
    }

    public int getProvider_ID(){
        return Provider_ID;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setProvider_ID(int Provider_ID){
        this.Provider_ID = Provider_ID;
    }
}
