package com.example.dada.Model.Task;

/**
 * Task that has been sent by the requester,
 * and accepted by one or more provider. Also,
 * it has been confirmed by the requester as well.
 *
 * @see Task
 */
public class AssignedTask extends Task {

    public AssignedTask(String title, String description) {
        super(title, description, "assigned");
    }

//    public void riderCompleteTask() {
//        super.riderConfirmRequestComplete();
//    }

}