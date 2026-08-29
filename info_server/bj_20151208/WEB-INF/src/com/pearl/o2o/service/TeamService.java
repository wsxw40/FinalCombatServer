package com.pearl.o2o.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.pearl.o2o.pojo.LevelInfo;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamProclamation;
import com.pearl.o2o.pojo.TeamRecord;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.Constants.SPECIAL_ITEM_IIDS;

public class TeamService extends BaseService{

	private MessageService messageService;
	private static Logger log = LoggerFactory.getLogger(TeamService.class.getName());
	
	public List<PlayerTeam> getPlayerTeamByTeamIdSimple(int teamId) throws Exception{
		List<PlayerTeam> players = playerTeamDao.getPlayerTeam(teamId);
		return players;
	}
	public PlayerTeam getPlayerTeamByPlayerId(int playerId) throws Exception{
		PlayerTeam pt = playerTeamDao.getByPlayerId(playerId);
		if(pt != null){
			ApplicationContext context =new ClassPathXmlApplicationContext("WEB-INF");
			GetService bean = (GetService) context.getBean("getService");
			bean.joinPlayerAndPlayerTeam(pt);
		}
		return pt;
	}
	
	public Map<Integer,TeamProclamation>getTeamProclamationsByTeamId(int teamId){
		return teamProclamationDao.getTeamProclamationByTeamId(teamId);
		
	}
	public int getSmallestTeamRecordId(Map<Integer,?> map){
	   Integer smallest=null;
		for(Iterator<Integer> it= map.keySet().iterator();it.hasNext();){
		   Integer id= it.next();
		   if(smallest==null){
			   smallest=id;
		   }
		   if(smallest>=id){
			   smallest=id;
		   }
	   }
		return smallest;
	}
	public LevelInfo getLevelInfoById(int id){
		LevelInfo levelInfo=sysLevelDao.getLevelInfoById(id);
		return levelInfo;
		
	}
	public Map<Integer,TeamRecord>   getTeamRecordByTeamId(Integer teamId){
		return teamRecordDao.getTeamRecordByTeamId(teamId);
	}
	/**
	 * 
	 * @param teamId
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	public int getTeamSalary(int teamId,int playerId) throws Exception{
		PlayerTeam pt=getService.getPlayerTeamDao().getByPlayerId(playerId);
		Team team=getService.getTeam(teamId);
		int teamLevel= team.getLevel();
		int contribution= pt.getContribution();
		int level=0;
		int tempLevel=(int)(Math.log10(1-contribution*(3/Constants.TEAM_BUFF_A)*((1-Constants.TEAM_BUFF_B)/Constants.TEAM_BUFF_B))/Math.log10(Constants.TEAM_BUFF_B));
		if(tempLevel>teamLevel){
			level=teamLevel;
		}else{
			level=tempLevel;
		}
		return (int) (Math.pow(level, 2)+4*level)+1;

	}
	public int getTotalExp(int level) {
		int tmp = (int) (6958.5*(level+10)*Math.pow(1.61672, level));
		return tmp/1000*1000;
	}

	/**
	 * 超级能量块赠送。
	 * 战队升级特定等级赠送能量块
	 * @param team
	 * @date 2014/5/29
	 * @author OuYangGuang
	 * */
	public void giveSuperPower(Team team){
		int iid = 0;
		
		if(team.getLevel()>1 && team.getLevel()< 11) //站队升级2-10赠送相对应的能量快
		{
			SPECIAL_ITEM_IIDS s = SPECIAL_ITEM_IIDS.valueOf("SUPER_POWER"+(team.getLevel()-1));
			iid = s.getValue();
		}
		if(team.getLevel()==11){ //站队升级11赠送相对应的能量快
			iid = Constants.SPECIAL_ITEM_IIDS.SUPER_POWER10.getValue();//超级能量块(十)
		}
		
		if(iid > 0)
		{
			try {
				//根据IID类型锁定物品，超级能量块 IID 唯一 所以得到的list.size非0则为1
				List<SysItem> sItems = getService.getSysItemByIID(iid, Constants.DEFAULT_ITEM_TYPE);
				if(null != sItems && sItems.size() > 0)
				{
					SysItem sItem = sItems.get(0);
					int playerId = teamDao.getTeamHeader(team.getId());
					Player p = getService.getPlayerById(playerId);
//					Payment pt = null;
//					Map<Integer, Payment> m = getService.getPaymentDao().getPaymentMap(sItem.getId());
//					if(null != m && m.size()>0)
//					for(int key : m.keySet()){
//						pt = m.get(key);
//						break;
//					}
					
					//发送奖励
					//messageService.sendSystemMail(p , CommonMsg.TEAM_RANK_CHANGE + ":" +team.getLevel(), "" , sItem.getId(), new Payment(1,1));
					String content = CommonUtil.messageFormatI18N(CommonMsg.TEAM_RANK_CHANGE,team.getLevel());
					ServiceLocator.createService.sendSystemMail(p, CommonMsg.GIFT_EMAIL_SYS ,content, sItem.getId(), new Payment(1,1));
					
					log.info("TeamService: send gift current rank"+team.getLevel() + " super_power iid:"+iid +" team id :" +team.getId() +" teamName:" + team.getName());
				}else
				{
					log.error("TeamService: sItems is null:");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error(this.getClass().getPackage()+""+this.getClass().getName()+":"+ e.getMessage()+"\r\n IID:"+iid +" Team ID:"+team.getId());
			}
		}
	}
	public MessageService getMessageService() {
		return messageService;
	}
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

}
