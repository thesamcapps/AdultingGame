package com.samanthacapps.exceptions;

public class InvalidActionCountException extends Throwable {

    public String message;

    public InvalidActionCountException(int actionCount, String name) {
        this.message = "Action count " + actionCount + " is invalid for card " + name;
    }
}
