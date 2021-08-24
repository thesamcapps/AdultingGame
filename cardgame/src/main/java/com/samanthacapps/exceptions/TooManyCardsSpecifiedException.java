package com.samanthacapps.exceptions;

public class TooManyCardsSpecifiedException extends Throwable {

    public String message;

    public TooManyCardsSpecifiedException(int needed, int specified) {
        message = "You specified " + specified + " cards but needed to specify " + needed + " or fewer.";
    }
}
