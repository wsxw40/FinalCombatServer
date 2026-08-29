package com.pearl.o2o.servlet.client;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.nosql.NosqlKeyUtil;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * all sql scripts are under  "sql.playertop"
 * shell will generate data from these sql script and set them into redis 
 * each mode is a list in redis with key: "top:$scriptName"
 * 
 * @author Timon
 */
public class GetPlayerTop extends BaseClientServlet {

	private static final long serialVersionUID = -7902882472176853249L;
	static Logger log=LoggerFactory.getLogger(GetPlayerTop.class.getName());
	private static final String[] paramNames = {"uid","pid","t","st","self","page"};
	
	protected String innerService(String... args) {
		try{
			int userId = StringUtil.toInt(args[0]);
			int playerId = StringUtil.toInt(args[1]);
			String type = args[2]==null?"kCommon":args[2];
		
			String typeStr = "";
			String subType = args[3];
			
			/*-- t = kCommon(常规), kMode(模式), kJoy(趣味), kWeapon(武器专精)
			-- st = 
			    1:{},
			    2{kTeam,kTeamDead,kExplode,kBanner,kZombie,kTreasure}
				3:{1:kCr,2:kGp,3:kGrenade,4:kDrop,5:kGun,6:kReequip,7:kFlower,8:kDead}
				4:{"id of activity"}*/
			if(!"kWeek".equals(type)){
				typeStr=type+"Top";
			}else{
				typeStr=type;
			}
			if ("kJoy".equals(type)) {
				typeStr = subType+"Top";
			}
			
			int self = StringUtil.toInt(args[4]);
			int page = StringUtil.toInt(args[5]);
			int pageSize=Constants.TOP_PAGE_SIZE;
			int start = (page-1)*pageSize;
			int  rankNumInTop = getService.getPlayerRankNumInTop(playerId, typeStr, new Date());
			int rowNum = 0;
			//locate self 
			if(self==1){
				String selfRankKey = NosqlKeyUtil.selfRownumInTopByType(playerId, typeStr);
				String selfRowNumInTop = nosqlService.getNosql().get(selfRankKey);
				if (StringUtil.isEmptyString(selfRowNumInTop)){
					//log.error("selfRowNum is null, key is " + selfRankKey);//TODO
					//start = 0;
					//page = 1;
					//rowNum = 0;
					return Converter.warn(ExceptionMessage.TOP_NOT_HAVE);
				}else {
					rowNum = Integer.valueOf(selfRowNumInTop);
				}
				
				if(rowNum==0){
					start=0;
					page=1;
				}else if(rowNum%pageSize==0){
					start=(rowNum/pageSize-1)*pageSize;
					page=rowNum/pageSize;
				}else{
					start=((int)rowNum/pageSize)*pageSize;
					page=rowNum/pageSize+1;
				}
			}
			String key = NosqlKeyUtil.topByType(typeStr);
			List<String> rankEntry= nosqlService.getNosql().lrange(key, start, start + pageSize -1);
			
			if (rankEntry == null ) {
				log.warn("rank entry is null, key is " + key);
			}
			long pages = 0;
			long total = getService.getTopLength(key);
			
			if(total%pageSize==0){
				pages=(int)(total/pageSize);
			}else{
				pages=(int)(total/pageSize)+1;
			}
			int selectIndex=0;
			if(rowNum%10==0){
				selectIndex=10;
			}else{
				selectIndex=rowNum%10;
			}
			if(pages==0){
				pages=1;
			}
			String result = Converter.playerTop(page, pages, rankEntry,getTemplateByType(typeStr),rankNumInTop,CommonUtil.minsBetweenTimes(),selectIndex);
			return result;
		}catch (Exception e) {
			log.warn("Exception in GetPlayerTop", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
	private String getTemplateByType(String typeStr){
		if ("kCommonTop".equals(typeStr)) {
			return "PlayerTopGeneral.st";
		}else if ("kFightNumTop".equals(typeStr)) {
			return "PlayerTopFightNum.st";
		}
		else if ("kWeek".equals(typeStr)) {
			return "PlayerTopGeneral.st";
		}
		else if ("kControlTop".equals(typeStr)) {
			return "PlayerTopFun.st";
		}
		else if ("kRevengeTop".equals(typeStr)) {
			return "PlayerTopFun.st";
		}
		else if ("kAssistTop".equals(typeStr)) {
			return "PlayerTopFun.st";
		}
		else if ("kKnifeTop".equals(typeStr)) {
			return "PlayerTopFun.st";
		}
		else if ("kFireTop".equals(typeStr)) {
			return "PlayerTopFun.st";
		}
		else {
			return "PlayerTopGeneral.st";
		}
	}
}
