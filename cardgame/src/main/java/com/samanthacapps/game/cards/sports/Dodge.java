package com.samanthacapps.game.cards.sports;

import com.samanthacapps.game.PlayArea;
import com.samanthacapps.game.cards.Card;
import com.samanthacapps.game.cards.CardClassification;
import com.samanthacapps.game.cards.CardSubtype;
import com.samanthacapps.game.cards.CostType;

import java.util.List;

public class Dodge extends Card {

    public String name = "Dodge";
    public CardClassification classification = CardClassification.ACTIVITY;
    public CardSubtype cardSubtype = CardSubtype.NONE;
    public CostType costType = CostType.SPORTS;
    public final int costAmount = 4;
    public String primaryText = "Do 2 damage to your opponent. Then draw 2 cards;";
    public String followUpText = "";
    public int stepCount = 0;

    public void step(PlayArea playArea, List<String> input) {
        playArea.opponentDeck.discouragement(playArea, 2);
        playArea.playerDeck.draw(2);
    }
}
