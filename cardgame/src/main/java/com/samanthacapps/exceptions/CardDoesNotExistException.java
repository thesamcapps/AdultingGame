package com.samanthacapps.exceptions;

public class CardDoesNotExistException extends Throwable {

    public String message;

    public CardDoesNotExistException(int size, int index) {
        message = "Card index " + index + " does not exist in collection of cards with size " + size + ".";
    }

}
