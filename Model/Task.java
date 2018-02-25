package com.example.dada.Model;


import android.location.Location;
import android.media.Image;

import java.util.ArrayList;

/**
 * Created by rick on 24/02/2018.
 */

public class Task {
    private String name;
    private int status;
    private String description;
    private Location Slocation;
    private Image picture;
    private int provider;
    private int Assigned_Requester;
    private int Assigned_Pri;
    private float distance;
    private Location Elocation;
    private Bidded Bidded_History;

    public Task(String name, int status, String description,
                Location Slocation, Location Elocation,
                Image picture, int provider, float distance,
                int Assigned_Pri, int Assigned_Requester){
        this.name = name;
        this.status = status;
        this.description = description;
        this.Slocation = Slocation;
        this.picture = picture;
        this.provider = provider;
        this.Assigned_Requester = Assigned_Requester;
        this.Assigned_Pri = Assigned_Pri;
        this.distance = distance;
        this.Elocation = Elocation;
    }

    public String getName(){
        return name;
    }

    public int getStatus(){
        return status;
    }

    public String getDescription(){
        return description;
    }

    public Location getLocation(){
        return Slocation;
    }

    public Image getPicture(){
        return picture;
    }

    public int getProvider(){
        return provider;
    }

    public int getAssigned_Requester(){
        return Assigned_Requester;
    }

    public int getAssigned_Pri(){
        return Assigned_Pri;
    }

    public Location getElocation(){
        return Elocation;
    }

    public float getDistance(){
        return distance;
    }

    public Bidded getBidded_History(){
        return  Bidded_History;
    }

    public int getPrice(){
        return this.Bidded_History.getPrice();
    }

    public int getRequester_ID(){
        return this.Bidded_History.getRequester_ID();
    }

    public void setName(String name){
        this.name = name;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setSlocation(Location Slocation){
        this.Slocation = Slocation;
    }

    public void setPicture(Image picture){
        this.picture = picture;
    }

    public void setProvider(int provider){
        this.provider = provider;
    }

    public void setAssigned_Requester(int Assigned_Requester){
        this.Assigned_Requester = Assigned_Requester;
    }

    public void setAssigned_Pri(int Assigned_Pri){
        this.Assigned_Pri = Assigned_Pri;
    }

    public void setDistance(float distance){
        this.distance = distance;
    }

    public void setElocation(Location Elocation){
        this.Elocation = Elocation;
    }

    public void setBidded_History(Bidded Bidded_History){
        this.Bidded_History = Bidded_History;
    }

    public void setPrice(int price){
        Bidded_History.setPrice(price);
    }

    public void setRequester_ID(int Requester_ID){
        Bidded_History.setRequester_ID(Requester_ID);
    }
}
