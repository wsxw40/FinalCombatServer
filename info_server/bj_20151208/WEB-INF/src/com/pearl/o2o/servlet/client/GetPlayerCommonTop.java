package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.nosql.NoSql;
import com.pearl.o2o.nosql.NosqlKeyUtil;
import com.pearl.o2o.pojo.Character;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetPlayerCommonTop extends BaseClientServlet {
	private static final long serialVersionUID = -7901231472176853249L;
	static Logger log=LoggerFactory.getLogger(GetPlayerCommonTop.class.getName());
	private static final String[] paramNames = {"pid","t","cid","self","page"};
	
	protected String innerService(String... args) {
		try{
			int playerId = StringUtil.toInt(args[0]);
			Player player = getService.getPlayerById(playerId);
			CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
			String type = args[1];
			String cid = args[2];
			boolean isDynamicOn = ConfigurationUtil.SWITCH_PSNTOP_DYNAMIC.getIsOn();
			int self = StringUtil.toInt(args[3]);
			int page = StringUtil.toInt(args[4]);
			int pageSize=Constants.TOP_PAGE_SIZE;
			
			NoSql nosql = nosqlService.getNosql();
			String key = "";
			String rKey = "";
			int  rankNumInTop = 0;
			if("kFightNum".equals(type)){
				key = rKey = NosqlKeyUtil.fightNumInRealTopByType(cid);
				rankNumInTop = getService.getPlayerFightNumRankInTop(playerId, cid);
			}else{
				rKey = NosqlKeyUtil.commonLevelNumInRealTopByType(type);
				key = isDynamicOn?rKey:NosqlKeyUtil.commonLevelNumInTopByType(type);
				rankNumInTop = getService.getCommonRankByType(playerId, type);
			}
			long total = nosqlService.getNosql().zCard(key);
//			int realRankNumInTop = (int)nosql.zRank(rKey, String.valueOf(playerId)) + 1;
//			if(realRankNumInTop ==0){
//				if("kFightNum".equals(type)){
//					joinFightNumInRealTop(cid, player);
//				}else{ 
//					joinCommonInRealTop(type,player);
//				}
//			}
			long pages = 0;
			if(total>Constants.CURRENT_RANK_NUM){
				total = Constants.CURRENT_RANK_NUM;
			}
			if(total%pageSize==0){
				pages=(int)(total/pageSize);
			}else{
				pages=(int)(total/pageSize)+1;
			}
			if(pages==0){
				pages=1;
			}
			if(page>pages){
				page =1;
			}
			int start = (page-1)*pageSize;
			int rowNum = 0;
			//locate self 
			if(self==1){
				rowNum = rankNumInTop>Constants.CURRENT_RANK_NUM?-1:rankNumInTop;
				if(rowNum==0){
					start=0;
					page=1;
				}else if(rowNum%pageSize==0){
					start=(int)(rowNum/pageSize-1)*pageSize;
					page=(int)(rowNum/pageSize);
				}else{
					start=(int)(rowNum/pageSize)*pageSize;
					page=(int)(rowNum/pageSize+1);
				}
			}
			Set<String> rankSet= nosql.zRange(key, start, start + pageSize -1);
			if (rankSet == null ) {
				log.warn("rank entry is null, key is " + key);
			}
			List<String> rankEntry = new ArrayList<String>();
			for(String s :rankSet){
				Player p= getService.getPlayerById(Integer.parseInt(s));
				if(p!=null){
					String newStr = generateRetStr(key,p);
					rankEntry.add(newStr);
				}else{
					log.warn("GetPlayerCommonTop/PlayerNull:\t" + s);
					nosql.zRem(key,s);
					if(!key.equals(rKey)){
						nosql.zRem(rKey, s);}
				}
				
			}
			int selectIndex=0;
			if(rowNum%pageSize==0){
				selectIndex=10;
			}else{
				selectIndex=(int)rowNum%pageSize;
			}
			
			String result = Converter.playerTop(page, pages, rankEntry,"PlayerTopCommon.st",rankNumInTop,CommonUtil.minsBetweenTimes(),selectIndex);
			return result;
		}catch (Exception e) {
			log.warn("Exception in GetPlayerCommonTop", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	private String generateRetStr(String key,Player player) throws Exception{
		NoSql nosql = nosqlService.getNosql();
		String pId = String.valueOf(player.getId()); 
		String pref = new StringBuilder().append("{").append(nosql.zRank(key, pId)+1).append(",\"").append(player.getName()).append("\",").append(player.getIsVip()).append(",").append(player.getRank()).append(",").append(player.getExp()).append(",\"").append(player.getIcon()).append("\",").append(pId).append(",").toString();
		double value = Math.abs(nosql.zScore(key, pId));
		String valueStr = "";
		if(key.indexOf("kKillDead")!=-1){
			double temp = value - (int)value;
			int killNum =(int)value;
			int deadNum = temp==0?0:(int)Math.round(1.0/temp)-2;
			valueStr = new StringBuilder().append("\"").append(killNum).append("/").append(deadNum).append("\"").toString();
		}else if(key.indexOf("kWinRate")!=-1){
				valueStr = "\""+(int)value+"\","+"\""+(int)Math.round((2000*((float)value-(int)value)))/10.0+ "%\"";
		}else{
			valueStr = String.valueOf((int)value);
		}
		String result = new StringBuilder().append(pref).append(valueStr).append("},").toString();
		return result;	
	}
	/**
	 * 玩家加入真实排行榜记录
	 * @param type
	 * @param player
	 * @throws Exception
	 */
	private void joinCommonInRealTop(String type,Player player) throws Exception{
		String key = NosqlKeyUtil.commonLevelNumInRealTopByType(type);
		String pId = String.valueOf(player.getId());
		NoSql nosql = nosqlService.getNosql();
		double value = 0;
		
		if("kScore".equals(type)){
			value = player.getGScore();
		}else if("kAssist".equals(type)){
			value = player.getGAssist();
		}else if("kWinRate".equals(type)){
			value = player.getGWin()+player.getWinRate()/2;//按照胜场降序，再按胜率降序
		}else if("kKillDead".equals(type)){
			int killNum = player.getGKill();
			int deadNum = player.getGDead();
			value = killNum +  1.0/(deadNum+2);//按照击杀数降序，再按死亡数升序排列
		}
		int total = (int)nosql.zCard(key);
		
		if(total<Constants.REAL_TOP_RANK_NUM){
			nosql.zAdd(key, -value, pId);
		}else {
			double lastValue = Math.abs(nosql.zrangeWithScores(key, Constants.REAL_TOP_RANK_NUM-1, Constants.REAL_TOP_RANK_NUM-1).iterator().next().getScore());
			if(value > lastValue){
				nosql.zAdd(key, -value, pId);
			}
		}
	}
	/**
	 * 玩家加入真实排行榜记录
	 * @param cid
	 * @param player
	 * @throws Exception
	 */
	private void joinFightNumInRealTop(String cid ,Player player) throws Exception{
		String key = NosqlKeyUtil.fightNumInRealTopByType(cid);
		String pId =  String.valueOf(player.getId());
		int chid = Integer.parseInt(cid);
		NoSql nosql = nosqlService.getNosql();
		Character character = null;
		int value = 0;
		if(chid!=0){//0 表示综合战斗力
			character = getService.getCharacterByCharacterId(player.getId(), Integer.parseInt(cid));
			value = character.getFightNum();
		}else{
			value = player.getMaxFightNum();
		}
		int total = (int)nosql.zCard(key);
		
		if(total<Constants.REAL_TOP_RANK_NUM){
			nosql.zAdd(key, -value, pId);
		}else{
			int lastValue = (int)Math.abs(nosql.zrangeWithScores(key, Constants.REAL_TOP_RANK_NUM-1, Constants.REAL_TOP_RANK_NUM-1).iterator().next().getScore());
			if(value > lastValue){
				nosql.zAdd(key, -value, pId);
			}
		}
	}
	
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
