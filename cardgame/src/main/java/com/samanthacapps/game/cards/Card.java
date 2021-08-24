package com.samanthacapps.game.cards;

import com.samanthacapps.exceptions.*;
import com.samanthacapps.game.PlayArea;

import java.util.List;

public abstract class Card {

    public String name;
    public CardClassification classification;
    public CardSubtype cardSubtype;
    public CostType costType;
    public int costAmount;
    public String primaryText;
    public String followUpText;
    public int stepCount;

    public abstract void step(PlayArea playArea, List<String> input) throws InvalidStepCountException, NoCompetitionInPlayException, NotEnoughCardsInHandException, CardDoesNotExistException, NotEnoughCardsException, TooManyCardsSpecifiedException;
}