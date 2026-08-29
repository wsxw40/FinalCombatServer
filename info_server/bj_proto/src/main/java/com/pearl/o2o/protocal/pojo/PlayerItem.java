/**
 * 
 */
package com.pearl.o2o.protocal.pojo;

/**
 * @author lifengyang
 *
 */
public class PlayerItem {
	private byte iId;
	private byte iBuffId;
	private float iValue;
	
	public PlayerItem(byte iId, byte iBuffId, float iValue) {
		super();
		this.iId = iId;
		this.iBuffId = iBuffId;
		this.iValue = iValue;
	}
	public PlayerItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public byte getiId() {
		return iId;
	}
	public void setiId(byte iId) {
		this.iId = iId;
	}
	public byte getiBuffId() {
		return iBuffId;
	}
	public void setiBuffId(byte iBuffId) {
		this.iBuffId = iBuffId;
	}
	public float getiValue() {
		return iValue;
	}
	public void setiValue(float iValue) {
		this.iValue = iValue;
	}
	
}
