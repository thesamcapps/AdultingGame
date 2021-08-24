package com.samanthacapps.exceptions;

import com.samanthacapps.game.cards.Card;

import java.util.List;

public class NotEnoughCardsException extends Throwable {

    public String message;

    public NotEnoughCardsException(List<Card> list, int index) {
        message = list.size() + " cards are available but card of index " + index + " was asked for. Please specify " + list.size() + " cards or fewer.";
    }
}
