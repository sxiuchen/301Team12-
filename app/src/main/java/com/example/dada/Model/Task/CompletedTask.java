package com.example.dada.Model.Task;

import com.example.dada.Model.Task.Task;

/**
 * Task that has been completed in the past.
 * @see Task
 */
public class CompletedTask extends Task {
    public CompletedTask(String title, String description) {
        super(title, description, "completed");
    }
}