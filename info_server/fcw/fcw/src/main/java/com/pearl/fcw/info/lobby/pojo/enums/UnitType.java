package com.pearl.fcw.info.lobby.pojo.enums;

public enum UnitType {

    PERMANENT(1),   // 永久
    COST_BASE(2),   // 耐久度
    UNIT_BASE(3),   // 基于个数
    TIME_BASE(4),   // 基于时间
    ROUND_BASE(5);  // 基于场次

    private final int value;

    UnitType(int value){
        this.value = value;
    }

    public int getValue(){
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
