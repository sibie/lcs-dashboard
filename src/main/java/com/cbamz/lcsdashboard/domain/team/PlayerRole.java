package com.cbamz.lcsdashboard.domain.team;

public enum PlayerRole {
    TOP("Top"),
    JUNGLE("Jungle"),
    MID("Mid"),
    BOT("Bot"),
    SUPPORT("Support"),
    SUB("Substitute");

    // Mapping Enums to custom display value.
    private final String displayValue;

    private PlayerRole(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

}