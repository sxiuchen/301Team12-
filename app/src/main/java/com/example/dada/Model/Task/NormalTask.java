package com.example.dada.Model.Task;

import java.util.ArrayList;

public class NormalTask extends Task {

    public NormalTask() {
    }

    public NormalTask(String riderUserName, Route route) {
        super(riderUserName, route);
    }

    public NormalTask(String riderUserName, String driverUserName, Route route, double estimatedFare) {
        super(riderUserName, driverUserName, route, estimatedFare);
    }

    public NormalTask(String riderUserName, ArrayList<String> driverList, Route route, double estimatedFare) {
        super(riderUserName, driverList, route, estimatedFare);
    }
}
