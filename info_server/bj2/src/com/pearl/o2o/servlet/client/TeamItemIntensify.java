package com.pearl.o2o.servlet.client;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerInfo;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ItemIntensifyUtil;
import com.pearl.o2o.utils.StringUtil;

public class TeamItemIntensify extends BaseClientServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2448454949116504157L;
	private Logger log = LoggerFactory.getLogger(TeamItemIntensify.class);
	private static final String [] paramNames={"playerId","teamItemId","currency","cost","intensifyType"};
	@Override
	protected String innerService(String... strings) {
		boolean intensifySucc=false;
		if(!strings[0].matches("^\\d+$")){		
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		if(!strings[1].matches("^\\d+$")){		
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		if(!strings[2].matches("^\\d+$")){		
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		if(!strings[3].matches("^\\d+$")){		
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		if(!strings[4].matches("^\\d+$")){		
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		
		int playerId = StringUtil.toInt(strings[0]);
		int teamItemId = StringUtil.toInt(strings[1]);
		int currency = StringUtil.toInt(strings[2]);
		int cost=StringUtil.toInt(strings[3]);
		int intensifyType = StringUtil.toInt(strings[4]);//强化什么属性
		
		Player player =null;
		SysItem sysItem=null;
		int trueCOST=0, leftMoney=0,payType=0;
		StringBuffer sb = new StringBuffer(playerId+"|"+teamItemId+"|"+currency+"|"+intensifyType+"|");
		try {
			//检测是否输过二级密码
//			if(!checkEnterSPW(playerId)){
//				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
//			}
			player = getService.getPlayerById(playerId);
			TeamItem ti = getService.getTeamItemById(teamItemId);
			sysItem=ti.getSysItem();
			if(ti.getShowQuantity()<=0){
				return Converter.error(ExceptionMessage.ITEM_NOT_EXIST);
			}
			Team team = getService.getTeamById(ti.getTeamId());
			SysItem si = getService.getSysItemByItemId(ti.getItemId());
			String property = ti.getIntensifyInfo(intensifyType);
			int curLevel = 0;//未强化为0级
			double curExp =0;
			double value = 0;
			int levelChange=0;
			int intensifyLevel=0;
//			int isUp = 0;
			if(property!=null&&!"".equals(property)){
				String [] propertyInfo = property.split(",");
				curLevel = Integer.valueOf(propertyInfo[0]);
				curExp = Double.valueOf(propertyInfo[1]);
				value = Double.valueOf(propertyInfo[2]);
			}
			 if(curLevel==ItemIntensifyUtil.getMaxLevel(si.getSubType())){
				 return Converter.error(ExceptionMessage.IS_MAX_LEVEL);
			 }
			sb.append(property+"|");
			String newProperty="";
			if(team==null){
				return Converter.error("no this team！");
			}
			if(currency==Constants.RES_PAY_TEAM&&team.getHeaderId()!=player.getId()){//是否是队长
				return Converter.error(ExceptionMessage.NOT_LEADER_POWER);
			}
			
			HashMap<String, Integer> teamRes=team.getLatestTeamRes();
			if(currency==Constants.RES_PAY_TEAM){//钱是否足够
				if(cost>teamRes.get(Team.RES)){
					return Converter.error(ExceptionMessage.NOT_ENOUGH_USABLE_RESOURCE);
				}
			}else if(currency==Constants.CR_PAY){
				PlayerInfo playerInfo = getService.getPlayerInfoById(player.getId());
				if(cost>playerInfo.getXunleiPoint()){
					return Converter.error(ExceptionMessage.NOT_ENOUGH_FC);
				}
			}
					
					//资源点：FC:RMB = 1000:100:1,将exp直接设成资源点，
					
			int needRES = ItemIntensifyUtil.getIntensifyPrice(currency, ti.getLevel(), intensifyType, si, curLevel);
			int outOfCost = 0;
			if(curExp+ItemIntensifyUtil.CRAndRESChangeToExp(currency, cost)>=needRES){//是否要升级，如果要升能
				//可能升多级，超了要判断，如果超出30级则p 的值为 :   等级,经验,超出的经验
				String p = upgrade(currency,curExp+ItemIntensifyUtil.CRAndRESChangeToExp(currency, cost),curLevel,intensifyType,ti,si);
				String [] info = p.split(",");
				//TODO //teamItem 强化30级为一个大的等级 value为属性的加成值
				value = ItemIntensifyUtil.getIntensifyProperty(ti.getLevel(), si.getSubType(), intensifyType, Integer.valueOf(info[0]));
				if(ItemIntensifyUtil.getMaxLevel(si.getSubType())==Integer.valueOf(info[0])){//超了
					outOfCost = ItemIntensifyUtil.expChangeToCROrRES(currency, Double.valueOf(info[2]));
					cost = cost-outOfCost;
					newProperty = info[0]+","+info[1]+","+value;
				}else{
					newProperty=info[0]+","+info[1]+","+value;
				}
				levelChange = 1;
				intensifyLevel=Integer.valueOf(info[0]);
			}else{//没有发生升级
				value = ItemIntensifyUtil.getIntensifyProperty(ti.getLevel(), si.getSubType(), intensifyType,curLevel);
				newProperty = curLevel+","+(curExp+ItemIntensifyUtil.CRAndRESChangeToExp(currency, cost))+","+value;
			}
			ti.setIntensifyInfo(intensifyType, newProperty);
			sb.append(newProperty+"|");	
			int res = 0;//充fc是返回的资源点
			if(currency ==Constants.CR_PAY){
				PlayerInfo playerInfo = getService.getPlayerInfoById(player.getId());
				leftMoney=playerInfo.getXunleiPoint()-cost;
				sb.append(playerInfo.getXunleiPoint()+"|"+cost);
				res = getService.getRewardToPlayer4TeamZYZDZ(player, team, cost);
				HashMap<String, Integer> playerRes=player.getLatestPlayerRes(team.getTeamSpaceLevel());
				player.setUsableResource(playerRes.get(Player.RES)+res);
				playerInfo.setXunleiPoint(leftMoney);
				updateService.updatePlayerInfoOnly(player);
				updateService.updateTeamItem(team.getId(), ti);
				updateService.payFc(playerId, cost);
				soClient.messageUpdatePlayerMoney(player);
				
				{
					trueCOST=cost;
					payType=Constants.CR_PAY;
				}
				
			}else if(currency==Constants.RES_PAY_TEAM){
				leftMoney=teamRes.get(Team.RES)-cost;
				sb.append(teamRes.get(Team.RES)+"|"+cost);
				team.setUsableResource(leftMoney);
				updateService.updateTeamItem(ti.getId(), ti);
				updateService.updateTeamInfo(team);
				{
					trueCOST=cost;
					payType=Constants.RES_PAY_TEAM;
				}				
			}
			intensifySucc=true;
			return Converter.getIntensifyInfo(res, outOfCost,levelChange,intensifyLevel,0,null);

		} catch (Exception e) {
			log.warn("TeamItemIntensify",e);
		}finally{
			createService.createIntensifyTeamItemLog(sb.toString());
			if(intensifySucc){
				createService.createZYZDZPayLog(player, sysItem, trueCOST, leftMoney,payType,"升级");
			}
		}
		
		
		return super.innerService(strings);
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	//TODO 29升30的时候要调试一下
	private String upgrade(int currency,double curExp,int level,int intensifyType,TeamItem ti,SysItem si) throws Exception{
		int needRES = ItemIntensifyUtil.getIntensifyPrice(currency, ti.getLevel(), intensifyType, si, level);
		String s="";
		if(level>=ItemIntensifyUtil.getMaxLevel(si.getSubType())-1){
			if(level>=ItemIntensifyUtil.getMaxLevel(si.getSubType())){
				return ItemIntensifyUtil.getMaxLevel(si.getSubType())+","+0+","+curExp;
			}
			if(curExp>needRES){
				return ItemIntensifyUtil.getMaxLevel(si.getSubType())+","+0+","+(curExp-needRES);
			}
		}
		if(curExp<=needRES){
			return level+","+curExp;
		}else{
		 s=upgrade(currency,curExp-needRES,level+1,intensifyType,ti,si);
		}
		return s;
	}
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[1]));
	}
}
