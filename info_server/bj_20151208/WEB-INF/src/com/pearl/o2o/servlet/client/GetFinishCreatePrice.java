package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.exception.CRNotEnoughException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamItem;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ItemIntensifyUtil;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.ItemIntensifyUtil.ConditionForPlaceUp;

/**
 * 获得资源争夺战秒建造物品价格
 * 
 * @author leo.zhang
 */
public class GetFinishCreatePrice extends BaseClientServlet {
	private static final long serialVersionUID = -3928363495923777848L;

	static Logger logger = LoggerFactory.getLogger(GetFinishCreatePrice.class
			.getName());

	/**
	 * <li>pid - 玩家id </li>
	 * <li>type - 1:个人物品 2:团队物品 3:战队空间升级</li>
	 * <li>iid -ItemId 即 道具的ID ，团队物品: teamItemId ,个人物品 playerItemId </li>
	 * <li>atype -ActionType 1:查询 2:购买 </li>
	 * <li>ptype -PayType 0:(查询,不用付费) 1:(半价) 2:(全价) </li>
	 */
	private String[] paramNames = { "pid", "type", "iid", "atype", "ptype" };

	@Override
	protected String innerService(String... args) {

		String result = "";
		try {
			int playerId = StringUtil.toInt(args[0]);
			int type = StringUtil.toInt(args[1]);
			int itemId = StringUtil.toInt(args[2]);
			int aType = StringUtil.toInt(args[3]);
			int pType = StringUtil.toInt(args[4]);
			//检测是否输过二级密码
//			if(!checkEnterSPW(playerId)){
//				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
//			}			
			switch (aType) {
			case 1:
				result = doQuery(playerId, type, itemId);
				break;
			case 2:
				result = doFinishCreate(playerId, type, itemId, pType);
				break;
			default:
				return Converter
						.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
			}			return result;
		} catch (BaseException e) {
			logger.warn(e.getMessage(), e);
			return Converter.commonFeedback(e.getMessage());
		} catch (CRNotEnoughException e) {
			logger.warn(e.getMessage(), e);
			return Converter.commonFeedback(e.getMessage());
		} 
		catch (Exception e) {
			logger.warn(e.getMessage(), e);
			return Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
		}

	}

	/**
	 * 进行查询
	 * 
	 * @param playerId
	 * @param type
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	private String doQuery(int playerId, int type, int itemId) throws Exception {
		long[][] costs = null;
		long[] cost = null;
		switch (type) {
		case 1:
			PlayerItem playerItem = getService.getPlayerItemById(playerId,
					itemId);
			costs = getPlayerItemFinishCreateCosts(playerItem);
			break;
		case 2:
			TeamItem teamItem = getService.getFullTeamItemByTeamItemId(itemId);
			costs = getTeamItemFinishCreateCosts(teamItem);
			break;
		case 3:
			Team team = getService.getTeamByPlayerId(playerId);
			costs = getTeamPlaceFinishCreateCosts(team);
			break;			
		default:
			break;
		}

		if (costs == null) {// 找不到价格
			return Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
		}

		cost = getCost(costs);
		return Converter.getZYZDZFinishCosts(cost);
	}

	private String doFinishCreate(int playerId, int type, int itemId, int pType)
			throws Exception {
		String result = "";
		switch (type) {
		case 1:// 玩家
			result = doFinishCreatePlayerItem(playerId, itemId, pType);
			break;
		case 2:// 团队
			result = doFinishCreateTeamItem(playerId, itemId, pType);
			break;
		case 3:// 战队空间升级
			result = doFinishCreateTeamSpace(playerId,pType);
			break;			
		default:
			break;
		}

		return result;
	}

	private String doFinishCreatePlayerItem(int playerId, int playerItemId,
			int pType) throws Exception {
		PlayerItem playerItem = getService.getPlayerItemById(playerId,
				playerItemId);
		if (playerItem.getBuyCD() == 0) {
			throw new BaseException(ExceptionMessage.ALREADY_CREATED);
		}
		long[][] costs = getPlayerItemFinishCreateCosts(playerItem);
		int fcCOST = getFinishFCCost(costs, pType);
		createService.finishCreateItem(playerId, fcCOST, playerItem,
				playerItem.getSysItem());
		return "";
	}

	private String doFinishCreateTeamItem(int playerId, int teamItemId,
			int pType) throws Exception {
		TeamItem teamItem = getService.getFullTeamItemByTeamItemId(teamItemId);
		if (teamItem.getBuyCD() == 0) {
			throw new BaseException(ExceptionMessage.ALREADY_CREATED);
		}
		long[][] costs = getTeamItemFinishCreateCosts(teamItem);
		int fcCOST = getFinishFCCost(costs, pType);
		createService.finishCreateItem(playerId, fcCOST, teamItem, teamItem
				.getSysItem());
		return "";
	}
	
	/**
	 * 完成建造战队空间
	 * @param playerId
	 * @param pType
	 * @return
	 * @throws Exception
	 */
	private String doFinishCreateTeamSpace(int playerId,int pType) throws Exception {
		Player player=getService.getPlayerById(playerId);
		Team team =getService.getTeamByPlayerId(playerId);
		if(!team.getLeaderName().equals(player.getName())){
			throw new BaseException(ExceptionMessage.NOT_TEAM_LEADER);
		}
		
		ConditionForPlaceUp con = ItemIntensifyUtil.ConditionForPlaceUp.getConditionForPlaceUp(team.getTeamSpaceLevel());
		long fullCD=con.getTime()*1000;
		long buyCD=team.getLevelupCD(fullCD);		
		
		if (buyCD == 0) {
			throw new BaseException(ExceptionMessage.ALREADY_CREATED);
		}
		long[][] costs =getTeamPlaceFinishCreateCosts(team);
		int fcCOST = getFinishFCCost(costs, pType);
		createService.finishCreateItem(playerId, fcCOST, team ,null);
		return "";
	}
	

	/**
	 * 获得应该支付的价格
	 * 
	 * @param costs
	 * @param Ptype
	 * @return
	 * @throws BaseException
	 */
	private int getFinishFCCost(long[][] costs, int pType) throws BaseException {
		int calPtype = new Long(getCost(costs)[0]).intValue();
		if (calPtype == 2 && pType == 1) { // 计算出来必须要找全价来支付，而前台说需要按照半价支付
			throw new BaseException(ExceptionMessage.BUY_FAIL);
		} else {
			long[] cost = costs[pType - 1];
			Long fc = cost[3];
			return fc.intValue();
		}
	}

	/**
	 * 获得团队物品秒建造的价格
	 * 
	 * @param teamItemId
	 * @return
	 * @throws Exception
	 */
	private long[][] getTeamItemFinishCreateCosts(TeamItem teamItem)
			throws Exception {
		long[][] costs = getService.getFinishPricesByTeamIteam(teamItem);
		return costs;
	}

	/**
	 * 获得团队物品秒建造的价格
	 * 
	 * @param teamItemId
	 * @return
	 * @throws Exception
	 */
	private long[][] getTeamPlaceFinishCreateCosts(Team team)
			throws Exception {
		long[][] costs = getService.getFinishSpacePricesByTeam(team);
		return costs;
	}	
	
	/**
	 * 获得个人物品秒建造的金额
	 * 
	 * @param playerId
	 * @param playerItemId
	 * @return
	 * @throws Exception
	 */
	private long[][] getPlayerItemFinishCreateCosts(PlayerItem playerItem)
			throws Exception {
		long[][] costs = getService.getFinishPricesByPlayerItem(playerItem);
		return costs;
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

	/**
	 * 获得cost中当前应该使用的cost
	 * 
	 * @param costs
	 * @return
	 */
	private long[] getCost(long[][] costs) {
		if (costs[0][4] == 1) {
			return costs[0];
		} else {
			return costs[1];
		}
	}

}
