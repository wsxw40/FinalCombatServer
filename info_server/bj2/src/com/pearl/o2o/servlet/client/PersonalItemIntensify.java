package com.pearl.o2o.servlet.client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerInfo;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamItem;
import com.pearl.o2o.pojo.TeamTechnology;
import com.pearl.o2o.schedule.SynCacheWithDBTask;
import com.pearl.o2o.servlet.server.SynCacheServlet;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.DateUtil;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.ItemIntensifyUtil;

public class PersonalItemIntensify extends BaseClientServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1382427796919404926L;
	private Logger log = LoggerFactory.getLogger(PersonalItemIntensify.class);
	private static final String [] paramNames={"playerId","playerItemId","currency","cost","intensifyType"};
	@Override
	protected String innerService(String... strings) {
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
		int playerItemId = StringUtil.toInt(strings[1]);
		int currency = StringUtil.toInt(strings[2]);
		int cost=StringUtil.toInt(strings[3]);
		int intensifyType = StringUtil.toInt(strings[4]);//强化什么属性
		StringBuffer sb = new StringBuffer(playerId+"|"+playerItemId+"|"+currency+"|"+intensifyType+"|");
		try{
			//检测是否输过二级密码
//			if(!checkEnterSPW(playerId)){
//				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
//			}
			Player player = getService.getPlayerById(playerId);
			Team team = getService.getTeamByPlayerId(playerId);
			PlayerItem  playerItem = getService.getPlayerItemById(playerId, playerItemId);
			if(playerItem==null){
				return Converter.error(ExceptionMessage.ITEM_NOT_EXIST);
			}
			if(playerItem.getQuantity()<=0){
				//return Converter.error("the teamItem is not exist");
			}
			SysItem si = getService.getSysItemByItemId(playerItem.getItemId());
			String property = playerItem.getIntensifyInfo(intensifyType);
			int curLevel = 0;//未强化为0级
			double curExp =0;
			double value = 0;
			int levelChange=0;
			int intensifyLevel=0;
			int isUp = 0;
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
			HashMap<String, Integer> playerRes=player.getLatestPlayerRes(team.getTeamSpaceLevel());
			int resourceNum = playerRes.get(Player.RES);
			if(cost>resourceNum){
				return Converter.error(ExceptionMessage.NOT_ENOUGH_PERSONAL_USABLE_RESOURCE);
			}
			int needRES = ItemIntensifyUtil.getIntensifyPrice(currency, playerItem.getLevel(), intensifyType, si, curLevel);
			
			int outOfCost = 0;
			if(curExp+ItemIntensifyUtil.CRAndRESChangeToExp(currency, cost)>=needRES){//是否要升级，如果要升能
				//可能升多级，超了要判断，如果超出30级则p 的值为 :   等级,经验,超出的经验
				String p = upgrade(currency,curExp+ItemIntensifyUtil.CRAndRESChangeToExp(currency, cost),curLevel,intensifyType,playerItem,si);
				String [] info = p.split(",");
				value = ItemIntensifyUtil.getIntensifyProperty(playerItem.getLevel(), si.getSubType(), intensifyType, Integer.valueOf(info[0]));
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
				value = ItemIntensifyUtil.getIntensifyProperty(playerItem.getLevel(), si.getSubType(), intensifyType,curLevel);
				if(curLevel>=ItemIntensifyUtil.getMaxLevel(si.getSubType())){//最大等级
					outOfCost = cost;
					cost = 0;
					newProperty = curLevel+","+curExp+","+value;
				}else{
					newProperty = curLevel+","+(curExp+ItemIntensifyUtil.CRAndRESChangeToExp(currency, cost))+","+value;
				}
			}
			playerItem.setIntensifyInfo(intensifyType, newProperty);
			sb.append(newProperty+"|");	
			int newPlayerItemId =0;
			PlayerItem newPlayerItem = null;
			if(playerItem.isUpgrade()&&playerItem.getLevel()!=3){//升级
				levelChange=0;
				try{
				TeamTechnology tt = getService.getTeamTechnologyByCurNode(si.getId());
				SysItem sysItem = getService.getSysItemByItemId(tt.getNextNode());
				sysItem.setGunProperty1("0,0"+playerItem.getGunProperty1().substring(playerItem.getGunProperty1().lastIndexOf(",")));
				sysItem.setGunProperty2("0,0"+playerItem.getGunProperty2().substring(playerItem.getGunProperty2().lastIndexOf(",")));
				sysItem.setGunProperty3("0,0"+playerItem.getGunProperty3().substring(playerItem.getGunProperty3().lastIndexOf(",")));
				sysItem.setGunProperty6("0,0"+playerItem.getGunProperty6().substring(playerItem.getGunProperty6().lastIndexOf(",")));
				newPlayerItemId = createService.createPlayerItem(playerId, sysItem, playerItem.getQuantity(),playerItem.getPlayerItemUnitType(), Constants.BOOLEAN_NO, Constants.BOOLEAN_NO, Constants.BOOLEAN_NO,playerItem.getValidDate());
				if(newPlayerItemId!=0){
					newPlayerItem = getService.getPlayerItemById(playerId, newPlayerItemId);
					newPlayerItem.setValidDate(playerItem.getValidDate());
				}
				}catch (Exception e) {
					log.info("is max level");
				}
				updateService.updatePlayerItemPersonal(playerItem);
				deleteService.deletePlayerItem(playerItem,playerId,9);
				
				isUp = 1;
			}else{
				updateService.updatePlayerItem(playerItem);
			}
			player.setUsableResource(resourceNum-cost);
			updateService.updatePlayerInfo(player);
			return Converter.getIntensifyInfo(0, outOfCost,levelChange,intensifyLevel,isUp,newPlayerItem);
			
		}catch(Exception e){
			log.warn(""+e);
		}finally{
			createService.createIntensifyTeamItemLog(sb.toString());
		}
		
		
		
		
		return super.innerService(strings);
	}
	private String upgrade(int currency,double curExp,int level,int intensifyType,PlayerItem pi,SysItem si) throws Exception{
		int needRES = ItemIntensifyUtil.getIntensifyPrice(currency, pi.getLevel(), intensifyType, si, level);
		//double needExp = TeamItemIntensifyUtil.CRAndRESChangeToExp(currency,needRES);
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
		 s=upgrade(currency,curExp-needRES,level+1,intensifyType,pi,si);
		}
		return s;
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
