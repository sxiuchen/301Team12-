package com.example.dada.Model.Request;

import java.util.ArrayList;

public class NormalRequest extends Request {

    public NormalRequest() {
    }

    public NormalRequest(String riderUserName, Route route) {
        super(riderUserName, route);
    }

    public NormalRequest(String riderUserName, String driverUserName, Route route, double estimatedFare) {
        super(riderUserName, driverUserName, route, estimatedFare);
    }

    public NormalRequest(String riderUserName, ArrayList<String> driverList, Route route, double estimatedFare) {
        super(riderUserName, driverList, route, estimatedFare);
    }
}
