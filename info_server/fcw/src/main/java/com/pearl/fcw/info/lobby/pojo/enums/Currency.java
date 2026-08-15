package com.pearl.fcw.info.lobby.pojo.enums;

public enum Currency {

    // 金币，钻石，
    GP(6), DIAMOND(7);

    private final int value;

    Currency(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Currency valueOf(int value) {
        for (Currency t : Currency.class.getEnumConstants()) {
            if (t.getValue() == value) {
                return t;
            }
        }
        throw new IllegalArgumentException();
    }
}
