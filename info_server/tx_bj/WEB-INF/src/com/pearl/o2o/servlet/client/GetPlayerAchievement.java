package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerAchievement;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetPlayerAchievement extends BaseClientServlet {
	private static final long serialVersionUID = 6385243821827353337L;
	private static Logger log = LoggerFactory.getLogger(GetPlayerAchievement.class.getName());
	private static final String[] paramNames = { "pid", "type", "characterid", "page" };

	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			int type = StringUtil.toInt(args[1]);
			int characterid = StringUtil.toInt(args[2]);
			int page = StringUtil.toInt(args[3]);
			Player player = getService.getSimplePlayerById(playerId);
			if (player == null) {
				throw new BaseException(ExceptionMessage.NOT_FIND_PLAYER);
			}
			List<PlayerAchievement> list = null;
			int size = 0;
			int complete = 0;
			if (Constants.NUM_ONE == type) {//normal achievement
				list = getService.getPlayerAchievementByType(playerId, Constants.NUM_ONE);
				for (PlayerAchievement pa : list) {
					if (Constants.NUM_ONE == pa.getStatus()) {
						complete++;
					}
				}
				size = list.size();
			} else if (Constants.NUM_TWO == type) {//no use
				list = getService.getPlayerAchievementByType(playerId, Constants.NUM_TWO);
				size = list.size();
			} else if (Constants.NUM_THREE == type) {//character achievement
				List<PlayerAchievement> characterPAList = getService.getPlayerAchievementByType(playerId, Constants.NUM_THREE);
				size = characterPAList.size();
				list = new ArrayList<PlayerAchievement>();
				if (characterid <= 0) {
					characterid = 1;
				}
				for (PlayerAchievement pa : characterPAList) {
					if (Constants.NUM_ONE == pa.getStatus()) {
						complete++;
					}
					if (3 == pa.getAchievement().getType() && characterid == pa.getAchievement().getCharacterId()) {
						list.add(pa);
					}
				}
			}
			int pages = 0;
			List<PlayerAchievement> resultList = null;
			if (null != list && list.size() > 0) {
				pages = (int) Math.ceil((float) (list.size()) / 4F);
				if (pages == 0) {
					pages = 1;
				}
				if (page <= 0) {
					page = 1;
				}
				if(page > pages){
					page = pages;
				}
				int startIndex = (page - 1) * 4;
				int endIndex = (page * 4);
				endIndex = Math.min(endIndex, list.size());
				resultList = list.subList(startIndex, endIndex);
			}
			return Converter.playerAchievement(resultList, pages, size, complete);
		} catch (BaseException e) {
			log.debug(e.getMessage());
			return Converter.error(e.getMessage());
		} catch (Exception e) {
			log.warn(e.getMessage(), e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	protected static long getLastSundaySeconds() {
		Calendar c = Calendar.getInstance();
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			c.add(Calendar.DAY_OF_YEAR, -7);
		}
		while (c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
			c.add(Calendar.DAY_OF_YEAR, -1);
		}
		return c.getTimeInMillis() / 1000;
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

}
