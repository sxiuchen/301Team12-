package com.example.dada.Model.Task;

import java.util.ArrayList;

public class AcceptedTask extends Task {
    public AcceptedTask(String riderUserName, String driverUserName, Route route, Double estimatedFare) {
        super(riderUserName, driverUserName, route, estimatedFare);
    }

    public AcceptedTask(String riderUserName, ArrayList<String> driverList, Route route, Double estimatedFare) {
        super(riderUserName, driverList, route, estimatedFare);
    }

    @Override
    public void riderConfirmDriver(String driverUserName) throws RequestException{
        super.riderConfirmDriver(driverUserName);
    }
}
