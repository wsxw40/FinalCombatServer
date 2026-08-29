package com.pearl.o2o.servlet.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.TeamItem;
import com.pearl.o2o.pojo.TeamTechnology;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ItemIntensifyUtil;
import com.pearl.o2o.utils.StringUtil;

public class GetTreeItemList  extends BaseClientServlet{

	private static final long serialVersionUID = 30367273405503402L;
	private Logger log = LoggerFactory.getLogger(GetTreeItemList.class);
	private static final String [] paramNames={"playerId","teamId","treeType"};
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
		int playerId = StringUtil.toInt(strings[0]);
		int teamId = StringUtil.toInt(strings[1]);
		int treeType = StringUtil.toInt(strings[2]);
		try{
			Map<Integer, SysItem> map = new HashMap<Integer, SysItem>();
			List<TeamTechnology> list = getService.getTeamTechnologyByType(treeType);
			for(TeamTechnology tt:list){
				int place = tt.getPlace();
				SysItem si = getService.getSysItemByItemId(tt.getCurNode());
				map.put(place, si);
			}
			
			if(treeType==ItemIntensifyUtil.TEAM_STORAGE_TYPE.PERSONAL_TANK.getValue()){
				//
				List<PlayerItem> itemList = getService.getPlayerItemByItemIid(playerId, Constants.DEFAULT_ZYZDZ_TYPE, Constants.SPECIAL_ITEM_IIDS.TANK.getValue());
				return Converter.getTreeItem(map,itemList ,treeType);
			}else{
				//直接获得已删除物品
				List<TeamItem> itemList = getService.getTeamItemListByTeamId(teamId,treeType);
				return Converter.getTreeItem(map,itemList ,treeType);
			}
			
		
			
		}catch (Exception e) {
			log.warn("GetTreeItemList"+e);
		}
		return super.innerService(strings);
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
