package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class VipRandomList extends BaseClientServlet {

	private static final long serialVersionUID = -229367127237026087L;
	private static Logger log = LoggerFactory.getLogger("vipsafecabinet");
	private static final String[] paramNames = { "pid","playeritemid"};
	
	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			int playerItemId = StringUtil.toInt(args[1]);
			PlayerItem playerVip = getService.getPlayerItemByItemId(playerId,Constants.DEFAULT_OPEN_TYPE, playerItemId);
			Player player = getService.getPlayerById(playerId);
			CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
			//每次进入列表页面重新随机好15个VipSafeCabinet,并将VipSafeCabinet id存入redis
			List<OnlineAward> vscList = createService.createVipRandomAwardList(playerId);
			
			//初次进入便随机Constants.VIP_OPEN_CHANCE_NUM个index,玩家每次访问，返回第一个，然后把第一个remove出列表
			createService.createRandomVipAwardIndexs(playerId,vscList);
			
			int openChanceNum = Constants.VIP_OPEN_CHANCE_NUM;
			int vipNum = 0;
			if(playerVip!=null){
				vipNum=playerVip.getQuantity();
			}
			int needVipNum = Constants.VIP_START_COSTS[0];
			return Converter.vipRandomList(openChanceNum,vipNum,needVipNum,vscList);

		}catch (BaseException e) {
			log.warn("VipRandomList/Warn:\t",e.getMessage());
			return Converter.warn(e.getMessage());
		} catch (Exception e) {
			log.error("VipRandomList/Error:\t", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}

}
