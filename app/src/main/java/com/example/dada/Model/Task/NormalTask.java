package com.example.dada.Model.Task;

import java.util.ArrayList;

public class NormalTask extends Task {

    public NormalTask() {
    }

    public NormalTask(String title, String description, String requesterUserName) {
        super(title, description, requesterUserName, "requested");
    }

    public NormalTask(String requesterUserName, String providerUserName, double price) {
        super(requesterUserName, providerUserName, price);
    }

    public NormalTask(String requesterUserName, ArrayList<String> providerList, double price) {
        super(requesterUserName, providerList, price);
    }

}
