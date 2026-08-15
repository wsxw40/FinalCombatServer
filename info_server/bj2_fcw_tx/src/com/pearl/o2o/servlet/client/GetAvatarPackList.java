package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetAvatarPackList extends BaseClientServlet {
	private static final long serialVersionUID = -7186146783164259451L;
	static Logger log = LoggerFactory.getLogger(GetAvatarPackList.class.getName());
	private String[] paramNames={"uid","cid","pack","seq","page"};
	@Override
	protected String innerService(String... args) {
		int pages;
		try{
//			int userId = StringUtil.toInt(args[0]);
//			int playerId=StringUtil.toInt(args[1]);
//			int packId=StringUtil.toInt(args[2]);
//			int seq=StringUtil.toInt(args[3]);
//			int page=StringUtil.toInt(args[4]);
//			List<PlayerItem> returnList=getService.getItemPackList( playerId, Constants.DEFAULT_COSTUME_TYPE, packId, seq);
//			if(returnList.size()%Constants.DEFAULT_PAGE_SIZE==0){
//				pages=returnList.size()/Constants.DEFAULT_PAGE_SIZE;
//			}else{
//				pages=returnList.size()/Constants.DEFAULT_PAGE_SIZE+1;
//			}
//			int fromIndex=(page-1)*Constants.DEFAULT_PAGE_SIZE;
//			int toIndex=(page)*Constants.DEFAULT_PAGE_SIZE;
//			List<PlayerItem> list=returnList.subList(fromIndex, toIndex>returnList.size()?returnList.size():toIndex);
//			Player player=mcc.get(CacheUtil.oPlayer(playerId),Constants.CACHE_TIMEOUT);
//			if(player==null){
//				player=getService.getPlayerById(playerId);
//			}
//			return Converter.avatarPackList(page, pages, list,player.getRank());
			return "";
		}
		catch(Exception e){
			log.warn("Error in GetItemPackList: " , e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
