package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.TmpPlayerItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class MagicBoxList extends BaseClientServlet {

	private static final long serialVersionUID = -4453410778882153098L;
	private static Logger log = LoggerFactory.getLogger("magicbox");
	private static final String[] paramNames = {"pid","playeritemid"};
	
	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			int playerItemId = StringUtil.toInt(args[1]);
			PlayerItem playerMagicBox = getService.getPlayerItemByItemId(playerId, Constants.DEFAULT_OPEN_TYPE,playerItemId);
			List<PlayerItem> pswCardList = getService.getPlayerItemByItemIid(playerId, Constants.DEFAULT_ITEM_TYPE, Constants.SPECIAL_ITEM_IIDS.PASSWORD_CARD.getValue());
//			SysItem hummer = getService.getSysItemByItemId(Constants.SYSITEM_HUMMER_ID);\
//			if(hummer==null){
//				log.warn("MagicBoxList/SysItemNull:\t" + Constants.SYSITEM_HUMMER_ID);
//				throw new BaseException(ExceptionMessage.CAN_NOT_GET_HUMMER_MSG);
//			}
			int hummerNum =0;
			for(PlayerItem pi : pswCardList){
				hummerNum += pi.getQuantity();
			}
//			hummerNum = getService.getPlayerItemsTotalQuantity(playerId, Constants.DEFAULT_MATERIAL_TYPE,Constants.SYSITEM_HUMMER_ID);
			int boxNum =0;
			if(playerMagicBox!=null)
			boxNum = playerMagicBox.getQuantity();
			
			SysItem pswCard = getService.getSysItemByIID(Constants.SPECIAL_ITEM_IIDS.PASSWORD_CARD.getValue(), Constants.DEFAULT_ITEM_TYPE).get(0);
			if(pswCard==null){
				log.warn("MagicBoxList/SysItemNull:\t" + Constants.SYSITEM_HUMMER_ID);
				throw new BaseException(ExceptionMessage.CAN_NOT_GET_HUMMER_MSG);
			}

			int needNum = Constants.OPEN_MAGIC_BOX_NEED_NUM;
			List<TmpPlayerItem> magicBoxPlayerItems = getService.getMagicBoxOpenTmpPlayerItems_1();
			return Converter.magicBoxList(boxNum,hummerNum,needNum,pswCard,magicBoxPlayerItems);

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
