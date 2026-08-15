package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * 合成系统--强化接口
 * @author wuxiaofei
 *
 */
public class CombineStrength extends BaseClientServlet {
	private static final long serialVersionUID = -2470899400867670372L;
	static Logger log = LoggerFactory.getLogger(CombineStrength.class.getName());
	private String[] paramNames = { "pid", "playerItemId", "strengthenItemId", "safeItemId", "stableItemId"};

	/* (non-Javadoc)
	 * @see com.pearl.o2o.servlet.client.BaseClientServlet#getLockKey(java.lang.String[])
	 */
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]));
	}
	
	@Override
	protected String innerService(String... args) {
		try {
			int playerId = Integer.parseInt(args[0]);
			int playerItemId = Integer.parseInt(args[1]);
			int strengthenItemId = Integer.parseInt(args[2]);
			int safeItemId = Integer.parseInt(args[3]);
			int stableItemId = Integer.parseInt(args[4]);
			PlayerItem pi = null;
			PlayerItem[] itemList = new PlayerItem[3];
			//start加上对玩家的判断，判断是否完成任务跟达到等级
			Player player = getService.getPlayerById(playerId);
			if (player.getRank()<4||player.getTutorial().charAt(2)!='2') 
				return Converter.error(CommonMsg.FUNC_SIGN_ERROR);
			//end
			//检测是否输过二级密码
			if(!checkEnterSPW(playerId)){
				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
			}
			
			PlayerItem tempPlayerItem = getService.getPlayerItemById(playerId, playerItemId);
			CommonUtil.checkNull(tempPlayerItem, ExceptionMessage.NOT_FIND_ITEM);
			if (tempPlayerItem.getLevel() >= getService.getMaxStrengthLevel(playerId)) {
				//事实上，此处客户端只识别warn,而不管其内容
				return Converter.warn("the item already had the max level now you can reach!");
			}
			
//			itemList[0] = getService.getPlayerItemById(playerId, strengthenItemId);
//			itemList[1] = getService.getPlayerItemById(playerId, safeItemId);
//			itemList[2] = getService.getPlayerItemById(playerId, stableItemId);
			List<SysItem> failAwards = new ArrayList<SysItem>();
			int result = updateService.strengthenPlayerItem(playerId, playerItemId, strengthenItemId, safeItemId, stableItemId,itemList,failAwards);
			if(Constants.FAILED_BREAK_ITEM != result){
				pi = getService.getPlayerItemById(playerId, playerItemId);
			}
			
			if(result==Constants.SUCCESS){
				int flag =  0;//使用安定和增幅标志
				if(safeItemId>0){
					flag++;
				}
				if(stableItemId>0){
					flag+=2;
				}
				updateCombineStrengthAchievement(playerId, pi.getSysItem().getType(), flag, pi.getLevel());
			}
			return Converter.strength(result, pi, itemList,failAwards);
		}catch (BaseException e) {
			log.debug("Strengthen: ", e);
			return Converter.error(e.getMessage());
		} 
		catch (Exception e) {
			log.error("Error in Strengthen: ", e);
			return Converter.error(e.getMessage());
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	/**
	 * 强化成就
	 * @param player
	 * @param siType
	 * @param flag
	 * @param piLevel
	 * @throws Exception
	 */
	private void updateCombineStrengthAchievement(Integer playerId,int siType,int flag,int piLevel) throws Exception{
		switch (siType) {
		case Constants.DEFAULT_WEAPON_TYPE:
			updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_STRENGTH_ACTION.STRENGTH_WEAPEON_TIME.getValue(), 1,Constants.COMBINE_TYPE.STRENGTH);
			switch (flag) {
			case 1:
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_STRENGTH_ACTION.STRENGTH_WEAPEON_WITH_SAFE_TIME.getValue(), 1,Constants.COMBINE_TYPE.STRENGTH);
				break;
			case 2:
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_STRENGTH_ACTION.STRENGTH_WEAPEON_WITH_STABLE_TIME.getValue(), 1,Constants.COMBINE_TYPE.STRENGTH);
				break;
			case 3:
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_STRENGTH_ACTION.STRENGTH_WEAPEON_WITH_SAFE_STABLE_TIME.getValue(), 1,Constants.COMBINE_TYPE.STRENGTH);
				break;
			default:
				break;
			}
			if(piLevel>=4 && piLevel < 7){
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_STRENGTH_ACTION.STRENGTH_WEAPEON_BLUE.getValue(), 1,Constants.COMBINE_TYPE.STRENGTH);
			}else if(piLevel >= 7 && piLevel < 10){
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_STRENGTH_ACTION.STRENGTH_WEAPEON_GREEN.getValue(), 1,Constants.COMBINE_TYPE.STRENGTH);
			}else if(piLevel >= 10){
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_STRENGTH_ACTION.STRENGTH_WEAPEON_PURPLE.getValue(), 1,Constants.COMBINE_TYPE.STRENGTH);
			}
			break;
		case Constants.DEFAULT_COSTUME_TYPE:
			updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_STRENGTH_ACTION.STRENGTH_COSTUME_TIME.getValue(), 1,Constants.COMBINE_TYPE.STRENGTH);
			switch (flag) {
			case 1:
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_STRENGTH_ACTION.STRENGTH_COSTUME_WITH_SAFE_TIME.getValue(), 1,Constants.COMBINE_TYPE.STRENGTH);
				break;
			case 2:
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_STRENGTH_ACTION.STRENGTH_COSTUME_WITH_STABLE_TIME.getValue(), 1,Constants.COMBINE_TYPE.STRENGTH);
				break;
			case 3:
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_STRENGTH_ACTION.STRENGTH_COSTUME_WITH_SAFE_STABLE_TIME.getValue(), 1,Constants.COMBINE_TYPE.STRENGTH);
				break;
			default:
				break;
			}
			if(piLevel>=4 && piLevel < 7){
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_STRENGTH_ACTION.STRENGTH_COSTUME_BLUE.getValue(), 1,Constants.COMBINE_TYPE.STRENGTH);
			}else if(piLevel >= 7 && piLevel < 10){
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_STRENGTH_ACTION.STRENGTH_COSTUME_GREEN.getValue(), 1,Constants.COMBINE_TYPE.STRENGTH);
			}else if(piLevel >= 10){
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_STRENGTH_ACTION.STRENGTH_COSTUME_PURPLE.getValue(), 1,Constants.COMBINE_TYPE.STRENGTH);
			}
			break;
		case Constants.DEFAULT_PART_TYPE:
			updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_STRENGTH_ACTION.STRENGTH_PART_TIME.getValue(), 1,Constants.COMBINE_TYPE.STRENGTH);
			switch (flag) {
			case 1:
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_STRENGTH_ACTION.STRENGTH_PART_WITH_SAFE_TIME.getValue(), 1,Constants.COMBINE_TYPE.STRENGTH);
				break;
			case 2:
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_STRENGTH_ACTION.STRENGTH_PART_WITH_STABLE_TIME.getValue(), 1,Constants.COMBINE_TYPE.STRENGTH);
				break;
			case 3:
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_STRENGTH_ACTION.STRENGTH_PART_WITH_SAFE_STABLE_TIME.getValue(), 1,Constants.COMBINE_TYPE.STRENGTH);
				break;
			default:
				break;
			}
			if(piLevel>=4 && piLevel < 7){
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_STRENGTH_ACTION.STRENGTH_PART_BLUE.getValue(), 1,Constants.COMBINE_TYPE.STRENGTH);
			}else if(piLevel >= 7 && piLevel < 10){
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_STRENGTH_ACTION.STRENGTH_PART_GREEN.getValue(), 1,Constants.COMBINE_TYPE.STRENGTH);
			}else if(piLevel >= 10){	
				updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_STRENGTH_ACTION.STRENGTH_PART_PURPLE.getValue(), 1,Constants.COMBINE_TYPE.STRENGTH);
			}
			break;
		default:
			break;
		}
	}
}
