package com.pearl.fcw.info.lobby.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.pearl.fcw.info.core.persistence.BaseShardingEntity;
import com.pearl.fcw.info.core.persistence.config.annotation.CountPerGroup;
import com.pearl.fcw.info.core.persistence.config.annotation.NoIndex;
import com.pearl.fcw.info.core.persistence.config.annotation.Schema;
import com.pearl.fcw.info.lobby.pojo.enums.UnitType;

@Entity
@Schema(GoSchema.EXT)
@CountPerGroup(GoCountPerGroup.LARGE)
public class PlayerItem extends BasePojo implements PlayerStorage, BaseShardingEntity {

    private static final long serialVersionUID = 5722696199655859113L;

    @Id
    @GeneratedValue
    private long id;
    private long playerId;
    private long sysItemId;
    private int seq;
    private int itemType;
    private boolean isStorage;
    private int unitType = UnitType.UNIT_BASE.getValue();
    private int unit = 0;
    private boolean isDefault = false;
    private boolean isEquipped = false; // 是否正被装备
    private boolean canEquip = false;
    private boolean canUse = false;
    private int level; // 升级
    private int rank;// 升阶
    private int star; // 星级
    private int grade; // 进化等级
    private int life;
    private int userCount; // 使用次数

    private float critical;// 暴击
    private float criticalResistance; // 暴击抗性
    private float penetration; // 穿透
    private float defense; // 防御
    private float baseDamage; // 基础伤害，火力，每秒伤害
    private float disrupt; // 破防值
    protected float armour; // 装甲
    protected float shield; // 护盾

    @Transient
    private String uuid = "";

    @ManyToOne
    private Player player = null;
    @ManyToOne
    @NoIndex
    private SysItem sysItem = null;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getPlayerId() {
        return playerId;
    }

    @Override
    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    @Override
    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public boolean getIsStorage() {
        return isStorage;
    }

    @Override
    public void setIsStorage(boolean isStorage) {
        this.isStorage = isStorage;
    }

    public long getSysItemId() {
        return sysItemId;
    }

    public void setSysItemId(long sysItemId) {
        this.sysItemId = sysItemId;
    }

    @Override
    public int getUnitType() {
        return unitType;
    }

    public void setUnitType(int unitType) {
        this.unitType = unitType;
    }

    @Override
    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public boolean getIsEquipped() {
        return isEquipped;
    }

    public void setIsEquipped(boolean isEquipped) {
        this.isEquipped = isEquipped;
    }

    public boolean getCanEquip() {
        return canEquip;
    }

    public void setCanEquip(boolean canEquip) {
        this.canEquip = canEquip;
    }

    public boolean getCanUse() {
        return canUse;
    }

    public void setCanUse(boolean canUse) {
        this.canUse = canUse;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public SysItem getSysItem() {
        return sysItem;
    }

    public void setSysItem(SysItem sysItem) {
        this.sysItem = sysItem;
    }

    public void setStorage(boolean isStorage) {
        this.isStorage = isStorage;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public void setEquipped(boolean isEquipped) {
        this.isEquipped = isEquipped;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public float getCritical() {
        return critical;
    }

    public void setCritical(float critical) {
        this.critical = critical;
    }

    public float getCriticalResistance() {
        return criticalResistance;
    }

    public void setCriticalResistance(float criticalResistance) {
        this.criticalResistance = criticalResistance;
    }

    public float getPenetration() {
        return penetration;
    }

    public void setPenetration(float penetration) {
        this.penetration = penetration;
    }

    public float getDefense() {
        return defense;
    }

    public void setDefense(float defense) {
        this.defense = defense;
    }

    public float getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(float baseDamage) {
        this.baseDamage = baseDamage;
    }

    public float getDisrupt() {
        return disrupt;
    }

    public void setDisrupt(float disrupt) {
        this.disrupt = disrupt;
    }

    public float getArmour() {
        return armour;
    }

    public void setArmour(float armour) {
        this.armour = armour;
    }

    public float getShield() {
        return shield;
    }

    public void setShield(float shield) {
        this.shield = shield;
    }

    @Override
    public int getQuantity() {
        return UnitType.UNIT_BASE.getValue() == unitType ? unit : 1;
    }

    public void setQuantity(int quantity) {
        if (UnitType.UNIT_BASE.getValue() != unitType) {
            throw new RuntimeException();
        }
        unit = quantity;
    }

    public void addQuantity(int count) {

        if (UnitType.UNIT_BASE.getValue() != unitType) {
            throw new RuntimeException();
        }

        unit += count;

        // 物品移空时，该位置的物品信息置空
        if (unit < 0) {
            unit = 0;
        }
    }

    public int getMaxQuantity() {
        return sysItem.getMaxQuantity();    // 不判断，不正确的使用直接NullPoint
    }

    public boolean merge(PlayerItem pi) {

        if (UnitType.UNIT_BASE.getValue() != this.unitType || pi == null || !this.equals(pi)) {
            return false;
        }

        int count = this.getMaxQuantity() - this.getQuantity();
        count = pi.getQuantity() < count ? pi.getQuantity() : count;

        if (count <= 0) {
            return false;
        }

        this.addQuantity(count);
        pi.addQuantity(-count);

        return true;

    }

    public void setEmpty() {
        this.unit = 0;
    }

    public boolean isEmpty() {
        return getQuantity() <= 0;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (!(o instanceof PlayerItem)) {
            return false;
        }

        PlayerItem p = (PlayerItem) o;

        if (id != 0 && id == p.getId()) {
            return true;
        }

        if (playerId != p.getPlayerId()) {
            return false;
        }
        if (sysItemId != p.getSysItemId()) {
            return false;
        }
        if (unitType != p.getUnitType()) {
            return false;
        }
        if (isDefault != p.getIsDefault()) {
            return false;
        }
        if (isDeleted != p.getIsDeleted()) {
            return false;
        }
        if (canEquip != p.getCanEquip()) {
            return false;
        }
        if (canUse != p.getCanUse()) {
            return false;
        }
        if (unitType != UnitType.UNIT_BASE.getValue() && unit != p.getUnit()) {
            return false;
        }

        return true;

    }

    @Override
    public long getShardingKey() {
        return playerId;
    }

    /**
     * 创建新的物品
     * @param playerId 角色id
     * @return PlayerItem
     */
    public static PlayerItem createNew(long playerId, SysItem sysItem, int unit) {
        PlayerItem item = createNew(playerId, sysItem.getId(), sysItem.getType(), UnitType.UNIT_BASE.getValue(), unit, 0, sysItem.getLevel(), true);
        item.setSysItem(sysItem);
        return item;
    }

    /**
     * 创建新的物品
     * @param playerId
     * @param sysItemId
     * @param itemType
     * @param unitType
     * @param unit
     * @param grade
     * @return PlayerItem
     */
    public static PlayerItem createNew(long playerId, long sysItemId, int itemType, int unitType, int unit, int grade, int level, boolean isStorage) {
        PlayerItem pi = new PlayerItem();

        pi.setPlayerId(playerId);
        pi.setSysItemId(sysItemId);
        pi.setCanEquip(true);
        pi.setItemType(itemType);
        pi.setUnitType(unitType);
        pi.setUnit(unit);
        pi.setCanUse(true);
        pi.setIsDefault(false);
        pi.setGrade(grade);
        pi.setLevel(level);
        pi.setIsStorage(isStorage);

        return pi;
    }

}
