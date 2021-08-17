package com.samanthacapps.exceptions;

public class DeckWrongSizeException extends Throwable {

    public String message;

    public DeckWrongSizeException(String whichDeck, int size) {
        this.message = whichDeck + " deck does not contain exactly 60 cards. It contains " + size;
    }

}
