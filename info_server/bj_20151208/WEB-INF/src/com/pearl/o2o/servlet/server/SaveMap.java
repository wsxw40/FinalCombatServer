package com.pearl.o2o.servlet.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.pojo.TeamItem;
import com.pearl.o2o.pojo.TeamLevelInfo;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.Constants;

@SuppressWarnings("serial")
public class SaveMap extends BaseServerServlet{

	private static Logger logger =LoggerFactory.getLogger("saveMap");
	
	@Override
	protected  byte[] innerService(BinaryReader r) throws Exception{

		try {
			int levelId=r.readInt();  //teamLevel id
			int playerId = r.readInt();
		
			PlayerTeam playerTeam=getService.getPlayerTeamByPlayerId(playerId);
			if(playerTeam==null){
				logger.error("Get wrong playerId where save map!");
				throw new NullPointerException();
			}
			int teamId=playerTeam.getTeamId();
			TeamLevelInfo teamLevelInfo=getService.getTeamLevelInfo(teamId,levelId);
//			if(teamLevelInfo==null){
//				teamLevelInfo=createService.createTeamLevel(playerTeam.getTeamId());							
//			}
			//存放 摆放的资源数
			HashMap<Integer,Integer> itemUsedCountHashMap=new HashMap<Integer, Integer>();
			
			ArrayList<String> allConfigs=new ArrayList<String>();
			int size=r.readInt();	//放置资源总数
			for(int i=0;i<size; i++){			
				int sysItemId=r.readInt();		
				float x=r.readFloat();
				float y=r.readFloat();
				float z=r.readFloat();
				float r1=r.readFloat();
				float r2=r.readFloat();
				float r3=r.readFloat();
				float r4=r.readFloat();
				
				StringBuilder sbBuilder=new StringBuilder();
				sbBuilder.append(sysItemId).append(",").append(r1).append(",")
				.append(r2).append(",").append(r3).append(",").append(r4).append(",").append(x).append(",")
				.append(y).append(",").append(z);
				
				allConfigs.add(sbBuilder.toString());
				
				
				if(itemUsedCountHashMap.get(sysItemId)!=null){
					int count=itemUsedCountHashMap.get(sysItemId);
					itemUsedCountHashMap.remove(sysItemId);
					itemUsedCountHashMap.put(sysItemId, count+1);
				}else{
					itemUsedCountHashMap.put(sysItemId, 1);
				}
			}
			
			if (teamLevelInfo!=null) {
				teamLevelInfo.setLastUpdatePlayer(playerId);
				teamLevelInfo.setLastUpdateTime((int)(System.currentTimeMillis()/1000));
				teamLevelInfo.setIsEditable("N");
				teamLevelInfo.setConfigPoints(allConfigs.toArray(new String[allConfigs.size()]));
				
				updateService.updateTeamLevelInfo(teamLevelInfo);
				
				//使用个数全部初始化回去
				List<TeamItem> allTeamItems=getService.getTeamItemList(teamId);
				for(TeamItem ti: allTeamItems){
					ti.setUsedCount(0);
				}
				//记录使用次数
				for(int sysItemId : itemUsedCountHashMap.keySet()){
					for(TeamItem ti:allTeamItems){
						if(ti.getItemId()==sysItemId){
							ti.setUsedCount(itemUsedCountHashMap.get(sysItemId));
						}
					}
					
				}
				for(TeamItem ti: allTeamItems){
					updateService.updateTeamItem(teamId, ti);
				}
				
			}
			
		return Constants.EMPTY_BYTE_ARRAY; 
		}
		catch (Exception e) {
			logger.warn("Error happend when processing the map save. Exception is " , e);
		}
		return Constants.EMPTY_BYTE_ARRAY;
	
	}

}
