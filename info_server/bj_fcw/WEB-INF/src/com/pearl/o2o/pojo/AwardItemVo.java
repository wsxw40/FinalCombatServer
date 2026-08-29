/**
 * 
 */
package com.pearl.o2o.pojo;

/**
 * @author lifengyang
 *
 */
public class AwardItemVo {
	private int vip;
	private SysItem item;
	private int itemNum;
	public AwardItemVo(int vip, SysItem item, int itemNum) {
		super();
		this.vip = vip;
		this.item = item;
		this.itemNum = itemNum;
	}
	public int getVip() {
		return vip;
	}
	public void setVip(int vip) {
		this.vip = vip;
	}
	public SysItem getItem() {
		return item;
	}
	public void setItem(SysItem item) {
		this.item = item;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	@Override
	public String toString() {
		return "AwardItemVo [vip=" + vip + ", item=" + item + ", itemNum=" + itemNum + "]";
	}
	
}
