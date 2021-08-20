package com.samanthacapps.game.cards.competitions;

import com.samanthacapps.exceptions.InvalidActionCountException;
import com.samanthacapps.game.PlayArea;
import com.samanthacapps.game.cards.Card;
import com.samanthacapps.game.cards.CardClassification;
import com.samanthacapps.game.cards.CardSubtype;
import com.samanthacapps.game.cards.CostType;

import java.util.List;

public class Overtime extends Card {

    public String name = "Overtime";
    public CardClassification classification = CardClassification.COMPETITION;
    public CardSubtype cardSubtype = CardSubtype.NONE;
    public CostType costType = CostType.SPORTS;
    public final int costAmount = 1;
    public String primaryText = "In order to win, do 30 damage to your opponent while this card is in play. (That damage doesn't have to be done all at once.) The prize is the winner does 15 discouragement damage to their opponent.";
    public int stepCount = 0;

    private int damageDoneToPlayerAByPlayerBCount = 0;
    private int damageDoneToPlayerBByPlayerACount = 0;

    public void step(PlayArea playArea, List<String> input) throws InvalidActionCountException {
        switch (stepCount) {
            case 0 -> firstStep(playArea);
            case 1 -> finalStep(playArea);
            default -> throw new InvalidActionCountException(stepCount, name);
        };
    }
    
    private void firstStep(PlayArea playArea) {
        playArea.competition = this;
        stepCount++;
    }
    
    private void finalStep(PlayArea playArea) {
        //TODO what if both hit 30 on same step or multiple damage in one step
        if ((playArea.whichPlayer == 'a' && damageDoneToPlayerAByPlayerBCount >= 30) || (playArea.whichPlayer == 'b' && damageDoneToPlayerBByPlayerACount >= 30)) {
            playArea.playerDeck.discouragement(playArea, 15);
            stepCount = 0;
            playArea.competition = null;
        } else if ((playArea.whichPlayer == 'a' && damageDoneToPlayerBByPlayerACount >= 30) || (playArea.whichPlayer == 'b' && damageDoneToPlayerAByPlayerBCount >= 30)) {
            playArea.opponentDeck.discouragement(playArea, 15);
            stepCount = 0;
            playArea.competition = null;
        }
    }
}
