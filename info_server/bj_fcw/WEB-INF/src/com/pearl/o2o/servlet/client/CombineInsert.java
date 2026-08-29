package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.GrowthMissionConstants.GrowthMissionType;
import com.pearl.o2o.utils.StringUtil;

public class CombineInsert extends BaseClientServlet {
	private static final long serialVersionUID = -2470899400867670372L;
	static Logger log = LoggerFactory.getLogger(CombineInsert.class.getName());
	private String[] paramNames = { "pid", "playerItemId", "index", "propertyItemId"};

	public CombineInsert() {
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
		int playerItemId = Integer.parseInt(args[1]);
		int index = Integer.parseInt(args[2]);
		int propertyItemId = Integer.parseInt(args[3]);
		try {
			Player player = getService.getPlayerById(playerId);
			CommonUtil.checkNull(player,ExceptionMessage.NO_HAVE_THE_CHARACTER);
			//检测是否输过二级密码
			if(!checkEnterSPW(playerId)){
				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
			}
			StringBuilder sb = new StringBuilder();
			boolean result = updateService.insertToPlayerItem(playerId, playerItemId, index, propertyItemId, sb);
			PlayerItem pi = getService.getPlayerItemById(playerId, playerItemId);
			int insertNum = 0;
			for(int i = 1;i<=3;i++){
				String currentHoleProperty = pi.getGunPropertyByHoleIndex(i);
				if (currentHoleProperty.length() > 2) {
					insertNum++;
				} 
			}
			if(result){
				//成长任务：进行一次镶嵌
				updateService.updatePlayerGrowthMission(playerId, GrowthMissionType.FIRST_BESET);
				updateService.updatePlayerGrowthMission(playerId, GrowthMissionType.FIRST_STILETTO);
				//合成系统成就：镶嵌
				//get new player
				updateCombineInsertAchievement(playerId, 1, insertNum,  pi.getSysItem().getType());
				return Converter.combineInsert(Constants.SUCCESSED, pi, sb.toString());
			} else {
				return Converter.combineInsert(Constants.FAILED, pi, sb.toString());
			}
		} catch (Exception e) {
			log.warn("Error in Insert: ", e);
			return Converter.error(e.getMessage());
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	/**
	 * 镶嵌成就
	 * @param player
	 * @param number
	 * @param insertNum
	 * @param sysItemType
	 * @param mValue
	 * @throws Exception
	 */
	private void updateCombineInsertAchievement(Integer playerId , int number,int insertNum,int sysItemType) throws Exception{
		switch (sysItemType) {//成就
		case Constants.DEFAULT_WEAPON_TYPE:
			if(insertNum==2){
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_INSERT_ACTION.INSERT_WEAPEON_TWO.getValue(), 1,Constants.COMBINE_TYPE.INSERT);
			}else if(insertNum==3){
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_INSERT_ACTION.INSERT_WEAPEON_THREE.getValue(), 1,Constants.COMBINE_TYPE.INSERT);
			}
			break;
		case Constants.DEFAULT_COSTUME_TYPE:
			if(insertNum==2){
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_INSERT_ACTION.INSERT_COSTUME_TWO.getValue(), 1,Constants.COMBINE_TYPE.INSERT);
			}else if(insertNum==3){
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_INSERT_ACTION.INSERT_COSTUME_THREE.getValue(), 1,Constants.COMBINE_TYPE.INSERT);
			}
			break;
		case Constants.DEFAULT_PART_TYPE:
			if(insertNum==2){
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_INSERT_ACTION.INSERT_PART_TWO.getValue(), 1,Constants.COMBINE_TYPE.INSERT);
			}else if(insertNum==3){
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_INSERT_ACTION.INSERT_PART_THREE.getValue(), 1,Constants.COMBINE_TYPE.INSERT);
			}
			break;
		default:
			break;
		}
	}
}
