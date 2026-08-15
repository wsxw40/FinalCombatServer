package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.dao.impl.nonjoin.SysItemDao;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetSysItemList extends BaseClientServlet {
	private static final long serialVersionUID = -4205634889709446795L;
	private static Logger log = LoggerFactory.getLogger(GetSysItemList.class.getName());
	private static final String[] paramNames = { "uid", "pid", "t", "cid", "p", "st", "pt" };
	
	
	protected String innerService(String... args) {
		try{
			int userId = StringUtil.toInt(args[0]);
			int playerId = StringUtil.toInt(args[1]);
			int type = StringUtil.toInt(args[2]);
			int characterId = StringUtil.toInt(args[3]);
			int page = StringUtil.toInt(args[4]);
			int subType = 0;//子类型，与客户端的下拉框对应
			int payType = 0;//付款方式：0，全部  1，fc点 2，c币
			if(null != args[5]){
				subType = StringUtil.toInt(args[5]);
			}
			if(null != args[6]){
				payType = StringUtil.toInt(args[6]);
			}
			if(subType < 0){
				subType = 0;
			} 
			if(payType < 0){
				payType = 0;
			}
			if (type < Constants.NUM_ONE || type > Constants.TYPE_NUM  || page < Constants.NUM_ONE ||type==Constants.DEFAULT_ZYZDZ_TYPE) {//老商城中，不允许显示资源争夺战的物品
				log.warn("type or subtype or page not correct.");
			} else if(type!=Constants.DEFAULT_WEAPON_TYPE&&type!=Constants.DEFAULT_COSTUME_TYPE&&type!=Constants.DEFAULT_PART_TYPE){
				characterId = 0;
			}
			Player player = getService.getSimplePlayerById(playerId);
			
			List<SysItem> sysItemList = new ArrayList<SysItem>();
			String result="";
			int pages = 0;
			/*String objKey=CacheUtil.oShop(type, characterId);
			ArrayList<SysItem>[] array = mcc.get(objKey,Constants.CACHE_TIMEOUT);
			if (array == null) {
				array = getService.getSysItem(type, characterId);
			}
			if (array != null) {
				pages = array.length;
			}
			if (pages >= page&&page!=0) {
				sysItemList = array[page - 1];
			}*/
			String key = SysItemDao.getClassifyKey(characterId, type, subType, payType);
			sysItemList = getService.getClassifySysItemMap().get(key);
			int pageSize = Constants.DEFAULT_C_PAGE_SIZE;
			if(type == Constants.DEFAULT_PART_TYPE){
				pageSize = Constants.DEFAULT_B_PAGE_SIZE;
			} else if(type > 3){
				pageSize = Constants.DEFAULT_A_PAGE_SIZE;
			}
			
			pages = CommonUtil.getListPages(sysItemList, pageSize);
			
			if(pages==0){
				pages=1;
			}
			
			//page [1,pages]
			if(page<1 && page>pages){
				log.warn(String.format("GetSysItem page out range!uid:%s,pid:%s,t:%s,cid:%s,p:%s,st:%s,pt:%s,pages:%s",userId,playerId,type,characterId,page,subType,payType,pages));
				page = pages;
			}
			
			int endIndex = page * pageSize;
			int startIndex = (page - 1) * pageSize;
			
			sysItemList = sysItemList.subList(startIndex, endIndex > sysItemList.size()?sysItemList.size():endIndex);
			
//			for(SysItem si:sysItemList){
//				si.initFightNumFront();
//			}
			result = Converter.sysItemList(page, pages, sysItemList, player.getRank());
			return result;
		}
		catch(Exception e){
			log.warn("Error in GetSysItem: ",e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
}
