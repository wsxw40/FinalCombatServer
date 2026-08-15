package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.pearl.fcw.gm.service.WSysConfigService;
import com.pearl.fcw.utils.LobbyCompareUtil;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ComparatorUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetPlayerItemList extends BaseClientServlet {
	private static final long serialVersionUID = 5117201445216191162L;
	static Logger log = LoggerFactory.getLogger(GetPlayerItemList.class.getName());
	private static final String[] paramNames = { "uid", "pid", "t", "cid", "p" , "st"};

	@Override
	protected String innerService(String... args) {
		try {
			int userId = StringUtil.toInt(args[0]);
			int playerId = StringUtil.toInt(args[1]);
			int type = StringUtil.toInt(args[2]);
			int characterId = StringUtil.toInt(args[3]);
			int page = StringUtil.toInt(args[4]);
			if (type < Constants.NUM_ONE || type > Constants.TYPE_NUM || page < Constants.NUM_ONE || type == Constants.DEFAULT_ZYZDZ_TYPE) {//老的玩家背包不允许显示资源争夺战的物品
				log.warn("type or subtype not correct. type="+type+" page="+page);
			} else if (type != Constants.DEFAULT_WEAPON_TYPE && type != Constants.DEFAULT_COSTUME_TYPE && type != Constants.DEFAULT_PART_TYPE) {
				characterId = 0;
			}
			int subType = 0;
			if(null != args[5]){
				subType = StringUtil.toInt(args[5]);
			}
			if(subType < 0){
				subType = 0;
			}
			String result = "";
			int pages = 0;

			List<PlayerItem> itemList = null;

			if (type < Constants.NUM_ONE || type > Constants.TYPE_NUM || page < Constants.NUM_ONE) {
				log.warn("type or subtype not correct in getPlayerItems. type="+type+" page="+page);
			} else {
//				List<PlayerItem> itemArray = getService.getPlayerItemList(playerId, type, characterId);
				List<PlayerItem> itemArray = getService.getPlayerItemList1(playerId, type, characterId, subType);
				if(type == Constants.DEFAULT_OPEN_TYPE){
					getService.checkNewPackage(getService.getSimplePlayerById(playerId), itemArray);
					itemArray = getService.getPlayerItemList1(playerId, type, characterId, subType);
				}
				if (itemArray != null) {
					if(type == Constants.DEFAULT_ITEM_TYPE){
						itemArray = new ArrayList<PlayerItem>(Collections2.filter(itemArray, new Predicate<PlayerItem>() {
							@Override
							public boolean apply(PlayerItem input) {
								SysItem sysItemByItemId = input.getSysItem();
								return !(null != sysItemByItemId && sysItemByItemId.getIId() == 1 && sysItemByItemId.getIBuffId() >= 61 && sysItemByItemId.getIBuffId() <= 74);//ս��buff:ibuffId:61-74�����ڲֿ���ʾ
							}
						}));
					}
					//仓库排序
					if(type<=Constants.DEFAULT_PART_TYPE||type>=Constants.DEFAULT_WEAPON_TYPE){
						Collections.sort(itemArray, new ComparatorUtil.PlayerStorgeComparator());
					}
					pages = CommonUtil.getSubListPages(itemArray, type);
					if (page > pages || page <= 0) {
						page = 1;
					}
					int fromIndex = (page - 1) * Constants.PAGE_SIZE[type - 1];
					int toIndex = (page) * Constants.PAGE_SIZE[type - 1];
					//zlm2015-5-7-限时装备-开始---------在仓库上显示续费时间----------------------------
					for (PlayerItem playerItem : itemArray) {
						//先缩小范围
					    int sysItemId =playerItem.getSysItem().getId();
						if(sysItemId >= Constants.PROVISIONAL_ITEM_SCOPE_BEGIN && sysItemId <= Constants.PROVISIONAL_ITEM_SCOPE_END){
						    playerItem.setProvisional_item_day(CommonUtil.provisional_time(nosqlService.getNosql(), playerItem, playerId));
						}
					}
					//zlm2015-5-7-限时装备-结束-------------------------------------
					itemList = itemArray.subList(fromIndex, toIndex > itemArray.size() ? itemArray.size() : toIndex);
				}
			}
			Player player = getService.getSimplePlayerById(playerId);

			if(pages==0){
				pages=1;
			}
			itemList.sort(LobbyCompareUtil.PLAYER_ITEM_COMPARATOR);
			result = Converter.playerItemList(page, pages, itemList, player.getRank(), WSysConfigService.getPlayerItemDuration().getMaxDuration());
			return result;
		} catch (Exception e) {
			log.warn("Exception in GetPlayerItems", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
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
