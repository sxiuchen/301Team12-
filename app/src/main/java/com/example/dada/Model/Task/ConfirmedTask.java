package com.example.dada.Model.Task;

/**
 * Request that has been sent by the rider,
 * and accepted by one or more driver. Also,
 * it has been confirmed by the rider as well.
 *
 * @see Task
 */
public class ConfirmedTask extends Task {
    public ConfirmedTask(String riderUserName, String driverUserName, Route route, double estimatedFare) {
        super(riderUserName, driverUserName, route, estimatedFare);
    }

    public void riderCompleteTask() {
        super.riderConfirmRequestComplete();
    }
}

