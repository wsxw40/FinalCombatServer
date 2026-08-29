
package com.pearl.o2o.servlet.client;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.BattleFieldRobDaily;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ResRecord;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;


public class TeamResRecordList extends BaseClientServlet {

	private static final long serialVersionUID = 7794098604530646005L;
	static Logger logger=LoggerFactory.getLogger(TeamResRecordList.class.getName());
	/**
	 * tid:teamId
	 * type:查询类型{1：匹配，2：挑战}
	 */
	private static final String[] paramNames = {"tid","type"};
	/**
	 * 获取战队详细信息
	 */
	protected String innerService(String... args) {
		try{
			int teamId = StringUtil.toInt(args[0]);
			int iType= StringUtil.toInt(args[1]);
			Constants.BattleFieldRobDailyType oType=Constants.BattleFieldRobDailyType.MATCH;
			
			Date now=new Date();
			Date pre=now;
			
//			Calendar c = Calendar.getInstance();
		
			if(iType==Constants.BattleFieldRobDailyType.CHALLENGE.getValue()){
				oType=Constants.BattleFieldRobDailyType.CHALLENGE;
			}
			
			pre=new Date(now.getTime()-1000l*60*60*24*7);//1周前
			
			List<ResRecord> list=new ArrayList<ResRecord>();
			Team myTeam=getService.getTeamById(teamId);
			List<BattleFieldRobDaily>  bfCol= ServiceLocator.getService.getBattleFieldRobDailyDao().getBattleFieldRobDailyByTeamAndTime(teamId,now, pre,oType);
			if(bfCol!=null&&myTeam!=null){
				for (BattleFieldRobDaily battleFieldRobDaily : bfCol) {
					Team attackTeam=null;
					Team defenceTeam=null;
					boolean isAttacker;
					if(myTeam.getId()==battleFieldRobDaily.getAttTeamId()){
						attackTeam=myTeam;
						defenceTeam=getService.getTeamById(battleFieldRobDaily.getDefTeamId());
						isAttacker=true;
					}else{
						attackTeam=getService.getTeamById(battleFieldRobDaily.getAttTeamId());
						defenceTeam=myTeam;
						isAttacker=false;
					}
					
					if(attackTeam==null||defenceTeam==null){
						continue;
					}
					ResRecord resRecord=new ResRecord();
					resRecord.setIsAttacker(isAttacker);
					resRecord.setAttackTeam(attackTeam);
					resRecord.setDefenceTeam(defenceTeam);
					resRecord.setBattleFieldRobDaily(battleFieldRobDaily);
					list.add(resRecord);
				}
			}
			
			return Converter.teamResRecord(list);
		}catch (Exception e) {
			logger.warn(e.getMessage(),e);
			return Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

}
