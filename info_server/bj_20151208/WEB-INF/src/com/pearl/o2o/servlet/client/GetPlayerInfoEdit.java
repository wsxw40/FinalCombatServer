package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerAchievement;
import com.pearl.o2o.pojo.SysIcon;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetPlayerInfoEdit extends BaseClientServlet {
	private static final long serialVersionUID = 6385243821827353337L;
	private static Logger log = LoggerFactory.getLogger(GetPlayerInfoEdit.class.getName());	
	private static final String[] paramNames = {"pid","type","page"};

	protected String innerService(String... args) {
		try{					
			int playerId = StringUtil.toInt(args[0]);
			int type = StringUtil.toInt(args[1]);//1--15,2--32,3--40  1：头像 2：称号 3：一般成就 4：角色成就
			int page = StringUtil.toInt(args[2]);
			
			Player player = null;
			
			//get basic real time information

			player = getService.getSimplePlayerById(playerId);
			if(player==null){
				throw new BaseException(ExceptionMessage.NOT_FIND_PLAYER);
			}
			
			if(1 > type || 4 < type || page < 0){
				throw new BaseException(ExceptionMessage.NOT_RIGHT_PARAM);
			}
			
			int pageSize = 1;
			int totalPage = 1;
			List<SysIcon> iconList = null;
			List<PlayerAchievement> achievementList = null;
			List<PlayerAchievement> resultList = new ArrayList<PlayerAchievement>();
			if(1 == type){
				iconList = getService.getSysIcon();
				pageSize = 10;
				totalPage = (iconList.size() % pageSize == 0) ? (iconList.size() / pageSize) : (iconList.size() / pageSize + 1);
				page = page > totalPage ? totalPage : page;
				iconList = iconList.subList(pageSize * (page - 1), (pageSize * page) > iconList.size() ? (iconList.size()) : (pageSize * page));
			} else if(2 == type){
				achievementList = getService.getAllPlayerAchievementList(playerId);
				for(PlayerAchievement a : achievementList){
					if(!"".equals(a.getAchievement().getTitle())){
						resultList.add(a);
					}
				}
				pageSize = 32;
			} else if(3 == type){
				achievementList = getService.getAllPlayerAchievementList(playerId);
				for(PlayerAchievement a : achievementList){
					if(a.getAchievement().getType() == 1){
						resultList.add(a);
					}
				}
				pageSize = 40;
			} else if(4 == type){
				achievementList = getService.getAllPlayerAchievementList(playerId);
				for(PlayerAchievement a : achievementList){
					if(a.getAchievement().getType() == 3){
						resultList.add(a);
					}
				}
				pageSize = 40;
			}
			if(type != 1){
				totalPage = (resultList.size() % pageSize == 0) ? (resultList.size() / pageSize) : (resultList.size() / pageSize + 1);
				page = page > totalPage ? totalPage : page;
				resultList = resultList.subList(pageSize * (page - 1), (pageSize * page) > resultList.size() ? resultList.size() : (pageSize * page));
			}
			String[] titleList = {CommonMsg.TITLE1,CommonMsg.TITLE2,CommonMsg.TITLE3,CommonMsg.TITLE4};
			
			return Converter.playerInfoEdit(player, iconList,titleList,resultList, type, totalPage);
		}catch (BaseException e) {
			log.debug(e.getMessage());
			return  Converter.error(e.getMessage());
		}
		catch (Exception e) {
			log.warn(e.getMessage(),e);
			return  Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	
	protected static long getLastSundaySeconds(){
		Calendar c = Calendar.getInstance();
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			c.add(Calendar.DAY_OF_YEAR, -7);
		}
		while(c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY){
			c.add(Calendar.DAY_OF_YEAR, -1);
		}
		return c.getTimeInMillis()/1000;
	}
	
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
}
