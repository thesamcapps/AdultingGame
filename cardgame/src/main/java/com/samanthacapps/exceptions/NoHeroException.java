package com.samanthacapps.exceptions;

public class NoHeroException extends Throwable {

    public String message;

    public NoHeroException(String whichDeck) {
        this.message = whichDeck + " deck does not have a hero.";
    }

}
