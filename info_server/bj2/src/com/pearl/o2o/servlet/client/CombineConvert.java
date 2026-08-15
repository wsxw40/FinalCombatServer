package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.CombineProperty;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

/**
 * 合成系统--转换接口
 * @author wuxiaofei
 *
 */
public class CombineConvert extends BaseClientServlet {
	private static final long serialVersionUID = -2470899400867670372L;
	static Logger log = LoggerFactory.getLogger(CombineConvert.class.getName());
	private String[] paramNames = { "pid", "fromItemId", "toItemId","islose","converterItemId", "saferItemId"};

	public CombineConvert() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see com.pearl.o2o.servlet.client.BaseClientServlet#getLockKey(java.lang.String[])
	 */
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]));
	}
	
	@Override
	protected String innerService(String... args) {
		int playerId = Integer.parseInt(args[0]);
		int fromItemId = Integer.parseInt(args[1]);
		int toItemId = Integer.parseInt(args[2]);
		int islose = Integer.parseInt(args[3]);
		try {
			PlayerItem fromItem = getService.getPlayerItemById(playerId, fromItemId);
			PlayerItem toItem = getService.getPlayerItemById(playerId, toItemId);

			if(fromItem.getType()!=toItem.getType()){
				log.error("The two things which prepare to convert have different item type!");
				return Converter.error("Type is inconsistent!");
			}
			//检测是否输过二级密码
			if(!checkEnterSPW(playerId)){
				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
			}
			//result=5:两个都不退级  10:两个都退级  6:源物品退级,转换物品不退级  9:源物品不退级，转换物品退级
			int result = 5;
//			final PlayerItem fromItem = getService.getPlayerItemById(playerId, fromItemId);
//			final PlayerItem toItem = getService.getPlayerItemById(playerId, toItemId);
			final boolean isLoseCoins= islose==1 ? true : false;
//			if(fromItem.getSysItem().getWId()==13||toItem.getSysItem().getWId()==13){
//				
//				result = updateService.convertPlayerItemWithGP(playerId, fromItemId, toItemId,isLoseCoins);
//			}else 
			if(islose!=1){
				result = updateService.convertPlayerItem(playerId, fromItemId, toItemId);
			}else{
				result = updateService.convertPlayerItemWithGP(playerId, fromItemId, toItemId,isLoseCoins);
			}
			
			final PlayerItem converted_fromItem = getService.getPlayerItemById(playerId, fromItemId);
			final PlayerItem converted_toItem = getService.getPlayerItemById(playerId, toItemId);
			
			int fromSiRareLevel = converted_fromItem.getSysItem().getRareLevel();
			int toSiRareLevel = converted_toItem.getSysItem().getRareLevel();
			int rateFrom = CombineProperty.calcConvertPlayerItemLoseRate(converted_fromItem.getLevel(),converted_fromItem.getSysItem().getRareLevel(),converted_toItem.getSysItem().getRareLevel());
			int rateTo = CombineProperty.calcConvertPlayerItemLoseRate(converted_toItem.getLevel(),converted_toItem.getSysItem().getRareLevel(),converted_fromItem.getSysItem().getRareLevel());
			if(result==5){//成就
				boolean flag1 = fromSiRareLevel<26&&toSiRareLevel<26;//普通对普通转换
				boolean flag2 = fromSiRareLevel<=50&&fromSiRareLevel>=26&&toSiRareLevel<=50&&toSiRareLevel>=26;//精良对精良转换
				updateCombineConvertAchievement(playerId, 1, converted_fromItem.getSysItem().getType(), flag1, flag2);
			}
			int rate = CombineProperty.getConvertPlayerItemLoseRate(converted_fromItem.getLevel(),converted_toItem.getLevel(),fromSiRareLevel,toSiRareLevel);
		
			return Converter.combineConvert(rate,result, converted_fromItem, converted_toItem,rateFrom,rateTo);
		} catch (Exception e) {
			log.warn("Exception in Convert: "+e.getMessage());
			return Converter.error(e.getMessage());
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	/**
	 * 转换成就
	 * @param player
	 * @param number
	 * @param sysItemType
	 * @param flag1
	 * @param flag2
	 * @throws Exception
	 */
	private void updateCombineConvertAchievement(Integer playerId , int number,int sysItemType,boolean flag1,boolean flag2) throws Exception{
		switch (sysItemType) {
		case Constants.DEFAULT_WEAPON_TYPE:
			if(flag1){
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_CONVERT_ACTION.CONVERT_COMMON_WEAPEON_TIME.getValue(), 1,Constants.COMBINE_TYPE.CONVERT);
			}else if(flag2){
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_CONVERT_ACTION.CONVERT_EXCELLENT_WEAPEON_TIME.getValue(), 1,Constants.COMBINE_TYPE.CONVERT);
			}
			break;
		case Constants.DEFAULT_COSTUME_TYPE:
			if(flag1){
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_CONVERT_ACTION.CONVERT_COMMON_COSTUME_TIME.getValue(), 1,Constants.COMBINE_TYPE.CONVERT);
			}else if(flag2){
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_CONVERT_ACTION.CONVERT_EXCELLENT_COSTUME_TIME.getValue(), 1,Constants.COMBINE_TYPE.CONVERT);
			}
			break;
		case Constants.DEFAULT_PART_TYPE:
			if(flag1){
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_CONVERT_ACTION.CONVERT_COMMON_PART_TIME.getValue(), 1,Constants.COMBINE_TYPE.CONVERT);
			}else if(flag2){
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_CONVERT_ACTION.CONVERT_EXCELLENT_PART_TIME.getValue(), 1,Constants.COMBINE_TYPE.CONVERT);
			}
			break;
		default:
			break;
		}
	}
}
