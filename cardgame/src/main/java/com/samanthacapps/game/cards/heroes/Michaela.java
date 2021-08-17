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
    public String text = "You may use an Effort to reveal the top 3 cards of your deck. Put all of those cards that need Brains Skill into your hands. Put the rest on the bottom of your deck (in any order).";
    public int effortCount = 0;

    public void effort(PlayArea playArea, List<String> input) throws InvalidActionCountException {
        switch (actionCount) {
            case 0 -> firstEffort(playArea);
            case 1 -> finalEffort(playArea, input);
            default -> throw new InvalidActionCountException(actionCount, name);
        };
    }

    private void firstEffort(PlayArea playArea) {
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
            effortCount++;
        }
    }

    private void finalEffort(PlayArea playArea, List<String> input) {
        for (var i = 1; i < input.size(); i++) {
            playArea.playerDeck.putCardOnBottom(playArea.playerCardsAwaitingAction.get(i));
        }

        playArea.playerCardInProgress = null;
    }
}
