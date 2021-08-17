package com.samanthacapps.game.cards;

public enum CostType {
    BRAINS("brains"),
    SPORTS("sports"),
    NONE("none");

    private final String value;

    CostType(String name) { this.value = name; }

    public String toString() {
        return value;
    }
}
