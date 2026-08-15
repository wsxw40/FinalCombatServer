package com.pearl.fcw.core.pojo;

/**
 * 表类型
 */
public enum Schema {
    /**
     * 系统
     */
    SYS(1),
    /**
     * 主逻辑
     */
    MAIN(2),
    /**
     * 副逻辑分区
     */
    EXT(3),
    /**
     * GM
     */
    GM(4),
    /**
     * 嵌套，在其他表的字段内
     */
    NEST(5);

    private final int number;

    Schema(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static Schema valueOf(int value) {
        for (Schema t : Schema.class.getEnumConstants()) {
            if (t.getNumber() == value) {
                return t;
            }
        }
        throw new IllegalArgumentException();
    }
}
