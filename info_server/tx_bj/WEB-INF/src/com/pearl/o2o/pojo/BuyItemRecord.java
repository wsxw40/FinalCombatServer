package com.pearl.o2o.pojo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.DBRouteUtil;


public class BuyItemRecord extends BaseMappingBean<BuyItemRecord> {
	private static final long serialVersionUID = 703320768855382578L;
	private int playerId;
	private int itemId;
	private int costId;
	private int record;	//当前 当日已购买几次
	private int payType;//最后一次付款货币种类
	private int payAmount;//最后一次付款额度
	private Date createTime;
	private Date lastBuyDate; //最后一次购买日期
	private String createTimeStr;
	private String lastBuyDateStr;
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
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
	public int getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(int payAmount) {
		this.payAmount = payAmount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(createTime);
		this.createTime = createTime;
	}
	public int getRecord() {
		if(!CacheUtil.isToday(lastBuyDate.getTime())){
			this.record=0;
			this.lastBuyDate=new Date();
		}
		return record;
	}
	public void setRecord(int record) {
		this.record = record;
	}

	public int getCostId() {
		return costId;
	}

	public void setCostId(int costId) {
		this.costId = costId;
	}

	public Date getLastBuyDate() {
		return lastBuyDate;
	}

	public void setLastBuyDate(Date lastBuyDate) {
		this.lastBuyDateStr=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(lastBuyDate);
		this.lastBuyDate = lastBuyDate;
	}

	public String getLastBuyDateStr() {
		return lastBuyDateStr;
	}

	public void setLastBuyDateStr(String lastBuyDateStr) {
		this.lastBuyDateStr = lastBuyDateStr;
	}

	@Override
	public String getTableSuffix() {
		return DBRouteUtil.getTableSuffix(BuyItemRecord.class, playerId);
	}

}
