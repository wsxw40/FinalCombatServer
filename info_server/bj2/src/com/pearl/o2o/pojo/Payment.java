package com.pearl.o2o.pojo;

import com.pearl.o2o.utils.Constants;

public class Payment extends BaseMappingBean<Payment> {
	private static final long serialVersionUID = -6238441255585643881L;
	private int forever = -1;
	public int itemId;
	public int payType;// 1 c币 ， 2 FC点 ,5个人黑晶石 ,6团队黑晶石  9 货币直接
	public int unitType;// 1 个数 ， 2 时间, 3 金币,0 永久
	public int cost;
	public int unit;// 数量/时限
	public int isShow = 1;
	public String priceStr;
	public int isChange = 1;
	private int level;
	private int finishPayType;// 1 c币 ， 2 FC点 ,5个人黑晶石 ,6团队黑晶石  9 货币直接
	private int finishCost;

	public void init() {
		switch (payType) {
		case 1:// gp
			if (unitType == 0) {
				priceStr = cost + Constants.DELIMITER_RESOURCE_STABLE + forever;
			} else if (unitType == 1) {
				priceStr = cost + Constants.DELIMITER_RESOURCE_STABLE + unit;
			} else if (unitType == 2) {
				priceStr = cost + Constants.DELIMITER_RESOURCE_STABLE + unit;
			}
			break;
		case 2:// cr
			if (unitType == 0) {
				priceStr = cost + Constants.DELIMITER_RESOURCE_STABLE + unit;
			} else if (unitType == 1) {
				priceStr = cost + Constants.DELIMITER_RESOURCE_STABLE + unit;
			} else if (unitType == 2) {
				priceStr = cost + Constants.DELIMITER_RESOURCE_STABLE + unit;
			}
			break;
		case 3:// voucher
			if (unitType == 0) {
				priceStr = cost + Constants.DELIMITER_RESOURCE_STABLE + unit;
			} else if (unitType == 1) {
				priceStr = cost + Constants.DELIMITER_RESOURCE_STABLE + unit;
			} else if (unitType == 2) {
				priceStr = cost + Constants.DELIMITER_RESOURCE_STABLE + unit;
			}
			break;
		case 5:// 使用个人黑晶石进行付费
			if (unitType == 0) {
				priceStr = cost + Constants.DELIMITER_RESOURCE_STABLE + forever;
			} else if (unitType == 1) {
				priceStr = cost + Constants.DELIMITER_RESOURCE_STABLE + unit;
			} else if (unitType == 2) {
				priceStr = cost + Constants.DELIMITER_RESOURCE_STABLE + unit;
			}
			break;
		case 6:// 使用团队黑晶石进行付费
			if (unitType == 0) {
				priceStr = cost + Constants.DELIMITER_RESOURCE_STABLE + forever;
			} else if (unitType == 1) {
				priceStr = cost + Constants.DELIMITER_RESOURCE_STABLE + unit;
			} else if (unitType == 2) {
				priceStr = cost + Constants.DELIMITER_RESOURCE_STABLE + unit;
			}
			break;
		default:
			break;
		}
	}

	public Payment() {
	}
	
	/**空的payment*/
	private static final Payment defNullPayment=new Payment();
	public static final Payment getDEFNullPayment(){
		return defNullPayment;
	}

	public Payment(int payType, int unitType, int cost, int unit) {
		super();
		this.payType = payType;
		this.unitType = unitType;
		this.cost = cost;
		this.unit = unit;
	}

	public Payment(int unit, int unitType) {
		this.unit = unit;
		this.unitType = unitType;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public int getUnitType() {
		return unitType;
	}

	public void setUnitType(int unitType) {
		this.unitType = unitType;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public String getPriceStr() {
		return priceStr;
	}

	public void setPriceStr(String priceStr) {
		this.priceStr = priceStr;
	}

	public int getIsChange() {
		return isChange;
	}

	public void setIsChange(int isChange) {
		this.isChange = isChange;
	}

	public int getIsShow() {
		return isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getFinishPayType() {
		return finishPayType;
	}

	public void setFinishPayType(int finishPayType) {
		this.finishPayType = finishPayType;
	}

	public int getFinishCost() {
		return finishCost;
	}

	public void setFinishCost(int finishCost) {
		this.finishCost = finishCost;
	}
	
	@Override
	public Payment clone() throws CloneNotSupportedException {
		return (Payment) super.clone();
	}
	
}
