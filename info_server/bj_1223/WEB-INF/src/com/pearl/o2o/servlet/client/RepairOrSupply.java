package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pde.log.common.LogMessage.Level;
import com.pearl.o2o.enumuration.LogServerMessage;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.pojo.TeamItem;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.LogUtils;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.ItemIntensifyUtil;

public class RepairOrSupply extends BaseClientServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6924430330076773458L;
	private static final String [] paramNames ={"pid","teamId","storageType","teamItemId","manageType"}; 
	private Logger log = LoggerFactory.getLogger(RepairOrSupply.class);
	@Override
	protected String innerService(String... strings) {
		String pid = strings[0];
		String teamId = strings[1];
		String storageType = strings[2];
		String teamItemid = strings[3];
		int manageType = Integer.valueOf(strings[4]);
		StringBuffer sb = new StringBuffer(pid+"|"+teamId+"|"+manageType+"|"+System.currentTimeMillis()+"|");
		try{
			
//			
			Team team = null;
			
			//检测是否输过二级密码
			if(!checkEnterSPW(Integer.valueOf(pid))){
				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
			}
			if(manageType==ItemIntensifyUtil.TEAM_ITEM_MANAGE_TYPE.REPAIR_TEAM_ITEM.getValue()){//维修
				TeamItem ti = getService.getTeamItemById(Integer.valueOf(teamItemid));
				if(ti.getDurable()>=100f){
					return Converter.error(ExceptionMessage.NOT_REPAIR);
				}
				team = getService.getTeamById(ti.getTeamId());
				
				int price = ti.getRepairPrice();
				
				sb.append(team.getLatestTeamRes().get(team.RES)+"|"+price);
				
				if(team.getLatestTeamRes().get(team.RES)<price){
					return Converter.error(ExceptionMessage.NOT_ENOUGH_USABLE_RESOURCE);
				}
				ti.setDurable(100);
				team.setUsableResource(team.getLatestTeamRes().get(team.RES)-price);
				updateService.updateTeamInfo(team);
				updateService.updateTeamItem(team.getId(), ti);
				sb.append("-");
				return "";
			}else if(manageType==ItemIntensifyUtil.TEAM_ITEM_MANAGE_TYPE.SUPPLY.getValue()){//补给
				TeamItem ti = getService.getTeamItemById(Integer.valueOf(teamItemid));
				if(ti.getDurable()>=100f){
					return Converter.error(ExceptionMessage.NOT_SUPPLY);
				}
				team = getService.getTeamById(ti.getTeamId());
				
				int price = ti.getSupplyPrice();//获得价格
				sb.append(team.getLatestTeamRes().get(team.RES)+"|"+price);
				if(team.getLatestTeamRes().get(team.RES)<price){
					return Converter.error(ExceptionMessage.NOT_ENOUGH_USABLE_RESOURCE);
				}
				ti.setBullet(100);
				team.setUsableResource(team.getLatestTeamRes().get(team.RES)-price);
				updateService.updateTeamInfo(team);
				updateService.updateTeamItem(team.getId(), ti);
				sb.append("-");
				return "";
			}
		}catch (Exception e) {
			log.warn("RepairOrSupply", e);
			return Converter.error("error");
		}finally{
			infoLogger.log(LogServerMessage.xunleiLog.name(), Level.INFO_INT, 
					LogUtils.JoinerByTab.join("12.3",sb.toString()));
		}
		return super.innerService(strings);
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[3]));
	}

}
