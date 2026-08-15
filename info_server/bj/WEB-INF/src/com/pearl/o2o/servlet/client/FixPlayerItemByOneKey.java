package com.pearl.o2o.servlet.client;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.PlayerPack;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class FixPlayerItemByOneKey extends BaseClientServlet {
	private static final long serialVersionUID = 348899082580431429L;
	static Logger log = LoggerFactory.getLogger(FixPlayerItemByOneKey.class.getName());
	private String[] paramNames={"pid","cid"};
	@Override
	protected String innerService(String... args) {
		try{
			int playerId = StringUtil.toInt(args[0]);
			int characterId=StringUtil.toInt(args[1]);
			List<PlayerPack> playerPacks = ServiceLocator.getService.getPlayerPackList(playerId);
			Map<Integer, PlayerItem> playerItemMap = getService.getPlayerItemMapByType1(playerId, Constants.DEFAULT_WEAPON_TYPE, characterId, 0);
			for(PlayerPack pp : playerPacks){
				PlayerItem pi = playerItemMap.get(pp.getPlayerItemId());
				if (pi != null && pi.getPlayerItemUnitType() == 0 && pi.getIsDefault().equals("N")) {
					SysItem si = ServiceLocator.getService.getSysItemByItemId(pi.getItemId());
					pi.setSysItem(si);
					pi.initWithoutPart(si);
					updateService.fixPlayerItem(0, playerId, pi.getId(), Constants.DEFAULT_WEAPON_TYPE);
				}
			}
			return "result=1";
		}
		catch (BaseException be) {
			log.debug("Exception in FixPlayerItemByOneKey:"+be.getMessage());	
			return Converter.commonFeedback(be.getMessage());	
		}
		catch(Exception e){
			log.warn("Error in FixPlayerItemByOneKey: " , e);
			return Converter.commonFeedback(e.getMessage());	
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]));
	}
}
