/* UserException
 *
 * Version 1.0
 *
 * March 15, 2018
 *
 * Copyright (c) 2018 Team 12 CMPUT 301. University of Alberta - All Rights Reserved.
 * You may use distribute or modify this code under terms and condition of the Code of Student Behaviour at University of Alberta.
 * You can find a copy of licence in this project. Otherwise please contact contact sfeng3@ualberta.ca.
 */

package com.example.dada.Exception;

/**
 * The User exception.
 */
public class UserException extends Exception {

    /**
     * Instantiates a new User exception.
     */
    public UserException() {

    }

    /**
     * Instantiates a new User exception.
     *
     * @param message the message
     */
    public UserException(String message) {
        super(message);
    }
}
