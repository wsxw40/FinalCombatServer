package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.GrowthMissionConstants.GrowthMissionType;
import com.pearl.o2o.utils.StringUtil;

/**
 * 合成系统--打孔接口
 * @author wuxiaofei
 *
 */
public class CombineSlotting extends BaseClientServlet {
	private static final long serialVersionUID = -2470899400867670372L;
	static Logger log = LoggerFactory.getLogger(CombineSlotting.class.getName());
	private String[] paramNames = { "pid", "playerItemId", "index", "sloterItemId"};

	public CombineSlotting() {
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
		int sloterItemId = Integer.parseInt(args[3]);
		try {
			Player player = getService.getPlayerById(playerId);
			CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
			//检测是否输过二级密码
			if(!checkEnterSPW(playerId)){
				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
			}
			
			boolean result = updateService.slottingOnPlayerItem(playerId, playerItemId, index, sloterItemId);
			PlayerItem pi = getService.getPlayerItemById(playerId, playerItemId);
			PlayerItem sloterItem = getService.getPlayerItemById(playerId, sloterItemId);
			SysItem si = pi.getSysItem();
			if(result){
				//成长任务：进行一次打孔
				updateService.updatePlayerGrowthMission(playerId, GrowthMissionType.FIRST_STILETTO);
				switch (si.getType()) {//成就
				case Constants.DEFAULT_WEAPON_TYPE:
					updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_SLOT_ACTION.SLOT_WEAPEON_TIME.getValue(), 1,Constants.COMBINE_TYPE.SLOT);
					break;
				case Constants.DEFAULT_COSTUME_TYPE:
					updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_SLOT_ACTION.SLOT_COSTUME_TIME.getValue(), 1,Constants.COMBINE_TYPE.SLOT);
					break;
				case Constants.DEFAULT_PART_TYPE:
					updateService.updatePlayerAchievementInCombine(playerId, Constants.COMBINE_SLOT_ACTION.SLOT_PART_TIME.getValue(), 1,Constants.COMBINE_TYPE.SLOT);
					break;
				default:
					break;
				}
				return Converter.combineSlotting(Constants.SUCCESSED, pi, sloterItem);
			} else {
				return Converter.combineSlotting(Constants.FAILED, pi, sloterItem);
			}
		} catch (Exception e) {
			log.warn("Error in Slotting: ", e);
			return Converter.error(e.getMessage());
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
