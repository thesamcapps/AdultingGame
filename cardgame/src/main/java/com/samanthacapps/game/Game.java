package com.samanthacapps.game;

import com.samanthacapps.exceptions.*;
import com.samanthacapps.game.cards.Card;
import com.samanthacapps.game.utils.Efforts;

import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class Game {

    private PlayArea playAreaA;
    private PlayArea playAreaB;
    private int whoseTurn;
    private int id;

    public Game() {
        playAreaA = new PlayArea();
        playAreaB = new PlayArea();
        
        whoseTurn = 1;

        Random random = new Random();
        this.id = random.nextInt(1_000_000_000);
    }

    public void startGame() throws DeckWrongSizeException, NoHeroException {
        playAreaA.setUpPlayArea();
    }

    public void effortA(List<String> input) throws SkillTypeNotPresentException, InvalidActionCountException, NotEnoughSkillException {
        if (playAreaA.playerCardInProgress != null) {
            playAreaA.playerCardInProgress.effort(playAreaA, input);
        } else if (input.get(0) == Efforts.HAND.toString()) {
            checkCostA(playAreaA.playerHand.cards.get(parseInt(input.get(1))));
            playAreaA.playerHand.cards.get(parseInt(input.get(1))).effort(playAreaA, input);
        }
        
        if (playAreaA.playerEffort > 1) {
            playAreaA.playerEffort--;
        } else {
            playAreaA.playerEffort = 2;
            
        }
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

    private PlayArea sync(PlayArea updatedPlayArea, PlayArea playAreaToUpdate) {
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

        //TODO DISCARD
    }
}
