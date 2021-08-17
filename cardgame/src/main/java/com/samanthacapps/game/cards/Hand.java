package com.samanthacapps.game.cards;

import com.samanthacapps.game.PlayArea;
import com.samanthacapps.game.utils.PlayerFlag;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    public List<Card> cards;

    public Hand() {}

    public void addCardToHand(Card card) {
        cards.add(card);
    }

    public Deck startingHand(Deck deck) {
        List<Card> drawnCards = deck.draw(7);

        cards.addAll(drawnCards);

        return deck;
    }
}
