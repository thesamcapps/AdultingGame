package com.samanthacapps.game.cards.heroes;

import com.samanthacapps.exceptions.InvalidActionCountException;
import com.samanthacapps.game.PlayArea;
import com.samanthacapps.game.cards.Card;
import com.samanthacapps.game.cards.CardClassification;
import com.samanthacapps.game.cards.CardSubtype;
import com.samanthacapps.game.cards.CostType;

import java.util.ArrayList;
import java.util.List;

public class Michaela extends Card {

    public String name = "Michaela";
    public CardClassification cardClassification = CardClassification.HERO;
    public CardSubtype cardSubtype = CardSubtype.YOUNG_PROFESSIONAL;
    public CostType costType = CostType.NONE;
    public int costAmount = 0;
    public String primaryText = "You may use an Effort to reveal the top 3 cards of your deck. Put all of those cards that need Brains Skill into your hands. Put the rest on the bottom of your deck (in any order).";
    public String followUpText = "Type 'do' followed by the order in which you want to put these cards on the bottom of your deck, where the last card listed will be the last card in the deck. Example: do 3 1 2";
    public int stepCount = 0;

    public void step(PlayArea playArea, List<String> input) throws InvalidActionCountException {
        switch (stepCount) {
            case 0 -> firstStep(playArea);
            case 1 -> finalStep(playArea, input);
            default -> throw new InvalidActionCountException(stepCount, name);
        };
    }

    private void firstStep(PlayArea playArea) {
        List<Card> cards = playArea.playerDeck.draw(3);
        List<Card> cardsToPutBack = new ArrayList<>();

        cards.forEach(card -> {
            if (card.costType == CostType.BRAINS) {
                playArea.playerHand.addCardToHand(card);
            } else {
                cardsToPutBack.add(card);
            }
        });

        if (cardsToPutBack.size() > 0) {
            playArea.playerCardsAwaitingAction = cardsToPutBack;
            stepCount++;
        }

        playArea.playerCardInProgress = this;
    }

    private void finalStep(PlayArea playArea, List<String> input) {
        for (var i = 1; i < input.size(); i++) {
            playArea.playerDeck.putCardOnBottom(playArea.playerCardsAwaitingAction.get(i));
        }

        playArea.playerCardInProgress = null;
        playArea.playerCardsAwaitingAction = new ArrayList<Card>();
        stepCount = 0;
    }
}
