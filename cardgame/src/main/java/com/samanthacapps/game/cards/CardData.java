package com.samanthacapps.game.cards;

import java.util.Map;

public class CardData {

    public Map<String, Hero> heroes;

    public CardData() {
        heroes.put("Michaela", new Hero());
    }
}
