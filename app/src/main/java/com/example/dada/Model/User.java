package com.example.dada.Model;

import android.media.Image;

import android.os.AsyncTask;
import android.util.Log;

import com.example.dada.Constant;
import com.example.dada.Util.UserUtil;
import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.io.IOException;

import io.searchbox.client.JestResult;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

public class User {
    private String user_name;
    private String ID;
    private int type;
    private Image profile_photo;
    private int phone_num;

    private transient static JestDroidClient client;

    public User(String user_name, String ID, int type, Image profile_photo, int phone_num){
        this.user_name = user_name;
        this.ID = ID;
        this.type = type;
        this.profile_photo = profile_photo;
        this.phone_num = phone_num;
    }

    /**
     * Instantiates a new User.
     */
    public User() {

    }

    /**
     * Static class that check user profile
     */
    public static class SearchUserExistTask extends AsyncTask<String, Void, Boolean> {

        /**
         * Check if username has been taken
         * @param query the username to be searched
         * @return True or Flase
         */
        @Override
        protected Boolean doInBackground(String... query) {
            verifySettings();

            User user = new User();
            Search search = new Search.Builder(query[0])
                    .addIndex("team12")
                    .addType("user")
                    .build();
            try {
                JestResult result = client.execute(search);
                if (result.isSucceeded()) {
                    user = result.getSourceAsObject(User.class);
                    if (user != null) {
                        Log.i("Debug", "Username has been taken");
                        return true;
                    }
                    Log.i("Debug", "Successful");
                } else {
                    Log.i("Debug", "The search query failed to find any user that matched.");
                }
            } catch (Exception e) {
                Log.i("Debug", "Something went wrong when we tried to communicate with the elastic search server!");
            }
            return false;
        }
    }

    /**
     * Static class that create user profile
     */
    public static class CreateUserTask extends AsyncTask<User, Void, User> {
        /**
         * The Listener.
         */
        public OnAsyncTaskCompleted listener;

        /**
         * Instantiates a new Create user task.
         *
         * @param listener the listener
         */
        public CreateUserTask(OnAsyncTaskCompleted listener) {
            this.listener = listener;
        }

        /**
         * Update the user profile to the server
         * @param user the user object to be updated
         * @return user
         */
        @Override
        protected User doInBackground(User... user) {
            verifySettings();
            User newUser = new User();
            for (User u : user) {
                Index index = new Index.Builder(u)
                        .index("team12")
                        .type("user")
                        .id(u.getID())
                        .build();
                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()) {
                        u.setID(result.getId());
                        newUser = u;
                        Log.i("Debug", "Successful create user");
                    } else {
                        Log.i("Debug", "Elastic search was not able to add the update user.");
                    }
                } catch (Exception e) {
                    Log.i("Error", "We failed to add user profile to elastic search!");
                    e.printStackTrace();
                }
            }
            return newUser;
        }

        @Override
        protected void onPostExecute(User user) {
            if (listener != null) {
                listener.onTaskCompleted(user);
            }
        }
    }

    /**
     * Static class that update user profile
     */
    public static class UpdateUserTask extends AsyncTask<User, Void, User> {
        /**
         * The Listener.
         */
        public OnAsyncTaskCompleted listener;

        /**
         * Instantiates a new Update user task.
         *
         * @param listener the listener
         */
        public UpdateUserTask(OnAsyncTaskCompleted listener) {
            this.listener = listener;
        }
        @Override
        protected User doInBackground(User... users) {
            verifySettings();
            // Serialize object into Json string
            String query = UserUtil.serializer(users[0]);
            Index index = new Index.Builder(query)
                    .index("team12").type("user").id(users[0].getID()).build();

            try {
                DocumentResult result = client.execute(index);
                if (result.isSucceeded()) {
                    Log.i("Debug", "Successful update user profile");
                } else {
                    Log.i("Error", "We failed to update user profile to elastic search!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return users[0];
        }

        @Override
        protected void onPostExecute(User user) {
            if (listener != null) {
                listener.onTaskCompleted(user);
            }
        }
    }

    /**
     * Static class that get user profile
     */
    public static class GetUserProfileTask extends AsyncTask<String, Void, User> {
        /**
         * The Listener.
         */
        public OnAsyncTaskCompleted listener;

        /**
         * Instantiates a new Get user profile task.
         *
         * @param listener the listener
         */
        public GetUserProfileTask(OnAsyncTaskCompleted listener) {
            this.listener = listener;
        }
        /**
         * Get the user profile from the server
         * @param query the username to be searched
         * @return the mathed user obejct
         */
        @Override
        protected User doInBackground(String... query) {
            verifySettings();

            User user = new User();
            Search search = new Search.Builder(query[0])
                    .addIndex("team12")
                    .addType("user")
                    .build();
            try {
                SearchResult result = client.execute(search);
                if (result.isSucceeded()) {
                    User getUser = result.getSourceAsObject(User.class);
                    user = getUser;
                    Log.i("Debug", "Successful get user profile");
                    if (user == null) {
                        Log.i("Debug", "fail to deserilize");
                    }
                } else {
                    Log.i("Error", "The search query failed to find any user that matched.");
                }
            } catch (Exception e) {
                Log.i("Error", "Something went wrong when we tried to communicate with the elastic search server!");
            }
            return user;
        }

        @Override
        protected void onPostExecute(User user) {
            if (listener != null) {
                listener.onTaskCompleted(user);
            }
        }
    }

    /**
     * Set up the connection with server
     */
    private static void verifySettings() {
        // if the client hasn't been initialized then we should make it!
        if (client == null) {
            DroidClientConfig.Builder builder = new DroidClientConfig
                    .Builder(Constant.ELASTIC_SEARCH_URL);
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }


    public String getUser_name(){
        return user_name;
    }

    public String getID(){
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

    public void setID(String ID) {
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
