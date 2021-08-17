package com.samanthacapps.exceptions;

import com.samanthacapps.game.cards.Card;
import com.samanthacapps.game.cards.CostType;

public class SkillTypeNotPresentException extends Throwable {

    public String message;

    public SkillTypeNotPresentException(Card card) {
        this.message = "Skill type " + card.costType.toString() + " not in play, so this card cannot be used. " + card.name + " requires " + card.costAmount + " of skill " + card.costType;
    }

}
