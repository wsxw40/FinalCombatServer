package com.pearl.o2o.service.onbuy;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.service.NosqlService;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;

public class DailyDiscountBuy implements PlayerBuyWay {
	private Player player;
	private SysItem sysItem;
	private int index;
	private StringBuffer check;
	public DailyDiscountBuy() {
	}
	
	public DailyDiscountBuy(StringBuffer check,Player player, SysItem sysItem,int index) {
		this.player = player;
		this.sysItem = sysItem;
		this.index = index;
		this.check=check;
	}

	@Override
	public int buy() throws Exception {
		Payment payment = new Payment();
		payment.setUnitType(Constants.DEFAULT_NUMBASE_TYPE);
		payment.setUnit(1);
		Payment onePrice = null;
		if(null != sysItem.getAllCrPricesList() && !sysItem.getAllCrPricesList().isEmpty()){
			onePrice = sysItem.getAllCrPricesList().get(0);
		}else if(null != sysItem.getAllGpPricesList() && !sysItem.getAllGpPricesList().isEmpty()){
			onePrice = sysItem.getAllGpPricesList().get(0);
		}	
		if(onePrice == null){
			throw new BaseException(ExceptionMessage.NO_SINGLE_PRICE);
		}
		//判断是否有资格购买
		payment.setCost((int)(onePrice.getCost()*getPlayerDiscountByItemId(player.getId(), sysItem.getId(),index)));
		ServiceLocator.createService.buyPlayerItemByNewPayment(check,sysItem, player, payment);
		ServiceLocator.nosqlService.addPlayerDailyDiscountBuyFlag(player.getId(), sysItem.getId(), index);
		return SUCCESS;
	}

	
	private float getPlayerDiscountByItemId(int playerId,int sysItemId,int index) throws Exception{
		NosqlService nosql = ServiceLocator.nosqlService;
		int flagNum = nosql.getPlayerDailyDiscountBuyFlag(playerId, sysItemId);
		//判断是否有资格购买
		if((flagNum & (int)Math.pow(2,index)) !=0||Constants.DAILY_DISCOUNTS.length<=index){
			throw new BaseException(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		return Constants.DAILY_DISCOUNTS[index];
	}
}
