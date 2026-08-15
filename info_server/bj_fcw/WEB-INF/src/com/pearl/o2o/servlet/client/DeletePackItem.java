package com.pearl.o2o.servlet.client;


import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.NotEquipException;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class DeletePackItem extends BaseClientServlet {
	private static final Logger LOG = LoggerFactory.getLogger(DeletePackItem.class.getName());
	private static final long serialVersionUID = -3467750958211447191L;
	private final String[] paramNames={"uid","cid","pid","t","seq"};
	public DeletePackItem() {
		super();
	}
	@Override
	protected String innerService(String... args) {
		try{
//			int userId = StringUtil.toInt(args[0]);
			int playerId=StringUtil.toInt(args[1]);
			int itemId=StringUtil.toInt(args[2]);
			
			if(itemId!=0){
				PlayerItem pi=getService.getPlayerItemByItemId(playerId, Constants.DEFAULT_ITEM_TYPE, itemId);
				if(pi!=null){
					deleteService.deletePlayerBuff(pi);
					return Converter.deletePackEquipment(null);
				}else{
					return Converter.error(ExceptionMessage.NOT_FIND_ITEM);
				}
			}else{
				return Converter.error(ExceptionMessage.NOT_FIND_ITEM);
			}
		}catch (NotEquipException e) {
			return Converter.deletePackEquipment(e.getMessage());
		}
		catch (Exception nee) {
			LOG.warn("exception in DeletePackEquipment servlet",nee);
			return Converter.deletePackEquipment(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
