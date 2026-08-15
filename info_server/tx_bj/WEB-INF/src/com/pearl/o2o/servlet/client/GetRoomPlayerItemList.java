package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetRoomPlayerItemList extends BaseClientServlet {

	private static final long serialVersionUID = -8385148456704669758L;
	static Logger log = LoggerFactory.getLogger(GetRoomPlayerItemList.class.getName());
	private static final String[] paramNames = {"uid","cid","p","t","gtk"};
	
	protected String innerService(String... args) {
		try {
//			int userId = StringUtil.toInt(args[0]);
//			int playerId = StringUtil.toInt(args[1]);
//			int page = StringUtil.toInt(args[2]);
//			int filter = StringUtil.toInt(args[3]);
//			String gameType =args[3];
//			int type = Constants.DEFAULT_ITEM_TYPE;
			String result = "";
//			int pages = 0;
//			String objKey = null;
////				CacheUtil.oStorage(playerId, type);
//			List<PlayerItem> itemArray[][] = new ArrayList[Constants.SUB_TYPE_NUM][];
//			List<PlayerItem> itemList = new ArrayList<PlayerItem>(
//					Constants.DEFAULT_PAGE_SIZE);
//
//			if (type < Constants.NUM_ONE || type > Constants.TYPE_NUM
//					|| page < Constants.NUM_ONE) {
//				log.warn("type or subtype not correct in getPlayerItems.");
//			} else {
//				// 1. Get Player Item from cache
//				itemArray = mcc.get(objKey,Constants.CACHE_TIMEOUT);
//				// 2. If cache miss, get data from database and construct into
//				// 2-dimension array
//				if (itemArray == null) {
//					itemArray = getService.getPlayerItems_O( playerId,
//							type);
//				}
//				// 3. get page and pages, coverter
//				List<PlayerItem> pageArray[] = itemArray[type - 1];
//
//				if (pageArray != null) {
//					for(int i=0;i<pageArray.length;i++){
//						for(PlayerItem pi:pageArray[i]){
////							if(gameType.equals(Constants.GAMETYPESTR.kZombie.name())){
////								if(pi.getSysItem().getIId()==9||pi.getSysItem().getIId()==10){
////									itemList.add(pi);
////								}
////							}else{
////								if(filter==1){
////									if(pi.getSysItem().getIId()==9||pi.getSysItem().getIId()==10||pi.getSysItem().getIId()==11||pi.getSysItem().getIId()==12){
////										itemList.add(pi);
////									}
////								}else{
////									if(pi.getSysItem().getIId()==9||pi.getSysItem().getIId()==10||pi.getSysItem().getIId()==13||pi.getSysItem().getIId()==14){
////										System.out.println("filter="+pi.getSysItem().getIId());
////										itemList.add(pi);
////									}
////								}
////								
////							}
//						}
//					}
//				}
//			}
//			if(itemList.size()%Constants.DEFAULT_PAGE_SIZE==0){
//				pages=itemList.size()/Constants.DEFAULT_PAGE_SIZE;
//			}else{
//				pages=itemList.size()/Constants.DEFAULT_PAGE_SIZE+1;
//			}
//			int fromIndex=(page-1)*Constants.DEFAULT_PAGE_SIZE;
//			int toIndex=(page)*Constants.DEFAULT_PAGE_SIZE;
//			List<PlayerItem> list=itemList.subList(fromIndex, toIndex>itemList.size()?itemList.size():toIndex);
//			Player player = mcc.get(CacheUtil.oPlayer(playerId),Constants.CACHE_TIMEOUT);
//			if (player == null) {
//				player = getService.getSimplePlayerById(playerId);
//			}
//			result = Converter.playerItemList(page, pages, list, player.getRank());
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
}
