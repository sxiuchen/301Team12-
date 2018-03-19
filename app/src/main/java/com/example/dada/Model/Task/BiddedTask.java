package com.example.dada.Model.Task;

import java.util.ArrayList;

/**
 * Task that has been sent by the requester,
 * and also bidded by one or more provider, but
 * has not been assigned by the requester.
 * @see Task
 */
public class BiddedTask extends Task {

    public BiddedTask(String requesterUserName, String providerUserName, Route route, Double estimatedFare) {
        super(requesterUserName, providerUserName, route, estimatedFare);
    }

    public AcceptedRequest(String riderUserName, ArrayList<String> driverList, Route route, Double estimatedFare) {
        super(riderUserName, driverList, route, estimatedFare);
    }

    @Override
    public void riderConfirmDriver(String driverUserName) throws RequestException{
        super.riderConfirmDriver(driverUserName);
    }
}