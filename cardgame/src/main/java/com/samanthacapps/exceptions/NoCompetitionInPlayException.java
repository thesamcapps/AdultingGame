package com.samanthacapps.exceptions;

public class NoCompetitionInPlayException extends Throwable {

    public String message;

   public NoCompetitionInPlayException() {
       message = "There is no competition in play so this card cannot be played.";
   }
}
