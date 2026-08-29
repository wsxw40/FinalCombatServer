package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.BuyItemRecord;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.sort.SysItemComparator;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetZYZDZExchangeList extends BaseClientServlet {

	private static final long serialVersionUID = 7198278673447408347L;
	private static Logger log = LoggerFactory
			.getLogger(GetZYZDZExchangeList.class.getName());
	private static final String[] paramNames = { "pid", "page", "type" };

	protected String innerService(String... args) {
		int playerId = StringUtil.toInt(args[0]);
		int page = StringUtil.toInt(args[1]);
		int type = StringUtil.toInt(args[2]); // 1:buff 2:其他
		try {
			Player player = getService.getSimplePlayerById(playerId);
			PlayerTeam playerTeam = getService
					.getPlayerTeamByPlayerId(playerId);
			Team team = getService.getSimpleTeamById(playerTeam.getTeamId());

			List<SysItem> srcList = getService
					.getZYZDZExchangeSysItemList(type);
			List<SysItem> cloneList = new ArrayList<SysItem>();

			for (SysItem siItem : srcList) {
				SysItem cloneSiItem = siItem.clone(true);
				BuyItemRecord buyItemRecord = getService.getBuyItemRecord(
						playerId, cloneSiItem.getId());
				int record =0;
				if (buyItemRecord != null) {
					record = buyItemRecord.getRecord();
				}
				cloneSiItem.setBuyRecordTimes(record);
				int baseCost = cloneSiItem.getAllResPricesList().get(0)
						.getCost();
				cloneSiItem.getAllResPricesList().get(0).setCost(
						CommonUtil.getCostForBuyRecord(record, baseCost,
								cloneSiItem.getIId()));				
				// 根据物品等级设置是否可以exchange
				if (cloneSiItem.getIsExchange() != Constants.TeamSpaceConstants.EXCHANGE_LOCK
						&& cloneSiItem.getAllResPricesList().get(0).getLevel() > team.getTeamSpaceLevel()) {
					cloneSiItem.setIsExchange(Constants.TeamSpaceConstants.EXCHANGE_LOCK);
				} else {
					cloneSiItem.setIsExchange(Constants.TeamSpaceConstants.EXCHANGE_UNLOCK);
				}

				cloneList.add(cloneSiItem);
			}

			Collections.sort(cloneList, new SysItemComparator());
			HashMap<String, Integer> playerRes = player.getLatestPlayerRes(team
					.getTeamSpaceLevel());
			int pRes = playerRes.get(Player.RES);
			int pageSize = Constants.DEFAULT_A_PAGE_SIZE;
			int pages = CommonUtil.getListPages(cloneList, pageSize);
			if (pages != 0 && page > pages) {
				log.warn("Error in page bigger than pages");
				return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
			}
			int endIndex = page * pageSize;
			int startIndex = (page - 1) * pageSize;
			endIndex = endIndex > cloneList.size() ? cloneList.size()
					: endIndex;
			cloneList = cloneList.subList(startIndex, endIndex);
			if (pages == 0) {
				pages = 1;
			}
			return Converter.zyzdzExchangeItemList(page, pages, cloneList, team
					.getTeamSpaceLevel(), pRes);
		} catch (Exception e) {
			log.warn("Error in GetExchangeSysItem: ", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}

	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
