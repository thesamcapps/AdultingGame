package com.samanthacapps.exceptions;

import com.samanthacapps.game.cards.Card;

public class NotEnoughSkillException extends Throwable {

    public String message;

    public NotEnoughSkillException(int actualSkill, Card card) {
        this.message = "You have " + actualSkill + " " + card.costType.toString() + " skills, but card " + card.name + " requires " + card.costAmount + "skills.";
    }
}
