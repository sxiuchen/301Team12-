package com.example.dada.Model;

/**
 * The interface On async task completed, will be
 * called after the async event is done.
 */
public interface OnAsyncTaskCompleted {
    /**
     * On task completed.
     *
     * @param o the object will be return by the asynctask
     */
    void onTaskCompleted(Object o);
}
