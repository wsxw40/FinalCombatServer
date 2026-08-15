//package com.pearl.o2o.servlet.client;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.slf4j.Logger;import org.slf4j.LoggerFactory;
//
//import com.pearl.o2o.pojo.Player;
//import com.pearl.o2o.pojo.PlayerItem;
//import com.pearl.o2o.pojo.SysItem;
//import com.pearl.o2o.utils.CacheUtil;
//import com.pearl.o2o.utils.CommonUtil;
//import com.pearl.o2o.utils.Constants;
//import com.pearl.o2o.utils.Converter;
//import com.pearl.o2o.utils.StringUtil;
//
//
///**
// * @author bobby
// * 改枪界面内的配件商城
// */
//public class GetPartItemList extends BaseClientServlet {
//	private static final long serialVersionUID = -5147729884336995104L;
//	private static Logger logger=LoggerFactory.getLogger(GetPartItemList.class.getName());
//	private static final String[] paramNames = {"uid","cid","subtype","p","pid"};
//	
//	protected String innerService(String... args) {
//		try{
//			int userId = StringUtil.toInt(args[0]);
//			int playerId = StringUtil.toInt(args[1]);
//			int type = Constants.DEFAULT_PART_TYPE;
//			int subType = StringUtil.toInt(args[2]);
//			int page = StringUtil.toInt(args[3]);
//			int itemId = StringUtil.toInt(args[4]);
//			Player player = getService.getPlayerById(playerId);
//			PlayerItem playerItem=getService.getPlayerItemByItemId(playerId, Constants.DEFAULT_WEAPON_TYPE, itemId);
//			/*int newGP = player.getGPoint();
//			int newCR = 0; 
//			try{
//				newCR = getService.getCR(userId);
//			}catch(Exception e){
//				newCR = -1;
//			}*/
//			List<SysItem> sysItemList = new ArrayList<SysItem>();
//			String result="";
//			int pages = 0;
//			ArrayList<SysItem> array[] = null;	
//			if (type < Constants.NUM_ONE || type > Constants.TYPE_NUM || subType < Constants.NUM_ZERO || subType > Constants.SUB_TYPE_NUM || page < Constants.NUM_ONE) {
//				logger.warn("type or subtype not correct.");
//			}
//			List<SysItem> list = new ArrayList<SysItem>();
//			
//			
//			array = mcc.get(CacheUtil.oShopPart(playerId,subType));
//			if (array == null) {
//				array = getService.getSysItem(userId, playerId, type,subType, Constants.GENDER_BOTH,Constants.NUM_ZERO);
//			}
//			if(array!=null){
//				for(ArrayList<SysItem> arrayList:array ){
//					if(arrayList.size()!=0){
//						for(SysItem si:arrayList){
//							if(si.getPSuitable().indexOf(playerItem.getSysItem().getName())!=-1){
//								sysItemList.add(si);
//							}
//						}
//					}
//				}
//				pages=CommonUtil.getListPages(sysItemList,Constants.DEFAULT_PART_VIEW_SIZE);
//				int fromIndex=(page-1)*Constants.DEFAULT_PART_VIEW_SIZE;
//				int toIndex=(page)*Constants.DEFAULT_PART_VIEW_SIZE;
//				list=sysItemList.subList(fromIndex, toIndex>sysItemList.size()?sysItemList.size():toIndex);
//			}
//			result = Converter.sysItemList(page, pages, list,player.getRank());
//			return result;
//		}
//		catch(Exception e){
//			logger.error("Error in GetSysItem: " ,e);
//			return Converter.error("系统出现异常错误，请联系GM");
//		}
//	}
//
//	@Override
//	protected String[] paramNames() {
//		return paramNames;
//	}
//
//}
