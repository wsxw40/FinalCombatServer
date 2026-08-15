package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

public class GetRoomPlayerItemList extends BaseClientServlet {
	static Logger log = Logger.getLogger(GetRoomPlayerItemList.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		try {
			int userId = StringUtil.toInt(req.getParameter("uid"));
			int playerId = StringUtil.toInt(req.getParameter("cid"));
			int filter = StringUtil.toInt(req.getParameter("t"));
			int page = StringUtil.toInt(req.getParameter("p"));
			int type = Constants.DEFAULT_ITEM_TYPE;
			String result = "";
			int pages = 0;
			String objKey = CacheUtil.oStorage(playerId, type);
			List<PlayerItem> itemArray[][] = new ArrayList[Constants.SUB_TYPE_NUM][];
			List<PlayerItem> itemList = new ArrayList<PlayerItem>(
					Constants.DEFAULT_PAGE_SIZE);

			if (type < Constants.NUM_ONE || type > Constants.TYPE_NUM
					|| page < Constants.NUM_ONE) {
				log.warn("type or subtype not correct in getPlayerItems.");
			} else {
				// 1. Get Player Item from cache
				itemArray = mcc.get(objKey);
				// 2. If cache miss, get data from database and construct into
				// 2-dimension array
				if (itemArray == null) {
					itemArray = getService.getPlayerItems_O(userId, playerId,
							type);
				}
				// 3. get page and pages, coverter
				List<PlayerItem> pageArray[] = itemArray[type - 1];

				if (pageArray != null) {
					for(int i=0;i<pageArray.length;i++){
						for(PlayerItem pi:pageArray[i]){
							if(pi.getIId()==9||pi.getIId()==10||pi.getIId()==11||pi.getIId()==12){
								if(filter==1&&pi.getIValue()==0){
									itemList.add(pi);
								}else if(filter==2){
									itemList.add(pi);
								}
								
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
			Player player = mcc.get(CacheUtil.oPlayer(playerId));
			if (player == null) {
				player = getService.getPlayerById(playerId);
			}
			result = Converter.playerItemList(page, pages, list, player
					.getRank());
			out.write(result);
		} catch (Exception e) {
			log.error("Exception in GetPlayerItems", e);
			out.write(Converter.error("系统出现异常错误，请联系GM"));
		}
	}
}
