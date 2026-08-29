package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.exception.CRNotEnoughException;
import com.pearl.o2o.exception.NotBuyEquipmentException;
import com.pearl.o2o.exception.STONENotEnoughException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamItem;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class CreateTeamItemZYZDZ extends BaseClientServlet {
	private static final long serialVersionUID = 6356406975633293330L;
	static Logger log = LoggerFactory.getLogger(CreateTeamItemZYZDZ.class
			.getName());
	private String[] paramNames = { "pid", "sid", "t", "costid", "packid" };

	@Override
	protected String innerService(String... args) {

		// int userId = StringUtil.toInt(args[0]);

		for (int i = 1; i < args.length; i++) {

			if (!args[i].matches("^\\d+$")) {

				return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
			}
		}
		int playerId = StringUtil.toInt(args[0]);
		int sysItemId = StringUtil.toInt(args[1]);
		int type = StringUtil.toInt(args[2]);
		int costId = StringUtil.toInt(args[3]);
		int packId = StringUtil.toInt(args[4]);

		StringBuffer sbBuffer = new StringBuffer(playerId + "|" + sysItemId
				+ "|" + type + "|" + costId + "-");

		try {
			if (costId == 0) {
				throw new BaseException(ExceptionMessage.NOT_HAVE_PAYTYPE);
			}

			// 1.get player info by playerId
			Player player = getService.getPlayerById(playerId);

			// 检测是否输过二级密码
			if (!checkEnterSPW(playerId)) {
				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
			}

			// 检验是否是队长
			Team team = getService.getTeamByPlayerId(playerId);
			if (team == null) {
				return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
			} else if (!team.getLeaderName().equals(player.getName())) {
				return Converter.error(ExceptionMessage.NOT_TEAM_LEADER);
			}

			// 2.get item info by sysitemId
			SysItem sysItem = getService.getSysItemByItemId(sysItemId);

			if (getService.teamItemUnderBuilding(team.getId(), sysItem)) {
				throw new BaseException(ExceptionMessage.BUY_ITEM_CD);
			}

			// 消耗类需要装备或者立刻使用
			if (sysItem.getType() != Constants.DEFAULT_ZYZDZ_TYPE
					|| type != Constants.DEFAULT_ZYZDZ_TYPE) {// 非资源争夺战物品
				throw new BaseException(ExceptionMessage.BUY_FAIL);
			} else if (sysItem.getSubType() != 1 && sysItem.getSubType() != 2) {// 非资源争夺战团队物品
				throw new BaseException(ExceptionMessage.BUY_FAIL);
			} else {// 是资源争夺战的团队物品
				// 看数量是否超过上限
				TeamItem teamItem = getService.getFullTeamItemByTeamIdAndItemId(
						team.getId(), sysItem.getId());
				if (teamItem != null) {
					int quantity = teamItem.getShowQuantity();
					int maxQuantity = getService.getTeamItemCreateMaxQuantity(
							sysItem, team.getTeamSpaceLevel());
					if (quantity >= maxQuantity) {
						throw new BaseException(
								ExceptionMessage.BIG_THAN_CREATE_MAX);
					}
				} else {// 没有点开科技树
					throw new BaseException(ExceptionMessage.BUY_FAIL);
				}

				// packid逻辑待处理
				// if ((sysItem.getType() == Constants.DEFAULT_ITEM_TYPE &&
				// sysItem
				// .getSubType() == 7)) {
				// packId = 1;
				// } else {
				// packId = 0;
				// }
			}

			int teamItemId = createService.buyTeamItem(sbBuffer, player
					.getUserName(), player, sysItem, costId,
					Constants.BOOLEAN_NO, packId);
			if (teamItemId == -1) {
				throw new BaseException(ExceptionMessage.BUY_FAIL);
			}

			/** 需要立刻使用 */
			// if (packId == 1 && teamItemId != 0) {
			// if (type == Constants.DEFAULT_WEAPON_TYPE) {
			// updateService.updateWeaponPackEquipment(playerId, type,
			// teamItemId, characterId);
			// } else if (type == Constants.DEFAULT_COSTUME_TYPE || type ==
			// Constants.DEFAULT_PART_TYPE) {
			// updateService.updateCostumePackEquipment(playerId, type,
			// teamItemId, characterId);
			// }
			// else if(sysItem.getType() == Constants.DEFAULT_ITEM_TYPE){
			// if (sysItem.getSubType() < Constants.itemTypeArray[0][4]) {
			// ServiceLocator.createService.useItemById(player,
			// sysItem.getType(), teamItemId, "", 0, 0);
			// }
			// if ((sysItem.getIBuffId() == 7 || sysItem.getIBuffId() == 8||
			// sysItem.getIBuffId() == BiochemicalConstants.ordinaryBuffId||
			// sysItem.getIBuffId() == BiochemicalConstants.especialBuffId)) {
			//						
			// ServiceLocator.createService.useItemById(player,
			// sysItem.getType(), teamItemId, "", 0, 0);
			// }
			// if(sysItem.getIId() == 15){
			// ServiceLocator.createService.useItemById(player,
			// sysItem.getType(), teamItemId, "", 0, 0);
			// }
			// }
			// }
			mcc.delete(CacheUtil.oPlayer(playerId));
			mcc.delete(CacheUtil.sPlayer(playerId));

			return Converter.createTeamItem(teamItemId,sysItem.getTimeForCreateMsec(), null);
		}catch (STONENotEnoughException nbe) {
			log.debug("STONENotEnoughException in CreateTeamItem"
					+ nbe.getMessage());
			return Converter.createTeamItem(-2,null, null);
		}  catch (NotBuyEquipmentException e) {
			log.debug("NotBuyEquipmentException in CreateTeamItem"
					+ e.getMessage());
			return Converter.createTeamItem(0,null, e.getMessage());
		} catch (CRNotEnoughException nbe) {
			log.debug("CRNotEnoughException in CreateTeamItem"
					+ nbe.getMessage());
			return Converter.createTeamItem(-2,null, null);
		} catch (BaseException e) {
			log.warn("Exception in CreateTeamItem", e);
			return Converter.error(e.getMessage());
		} catch (Exception e) {
			log.warn("Exception in CreateTeamItem", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		} finally {
			if (!sbBuffer.toString().endsWith("-")) {
				createService.createPlayerBuyTeamItemLog(sbBuffer.toString());
			}

		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[1]));
	}
}
