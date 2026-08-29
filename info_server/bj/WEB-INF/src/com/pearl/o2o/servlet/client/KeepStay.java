package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerMission;
import com.pearl.o2o.pojo.SysMission;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class KeepStay extends BaseClientServlet {
	private static final long serialVersionUID = 585111220320711936L;
	static Logger log = LoggerFactory.getLogger(GetMessage.class.getName());
	private String[] paramNames={"pid"};

	@Override
	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			Player player = getService.getSimplePlayerById(playerId);
			if(null == player){
				return Converter.error(ExceptionMessage.NOT_FIND_PLAYER);
			}
			List<Integer> datas = nosqlService.getStayData(player);
			List<PlayerMission> missions = getService.getPlayerMissionList(playerId);
			for(PlayerMission pm : missions){
				//join SysMission
				SysMission sysMission = getService.getSysMissionById(pm.getSysMissionId());
				pm.setDescription(CommonUtil.messageFormat(sysMission.getDescription(), pm.getTarget()));
//				updateService.joinPrize(sysMission);
				pm.setSysMission(sysMission);
			}
			return Converter.keepStay(missions, datas.get(0), datas.get(1), datas.get(2), datas.get(3), datas.get(4), datas.get(5), datas.get(6), datas.get(7));
		} catch (Exception e) {
			log.warn("Error in KeepStay: " , e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
