package com.example.dada.Model.Request;

import java.util.ArrayList;

public class AcceptedRequest extends Request {
    public AcceptedRequest(String riderUserName, String driverUserName, Route route, Double estimatedFare) {
        super(riderUserName, driverUserName, route, estimatedFare);
    }

    public AcceptedRequest(String riderUserName, ArrayList<String> driverList, Route route, Double estimatedFare) {
        super(riderUserName, driverList, route, estimatedFare);
    }

    @Override
    public void riderConfirmDriver(String driverUserName) throws RequestException{
        super.riderConfirmDriver(driverUserName);
    }
}
