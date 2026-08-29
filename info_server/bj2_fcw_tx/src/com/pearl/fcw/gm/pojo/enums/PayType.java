package com.pearl.fcw.gm.pojo.enums;


/**
 * 交易类型。对应payment的payType
 */
public enum PayType {
    /** 游戏币 */
    GP(1),
    /** 迅雷点 */
    XUNLEI_POINT(2),
    /** 代金券 */
    VOUCHER(3),
    /** 勋章 */
    MEDAL(4),
    /** 个人黑晶石 */
    PERSONAL_RES(5),
    /** 团队黑晶石 */
    TEAM_RES(6),
    /** 个人黑原石 */
    PERSONAL_RES_DIS(7),
    /** 个人黑原石 */
    TEAM_RES_DIS(8),
    /** 复活币 */
    REVIVE_COIN(9),
    /** VIP经验 */
    VIP_EXP(10),
    /** A碎片 */
    A_CHIP(101),
    /** B碎片 */
    B_CHIP(102),
    /** C碎片 */
    C_CHIP(103),
    /** 过期碎片 */
    OFF_CHIP(104);

    private final int value;

    PayType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PayType valueOf(int value) {
        for (PayType t : PayType.class.getEnumConstants()) {
            if (t.getValue() == value) {
                return t;
            }
        }
        throw new IllegalArgumentException();
    }

}
