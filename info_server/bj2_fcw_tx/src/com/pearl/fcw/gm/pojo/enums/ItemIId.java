package com.pearl.fcw.gm.pojo.enums;


/**
 * 对应SysItem的iId字段
 */
public enum ItemIId {
    /** Buff类 */
    BUFF(1),
    /** 小喇叭 */
    TRUMPET(2),
    /** 大喇叭 */
    TYPHON(3),
    /** 改头换面 */
    CHANGE_APPEARANCE(4),
    /** 战绩清零卡 */
    CARD_CLEAR_BATTLE_RECORD(5),
    /** 大家一起来升级 */
    EVERYONE_UPDATE(6),
    /** 有钱同赚 */
    EVERYONE_MAKE_MONEY(7),
    /** 游戏币道具 */
    ITEM_GAME_COIN(8),
    /** 扩充战队 */
    EXPAND_TEAM(9),
    /** 职业解锁道具 */
    ITEM_UNLOCK_CHARACTER(10),
    /** 死亡清零卡 */
    CARD_CLEAR_DEAD(11),
    /** 逃跑清零卡 */
    CARD_CLEAR_ESCAPE(12),
    /** 战队清零卡 */
    CARD_CLEAR_TEAM(13),
    /** 创建战队卡 */
    CARD_CREATE_TEAM(14),
    /** VIP */
    VIP(15),
    /** 战队更名卡 */
    CARD_CHANGE_TEAM_NAME(16),
    /** 勋章 */
    MEDAL(17),
    /** 锦囊,VIP箱子(生存模式) */
    BOX_VIP(18),
    /** 签到礼盒 */
    GIFT_BOX_SIGNIN(19),
    /** VIP保险柜 */
    SAFE_BOX_VIP(20),
    /** 经验类道具 */
    ITEM_EXP(21),
    /** 魔罐 */
    MAGIC_JAR(22),
    /** 等级礼盒 */
    GIFT_BOX_LEVEL(23),
    /** 隔天礼盒 */
    GIFT_BOX_PER_TOW_DAYS(24),
    /** 进阶礼盒 */
    GIFT_BOX_UPDATE_RANK(25),
    /** 多选一礼盒 */
    GIFT_CHECK_BOX(26),
    /** 好友礼盒 */
    GIFT_BOX_FRIEND(27),
    /** 复活币 */
    REVIVE_COIN(28),
    /** 密码卡 */
    CARD_PASSWORD(29),
    /** 幸运彩盒卡 */
    CARD_LUCKY_CHROMATIC_BOX(30),
    /** 靶场子弹 */
    BULLET_SHOOTING_RANGE(31),
    /** 偷看道具 */
    ITEM_PEER(32),
    /** 加连射 */
    ADD_CONTINUOUS_SHOOTING(33),
    /** 空靶 */
    MISS_TARGET(34),
    /** 下次射击不消耗子弹 */
    UNCONSUME_BULLET_NEXT_TIME(35),
    /** 商城大礼包 */
    GIFT_BAG_SHOP(36),
    /** 福利石头 */
    WEAL_STONE(37),
    /** VIP经验块 */
    VIP_EXP_STONE(38),
    /** 加血道具 */
    ITEM_INCREASE_MAX_HP(39),
    /** 弹药道具 */
    ITEM_AMMO(40),
    /** 坦克道具 */
    ITEM_TANK(41),
    /** 死亡奖励 */
    PRIZE_FOR_DEAD(42),
    /** 资源争夺战攻击BUFF */
    BUFF_ATTACK_FOR_RESOURCE_BATTLE(43),
    /** 资源争夺战攻速BUFF */
    BUFF_ATTACK_SPEED_FOR_RESOURCE_BATTLE(44),
    /** 资源争夺战抗性BUFF */
    BUFF_RESISTANCE_FOR_RESOURCE_BATTLE(46),
    /** 资源争夺战移动BUFF */
    BUFF_MOVE_SPEED_FOR_RESOURCE_BATTLE(47),
    /** 资源争夺战兑换宝箱 */
    BUFF_EXCHANGE_BOX_FOR_RESOURCE_BATTLE(48),
    /** 超级能量块 */
    SUPER_ENERGY_STONE_1(49),
    /** 超级能量块 */
    SUPER_ENERGY_STONE_2(50),
    /** 超级能量块 */
    SUPER_ENERGY_STONE_3(51),
    /** 超级能量块 */
    SUPER_ENERGY_STONE_4(52),
    /** 超级能量块 */
    SUPER_ENERGY_STONE_5(53),
    /** 超级能量块 */
    SUPER_ENERGY_STONE_6(54),
    /** 超级能量块 */
    SUPER_ENERGY_STONE_7(55),
    /** 超级能量块 */
    SUPER_ENERGY_STONE_8(56),
    /** 超级能量块 */
    SUPER_ENERGY_STONE_9(57),
    /** 圣诞跳跳乐箱子 */
    BOX_CHRISMAS_JUMP(58),
    /** 新手升级礼包 */
    GIFT_BAG_UPDATE_FOR_FRESHMAN(59),
    /** 老玩家回流礼包 */
    GIFT_BAG_COMEBACK_FOR_VETERAN(60),
    /** 限时装备的多选一礼包(默认送3天时间) */
    GIFT_CHECK_BOX_LIMIT_TIME(61),
    /** 暑假礼包（礼包内包含多样物品） */
    GIFT_BOX_FOR_SUMMER(62);

    private final int value;

    ItemIId(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ItemIId valueOf(int value) {
        for (ItemIId t : ItemIId.class.getEnumConstants()) {
            if (t.getValue() == value) {
                return t;
            }
        }
        throw new IllegalArgumentException();
    }

}
