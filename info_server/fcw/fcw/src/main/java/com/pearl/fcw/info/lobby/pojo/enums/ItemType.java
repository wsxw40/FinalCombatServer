package com.pearl.fcw.info.lobby.pojo.enums;

public enum ItemType {

    // 1:道具 2:武器 3:防具 4:货币
    NULL(0), ITEM(1), WEAPON(2), ARMOR(3), CURRENCY(4), MONSTER(5), SKILL(6);

    private final int value;

    ItemType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ItemType valueOf(int value) {
        for (ItemType t : ItemType.class.getEnumConstants()) {
            if (t.getValue() == value) {
                return t;
            }
        }
        throw new IllegalArgumentException();
    }

    public static boolean isItem(int value) {
        if (value == WEAPON.getValue() || value == ITEM.getValue() || value == ARMOR.getValue()) {
            return true;
        }
        return false;
    }

}
