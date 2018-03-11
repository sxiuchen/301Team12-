package com.example.dada.Model.Request;

/**
 * Request that has been completed in the past.
 * @see Request
 */
public class CompletedRequest extends Request {
    public CompletedRequest(String riderUserName, String driverUserName, Route route, double estimatedFare) {
        super(riderUserName, driverUserName, route, estimatedFare);
    }
}