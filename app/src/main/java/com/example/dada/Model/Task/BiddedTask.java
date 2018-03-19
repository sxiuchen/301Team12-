package com.example.dada.Model.Task;

import com.example.dada.Exception.TaskException;

import java.util.ArrayList;

/**
 * Task that has been sent by the requester,
 * and also bidded by one or more provider, but
 * has not been assigned by the requester.
 * @see Task
 */
public class BiddedTask extends Task {

    public BiddedTask(String requesterUserName, String providerUserName, Double price) {
        super(requesterUserName, providerUserName, price);
    }

    public BiddedTask(String requesterUserName, ArrayList<String> providerList, Double price) {
        super(requesterUserName, providerList, price);
    }

    @Override
    public void requesterAssignProvider(String providerUserName) throws TaskException {
        super.requesterAssignProvider(providerUserName);
    }
}