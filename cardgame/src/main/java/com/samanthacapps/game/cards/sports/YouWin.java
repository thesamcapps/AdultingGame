package com.samanthacapps.game.cards.sports;

import com.samanthacapps.exceptions.*;
import com.samanthacapps.game.PlayArea;
import com.samanthacapps.game.cards.Card;
import com.samanthacapps.game.cards.CardClassification;
import com.samanthacapps.game.cards.CardSubtype;
import com.samanthacapps.game.cards.CostType;

import java.util.List;

public class YouWin extends Card {

    public String name = "You Win";
    public CardClassification classification = CardClassification.ACTIVITY;
    public CardSubtype cardSubtype = CardSubtype.NONE;
    public CostType costType = CostType.SPORTS;
    public final int costAmount = 7;
    public String primaryText = "You win the current Competition. (You get the prize.)";
    public int stepCount = 0;

    public void step(PlayArea playArea, List<String> input) throws InvalidStepCountException, NoCompetitionInPlayException, NotEnoughCardsException, CardDoesNotExistException, NotEnoughCardsInHandException {
        if (playArea.competition != null) {
            playArea.competition.step(playArea, List.of("win"));
        } else {
            throw new NoCompetitionInPlayException();
        }
    }
}
