package com.samanthacapps.game.cards.skills;

import com.samanthacapps.exceptions.InvalidStepCountException;
import com.samanthacapps.game.PlayArea;
import com.samanthacapps.game.cards.Card;
import com.samanthacapps.game.cards.CardClassification;
import com.samanthacapps.game.cards.CostType;
import com.samanthacapps.game.cards.CardSubtype;

import java.util.List;

public class Sports extends Card {

    public String name = "Sports Skill";
    public CardClassification cardClassification = CardClassification.SKILL;
    public CardSubtype cardSubtype = CardSubtype.NONE;
    public CostType costType = CostType.NONE;
    public int costAmount = 0;
    public String primaryText = "";
    public int stepCount = 0;

    public void step(PlayArea playArea, List<String> input) throws InvalidStepCountException {
        playArea.playerSkillsInPlay.sportsSkillsInPlay++;
    }
}
