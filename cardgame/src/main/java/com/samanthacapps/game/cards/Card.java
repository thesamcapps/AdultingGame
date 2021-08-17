package com.samanthacapps.game.cards;

import com.samanthacapps.exceptions.InvalidActionCountException;
import com.samanthacapps.game.PlayArea;

import java.util.List;

public abstract class Card {

    public String name;
    public CardClassification classification;
    public CardSubtype cardSubtype;
    public CostType costType;
    public int costAmount;
    public String text;
    public int actionCount;

    public abstract void effort(PlayArea playArea, List<String> input) throws InvalidActionCountException;
}