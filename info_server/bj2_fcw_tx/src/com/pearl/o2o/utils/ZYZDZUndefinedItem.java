package com.pearl.o2o.utils;

import com.pearl.o2o.enumuration.CoustomIDS;
import com.pearl.o2o.pojo.SysItem;

public class ZYZDZUndefinedItem extends SysItem {
	public static final long serialVersionUID=131544894651989415L; 
	
	private static final int iType = 9;// 资源争夺战type
	private String displayNameCN;
	private Integer type;

	private ZYZDZUndefinedItem(int id, String displayNameCN, int type) {
		this.id = id;
		this.displayNameCN = displayNameCN;
		this.type = type;
	}

	private static final ZYZDZUndefinedItem finishTeamSpaceLevelUp = new ZYZDZUndefinedItem(
			CoustomIDS.TeamSpaceLevelUp.getId(), "加速完成资源争夺战空间", iType);

	private static final ZYZDZUndefinedItem transformStoneP = new ZYZDZUndefinedItem(
			CoustomIDS.TransformStoneP.getId(), "转换个人晶石", iType);
	private static final ZYZDZUndefinedItem transformStoneT = new ZYZDZUndefinedItem(
			CoustomIDS.TransformStoneT.getId(), "转换团队晶石", iType);

	private static final ZYZDZUndefinedItem buyStoneT = new ZYZDZUndefinedItem(
			CoustomIDS.BuyStoneT.getId(), "购买团队晶石", iType);
	
	private static final ZYZDZUndefinedItem startChallenge = new ZYZDZUndefinedItem(
			CoustomIDS.StartChallenge.getId(), "进行挑战", iType);	

	public static ZYZDZUndefinedItem getFinishTeamSpaceLevelUp() {
		return finishTeamSpaceLevelUp;
	}

	public static ZYZDZUndefinedItem getTransformStoneP() {
		return transformStoneP;
	}

	public static ZYZDZUndefinedItem getTransformStoneT() {
		return transformStoneT;
	}

	public static ZYZDZUndefinedItem getBuyStoneT() {
		return buyStoneT;
	}

	public static ZYZDZUndefinedItem getStartChallenge() {
		return startChallenge;
	}

	@Override
	public String getDisplayNameCN() {
		return displayNameCN;
	}

	@Override
	public void setDisplayNameCN(String displayNameCN) {
		this.displayNameCN = displayNameCN;
	}

	@Override
	public Integer getType() {
		return type;
	}

	@Override
	public void setType(Integer type) {
		this.type = type;
	}

}
