package com.samanthacapps.game.cards.sports;

import com.samanthacapps.exceptions.InvalidActionCountException;
import com.samanthacapps.game.PlayArea;
import com.samanthacapps.game.cards.Card;
import com.samanthacapps.game.cards.CardClassification;
import com.samanthacapps.game.cards.CardSubtype;
import com.samanthacapps.game.cards.CostType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class SlamDunk extends Card {

    public String name = "Slam Dunk";
    public CardClassification classification = CardClassification.ACTIVITY;
    public CardSubtype cardSubtype = CardSubtype.NONE;
    public CostType costType = CostType.SPORTS;
    public final int costAmount = 5;
    public String primaryText = "Discard any number of cards from your hand. Do that much damage to your opponent.";
    public String followUpText = "Type 'do' followed by the numbers of the cards you want to discard. Example: do 3 1 2";
    public int effortCount = 0;

    public void effort(PlayArea playArea, List<String> input) throws InvalidActionCountException {
        switch (effortCount) {
            case 0 -> firstEffort(playArea);
            case 1 -> finalEffort(playArea, input);
            default -> throw new InvalidActionCountException(effortCount, name);
        };
    }

    private void firstEffort(PlayArea playArea) {
        effortCount++;
        playArea.playerCardInProgress = this;
        playArea.playerCardsAwaitingAction = playArea.playerHand.cards;
    }

    private void finalEffort(PlayArea playArea, List<String> input) {
        input.forEach(string -> {
            playArea.playerDiscard.add(playArea.playerCardsAwaitingAction.get(parseInt(string)));
            playArea.playerCardsAwaitingAction.set(parseInt(string), null);
        });

        playArea.playerHand.cards = playArea.playerCardsAwaitingAction.stream().filter(Objects::nonNull).collect(Collectors.toList());

        playArea.playerCardInProgress = null;
        playArea.playerCardsAwaitingAction = new ArrayList<Card>();
        effortCount = 0;
    }
}
