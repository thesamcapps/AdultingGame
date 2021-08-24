package com.samanthacapps.exceptions;

public class InvalidStepCountException extends Throwable {

    public String message;

    public InvalidStepCountException(int actionCount, String name) {
        this.message = "Action count " + actionCount + " is invalid for card " + name;
    }
}
