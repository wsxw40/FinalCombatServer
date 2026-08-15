package com.pearl.o2o.utils;


public class SysItemVariationMagicBox extends SysItemVariation {
	public SysItemVariationMagicBox(int id, int baseCost, int count,
			String iValue,int iid) {
		this.id = id;
		this.baseCost = baseCost;
		this.count = count;
		this.iValue = iValue;
		this.iid=iid;
	}


	@Override
	public int equationResult(int count) {
		return CommonUtil.getCostForBuyRecord(count, baseCost, iid);
	}

	@Override
	public int getThisCalCost() {
		return equationResult(count);
	}

	@Override
	public int getNextCalCost() {
		return equationResult(count + 1);
	}

}
