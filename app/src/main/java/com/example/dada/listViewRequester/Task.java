//package com.example.dada.listViewRequester;
//
//import android.location.Location;
//import android.media.Image;
//import android.os.Parcel;
//import android.os.Parcelable;
//
//import com.example.dada.Model.Bidded;
//
//import java.util.ArrayList;
//
///**
// * Created by kq on 3/18/2018.
// */
//
//public class Task implements Parcelable {
//    private String name;
//    private String status;
//    private String description;
//    private Location Slocation;
//    private Image picture;
//    private int Requester;
//    private int Assigned_Requester;
//    private double Assigned_Pri;
//    private float distance;
//    private Location Elocation;
//    private ArrayList<Bidded> Bidded_History = new ArrayList<Bidded>();
//
//    public Task(String name, String status, String description,
//                Location Slocation, Location Elocation,
//                Image picture, int Requester, float distance,
//                double Assigned_Pri, int Assigned_Requester,
//                ArrayList<Bidded> Bidded_History){
//        this.name = name;
//        this.status = status;
//        this.description = description;
//        this.Slocation = Slocation;
//        this.picture = picture;
//        this.Requester = Requester;
//        this.Assigned_Requester = Assigned_Requester;
//        this.Assigned_Pri = Assigned_Pri;
//        this.distance = distance;
//        this.Elocation = Elocation;
//    }
//
//    public String getName(){
//        return this.name;
//    }
//
//    public String getStatus(){
//        return this.status;
//    }
//
//    public String getDescription(){
//        return this.description;
//    }
//
//    public Location getLocation(){
//        return this.Slocation;
//    }
//
//    public Image getPicture(){
//        return this.picture;
//    }
//
//    public int getRequester(){
//        return this.Requester;
//    }
//
//    public int getAssigned_Requester(){
//        return this.Assigned_Requester;
//    }
//
//    public double getAssigned_Pri(){
//        return this.Assigned_Pri;
//    }
//
//    public Location getElocation(){
//        return this.Elocation;
//    }
//
//    public float getDistance(){
//        return this.distance;
//    }
//
//    public ArrayList<Bidded> getBidded_History(){
//        return this.Bidded_History;
//    }
//
//
//
//    public void setName(String name){
//        this.name = name;
//    }
//
//    public void setStatus(String status){
//        this.status = status;
//    }
//
//    public void setDescription(String description){
//        this.description = description;
//    }
//
//    public void setSlocation(Location Slocation){
//        this.Slocation = Slocation;
//    }
//
//    public void setPicture(Image picture){
//        this.picture = picture;
//    }
//
//    public void setRequester(int Requester){
//        this.Requester = Requester;
//    }
//
//    public void setAssigned_Requester(int Assigned_Requester){
//        this.Assigned_Requester = Assigned_Requester;
//    }
//
//    public void setAssigned_Pri(double Assigned_Pri){
//        this.Assigned_Pri = Assigned_Pri;
//    }
//
//    public void setDistance(float distance){
//        this.distance = distance;
//    }
//
//    public void setElocation(Location Elocation){
//        this.Elocation = Elocation;
//    }
//
//    public void setBidded_History(ArrayList<Bidded> Bidded_History){
//        this.Bidded_History = Bidded_History;
//    }
//
//
//
//    public void add_bid(Bidded bid){
//        this.Bidded_History.add(bid);
//    }
//
//    public void delete_bid(Bidded bid){
//        this.Bidded_History.remove(bid);
//    }
//
//    public int get_count(){
//        return this.Bidded_History.size();
//    }
//
//    public int FetchLowestPrice() {
//        int lowestPrice = -1;
//        if (Bidded_History.size() >= 1) {
//            lowestPrice = Bidded_History.get(0).getPrice();
//            for (int i=1; i<Bidded_History.size(); i++) {
//                if (lowestPrice > Bidded_History.get(i).getPrice()) {
//                    lowestPrice = Bidded_History.get(i).getPrice();
//                }
//            }
//        }
//        return lowestPrice;
//    }
//
//    public void addBiddedRecord(Bidded bid) {
//        Bidded_History.add(bid);
//        FetchLowestPrice();
//    }
//
//    protected Task(Parcel in) {
//        name = in.readString();
//        status = in.readString();
//        description = in.readString();
//        Slocation = (Location) in.readValue(Location.class.getClassLoader());
//        picture = (Image) in.readValue(Image.class.getClassLoader());
//        Requester = in.readInt();
//        Assigned_Requester = in.readInt();
//        Assigned_Pri = in.readDouble();
//        distance = in.readFloat();
//        Elocation = (Location) in.readValue(Location.class.getClassLoader());
//        if (in.readByte() == 0x01) {
//            Bidded_History = new ArrayList<Bidded>();
//            in.readList(Bidded_History, Bidded.class.getClassLoader());
//        } else {
//            Bidded_History = null;
//        }
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(name);
//        dest.writeString(status);
//        dest.writeString(description);
//        dest.writeValue(Slocation);
//        dest.writeValue(picture);
//        dest.writeInt(Requester);
//        dest.writeInt(Assigned_Requester);
//        dest.writeDouble(Assigned_Pri);
//        dest.writeFloat(distance);
//        dest.writeValue(Elocation);
//        if (Bidded_History == null) {
//            dest.writeByte((byte) (0x00));
//        } else {
//            dest.writeByte((byte) (0x01));
//            dest.writeList(Bidded_History);
//        }
//    }
//
//    @SuppressWarnings("unused")
//    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
//        @Override
//        public Task createFromParcel(Parcel in) {
//            return new Task(in);
//        }
//
//        @Override
//        public Task[] newArray(int size) {
//            return new Task[size];
//        }
//    };
//}
