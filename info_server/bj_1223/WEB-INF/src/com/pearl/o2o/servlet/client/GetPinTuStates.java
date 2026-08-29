package com.pearl.o2o.servlet.client;

import java.util.HashSet;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class GetPinTuStates extends BaseClientServlet {

	private static final long serialVersionUID = 661949539508628790L;
	private static final String[] paramNames = { "pid"};
	private static Logger logger = LoggerFactory.getLogger(GetPinTuStates.class);
	@Override
	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			String playerPinTuFlags = nosqlService.getNosql().get(Constants.PLAYER_PT_FLAG_KEY+playerId);
			if(playerPinTuFlags==null||!playerPinTuFlags.matches("[01]{"+Constants.PLAYER_PT_PRI_FLAGS.length()+"}[0-" + Constants.PLAYER_PT_TOTAL_CHANCE + "]")){
				playerPinTuFlags = Constants.PLAYER_PT_PRI_FLAGS + Constants.PLAYER_PT_TOTAL_CHANCE;
				openForVip(playerId,playerPinTuFlags);		
			}
			if(playerPinTuFlags.equals(Constants.PLAYER_PT_PRI_FLAGS + Constants.PLAYER_PT_TOTAL_CHANCE)){
				openForVip(playerId,playerPinTuFlags);	
			}
			
			String playerPinTuFlagsRet = playerPinTuFlags.replaceAll("", ",").replaceFirst(",", "");//返回给客户端的当前拼图状态
			return "flags={" + playerPinTuFlagsRet + "}";
		}catch (BaseException e) {
			logger.warn("Exception in GetPinTuStates :" + e.getMessage());
			return Converter.warn(e.getMessage());
		} catch (Exception e) {
			logger.error("GetPinTuStates error happens : ",e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
	private HashSet<Integer> getRandoms(int num){
		HashSet<Integer> cards=new HashSet<Integer>();
		Random random=new Random();
		int length=Constants.PLAYER_PT_PRI_FLAGS.length()-1;
		//make sure num<5
		num= num>6 ? 4: Math.round(num/2.0f)+1;
		while(cards.size()<(num+1)){
			cards.add(random.nextInt(length));
		}
		
		cards.remove(cards.iterator().next());
		return cards;	
	}
	
	private void openForVip(int playerId,String playerPinTuFlags)throws Exception{
		//新图时，不同vip等级翻不同张数 ,vip6直接翻4张
		Player player=getService.getPlayerById(playerId);
		CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
		int vipLevel=player.getIsVip();
		if(vipLevel>=3){
			HashSet<Integer> openCards=getRandoms(vipLevel);
			for(int i : openCards){
				playerPinTuFlags = StringUtil.replaceByIndex(playerPinTuFlags, i, '1');
				ServiceLocator.getLuckyPackageLog.info("Vip "+vipLevel+" open Pintu Card when begin new Pintu:\t" + playerId + "\t" + i);					
			}
			nosqlService.getNosql().set(Constants.PLAYER_PT_FLAG_KEY+ playerId, playerPinTuFlags);
		}
	}
}
