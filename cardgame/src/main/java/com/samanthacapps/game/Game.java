package com.samanthacapps.game;

import com.samanthacapps.exceptions.*;
import com.samanthacapps.game.cards.Card;
import com.samanthacapps.game.utils.Efforts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class Game {

    private final PlayArea playAreaA;
    private final PlayArea playAreaB;
    private final int id;

    public char whoseTurn;

    public Game() {
        playAreaA = new PlayArea('a');
        playAreaB = new PlayArea('b');
        
        whoseTurn = 'a';

        Random random = new Random();
        this.id = random.nextInt(1_000_000_000);
    }

    public void startGame() throws DeckWrongSizeException, NoHeroException {
        playAreaA.setUpPlayArea();
        sync(playAreaA, playAreaB);
    }

    public void effortA(List<String> input) throws SkillTypeNotPresentException, InvalidStepCountException, NotEnoughSkillException, NoCompetitionInPlayException {
        if (playAreaA.playerCardInProgress != null) {
            playAreaA.playerCardInProgress.step(playAreaA, input);
        } else if (input.get(0).equals(Efforts.HAND.toString())) {
            checkCostA(playAreaA.playerHand.cards.get(parseInt(input.get(1))));
            playAreaA.playerHand.cards.get(parseInt(input.get(1))).step(playAreaA, input);
            playAreaA.playerHand.cards.remove(parseInt(input.get(1)))
        }
        
        if (playAreaA.playerEffort > 1) {
            playAreaA.playerEffort--;
        } else if (playAreaA.needInputFromOpponent) {
            whoseTurn = 'b';
        } else {
            playAreaA.playerEffort = 2;
            whoseTurn = 'b';
        }

        playAreaA.competition.step(playAreaA, new ArrayList<>());

        sync(playAreaA, playAreaB);
    }

    private void checkCostA(Card card) throws SkillTypeNotPresentException, NotEnoughSkillException {
        switch (card.costType) {
            case SPORTS:    if (playAreaA.playerSkillsInPlay.sportsSkillsInPlay <= 0) {
                                throw new SkillTypeNotPresentException(card);
                            } else if (playAreaA.playerSkillsInPlay.sportsSkillsInPlay < card.costAmount) {
                                throw new NotEnoughSkillException(playAreaA.playerSkillsInPlay.sportsSkillsInPlay, card);
                            }
            default:        throw new SkillTypeNotPresentException(card);
        }
    }

    public void effortB(List<String> input) throws SkillTypeNotPresentException, InvalidStepCountException, NotEnoughSkillException, NoCompetitionInPlayException {
        if (playAreaB.playerCardInProgress != null) {
            playAreaB.playerCardInProgress.step(playAreaB, input);
        } else if (input.get(0).equals(Efforts.HAND.toString())) {
            checkCostB(playAreaB.playerHand.cards.get(parseInt(input.get(1))));
            playAreaB.playerHand.cards.get(parseInt(input.get(1))).step(playAreaB, input);
            playAreaB.playerHand.cards.remove(parseInt(input.get(1)))
        }

        if (playAreaB.playerEffort > 1) {
            playAreaB.playerEffort--;
        } else if (playAreaA.needInputFromOpponent) {
            whoseTurn = 'a';
        } else {
            playAreaB.playerEffort = 2;
            whoseTurn = 'a';
        }

        sync(playAreaB, playAreaA);
    }

    private void checkCostB(Card card) throws SkillTypeNotPresentException, NotEnoughSkillException {
        switch (card.costType) {
            case SPORTS:    if (playAreaB.playerSkillsInPlay.sportsSkillsInPlay <= 0) {
                throw new SkillTypeNotPresentException(card);
            } else if (playAreaB.playerSkillsInPlay.sportsSkillsInPlay < card.costAmount) {
                throw new NotEnoughSkillException(playAreaB.playerSkillsInPlay.sportsSkillsInPlay, card);
            }
            default:        throw new SkillTypeNotPresentException(card);
        }
    }

    private void sync(PlayArea updatedPlayArea, PlayArea playAreaToUpdate) {
        playAreaToUpdate.playerDeck = updatedPlayArea.opponentDeck;
        playAreaToUpdate.opponentDeck = updatedPlayArea.playerDeck;

        playAreaToUpdate.playerOtherHeroes = updatedPlayArea.opponentOtherHeroes;
        playAreaToUpdate.opponentOtherHeroes = updatedPlayArea.playerOtherHeroes;

        playAreaToUpdate.playerHand = updatedPlayArea.opponentHand;
        playAreaToUpdate.opponentHand = updatedPlayArea.playerHand;

        playAreaToUpdate.playerCardInProgress = updatedPlayArea.opponentCardInProgress;
        playAreaToUpdate.opponentCardInProgress = updatedPlayArea.playerCardInProgress;

        playAreaToUpdate.playerSkillsInPlay = updatedPlayArea.opponentSkillsInPlay;
        playAreaToUpdate.opponentSkillsInPlay = updatedPlayArea.playerSkillsInPlay;

        playAreaToUpdate.playerEffort = updatedPlayArea.opponentEffort;
        playAreaToUpdate.opponentEffort = updatedPlayArea.playerEffort;

        playAreaToUpdate.playerDiscard = updatedPlayArea.opponentDiscard;
        playAreaToUpdate.opponentDiscard = updatedPlayArea.playerDiscard;
    }
}
