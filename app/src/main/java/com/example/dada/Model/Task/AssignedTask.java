package com.example.dada.Model.Task;

/**
 * Task that has been sent by the requester,
 * and accepted by one or more driver. Also,
 * it has been confirmed by the rider as well.
 *
 * @see Task
 */
public class AssignedTask extends Task{

    public AssignedTask(String requesterUserName, String providerUserName, double price) {
        super(requesterUserName, providerUserName, price);
    }

    public void requesterCompleteTask() {
        super.requesterConfirmTaskComplete();
    }
}