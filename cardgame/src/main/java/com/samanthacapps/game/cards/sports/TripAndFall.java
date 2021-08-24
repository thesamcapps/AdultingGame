package com.samanthacapps.game.cards.sports;

import com.samanthacapps.game.PlayArea;
import com.samanthacapps.game.cards.Card;
import com.samanthacapps.game.cards.CardClassification;
import com.samanthacapps.game.cards.CardSubtype;
import com.samanthacapps.game.cards.CostType;

import java.util.List;

public class TripAndFall extends Card {

    public String name = "Trip And Fall";
    public CardClassification classification = CardClassification.ACTIVITY;
    public CardSubtype cardSubtype = CardSubtype.NONE;
    public CostType costType = CostType.SPORTS;
    public final int costAmount = 8;
    public String primaryText = "Do 4 damage to your opponent. Then draw 4 cards;";
    public String followUpText = "";
    public int stepCount = 0;

    public void step(PlayArea playArea, List<String> input) {
        playArea.opponentDeck.discouragement(playArea, 4);
        playArea.playerDeck.draw(4);
    }
}
