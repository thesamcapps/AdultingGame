package com.samanthacapps.game.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

    public List<Card> cards;
    public Card hero;

    public List<Card> draw(int num) {
        List<Card> drawnCards = new ArrayList<>();

        for (var i = 0; i < num; i++) {
            drawnCards.add(cards.remove(i));
        }

        return drawnCards;
    }

    public void shuffle() {
        List<Card> shuffledDeck = new ArrayList<>();
        Random random = new Random();
        int index = 0;

        for (var i = 0; i < 59; i++) {
            index = (++index + random.nextInt(30)) % this.cards.size();

            shuffledDeck.add(this.cards.remove(index));
        }

        shuffledDeck.add(this.cards.get(0));

        this.cards = shuffledDeck;
    }

    public void discouragement() {
        cards.remove(0);
    }

    public boolean checkSize() {
        return this.cards.size() == 60;
    }

    public Deck(List<Card> cards, Card hero) {
        this.cards = cards;
        this.hero = hero;
    }

    public void putCardOnBottom(Card card) {
        cards.add(card);
    }
}
