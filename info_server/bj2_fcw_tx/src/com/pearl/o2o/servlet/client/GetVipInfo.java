package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class GetVipInfo extends BaseClientServlet {

	private static final long serialVersionUID = 8661966034817014468L;
	private static Logger log = LoggerFactory.getLogger(GetVipInfo.class.getName());
	private static final String[] paramNames = {"pid"};
	//hardcode vip item id
	private static final int VIP_ITEM=4305;
	
	protected String innerService(String... args) {
		if(!StringUtil.isAllNumber(args[0])){
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		try{
			int playerId = StringUtil.toInt(args[0]);
			Player player=getService.getPlayerById(playerId);
			CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
			SysItem vipItem=getService.getSysItemByItemId(VIP_ITEM);
			int cExpItem = player.getVipExpGiftLevel()+1;
			if(vipItem!=null){ 
				int vipLevel=player.getIsVip();
				//max level vip
				if(vipLevel==Constants.VIP_LEVEL_EXP.length){
					return Converter.GetVipInfo(vipLevel,player.getVipExp(),player.getLeftMinites(), -1,vipItem,cExpItem);
				}	
				return Converter.GetVipInfo(vipLevel,player.getVipExp(), player.getLeftMinites(),Constants.VIP_LEVEL_EXP[vipLevel],vipItem,cExpItem);
			}
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}catch(Exception e){
			log.error("Error happened when get vip info "+e.getMessage());
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		
	
	}
	
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
}
