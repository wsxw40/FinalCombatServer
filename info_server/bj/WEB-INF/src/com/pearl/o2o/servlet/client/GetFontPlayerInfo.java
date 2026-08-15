package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerInfo;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.BiochemicalConstants;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class GetFontPlayerInfo extends BaseClientServlet {

	private static final long serialVersionUID = 2566986664006349625L;
	private static Logger log = LoggerFactory.getLogger(GetFontPlayerInfo.class
			.getName());
	private static final String[] paramNames = { "uid", "pid" };

	protected String innerService(String... args) {
		try {
			int userId = StringUtil.toInt(args[0]);
			int playerId = StringUtil.toInt(args[1]);
			Player player = getService.getPlayerById(playerId);
			ServiceLocator.missionLog.debug("GetFontPlayerInfo playerId="
					+ playerId);
			PlayerInfo playerInfo = getService.getPlayerInfoById(playerId);
			ServiceLocator.missionLog.debug("GetFontPlayerInfo servlet point="
					+ playerInfo.getXunleiPoint() + " playerId="
					+ playerInfo.getPlayerId() + " "
					+ (playerInfo.getXunleiPoint() == 0));
			if (player.getIcon() == null) {
				log.warn("icon is null auto reset, playerId=" + playerId);
				player.setIcon(Constants.DEFAULT_PLAYER_ICON);
				updateService.updatePlayerInfoOnly(player);
			}
			int emailNum = getService.getNewMessageNum(playerId);
			int teamRequestNum = getService.getNewTeamRequest(userId, playerId);
			int card = 0;
			int currentyEXPItem = player.getVipExpGiftLevel() + 1;
			// vip 过期后清空vip buff
			if (player.getIsVip() <= 0) {
				player = deleteService.deleteVipBuff(player);
				currentyEXPItem = -1;
			}

			List<PlayerItem> buffList = new ArrayList<PlayerItem>();
			if (player.getBuffList() != null
					&& player.getBuffList().size() != 0) {
				for (PlayerItem pi : player.getBuffList()) {
					SysItem si = pi.getSysItem();
					// getService.getSysItemByItemId(pi.getItemId());
					if (Constants.DEFAULT_ITEM_TYPE == si.getType()
							&& si.getIId() == 1 && si.getIBuffId() == 32) {
						card = StringUtil.toInt(si.getIValue());
					}
					if (Constants.DEFAULT_ITEM_TYPE == si.getType()
							&& si.getIId() == 1
							&& si.getIBuffId() != 6
							&& si.getIId() == 1
							&& si.getIBuffId() != 32
							&& si.getIBuffId() != BiochemicalConstants.ordinaryBuffId) {
						buffList.add(pi);
					}
				}

			}
			// List<PlayerItem> displayList=new ArrayList<PlayerItem>();
			// if(buffList.size()>8){
			// displayList=buffList.subList(buffList.size()-8, buffList.size());
			// }else{
			// displayList=buffList;
			// }
			player.setBuffList(buffList);// just for smarty display in
			// GUI,must't update player
			int rankNumInTop = getService.getPlayerFightNumRankInTop(playerId,
					String.valueOf(Constants.CHARACTER_IDS[0]));
			// String rankKey = NosqlKeyUtil.selfLevelnumInTopByType(playerId,
			// "kCommonTop");
			// int rankNumInTop =StringUtil.toInt(
			// nosqlService.getNosql().get(rankKey));
			float winRate = 0;
			if (player.getGWin() != 0 || player.getGLose() != 0) {
				winRate = CommonUtil.toTwoFloat(player.getGWin()
						/ (player.getGWin() + player.getGLose()));
			}
			String team = player.getTeam() == null ? "" : player.getTeam();
			String result = Converter.fontPlayerInfo(player, emailNum,
					teamRequestNum, null, card, playerInfo, rankNumInTop,
					winRate, team, getService.getMaxStrengthLevel(playerId),
					player.getLeftMinites(), currentyEXPItem);
			return result;
		} catch (Throwable e) {
			log.warn("Throwable in GetPlayer: " + args[1], e);
			return Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
			// return Converter.fontPlayerInfo(new Player(),0,0,
			// e.getMessage(),0,new PlayerInfo());
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
