package com.example.dada.Util;

import android.content.Context;
import android.util.Log;

import com.example.dada.Exception.UserException;
import com.example.dada.Model.OnAsyncTaskCompleted;
import com.example.dada.Model.Request.Request;
import com.example.dada.Model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * Utility class to help pass request object through intent
 * or update to the server
 */
public class RequestUtil {

//    public static String serializer(Request request) {
//        Gson gson = new GsonBuilder().registerTypeAdapter(GeoPoint.class, new GeoPointConverter()).create();
//        return gson.toJson(request);
//    }
//
//    public static Request deserializer(String string) {
//        Gson gson = new GsonBuilder().registerTypeAdapter(GeoPoint.class, new GeoPointConverter()).create();
//        return gson.fromJson(string, NormalRequest.class);
//    }
//
//    public static Gson customGsonBuilder() {
//        return new GsonBuilder().registerTypeAdapter(GeoPoint.class, new GeoPointConverter()).create();
//    }
//
//    public static String generateOfflineRequestFileName(Request request) {
//        return "offline-" + request.getID() + ".json";
//    }
//
//    public static String generateAcceptedReqestFileName(Request request) {
//        return "accepted-" + request.getID() + ".json";
//    }
//
//    public static String generateRiderRequestFileName(Request request) {
//        return "rider-" + request.getID() + ".json";
//    }
//
//    public static String generateDriverRequestFileName(Request request) {
//        return "driver-" + request.getID() + ".json";
//    }

    public static ArrayList<String> getOfflineRequestList(Context context) {
        String[] fileList = context.fileList();
        ArrayList<String> offlineRequestFileList = new ArrayList<>();
        for (String f : fileList) {
            if (f != null && f.startsWith("offline-")) {
                offlineRequestFileList.add(f);
            }
        }
        return offlineRequestFileList;
    }

    public static ArrayList<String> getRiderRequestList(Context context) {
        String[] fileList = context.fileList();
        ArrayList<String> offlineAcceptedRequestFileList = new ArrayList<>();
        for (String f : fileList) {
            if (f != null && f.startsWith("rider-")) {
                Log.i("Debug", f);
                offlineAcceptedRequestFileList.add(f);
            }
        }
        return offlineAcceptedRequestFileList;
    }

    public static ArrayList<String> getDriverRequestList(Context context) {
        String[] fileList = context.fileList();
        ArrayList<String> offlineAcceptedRequestFileList = new ArrayList<>();
        for (String f : fileList) {
            if (f != null && f.startsWith("driver-")) {
                Log.i("Debug", f);
                offlineAcceptedRequestFileList.add(f);
            }
        }
        return offlineAcceptedRequestFileList;
    }

    public static ArrayList<String> getAcceptedRequestList(Context context) {
        String[] fileList = context.fileList();
        ArrayList<String> offlineAcceptedRequestFileList = new ArrayList<>();
        for (String f : fileList) {
            if (f != null && f.startsWith("accepted-")) {
                Log.i("Debug", f);
                offlineAcceptedRequestFileList.add(f);
            }
        }
        return offlineAcceptedRequestFileList;
    }
}