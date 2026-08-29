package com.pearl.fcw.gm.pojo.enums;


/**
 * 对应SysItem的type字段
 */
public enum ItemType {
    /** 武器 */
    WEAPON(1),
    /** 角色 */
    CHARACTER(2),
    /** 配饰 */
    PART(3),
    /** 道具 */
    ITEM(4),
    /** 素材 */
    MATERIAL(5),
    /** 蓝图 */
    BLUEPRINT(6),
    /** 大礼包 */
    PACKAGE(7),
    /** 打开类 */
    OPEN(8),
    /** 资源争夺战类 */
    BATTLE_FOR_RESOURCE(9),
    /** 推荐类，只在商城分类中使用，不作为实际的类型 */
    POPULAR(10);

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

}
