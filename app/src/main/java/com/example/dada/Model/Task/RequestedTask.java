package com.example.dada.Model.Task;

public class RequestedTask extends Task {

    public RequestedTask() {
    }

    /**
     * Constructor for pending request
     * @param title         title of the task
     * @param description   description of the task
     */
    public RequestedTask(String title, String description, String requesterUserName) {
        super(title, description, requesterUserName,"Requested");
    }

    /**
     * Provider bids the requested task.
     *
     * @param providerUserName the provider user name who bids the requested task
     */
    @Override
    public void providerBidTask(String providerUserName) {
        super.providerBidTask(providerUserName);
    }

}
