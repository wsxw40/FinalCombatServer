package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class GetCompeteBuyItems extends BaseClientServlet {
	private static final long serialVersionUID = 4052662283572185865L;
	private static Logger log = LoggerFactory.getLogger(GetCompeteBuyItems.class.getName());
	private String[] paramNames = { "pid"};
//	private int[] payMethods={Constants.CMPT_ITEM_ID_NUM_COST_ID_NUMS[0][1],Constants.CMPT_ITEM_ID_NUM_COST_ID_NUMS[1][1],Constants.CMPT_ITEM_ID_NUM_COST_ID_NUMS[2][1]};
	
	
	@Override
	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			String[] timeStatus = getService.getCompeteBuyTime(Constants.CMPT_BUY_STT_WEEK_DAY,Constants.CMPT_BUY_STT_TIME_HOUR,Constants.CMPT_BUY_END_WEEK_DAY,Constants.CMPT_BUY_END_TIME_HOUR);
			String timeType = timeStatus[1];
			String retTimeStr = timeStatus[0];
			StringBuilder leastPrices = new StringBuilder();
			StringBuilder validPrices = new StringBuilder();
			StringBuilder highestPrices = new StringBuilder();
			StringBuilder myprices = new StringBuilder();
			StringBuilder itemNums = new StringBuilder();
			StringBuilder leftNums = new StringBuilder();
			NoSql nosql = nosqlService.getNosql();
			
			//get the pay unit (1:黄金卡,2:勋章,3:fc点(目前没有))
			String payUnits=ServiceLocator.getService.getSysConfig().get("compete.currency");
			String[] payUnitArrayStrings=payUnits.split(",");
			for(int i=0;i<payUnitArrayStrings.length;i++){
				if(StringUtil.isAllNumber(payUnitArrayStrings[i])){
					int payUnit=Integer.parseInt(payUnitArrayStrings[i]);
					if(0<payUnit && 4>payUnit){
						switch (payUnit) {
						case 1:  Constants.CMPT_ITEM_ID_NUM_COST_ID_NUMS[i][1]=4879;	
							break;
						case 2:  Constants.CMPT_ITEM_ID_NUM_COST_ID_NUMS[i][1]=4479;
							break;
						case 3:   Constants.CMPT_ITEM_ID_NUM_COST_ID_NUMS[i][1]=-1;
							break;
						default:
							break;
						}
					}
				}
			}	
			
			
			for(int i=0;i<Constants.CMPT_ITEM_ID_NUM_COST_ID_NUMS.length;i++){
				int[] item=Constants.CMPT_ITEM_ID_NUM_COST_ID_NUMS[i];
//				SysItem si = getService.getSysItemByItemId(item[0]);
//				items.add(si);
				leastPrices.append(item[2]).append(",");
				itemNums.append(item[3]).append(",");
				String key = Constants.COMPETE_BUY_ITEM_KEY_PREX+item[0];
				int playerNum = (int) nosql.zCard(key);
				int validPrice = item[2];
				int highestPrice = item[2];
				if(playerNum>0){
					highestPrice = (int) nosql.revRangeWithScores(key,0,0).iterator().next().getScore();
				}
				if(playerNum>=item[3]){
					validPrice = (int) nosql.revRangeWithScores(key,item[3]-1,item[3]-1).iterator().next().getScore();
				}
				validPrices.append(validPrice).append(",");
				highestPrices.append(highestPrice).append(",");
				myprices.append(String.valueOf((int)nosql.zScore(key, args[0]))).append(",");
				List<PlayerItem> costItems = ServiceLocator.getService.getPlayerItemListByItemId(playerId,  Constants.CMPT_ITEM_ID_NUM_COST_ID_NUMS[i][1]);
				int leftNum=0;
				for(PlayerItem pi : costItems){
					leftNum+=pi.getQuantity();
				}
				leftNums.append(leftNum).append(",");
			}
			return Converter.getCompeteBuyItems(timeType+"",retTimeStr,itemNums.toString(),leastPrices.toString(),validPrices.toString(),highestPrices.toString(),myprices.toString(),leftNums.toString());
		} catch (BaseException be){
			log.warn("GetCompeteBuyItems:\t",be);
			return Converter.warn(be.getMessage());
		}catch (Exception e) {
			log.error("GetCompeteBuyItems:\t",e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]));
	}
	
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
