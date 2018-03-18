package com.example.dada.Model.Task;

public class RequestedTask extends Task {

    public RequestedTask() {
    }

    /**
     * Constructor for pending request
     * @param title         title of the task
     * @param description   description of the task
     */
    public RequestedTask(String title, String description) {
        super(title, description, "Requested");
    }

//    /**
//     * Driver accepts the request.
//     *
//     * @param driverUserName the driver user name who accepts the ride request
//     */
//    @Override
//    public void driverAcceptRequest(String driverUserName) { // changed from driverConfirmRequest
//        super.driverAcceptRequest(driverUserName);
//    }

}
