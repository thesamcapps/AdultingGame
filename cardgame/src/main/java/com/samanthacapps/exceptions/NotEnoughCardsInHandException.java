package com.samanthacapps.exceptions;

public class NotEnoughCardsInHandException extends Throwable {

    public String message;

    public NotEnoughCardsInHandException(int needed, int present, String cardName) {
        this.message = "Card " + cardName + " requires at least " + needed + " in your hand in order to play but you have " + present + "cards.";
    }
}
