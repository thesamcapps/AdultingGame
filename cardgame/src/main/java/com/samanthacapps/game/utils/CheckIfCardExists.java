package com.samanthacapps.game.utils;

import com.samanthacapps.exceptions.CardDoesNotExistException;
import com.samanthacapps.game.cards.Card;

import java.util.List;

public class CheckIfCardExists {

    public static void checkIfCardExists(List<Card> cards, int index) throws CardDoesNotExistException {
        if (cards.get(index) == null) {
            throw new CardDoesNotExistException(cards.size(), index);
        }
    }
}
