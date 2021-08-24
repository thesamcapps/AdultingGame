package com.samanthacapps.game.cards.sports;

import com.samanthacapps.exceptions.CardDoesNotExistException;
import com.samanthacapps.exceptions.InvalidStepCountException;
import com.samanthacapps.game.PlayArea;
import com.samanthacapps.game.cards.Card;
import com.samanthacapps.game.cards.CardClassification;
import com.samanthacapps.game.cards.CardSubtype;
import com.samanthacapps.game.cards.CostType;
import com.samanthacapps.game.utils.CheckIfCardExists;

import java.util.List;

import static com.samanthacapps.game.utils.CheckIfCardExists.checkIfCardExists;
import static java.lang.Integer.parseInt;

public class AnkleSprain extends Card {

    public String name = "Ankle Sprain";
    public CardClassification classification = CardClassification.ACTIVITY;
    public CardSubtype cardSubtype = CardSubtype.NONE;
    public CostType costType = CostType.SPORTS;
    public final int costAmount = 7;
    public String primaryText = "Do 3 damage to your opponent. Then, if your opponent has any cards in their hand, they choose 2 of them and discard them (1 if they only have 1).";
    public String followUpText = "Type 'do' followed by which two cards you will discard. Example: do 3 2";
    public int stepCount = 0;

    public void step(PlayArea playArea, List<String> input) throws InvalidStepCountException, CardDoesNotExistException {
        switch (stepCount) {
            case 0 -> firstStep(playArea);
            case 1 -> finalStep(playArea, input);
            default -> throw new InvalidStepCountException(stepCount, name);
        };
    }

    private void firstStep(PlayArea playArea) {
        playArea.opponentDeck.discouragement(playArea, 3);

        if (playArea.opponentHand.cards.size() == 1) {
            playArea.opponentHand.cards.remove(0);
        } else if (playArea.opponentHand.cards.size() >= 2) {
            playArea.needInputFromOpponent = true;
            playArea.opponentCardInProgress = this;
            stepCount++;
        }
    }

    private void finalStep(PlayArea playArea, List<String> input) throws CardDoesNotExistException {
        checkIfCardExists(playArea.playerHand.cards, parseInt(input.get(1)));
        checkIfCardExists(playArea.playerHand.cards, parseInt(input.get(2)));

        if (parseInt(input.get(1)) > parseInt(input.get(2))) {
            playArea.playerHand.cards.remove(parseInt(input.get(1)));
            playArea.playerHand.cards.remove(parseInt(input.get(2)));
        } else {
            playArea.playerHand.cards.remove(parseInt(input.get(2)));
            playArea.playerHand.cards.remove(parseInt(input.get(1)));
        }

        playArea.needInputFromOpponent = false;
        playArea.playerEffort = 0;
        playArea.playerCardInProgress = null;
        stepCount = 0;
    }
}
