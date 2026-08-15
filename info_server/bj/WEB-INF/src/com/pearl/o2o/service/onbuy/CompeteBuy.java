package com.pearl.o2o.service.onbuy;

import java.util.Date;
import java.util.List;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.nosql.NosqlKeyUtil;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;
/**
 * 竞拍
 * @author wangzhilong
 *
 */
public class CompeteBuy implements PlayerBuyWay {
	/**
	 * 不能低于最低数量
	 */
	public static final int CANT_LOWER_LEAST = 3;
	/**
	 * 参数错误
	 */
	public static final int PARAM_EROR = 4;
	private Player player;
//	private SysItem sysItem;
	private int index;
	private int payAmount;
	private int payMethod;
	
	private int[] payMethods={4879,4479};
	public CompeteBuy(Player player,int index ,int payAmount,int payMethod) {
		this.player = player;
		this.index = index;
		this.payAmount = payAmount;
		this.payMethod=payMethod;
	}
	@Override
	public int buy() throws Exception {
	
		if(index<0||index>=Constants.CMPT_ITEM_ID_NUM_COST_ID_NUMS.length){
			return PARAM_EROR;
		}
		
//		//get the pay unit (1:黄金卡,2:勋章,3:fc点)
//		String payUnits=ServiceLocator.getService.getSysConfig().get("compete.currency");
//		String[] payUnitArrayStrings=payUnits.split(",");
//		for(int i=0;i<payUnitArrayStrings.length;i++){
//			if(StringUtil.isAllNumber(payUnitArrayStrings[i])){
//				int payUnit=Integer.parseInt(payUnitArrayStrings[i]);
//				if(0<payUnit && 4>payUnit){
//					switch (payUnit) {
//					case 1:  payMethods[i]=4879;	
//						break;
//					case 2:  payMethods[i]=4479;
//						break;
//					case 3:  payMethods[i]=-1;
//						break;
//					default:
//						break;
//					}
//				}
//			}
//		}	
		
		
		String key =NosqlKeyUtil.competeBuyBid(Constants.CMPT_ITEM_ID_NUM_COST_ID_NUMS[index][0]);
		NoSql nosql = ServiceLocator.nosqlService.getNosql();
		double payBefore = (int)nosql.zScore(key,  String.valueOf(player.getId()));
		double timeCount= 1.0/(2+new Date().getTime() - ServiceLocator.getService.getCompeteBuyTime().getTime());//购买时间排序
		int costItemId;
		if(payMethod>0 && payMethod<3){
			costItemId=payMethods[payMethod-1];
		}else{
			costItemId =Constants.CMPT_ITEM_ID_NUM_COST_ID_NUMS[index][1];
		}
	
		int leastNum = Constants.CMPT_ITEM_ID_NUM_COST_ID_NUMS[index][2];
		if(payBefore+payAmount<leastNum){
			return CANT_LOWER_LEAST;
		}
		//使用黄金卡或勋章拍
		if(costItemId!=-1){
			List<PlayerItem> items = ServiceLocator.getService.getPlayerItemListByItemId(player.getId(), costItemId);
			int num=0;
			for(PlayerItem pi : items){
				num+=pi.getQuantity();
			} 
			if(payAmount>num){
				return NOT_ENOUGH;
			}
			int count = payAmount;
			for(PlayerItem pi: items){
				if(pi.getQuantity()>=1){
					if(pi.getQuantity()<count){
						count-=pi.getQuantity();
						ServiceLocator.deleteService.deleteCombiningItem(pi,pi.getQuantity());
					}else{
						ServiceLocator.deleteService.deleteCombiningItem(pi,count);
						break;
					}
				}
			}
//		}else{   //使用fc点拍
//			PlayerInfo playerInfo= ServiceLocator.getService.getPlayerInfoById(player.getId());
//			if(playerInfo!=null){
//				int fcAccountNum=playerInfo.getXunleiPoint();
//				if(payAmount>fcAccountNum){
//					return NOT_ENOUGH;
//				}
//				playerInfo.setXunleiPoint(fcAccountNum-payAmount);
//				ServiceLocator.updateService.updatePlayerFCInfo(playerInfo);
//			}
			
		
		}
		
		nosql.zAdd(key,payBefore+payAmount+timeCount, String.valueOf(player.getId()));
		return SUCCESS;
	}

}
