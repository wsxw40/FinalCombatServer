package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetMessageItemList extends BaseClientServlet {

	private static final long serialVersionUID = 585111220320711936L;
	static Logger log = LoggerFactory.getLogger(GetMessageItemList.class.getName());
	private String[] paramNames={"uid","pid","cid","type","page"};
	@Override
	protected String innerService(String... args) {
		try{
//			int userId = StringUtil.toInt(args[0]);
			int playerId=StringUtil.toInt(args[1]);
			int characterId=StringUtil.toInt(args[2]);
			int type = Integer.valueOf(args[3]);
			int page = Integer.valueOf(args[4]);
			
			if (type < Constants.NUM_ONE || type > Constants.TYPE_NUM || page < Constants.NUM_ONE) {
				log.warn("type or subtype not correct.");
			} else if (type != Constants.DEFAULT_WEAPON_TYPE && type != Constants.DEFAULT_COSTUME_TYPE && type != Constants.DEFAULT_PART_TYPE) {
				characterId = 0;
			}
			int subType = 0;
			String result = "";
			int pages = 0;

			List<PlayerItem> itemList = null;

			if (type < Constants.NUM_ONE || type > Constants.TYPE_NUM || page < Constants.NUM_ONE) {
				log.warn("type or subtype not correct in getPlayerItems.");
			} else {
//				List<PlayerItem> itemArray = getService.getPlayerItemList(playerId, type, characterId);
				List<PlayerItem> itemArray = getService.getPlayerItemList1(playerId, type, characterId, subType);
				
				if (itemArray != null) {
//					List<PlayerItem> list=new ArrayList<PlayerItem>();
//					for(PlayerItem pi:itemArray){
//						if(Constants.BOOLEAN_NO.equals(pi.getIsBind())){
//							list.add(pi);
//						}
//					}
					if(type == Constants.DEFAULT_ITEM_TYPE){
						itemArray = new ArrayList<PlayerItem>(Collections2.filter(itemArray, new Predicate<PlayerItem>() {
							@Override
							public boolean apply(PlayerItem input) {
								SysItem sysItemByItemId = input.getSysItem();
								return !(null != sysItemByItemId&&sysItemByItemId.getIId() == 1&&sysItemByItemId.getIBuffId()>=61&&sysItemByItemId.getIBuffId()<=74);
							}
						}));
					}
					pages = CommonUtil.getMessageListtPages(itemArray, type);
					if (page > pages || page <= 0) {
						page = 1;
					}
					int fromIndex = (page - 1) * Constants.PAGE_SIZE_MESSAGE_ITEM[type - 1];
					int toIndex = (page) * Constants.PAGE_SIZE_MESSAGE_ITEM[type - 1];
					itemList = itemArray.subList(fromIndex, toIndex > itemArray.size() ? itemArray.size() : toIndex);
				}
			}
			Player player = getService.getSimplePlayerById(playerId);
			
			if(pages==0){
				pages=1;
			}
			result = Converter.playerItemList(page, pages, itemList, player.getRank());
			return result;
		}catch(Exception e){
			log.warn("Error in GetMessageList: " ,e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
