package com.pearl.fcw.info.lobby.pojo.enums;

public enum PlayerState {

    OFFLINE(1), ONLINE(2), PLAY(3);

    private final int value;

    PlayerState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
