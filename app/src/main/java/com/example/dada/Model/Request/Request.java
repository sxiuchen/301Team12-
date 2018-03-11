package com.example.dada.Model.Request;

import android.location.Location;
import android.media.Image;

import com.example.dada.Model.Bidded;

import java.util.ArrayList;

public abstract class Request {

    private String name;
    private String status;
    private String description;
    private Location Slocation;
    private Image picture;
    private int Requester;
    private int Assigned_Requester;
    private double Assigned_Pri;
    private float distance;
    private Location Elocation;
    private ArrayList<Bidded> Bidded_History;

    public Request(String name, String status, String description,
                   Location Slocation, Location Elocation,
                   Image picture, int Requester, float distance,
                   double Assigned_Pri, int Assigned_Requester,
                   ArrayList<Bidded> Bidded_History){
        this.name = name;
        this.status = status;
        this.description = description;
        this.Slocation = Slocation;
        this.picture = picture;
        this.Requester = Requester;
        this.Assigned_Requester = Assigned_Requester;
        this.Assigned_Pri = Assigned_Pri;
        this.distance = distance;
        this.Elocation = Elocation;
        this.Bidded_History = Bidded_History;
    }

    public String getName(){
        return this.name;
    }

    public String getStatus(){
        return this.status;
    }

    public String getDescription(){
        return this.description;
    }

    public Location getLocation(){
        return this.Slocation;
    }

    public Image getPicture(){
        return this.picture;
    }

    public int getRequester(){
        return this.Requester;
    }

    public int getAssigned_Requester(){
        return this.Assigned_Requester;
    }

    public double getAssigned_Pri(){
        return this.Assigned_Pri;
    }

    public Location getElocation(){
        return this.Elocation;
    }

    public float getDistance(){
        return this.distance;
    }

    public ArrayList<Bidded> getBidded_History(){
        return Bidded_History;
    }


    public void setName(String name){
        this.name = name;
    }

    public void setStatus(String status){
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

    public void setRequester(int Requester){
        this.Requester = Requester;
    }

    public void setAssigned_Requester(int Assigned_Requester){
        this.Assigned_Requester = Assigned_Requester;
    }

    public void setAssigned_Pri(double Assigned_Pri){
        this.Assigned_Pri = Assigned_Pri;
    }

    public void setDistance(float distance){
        this.distance = distance;
    }

    public void setElocation(Location Elocation){
        this.Elocation = Elocation;
    }

    public void setBidded_History(ArrayList<Bidded> Bidded_History){
        this.Bidded_History = Bidded_History;
    }
}
