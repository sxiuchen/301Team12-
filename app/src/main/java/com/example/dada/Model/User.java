package com.example.dada.Model;

import android.media.Image;

/**
 * Created by rick on 24/02/2018.
 */

public class User {
    private String user_name;
    private int ID;
    private int type;
    private Image profile_photo;
    private int phone_num;

    public User(String user_name, String user_email,
                int ID, Image profile_photo, int phone_num){
        this.user_name = user_name;
        this.ID = ID;
        this.profile_photo = profile_photo;
        this.phone_num = phone_num;
    }

    public String getUser_name(){
        return user_name;
    }


    public int getID(){
        return ID;
    }

    public int getType(){
        return type;
    }

    public int getPhone_num(){
        return phone_num;
    }

    public Image getProfile_photo(){
        return profile_photo;
    }

    public void setUser_name(String user_name){
        this.user_name = user_name;
    }


    public void setID(int ID){
        this.ID = ID;
    }

    public void setType(int type){
        this.type = type;
    }

    public void setProfile_photo(Image profile_photo){
        this.profile_photo = profile_photo;
    }

    public void setPhone_num(int phone_num){
        this.phone_num = phone_num;
    }

    public User Log_In(String user_name, String Password){
        // log in
        return null;
    }

    public User Sign_Up(String user_name, String Password, int phone_num){
        // sign up
        return null;
    }
}
