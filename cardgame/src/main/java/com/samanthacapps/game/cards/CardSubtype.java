package com.samanthacapps.game.cards;

public enum CardSubtype {

    YOUNG_PROFESSIONAL("young professional"),
    BOOK("book"),
    NONE("none");

    private final String value;

    CardSubtype(String name) { this.value = name; }

    public String toString() {
        return value;
    }

}
