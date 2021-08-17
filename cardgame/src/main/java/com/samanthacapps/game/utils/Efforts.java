package com.samanthacapps.game.utils;

public enum Efforts {
    HAND("hand"),
    HERO_EFFORT("hero-effort"),
    ITEM_EFFORT("item-effort"),
    WEAPON_ATTACK("weapon-effort");

    private final String value;

    Efforts(String name) { this.value = name; }

    public String toString() {
        return value;
    }
}
