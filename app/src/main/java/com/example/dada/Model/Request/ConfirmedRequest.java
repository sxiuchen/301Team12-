package com.example.dada.Model.Request;

/**
 * Request that has been sent by the rider,
 * and accepted by one or more driver. Also,
 * it has been confirmed by the rider as well.
 *
 * @see Request
 */
public class ConfirmedRequest extends Request {
    public ConfirmedRequest(String riderUserName, String driverUserName, Route route, double estimatedFare) {
        super(riderUserName, driverUserName, route, estimatedFare);
    }

    public void riderCompleteTask() {
        super.riderConfirmRequestComplete();
    }
}

