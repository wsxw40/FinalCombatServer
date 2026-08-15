package com.pearl.fcw.gm.pojo.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 对应SysItem的subType字段
 */
public class ItemSubType {
    public static Map<Integer, List<Integer>> values() {
        Map<Integer, List<Integer>> map=new HashMap<>();
        for(ItemType p:ItemType.values()){
            List<Integer> list = new ArrayList<>();
            switch (p) {
            case WEAPON:
                list = Stream.of(Weapon.values()).map(Weapon::getValue).collect(Collectors.toList());
                break;
            case CHARACTER:
                list = Stream.of(Character.values()).map(Character::getValue).collect(Collectors.toList());
                break;
            case PART:
                list = Stream.of(Part.values()).map(Part::getValue).collect(Collectors.toList());
                break;
            case ITEM:
                list = Stream.of(Item.values()).map(Item::getValue).collect(Collectors.toList());
                break;
            case MATERIAL:
                list = Stream.of(Material.values()).map(Material::getValue).collect(Collectors.toList());
                break;
            case BATTLE_FOR_RESOURCE:
                list = Stream.of(BattleForResource.values()).map(BattleForResource::getValue).collect(Collectors.toList());
                break;
            default:
                break;
            }
            map.put(p.getValue(), list);
        }
        return map;
    }

    /**
     * 父类型为@ItemType.WEAPON
     */
    public enum Weapon{
        /** 主武器 */
        MAIN(1),
        /** 副武器 */
        SUB(2),
        /** 近战 */
        CLOSE(3),
        /** 特殊 */
        SPECIAL(4);

        private final int value;

        Weapon(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Weapon valueOf(int value) {
            for (Weapon t : Weapon.class.getEnumConstants()) {
                if (t.getValue() == value) {
                    return t;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    /**
     * 父类型为@ItemType.CHARACTER
     */
    public enum Character {
        /** 套装 */
        SUIT(1);

        private final int value;

        Character(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Character valueOf(int value) {
            for (Character t : Character.class.getEnumConstants()) {
                if (t.getValue() == value) {
                    return t;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    /**
     * 父类型为@ItemType.PART
     */
    public enum Part {
        /** 帽子 */
        HAT(1),
        /** 饰品 */
        ADORNMENT(2);

        private final int value;

        Part(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Part valueOf(int value) {
            for (Part t : Part.class.getEnumConstants()) {
                if (t.getValue() == value) {
                    return t;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    /**
     * 父类型为@ItemType.ITEM
     */
    public enum Item {
        /** 加成 */
        ADDTION(1),
        /** 卡片 */
        CARD(2),
        /** 喷灌 */
        SPRAY(3),
        /** 功能 */
        FUNCTION(4),
        /** 非卖品 */
        NOT_FOR_SALE(5),
        /** 礼盒非卖品 */
        BOX_NOT_FOR_SALE(6),
        /** 消耗品 */
        CONSUME(7);

        private final int value;

        Item(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Item valueOf(int value) {
            for (Item t : Item.class.getEnumConstants()) {
                if (t.getValue() == value) {
                    return t;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    /**
     * 父类型为@ItemType.MATERIAL
     */
    public enum Material {
        /** 合成道具 */
        MATERIAL(1),
        /** 蓝图 */
        BLUE_PRINT(2);

        private final int value;

        Material(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Material valueOf(int value) {
            for (Material t : Material.class.getEnumConstants()) {
                if (t.getValue() == value) {
                    return t;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    /**
     * 父类型为@ItemType.BATTLE_FOR_RESOURCE
     */
    public enum BattleForResource {
        /** 城墙类 */
        WALL(1),
        /** 塔类 */
        TOWER(2),
        /** 个人消耗 */
        PERSONAL_CONSUME(3),
        /** 团队消耗 */
        TEAM_CONSUME(4),
        /** 系统公共资源 */
        PUBLIC_RESOURCE(5),
        /** 个人技能 */
        PERSONAL_SKILL(6);

        private final int value;

        BattleForResource(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static BattleForResource valueOf(int value) {
            for (BattleForResource t : BattleForResource.class.getEnumConstants()) {
                if (t.getValue() == value) {
                    return t;
                }
            }
            throw new IllegalArgumentException();
        }
    }
}
