package com.pearl.o2o.pojo;

import java.io.Serializable;


public class ItemMod implements Serializable {
	private static final long serialVersionUID = -8991577131279896467L;
	
	
	private Integer 	parentItemId;
	private	Integer		childItemId;
	private Integer		seq;
	
	/**
	 * @return the parentItemId
	 */
	public Integer getParentItemId() {
		return parentItemId;
	}
	/**
	 * @param parentItemId the parentItemId to set
	 */
	public void setParentItemId(Integer parentItemId) {
		this.parentItemId = parentItemId;
	}
	/**
	 * @return the childItemId
	 */
	public Integer getChildItemId() {
		return childItemId;
	}
	/**
	 * @param childItemId the childItemId to set
	 */
	public void setChildItemId(Integer childItemId) {
		this.childItemId = childItemId;
	}
	/**
	 * @return the seq
	 */
	public Integer getSeq() {
		return seq;
	}
	/**
	 * @param seq the seq to set
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
}
