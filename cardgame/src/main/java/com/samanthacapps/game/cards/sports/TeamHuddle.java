package com.samanthacapps.game.cards.sports;

import com.samanthacapps.exceptions.InvalidStepCountException;
import com.samanthacapps.exceptions.NotEnoughCardsException;
import com.samanthacapps.exceptions.NotEnoughCardsInHandException;
import com.samanthacapps.exceptions.TooManyCardsSpecifiedException;
import com.samanthacapps.game.PlayArea;
import com.samanthacapps.game.cards.Card;
import com.samanthacapps.game.cards.CardClassification;
import com.samanthacapps.game.cards.CardSubtype;
import com.samanthacapps.game.cards.CostType;
import com.samanthacapps.game.cards.skills.Sports;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class TeamHuddle extends Card {

    public String name = "Team Huddle";
    public CardClassification classification = CardClassification.ACTIVITY;
    public CardSubtype cardSubtype = CardSubtype.NONE;
    public CostType costType = CostType.SPORTS;
    public final int costAmount = 3;
    public String primaryText = "You may put up to 3 Sports Skills from your hand into play.";
    public String followUpText = "Type 'do' followed by how many Sports Skills you want to put into play.";
    public int stepCount = 0;

    public void step(PlayArea playArea, List<String> input) throws NotEnoughCardsInHandException, TooManyCardsSpecifiedException, InvalidStepCountException {
        switch (stepCount) {
            case 0 -> firstStep(playArea);
            case 1 -> finalStep(playArea, input);
            default -> throw new InvalidStepCountException(stepCount, name);
        };
    }

    private void firstStep(PlayArea playArea) throws NotEnoughCardsInHandException {
        List<Card> sportsSkillsInHand = playArea.playerHand.cards.stream().filter(card -> card.name == "Sports Skill").collect(Collectors.toList());

        if (sportsSkillsInHand.size() == 0) {
            throw new NotEnoughCardsInHandException(3, 0, "Sports Skills");
        } else if (sportsSkillsInHand.size() == 1) {
            playArea.playerHand.cards.remove(sportsSkillsInHand.get(0));
            playArea.playerSkillsInPlay.sportsSkillsInPlay++;
        } else {
            playArea.playerCardsAwaitingAction = sportsSkillsInHand;
            stepCount++;
            playArea.playerCardInProgress = this;
        }
    }

    private void finalStep(PlayArea playArea, List<String> input) throws TooManyCardsSpecifiedException {
        int limit = Math.min(3, playArea.playerCardsAwaitingAction.size());

        if (parseInt(input.get(1)) > limit) {
            throw new TooManyCardsSpecifiedException(limit, parseInt(input.get(1)));
        }

        for (var i = 0; i < parseInt(input.get(1)); i++) {
            playArea.playerHand.cards.remove(playArea.playerCardsAwaitingAction.get(0));
            playArea.playerSkillsInPlay.sportsSkillsInPlay++;
            stepCount = 0;
            playArea.playerCardInProgress = null;
            playArea.playerCardsAwaitingAction = new ArrayList<>();
        }
    }
}
