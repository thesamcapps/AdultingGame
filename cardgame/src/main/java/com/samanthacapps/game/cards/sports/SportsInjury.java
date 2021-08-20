package com.samanthacapps.game.cards.sports;

import com.samanthacapps.exceptions.InvalidActionCountException;
import com.samanthacapps.game.PlayArea;
import com.samanthacapps.game.cards.Card;
import com.samanthacapps.game.cards.CardClassification;
import com.samanthacapps.game.cards.CardSubtype;
import com.samanthacapps.game.cards.CostType;

import java.util.List;

import static java.lang.Integer.parseInt;

public class SportsInjury extends Card {

    public String name = "Sports Injury";
    public CardClassification classification = CardClassification.ACTIVITY;
    public CardSubtype cardSubtype = CardSubtype.NONE;
    public CostType costType = CostType.SPORTS;
    public final int costAmount = 5;
    public String primaryText = "Do 5 damage to your opponent. Then, if your opponent has any cards in his or her hand, he or she chooses 1 of them and discards it";
    public String followUpText = "Type 'do' followed by which Tool you want to discard. Example: do 3";
    public int stepCount = 0;

    public void step(PlayArea playArea, List<String> input) throws InvalidActionCountException {
        switch (stepCount) {
            case 0 -> firstStep(playArea);
            case 1 -> finalStep(playArea, input);
            default -> throw new InvalidActionCountException(stepCount, name);
        };
    }

    private void firstStep(PlayArea playArea) {
        playArea.opponentDeck.discouragement(playArea, 5);
        playArea.needInputFromOpponent = true;
        playArea.opponentCardInProgress = this;
        stepCount++;
    }

    private void finalStep(PlayArea playArea, List<String> input) {
        playArea.playerHand.cards.remove(parseInt(input.get(1)));
        playArea.needInputFromOpponent = false;
        playArea.playerEffort = 0;
        stepCount = 0;
    }
}
