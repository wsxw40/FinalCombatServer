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
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

public class GetPlayerPartList extends BaseClientServlet {
	private static final long serialVersionUID = 740064435714096338L;
	static Logger log=Logger.getLogger(GetPlayerPartList.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			int playerId = StringUtil.toInt(req.getParameter("cid"));
			int subtype = StringUtil.toInt(req.getParameter("subtype"));
			int weaponId = StringUtil.toInt(req.getParameter("pid"));
			int page = StringUtil.toInt(req.getParameter("p"));
			String result = "";
			String objKey=CacheUtil.oStorage(playerId,Constants.DEFAULT_PART_TYPE);
			List<PlayerItem> itemArray[][] = new ArrayList[Constants.SUB_TYPE_NUM][];
			List<PlayerItem> itemList = new ArrayList<PlayerItem>(Constants.DEFAULT_PAGE_SIZE);
			int type = Constants.DEFAULT_PART_TYPE;
			int pages=0;
			if ( page < Constants.NUM_ONE) {
					log.warn("page is not correct in getPlayerItems.");
			} else{
				Player player=mcc.get(CacheUtil.oPlayer(playerId));
				if(player==null){
					player=getService.getPlayerById(playerId);
				}
				//get the equited item
				PlayerItem playerItem=getService.getPlayerItemByItemId(userId, playerId, Constants.DEFAULT_WEAPON_TYPE, weaponId);
				PlayerItem part=null;
				List<PlayerItem> partList=playerItem.getParts();
				if(partList!=null&&partList.size()!=0){
					for(PlayerItem item:partList){
						if(item.getSubType()==subtype){
							part=item;
							break;
						}
					}
				}
					//1. Get Player Item from cache
					itemArray=mcc.get(objKey);
					//2. If cache miss, get data from database and construct into 2-dimension array
					if (itemArray == null) {
						itemArray = getService.getPlayerItems_O(userId, playerId, type);
					}
					//3. get page and pages, coverter
					List<PlayerItem> pageArray[] = itemArray[type - 1];
					if (pageArray != null) {
						pages = itemArray[type - 1].length;
					}
					
					for(int i=0;i<pages;i++){
						List<PlayerItem> list=pageArray[i];
						for(PlayerItem item:list){
							if(item.getSubType()==subtype&&item.getPSuitable().indexOf(playerItem.getName().trim())!=-1){
								if(part!=null&&item.getId().equals(part.getId())){
									itemList.add(0, item);
								}else if(item.getParentItemId()==0&&item.getLevel()<=player.getRank()) {
									itemList.add(item);
								}
							}
						}
					}
					pages=CommonUtil.getListPages(itemList, Constants.DEFAULT_PART_VIEW_SIZE);
					int fromIndex=(page-1)*Constants.DEFAULT_PART_VIEW_SIZE;
					int toIndex=page*Constants.DEFAULT_PART_VIEW_SIZE;
					if (pages >= page) {
						itemList = itemList.subList(fromIndex, toIndex>itemList.size()?itemList.size():toIndex);
					}else {
						itemList =new ArrayList<PlayerItem>();
					}
			}
			result = Converter.playerPartList(page, pages, itemList);
			out.write(result);
		}
		catch (Exception e) {
			log.error("Exception in GetPlayerItems", e);
			out.write(Converter.error("系统出现异常错误，请联系GM"));
		}
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.doGet(req, res);
	}
}
