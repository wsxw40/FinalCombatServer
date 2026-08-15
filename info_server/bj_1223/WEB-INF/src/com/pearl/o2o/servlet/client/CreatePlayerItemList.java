package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pde.log.common.LogMessage.Level;
import com.pearl.o2o.enumuration.LogServerMessage;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.exception.NotBuyEquipmentException;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.LogUtils;
import com.pearl.o2o.utils.StringUtil;

public class CreatePlayerItemList extends BaseClientServlet {
	private static final long serialVersionUID = -4864828406858822453L;
	static Logger log = LoggerFactory.getLogger(CreatePlayerItemList.class.getName());
	private String[] paramNames = { "uid", "pid", "cid", "packid", "list" };

	
	@Override
	protected String innerService(String... args) {
		for(int i=0;i<4;i++){
			if(!args[i].matches("^\\d+$")){	
				return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
			}
		}
		int playerId = StringUtil.toInt(args[1]);
		int characterId = StringUtil.toInt(args[2]);
		int packId = StringUtil.toInt(args[3]);
		
		//add the check str for log
		StringBuffer checklog=new StringBuffer(playerId+"-");
		
		try {
			// int userId = StringUtil.toInt(args[0]);
			
			String list = args[4];
			List<String[]> playeritems = new ArrayList<String[]>();
			if (characterId == 0 || list != null) {
				String[] buys = list.split(Constants.DELIMITER_RESOURCE_CHANGEABLE);
				if (buys.length != 0) {
					for (String str : buys) {
						String[] buyss = str.split(Constants.DELIMITER_RESOURCE_STABLE);
						if (buyss.length != 3) {
							throw new BaseException(ExceptionMessage.NOT_RIGHT_PARAM);
						} else {
							playeritems.add(buyss);
						}
					}
				} else {
					throw new BaseException(ExceptionMessage.NOT_RIGHT_PARAM);
				}
			} else {
				throw new BaseException(ExceptionMessage.NOT_RIGHT_PARAM);
			}

			//检测是否输过二级密码
			if(!checkEnterSPW(playerId)){
				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
			}
			//-3 both -1 fc -2 c
			int result = createService.buyPlayerItemList(checklog,playerId, playeritems, packId, characterId);
			mcc.delete(CacheUtil.oPlayer(playerId));
			mcc.delete(CacheUtil.sPlayer(playerId));
			return Converter.createPlayerItem(result, null);
		} catch (NotBuyEquipmentException nbe) {
			log.warn("Exception in CreatePlayerItem", nbe);
			return Converter.createPlayerItem(Constants.NUM_ZERO, nbe.getMessage());
		} catch (BaseException nbe) {
			log.debug("Exception in CreatePlayerItem:"+nbe.getMessage());
			return Converter.createPlayerItem(Constants.NUM_ZERO, nbe.getMessage());
		} catch (Exception e) {
			log.warn("Exception in CreatePlayerItem", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}finally{
			infoLogger.log(LogServerMessage.buyPlayerItem.name(), Level.INFO_INT, 
					LogUtils.JoinerByVertical.join("0.4", CommonUtil.simpleDateFormat.format(new Date()),
							checklog.toString()));
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[1]));
	}
}
