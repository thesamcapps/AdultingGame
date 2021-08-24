package com.samanthacapps.game.cards.skills;

import com.samanthacapps.game.PlayArea;
import com.samanthacapps.game.cards.Card;
import com.samanthacapps.game.cards.CardClassification;
import com.samanthacapps.game.cards.CardSubtype;
import com.samanthacapps.game.cards.CostType;

import java.util.List;

public class PetTraining extends Card {

    public String name = "Pet Training Skill";
    public CardClassification cardClassification = CardClassification.TOOL;
    public CardSubtype cardSubtype = CardSubtype.NONE;
    public CostType costType = CostType.NONE;
    public int costAmount = 0;
    public String primaryText = "";
    public int stepCount = 0;

    public void step(PlayArea playArea, List<String> input) {
        playArea.playerSkillsInPlay.petTrainingSkillsInPlay++;
    }
}
