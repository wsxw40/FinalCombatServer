package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;

public class GetReequipWeaponList extends BaseClientServlet {

	private static final long serialVersionUID = 2608596133991765474L;
	static Logger logger=LoggerFactory.getLogger(GetReequipWeaponList.class.getName());
	private static final String[] paramNames = {"uid","cid","pid","p"};
	
	protected String innerService(String... args) {
		String result="";	
		
//		int pages;
		try{
//			int userId = StringUtil.toInt(args[0]);
//			int playerId=StringUtil.toInt(args[1]);
//			int itemId=StringUtil.toInt(args[2]);
//			int page=StringUtil.toInt(args[3]);
//		
//			List<PlayerItem> returnList=getService.getReequipWeaponList(playerId, Constants.DEFAULT_WEAPON_TYPE,itemId);
//			if(returnList.size()%Constants.DEFAULT_PART_VIEW_SIZE==0){
//				pages=returnList.size()/Constants.DEFAULT_PART_VIEW_SIZE;
//			}else{
//				pages=returnList.size()/Constants.DEFAULT_PART_VIEW_SIZE+1;
//			}
//			int fromIndex=(page-1)*Constants.DEFAULT_PART_VIEW_SIZE;
//			int toIndex=(page)*Constants.DEFAULT_PART_VIEW_SIZE;
//			List<PlayerItem> list=returnList.subList(fromIndex, toIndex>returnList.size()?returnList.size():toIndex);
//			
//			Player player=mcc.get(CacheUtil.oPlayer(playerId),Constants.CACHE_TIMEOUT);
//			if(player==null){
//				player=getService.getPlayerById(playerId);
//			}
//			result=Converter.weaponPackList(page, pages, list,player.getRank());
			return result;
		}
//		catch (BaseException be) {
//			return Converter.commonFeedback(be.getMessage());
//		}
		catch(Exception e){
			logger.warn("Error in GetItemPackList: ",e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
}
