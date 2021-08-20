package com.samanthacapps.game.cards.petTraining;

import com.samanthacapps.exceptions.InvalidActionCountException;
import com.samanthacapps.game.PlayArea;
import com.samanthacapps.game.cards.Card;
import com.samanthacapps.game.cards.CardClassification;
import com.samanthacapps.game.cards.CardSubtype;
import com.samanthacapps.game.cards.CostType;

import java.util.List;
import java.util.stream.Collectors;

public class DogTrainingForDummies extends Card {

    public String name = "Dog Training For Dummies";
    public CardClassification classification = CardClassification.TOOL;
    public CardSubtype cardSubtype = CardSubtype.BOOK;
    public CostType costType = CostType.PET_TRAINING;
    public final int costAmount = 2;
    public String primaryText = "Provides one Pet Training Skill. You may use an Effort and discard this card from play to draw 3 cards.";
    public int stepCount = 0;

    public void step(PlayArea playArea, List<String> input) throws InvalidActionCountException {
        switch (stepCount) {
            case 0 -> firstStep(playArea);
            case 1 -> finalStep(playArea);
            default -> throw new InvalidActionCountException(stepCount, name);
        };
    }

    private void firstStep(PlayArea playArea) {
        playArea.playerItems.add(this);
        playArea.playerSkillsInPlay.bonusPetTrainingSkillsInPlay++;
        stepCount++;
    }

    private void finalStep(PlayArea playArea) {
        playArea.playerDeck.draw(3);
        stepCount = 0;
        playArea.playerSkillsInPlay.bonusPetTrainingSkillsInPlay--;
        playArea.playerDiscard.add(this);
        playArea.playerItems.remove(this);
    }
}
