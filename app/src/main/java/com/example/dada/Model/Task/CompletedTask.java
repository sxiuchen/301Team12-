package com.example.dada.Model.Task;

/**
 * Task that has been completed in the past.
 * @see Task
 */
public class CompletedTask extends Task {
    public CompletedTask(String requesterUserName, String providerUserName, double price) {
        super(requesterUserName, providerUserName, price);
    }
}