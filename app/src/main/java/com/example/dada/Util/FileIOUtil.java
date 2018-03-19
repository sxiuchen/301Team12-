package com.example.dada.Util;

import android.content.Context;
import android.util.Log;

import com.example.dada.Constant;
import com.example.dada.Model.User;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * The type File io util.
 */
public class FileIOUtil {
    /**
     * An utility method that save user profile locally
     *
     * @param user    the user object to be saved
     * @param context an android activity component
     */
    public static void saveUserInFile(User user, Context context) {
        try {
            Gson gson = new Gson();
            String jsonStr = gson.toJson(user);
            // write json string into corresponding file
            Log.i("Debug", jsonStr);
            FileOutputStream fos = context.openFileOutput(Constant.USER_PROFILE_FILENAME, 0);
            fos.write(jsonStr.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * An utility method that retrieve user profile locally
     *
     * @param context an android activity component
     * @return an User object
     */
    public static User loadUserFromFile(Context context) {
        User user = new User();
        try {
            Gson gson = new Gson();
            FileInputStream fis = context.openFileInput(Constant.USER_PROFILE_FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            user = gson.fromJson(in, User.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

//    /**
//     * Save request in file.
//     *
//     * @param request the  requests
//     * @param context the context
//     */
//    public static void saveOfflineRequestInFile(Task request, Context context) {
//        try {
//            Gson gson = TaskUtil.customGsonBuilder();
//            String jsonStr = gson.toJson(request);
//            String fileName = TaskUtil.generateOfflineRequestFileName(request);
//            FileOutputStream fos = context.openFileOutput(fileName, 0);
//            if (fos == null) {
//                Log.i("Debug", "null fos in save request");
//            }
//            try {
//                fos.write(jsonStr.getBytes());
//            } catch (NullPointerException e) {
//                Log.i("Debug", "getBytes() threw null pointer exception");
//            }
//            fos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException();
//        }
//    }

//    public static void saveRequestInFile(Task request, String fileName, Context context) {
//        try {
//            Gson gson = TaskUtil.customGsonBuilder();
//            String jsonStr = gson.toJson(request);
//            FileOutputStream fos = context.openFileOutput(fileName, 0);
//            if (fos == null) {
//                Log.i("Debug", "null fos in save request");
//            }
//            try {
//                fos.write(jsonStr.getBytes());
//            } catch (NullPointerException e) {
//                Log.i("Debug", "getBytes() threw null pointer exception");
//            }
//            fos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException();
//        }
//    }

//    public static void saveRiderRequestInFile(Task request, Context context) {
//        try {
//            Gson gson = TaskUtil.customGsonBuilder();
//            String jsonStr = gson.toJson(request);
//            String fileName = TaskUtil.generateRiderRequestFileName(request);
//            FileOutputStream fos = context.openFileOutput(fileName, 0);
//            if (fos == null) {
//                Log.i("Debug", "null fos in save request");
//            }
//            try {
//                fos.write(jsonStr.getBytes());
//            } catch (NullPointerException e) {
//                Log.i("Debug", "getBytes() threw null pointer exception");
//            }
//            fos.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException();
//        }
//    }

//    /**
//     * Load request from file array list.
//     *
//     * @param context the context
//     * @return the array list of requests
//     */
//    public static ArrayList<Task> loadRequestFromFile(Context context, ArrayList<String> fileList) {
//        ArrayList<Task> requestList = new ArrayList<>();
//        Gson gson = TaskUtil.customGsonBuilder();
//        for (String f : fileList) {
//            FileInputStream fis = null;
//            try {
//                fis = context.openFileInput(f);
//                BufferedReader in = new BufferedReader(new InputStreamReader(fis));
//                NormalRequest req = gson.fromJson(in, NormalRequest.class);
//                requestList.add(req);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        return requestList;
//    }

//    public static Task loadSingleRequestFromFile(String fileName, Context context) {
//        Task request = new NormalRequest();
//        FileInputStream fis = null;
//        Gson gson = TaskUtil.customGsonBuilder();
//        try {
//            fis = context.openFileInput(fileName);
//            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
//            request = gson.fromJson(in, NormalRequest.class);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return request;
//    }
}

