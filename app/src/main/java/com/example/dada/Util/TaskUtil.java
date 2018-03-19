package com.example.dada.Util;

import android.content.Context;
import android.util.Log;

import com.example.dada.Model.Task.NormalTask;
import com.example.dada.Model.Task.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

/**
 * Utility class to help pass task object through intent or update to the server
 */
public class TaskUtil {

    public static String serializer(Task task) {
        Gson gson = new Gson();
        return gson.toJson(task);
    }

    public static Task deserializer(String string) {
        Gson gson = new Gson();
        return gson.fromJson(string, NormalTask.class);
    }

//    public static String generateOfflineRequestFileName(Task request) {
//        return "offline-" + request.getID() + ".json";
//    }
//
//    public static String generateAcceptedReqestFileName(Task request) {
//        return "accepted-" + request.getID() + ".json";
//    }
//
//    public static String generateRiderRequestFileName(Task request) {
//        return "rider-" + request.getID() + ".json";
//    }
//
//    public static String generateDriverRequestFileName(Task request) {
//        return "driver-" + request.getID() + ".json";
//    }

    public static ArrayList<String> getOfflineTaskList(Context context) {
        String[] fileList = context.fileList();
        ArrayList<String> offlineRequestFileList = new ArrayList<>();
        for (String f : fileList) {
            if (f != null && f.startsWith("offline-")) {
                offlineRequestFileList.add(f);
            }
        }
        return offlineRequestFileList;
    }

    public static ArrayList<String> getRiderTaskList(Context context) {
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

    public static ArrayList<String> getDriverTaskList(Context context) {
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

    public static ArrayList<String> getAcceptedTaskList(Context context) {
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