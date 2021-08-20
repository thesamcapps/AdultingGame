package com.samanthacapps.game.cards.decks;

import com.samanthacapps.game.cards.Card;
import com.samanthacapps.game.cards.Deck;
import com.samanthacapps.game.cards.heroes.Michaela;
import com.samanthacapps.game.cards.skills.Sports;
import com.samanthacapps.game.cards.sports.HockeyFight;
import com.samanthacapps.game.cards.sports.SlamDunk;
import com.samanthacapps.game.cards.sports.SportsInjury;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class CoolGuyDeck {

    public Deck deck;

    public CoolGuyDeck() {
        Card hero = new Michaela();

        List<Card> cardsForDeck = new ArrayList<>();

        for (var i = 0; i < 4; i++) {
            deck.cards.add(new Sports());
        }

        deck.cards.add(new HockeyFight());

        deck.cards.add(new SlamDunk());

        deck.cards.add(new SportsInjury());

        deck = new Deck(cardsForDeck, hero);
    }

}
