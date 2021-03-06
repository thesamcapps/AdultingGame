package com.samanthacapps.game.cards.sports;

import com.samanthacapps.exceptions.CardDoesNotExistException;
import com.samanthacapps.exceptions.InvalidStepCountException;
import com.samanthacapps.game.PlayArea;
import com.samanthacapps.game.cards.Card;
import com.samanthacapps.game.cards.CardClassification;
import com.samanthacapps.game.cards.CardSubtype;
import com.samanthacapps.game.cards.CostType;

import java.util.ArrayList;
import java.util.List;

import static com.samanthacapps.game.utils.CheckIfCardExists.checkIfCardExists;
import static java.lang.Integer.parseInt;

public class FoulBall extends Card {

    public String name = "Foul Ball";
    public CardClassification classification = CardClassification.ACTIVITY;
    public CardSubtype cardSubtype = CardSubtype.NONE;
    public CostType costType = CostType.SPORTS;
    public final int costAmount = 6;
    public String primaryText = "Do 4 Discouragement damage to your opponent. Then, if your opponent has any Tools in play he or she chooses 1 of them and discards it.";
    public String followUpText = "Type 'do' followed by the number of the item you wish to discard. Example: do 2";
    public int stepCount = 0;

    public void step(PlayArea playArea, List<String> input) throws InvalidStepCountException, CardDoesNotExistException {
        switch (stepCount) {
            case 0 -> firstStep(playArea);
            case 1 -> finalStep(playArea, input);
            default -> throw new InvalidStepCountException(stepCount, name);
        };
    }

    private void firstStep(PlayArea playArea) {
        playArea.opponentDeck.discouragement(playArea, 4);

        if (playArea.playerItems.size() == 1) {
            playArea.playerItems.remove(0);
        } else if (playArea.playerItems.size() > 0) {
            playArea.needInputFromOpponent = true;
            playArea.opponentCardInProgress = this;
            stepCount++;
        }
    }

    private void finalStep(PlayArea playArea, List<String> input) throws CardDoesNotExistException {
        checkIfCardExists(playArea.playerItems, parseInt(input.get(1)));

        playArea.playerItems.remove(parseInt(input.get(1)));
        playArea.playerCardInProgress = null;
        playArea.playerCardsAwaitingAction = new ArrayList<Card>();
        stepCount = 0;
    }
}
