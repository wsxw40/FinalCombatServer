package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.BuyItemRecord;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
import com.pearl.o2o.utils.SysItemVariation;
import com.pearl.o2o.utils.SysItemVariationMagicBox;

/**
 * 资源争夺战宝箱
 * @author zhang.li
 *
 */
public class ResMagicBoxList extends BaseClientServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 760836883787284830L;
	private static Logger log = LoggerFactory.getLogger("resmagicbox");
	private static final String[] paramNames = {"pid","sid"};
	
	protected String innerService(String... args) {
		try {
			//初始化
			int playerId = StringUtil.toInt(args[0]);
			int sysItemId = StringUtil.toInt(args[1]);
			
			Player player = getService.getSimplePlayerById(playerId);
			SysItem sysItem=getService.getSysItemByItemId(sysItemId);
			Team team=getService.getTeamByPlayerId(playerId);
			
			//物品设定
			/**获取玩家购买该物品的记录*/
			BuyItemRecord buyItemRecord=getService.getBuyItemRecord(playerId, sysItemId);
			int baseCost=sysItem.getAllResPricesList().get(0).getCost();
			int count=0;
			if(buyItemRecord!=null){
				count = buyItemRecord.getRecord();
			}
			
			SysItemVariation sysIVa=new SysItemVariationMagicBox(sysItemId,baseCost,count,sysItem.getIValue(),sysItem.getIId());
			
			return Converter.resMagicBoxList(player,sysIVa,team.getTeamSpaceLevel(),sysItem);

		} catch (BaseException e) {
			log.warn("MagicBoxList/Warn:\t", e);
			return Converter.warn(e.getMessage());
		}catch (Exception e) {
			log.error("MagicBoxList/Error:\t", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
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
