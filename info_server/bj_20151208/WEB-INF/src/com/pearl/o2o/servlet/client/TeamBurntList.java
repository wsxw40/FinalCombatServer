
package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.lilystudio.smarty4j.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multiset;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.TeamConstants;

/**
 * @author lfyang
 */
public class TeamBurntList extends BaseClientServlet {
	private static final long serialVersionUID = 7794098604530646005L;
	private static Logger logger=LoggerFactory.getLogger(TeamBurntList.class.getName());
	private static final String[] paramNames = {"pid"};
	
	protected String innerService(String... args) {
		try{
			int playerId = Integer.valueOf(args[0]);
			
			List<SysItem> burnts = new ArrayList<SysItem>(TeamConstants.Burnt_Id_Set.size());
			for (int id : TeamConstants.Burnt_Id_Set) {
				burnts.add(getService.getSysItemByItemId(id));
			}
			
			Multiset<SysItem> burntNum = HashMultiset.<SysItem>create();
			
			Map<Integer,PlayerItem> map=getService.getPlayerItemMapByType1(playerId, Constants.DEFAULT_ITEM_TYPE, 0, 0);
			for(Map.Entry<Integer, PlayerItem> entry: map.entrySet()) {  
				for (SysItem sysItem : burnts) {
					if(entry.getValue().getItemId() == sysItem.getId()){
						burntNum.add(sysItem, entry.getValue().getQuantity());
						break;
					}
				}
			}
			
			Integer[] prices = new Integer[burnts.size()];
			
			for(int i = 0; i < burnts.size(); i++){
				burnts.get(i).setQuantity(burntNum.count(burnts.get(i)));
				prices[i] = 0;
			}
			
			
			Context ctx	= new Context();
			ctx.set("prices", prices);
			ctx.set("items", burnts);
			return Converter.smartTemplate("CombineGetPrice.st",  ctx);
		}catch (BaseException e) {
			return Converter.error(e.getMessage());
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