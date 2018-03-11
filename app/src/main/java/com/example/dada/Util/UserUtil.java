package com.example.dada.Util;

import com.example.dada.Model.User;
import com.google.gson.Gson;

/**
 * Utility class that help to serilize and deserialize the user object
 */
public class UserUtil {
    public static String serializer(User user) {
        Gson gson = new Gson();
        return gson.toJson(user);
    }

    public static User deserializer(String string) {
        Gson gson = new Gson();
        return gson.fromJson(string, User.class);
    }
}