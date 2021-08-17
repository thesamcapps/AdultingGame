package com.samanthacapps.game;

import com.samanthacapps.exceptions.DeckWrongSizeException;
import com.samanthacapps.exceptions.NoHeroException;
import com.samanthacapps.game.cards.Card;
import com.samanthacapps.game.cards.Deck;
import com.samanthacapps.game.cards.Hand;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayArea {

    public Deck playerDeck;
    public Deck opponentDeck;

    public Card playerPrimaryHero;
    public Card opponentPrimaryHero;

    public List<Card> playerOtherHeroes;
    public List<Card> opponentOtherHeroes;

    public Hand playerHand;
    public Hand opponentHand;

    public List<Card> playerCardsAwaitingAction;
    public List<Card> opponentCardsAwaitingAction;

    public Card playerCardInProgress;
    public Card opponentCardInProgress;

    public SkillsInPlay playerSkillsInPlay = new SkillsInPlay();
    public SkillsInPlay opponentSkillsInPlay = new SkillsInPlay();

    public int playerEffort;
    public int opponentEffort;

    //private DiscardPile playerDiscard;
    //private DiscardPile opponentDiscard;

    public PlayArea() {
        playerEffort = 2;
        opponentEffort = 2;
    }

    public void setUpPlayArea() throws NoHeroException, DeckWrongSizeException {
        if (!this.playerDeck.checkSize()) {
            throw new DeckWrongSizeException("Player", this.playerDeck.cards.size());
        }

        if (this.playerDeck.hero == null) {
            throw new NoHeroException("Player");
        }

        if (!this.opponentDeck.checkSize()) {
            throw new DeckWrongSizeException("Opponent", this.opponentDeck.cards.size());
        }

        if (this.opponentDeck.hero == null) {
            throw new NoHeroException("Opponent");
        }

        this.playerDeck.shuffle();
        this.opponentDeck.shuffle();

        this.playerDeck = this.playerHand.startingHand(this.playerDeck);
        this.opponentDeck = this.opponentHand.startingHand(this.opponentDeck);
    }

    public List<String> checkHeroActions() {
        AtomicInteger actionNum = new AtomicInteger(1);
        List<String> actions = new ArrayList<>();

        actions.add(actionNum.getAndIncrement() + this.playerPrimaryHero.text);

        if (!this.playerOtherHeroes.isEmpty()) {
            this.playerOtherHeroes.forEach(hero -> {
                actions.add(actionNum.getAndIncrement() + hero.text);
            });
        }

        return actions;
    }
}