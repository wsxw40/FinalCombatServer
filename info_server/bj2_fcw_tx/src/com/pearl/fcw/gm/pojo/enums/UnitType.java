package com.pearl.fcw.gm.pojo.enums;


/**
 * 物品消耗类型。对应sysItem的unitType
 */
public enum UnitType {
    /** 永久 */
    FOREVER(0),
    /** 基于数量 */
    QUANTITY(1),
    /** 基于时间 */
    TIME(2);

    private final int value;

    UnitType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UnitType valueOf(int value) {
        for (UnitType t : UnitType.class.getEnumConstants()) {
            if (t.getValue() == value) {
                return t;
            }
        }
        throw new IllegalArgumentException();
    }

}
