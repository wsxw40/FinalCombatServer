package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * @author bobby
 * get the upgrade item list
 *
 */
public class UpgradeTeam extends BaseClientServlet {

	private static final long serialVersionUID = -713707000142394514L;
	private static Logger  log=LoggerFactory.getLogger(UpgradeTeam.class.getName());
	private static final String[] paramNames = {"uid","cid","page"};
	
	protected String innerService(String... args) {
		try{
			int userId = StringUtil.toInt(args[0]);
			int playerId=StringUtil.toInt(args[1]);
			int page=StringUtil.toInt(args[2]);
			int type = Constants.DEFAULT_ITEM_TYPE;
			String result = "";
			int pages = 0;
			/*String objKey = CacheUtil.oStorage(playerId, type,0);
			List<PlayerItem> itemArray[][] = new ArrayList[Constants.SUB_TYPE_NUM][];
			List<PlayerItem> itemList = new ArrayList<PlayerItem>(
					Constants.DEFAULT_PAGE_SIZE);
			if (type < Constants.NUM_ONE || type > Constants.TYPE_NUM
					|| page < Constants.NUM_ONE) {
				log.warn("type or subtype not correct in getPlayerItems.");
			} else {
				// 1. Get Player Item from cache
				itemArray = mcc.get(objKey,Constants.CACHE_TIMEOUT);
				// 2. If cache miss, get data from database and construct into
				// 2-dimension array
				if (itemArray == null) {
					itemArray = getService.getPlayerItems_O(playerId,
							type);
				}
				// 3. get page and pages, coverter
				List<PlayerItem> pageArray[] = itemArray[type - 1];

				if (pageArray != null) {
					for(int i=0;i<pageArray.length;i++){
						for(PlayerItem pi:pageArray[i]){
							SysItem si=ServiceLocator.getService.getSysItemByItemId(pi.getItemId());
							if(si.getIId()==8){
								pi.setSysItem(si);
								itemList.add(pi);
							}
						}
					}
				}
			}
			if(itemList.size()%Constants.DEFAULT_PAGE_SIZE==0){
				pages=itemList.size()/Constants.DEFAULT_PAGE_SIZE;
			}else{
				pages=itemList.size()/Constants.DEFAULT_PAGE_SIZE+1;
			}
			int fromIndex=(page-1)*Constants.DEFAULT_PAGE_SIZE;
			int toIndex=(page)*Constants.DEFAULT_PAGE_SIZE;
			List<PlayerItem> list=itemList.subList(fromIndex, toIndex>itemList.size()?itemList.size():toIndex);
			Player player = mcc.get(CacheUtil.oPlayer(playerId),Constants.CACHE_TIMEOUT);
			if (player == null) {
				player = getService.getSimplePlayerById(playerId);
			}
			result = Converter.playerItemList(page, pages, list, player.getRank());*/
			return result;
		}
		catch(Exception e){
			log.warn("Error in UpgradeTeam: ", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
	
}
