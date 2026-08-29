package com.pearl.fcw.gm.pojo.enums;


/**
 * 对应SysItem的iBuffId字段
 */
public enum ItemIBuffId {
    /** 升级必备 */
    UPDATE_NECESSARY(1),
    /** 招财猫 */
    FORTUNE_CAT(2),
    /** 不死之身 */
    UNKILLABLE(3),
    /** 弹药专家 */
    SPECIALIST_AMMO(4),
    /** 治疗上瘾 */
    TREATING_ADDICTION(5),
    /** 喷灌 */
    SPRAY(6),
    /** 不败神话 */
    UNFAIL_MITH(7),
    /** 死亡契约 */
    DEAD_CONTRACTION(8),
    /** 主武器弹夹 */
    MAIN_WEAPON_CLIP(9),
    /** 占点专家 */
    SPECIALIST_TAKE_OVER(11),
    /** 占点专家 */
    SPECIALIST_PUSH_CART(12),
    /** 加速复活 */
    ACCELERATE_RESCUE_HP(13),
    /** 绚烂枪火 */
    GORGEOUS_BLAZE(31),
    /** 个性名片 */
    PERSONAL_CARD(32),
    /** 提高强化概率 */
    INCREASE_UPDATE_PROBABILITY_1(33),
    /** 提高强化概率 */
    INCREASE_UPDATE_PROBABILITY_2(34),
    /** 变身隐身生化人 */
    HIDE_1(51),
    /** 变身隐身生化人 */
    HIDE_2(52),
    /** 变身隐身生化人 */
    HIDE_3(53),
    /** 变身隐身生化人 */
    HIDE_4(54),
    /** 变身隐身生化人 */
    HIDE_5(55),
    /** 解锁强化等级至 */
    UNLOCK_UPDATE_LEVEL(61),
    /** 增加强化成功率 */
    INCREASE_UPDATE_SUCCESS_PROBABILITY(62),
    /** 增加攻击力 */
    INCREASE_ATTACK(63),
    /** 增加血量 */
    INCREASE_MAX_HP(64),
    /** 增加C币加成 */
    INCREASE_C_COIN_INCOME(65),
    /** 增加经验加成 */
    INCREASE_EXP_INCOME(66),
    /** 增加弹药补给获得量 */
    INCREASE_AMMO_INCOME(67),
    /** 增加生命补给获得量 */
    INCREASE_HP_DRUG_INCOME(68),
    /** 火箭兵减少自身伤害 */
    REDUCE_SELF_INJURE_BY_ROCKET_SOLDIER(69),
    /** 重机枪手减少热枪时间 */
    SHORTEN_COOLING_TIME_BY_MACHINE_GUN_SOLDIER(70),
    /** 狙击手减少充能时间 */
    SHORTEN_COOLING_TIME_BY_SNIPE_SOLDIER(71),
    /** 突击手减少换弹时间 */
    SHORTEN_COOLING_TIME_BY_ASSAULT_SOLDIER(72),
    /** 火焰兵增加子弹上限 */
    INCREASE_MAX_AMMO_BY_FLAME_SOLDIER(73),
    /** 护士增加充能速度 */
    SHORTEN_COOLING_TIME_BY_MEDICAL_SOLDIER(74),
    /** 新手攻击buff */
    BUFF_ATTACK_FRESHMAN(81),
    /** 新手防御buff */
    BUFF_DEFENSE_FRESHMAN(82),
    /** vip强化buff */
    BUFF_STRENGTH_VIP(83),
    /** 三十天礼包(月卡) */
    CARD_MONTH(84),
    /** 天天礼 */
    GIFT_BAG_DAILY(85);

    private final int value;

    ItemIBuffId(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ItemIBuffId valueOf(int value) {
        for (ItemIBuffId t : ItemIBuffId.class.getEnumConstants()) {
            if (t.getValue() == value) {
                return t;
            }
        }
        throw new IllegalArgumentException();
    }

}
