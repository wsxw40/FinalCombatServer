/**
 * 
 */
package com.pearl.o2o.pojo;

/**
 * @author lifengyang
 * 
 */
public class MeltingAward extends BaseMappingBean<PlayerMelting> {
	private static final long serialVersionUID = 8033134040743135216L;
	private byte type;
	private int totalRate;
	private int itemId;
	private transient SysItem item;
	private int unit;
	private int unitType;

	public MeltingAward(int itemId, SysItem item, int unit, int unitType) {
		this.itemId = itemId;
		this.item = item;
		this.unit = unit;
		this.unitType = unitType;
	}
	public MeltingAward(int itemId,int unit, int unitType) {
		this.itemId = itemId;
		this.unit = unit;
		this.unitType = unitType;
	}

	public MeltingAward() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SysItem getItem() {
		return item;
	}

	public void setItem(SysItem item) {
		this.item = item;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public int getTotalRate() {
		return totalRate;
	}

	public void setTotalRate(int totalRate) {
		this.totalRate = totalRate;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public int getUnitType() {
		return unitType;
	}

	public void setUnitType(int unitType) {
		this.unitType = unitType;
	}

	@Override
	public String toString() {
		return "MeltingAward [type=" + type + ", totalRate=" + totalRate + ", itemId=" + itemId + ", unit=" + unit + ", unitType=" + unitType + "]";
	}

}
