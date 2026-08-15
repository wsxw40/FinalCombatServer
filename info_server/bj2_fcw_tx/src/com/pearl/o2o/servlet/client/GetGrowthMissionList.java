package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.GrowthMissionVo;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetGrowthMissionList extends BaseClientServlet {
	private static final long serialVersionUID = 2412223517067028215L;
	private static Logger log = LoggerFactory.getLogger(GetGrowthMissionList.class.getName());
	private static final String[] paramNames = { "pid" ,"pageNo"};

	private static final int pageSize = 7;
	protected String innerService(String... args) {
		try {
			final int playerId = StringUtil.toInt(args[0]);
			final int pageNo = StringUtil.toInt(args[1]);
			
			Player player = getService.getPlayerById(playerId);
			List<GrowthMissionVo> growthMissions = getService.getPlayerGrowthMissionVoList(player);
			int no = pageNo - 1;
			int itemCount = growthMissions.size();
			int pageCount = itemCount%pageSize == 0 ?itemCount/pageSize:(itemCount/pageSize)+1;
			if(itemCount > 0 ){
				if(no<0||no>=pageCount){
					no = 0;
				}
				int fromIndex = no*pageSize;
				int toIndex = no == pageCount-1?itemCount:(no+1)*pageSize;
				growthMissions = growthMissions.subList(fromIndex, toIndex);
			}else{
				pageCount = 1;
			}
			int awoke = getService.missionNeedAward(playerId);
			return Converter.getGrowthMissionList(growthMissions, awoke,pageNo,pageCount);
		} catch (Exception e) {
			log.warn("Error in AcceptFriend: ", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
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
