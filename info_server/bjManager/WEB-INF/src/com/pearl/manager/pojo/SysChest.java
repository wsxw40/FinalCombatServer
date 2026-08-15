package com.pearl.manager.pojo;

/**
 * @author wuxiaofei
 *
 */
public class SysChest extends BaseMappingBean<SysChest> implements Comparable<SysChest>{

	private static final long serialVersionUID = -2471157768356744004L;
	
	private int type;//1:default item  2:random item where type=1, it's a sysChest
	private int level;//1,2,3,4
	private int sysItemId;
	private int weight;//用于直接购买的权重
	private int weight1;//用于勋章兑换的权重
	private int number;
	private int useType;//1:time 2:number
	private int isNotice;//0:not notice 1:notice
	private int price;//FC point price
	private int chipPrice;
	private int isDeleted;
	private String sysItemName;
	private SysItem sysItem;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getSysItemId() {
		return sysItemId;
	}
	public void setSysItemId(int sysItemId) {
		this.sysItemId = sysItemId;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getWeight1() {
		return weight1;
	}
	public void setWeight1(int weight1) {
		this.weight1 = weight1;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getUseType() {
		return useType;
	}
	public void setUseType(int useType) {
		this.useType = useType;
	}
	public int getIsNotice() {
		return isNotice;
	}
	public void setIsNotice(int isNotice) {
		this.isNotice = isNotice;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getChipPrice() {
		return chipPrice;
	}
	public void setChipPrice(int chipPrice) {
		this.chipPrice = chipPrice;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(SysChest o) {
		return this.id.compareTo(o.getId());
	}
	
	public String getSysItemName() {
		return sysItemName;
	}
	public void setSysItemName(String sysItemName) {
		this.sysItemName = sysItemName;
	}
	public SysItem getSysItem() {
		return sysItem;
	}
	public void setSysItem(SysItem sysItem) {
		this.sysItem = sysItem;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
