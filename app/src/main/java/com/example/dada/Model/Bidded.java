package com.example.dada.Model;

/**
 * Created by rick on 24/02/2018.
 */

public class Bidded {
    private int price;
    private int Provider_ID;


    public Bidded(int price, int Provider_ID){
        this.price = price;
        this.Provider_ID = Provider_ID;
    }

    public int getPrice(){
        return price;
    }

    public int getProvider_ID(){
        return Provider_ID;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public void setProvider_ID(int Provider_ID){
        this.Provider_ID = Provider_ID;
    }
}
