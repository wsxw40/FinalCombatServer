package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.nosql.object.playerevent.BasePlayerEvent;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetPlayerInfo extends BaseClientServlet {
	private static final long serialVersionUID = 6385243821827353337L;
	private static Logger log = LoggerFactory.getLogger(GetPlayerInfo.class.getName());	
	private static final String[] paramNames = {"pid","name"};

	protected String innerService(String... args) {
		try{					
			int playerId = StringUtil.toInt(args[0]);
			String cName = args[1];
			Player player = null;
			
			//get basic real time information
			if (!StringUtil.isEmptyString(cName)){
				player = getService.getPlayerByName(cName);
				if (player == null) {
					return Converter.error(ExceptionMessage.PALERY_NOT_EXIST);
				}
				playerId = player.getId();
				
			}
			player = getService.getPlayerById(playerId);
			if(player==null){
				throw new BaseException(ExceptionMessage.NOT_FIND_PLAYER);
			}
			if(player.getBuffList().size()>0){
				player.setBuffList(getService.filterBuffListFont(player));
			}
			/*int paNum = 0,gold = 0,silver=0,bronze = 0;
			List<PlayerAchievement> list = getService.getPlayerAchievement(player.getId());
			for(PlayerAchievement pa : list){
				if(pa.getVisible() == 1){
					paNum ++;
					if(pa.getStatus() == 1){
						if(pa.getAchievement().getColor() == 1){
							bronze ++;
						} else if(pa.getAchievement().getColor() == 2){
							silver ++;
						} else if(pa.getAchievement().getColor() == 3){
							gold ++;
						}
					}
				}
			}
			player.setTotal(paNum);
			player.setBronze(bronze);
			player.setSilver(silver);
			player.setGold(gold);
			updateService.updatePlayerInfo(player);*/
			int  rankNumInTop = getService.getPlayerFightNumRankInTop(playerId, String.valueOf(Constants.CHARACTER_IDS[0]));
//			String rankKey = NosqlKeyUtil.selfLevelnumInTopByType(playerId, "kCommonTop");
//			int  rankNumInTop =StringUtil.toInt( nosqlService.getNosql().get(rankKey));
			
			List<BasePlayerEvent> news = nosqlService.getPlayerEvent(player.getId(), player.getName());
			Integer mateRank=getService.getQW_MateRank(playerId);
			return Converter.playerInfo(player, news,rankNumInTop,mateRank);			
		}catch (BaseException e) {
			log.debug(e.getMessage());
			return  Converter.error(e.getMessage());
		}
		catch (Exception e) {
			log.warn(e.getMessage(),e);
			return  Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
}
