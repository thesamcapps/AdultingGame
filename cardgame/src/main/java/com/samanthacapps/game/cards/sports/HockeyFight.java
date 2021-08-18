package com.samanthacapps.game.cards.sports;

import com.samanthacapps.game.PlayArea;
import com.samanthacapps.game.cards.Card;
import com.samanthacapps.game.cards.CardClassification;
import com.samanthacapps.game.cards.CardSubtype;
import com.samanthacapps.game.cards.CostType;

import java.util.List;

public class HockeyFight extends Card {

    public String name = "Hockey Fight";
    public CardClassification classification = CardClassification.ACTIVITY;
    public CardSubtype cardSubtype = CardSubtype.NONE;
    public CostType costType = CostType.SPORTS;
    public final int costAmount = 4;
    public String primaryText = "Do 4 Discouragement damage to your opponent. During your rival's next turn, they get 1 fewer Effort. (If this would mean your rival has fewer than 1 Effort, they still get 1 Effort.)";
    public int actionCount = 0;

    public void effort(PlayArea playArea, List<String> input) {
        for (var i = 0; i < 4; i++) {
            playArea.opponentDeck.discouragement();
        }

        if (playArea.opponentEffort > 1) {
            playArea.opponentEffort--;
        }
    }
}
