package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pde.log.common.LogMessage.Level;
import com.pearl.o2o.enumuration.LogServerMessage;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.LogUtils;
import com.pearl.o2o.utils.StringUtil;

public class DeletePlayerItem extends BaseClientServlet {

	private static final long serialVersionUID = -6023850952782323311L;
	static Logger log = LoggerFactory.getLogger(DeletePlayerItem.class.getName());
	private String[] paramNames={"uid","pid","t","piid"};
	@Override
	protected String innerService(String... args) {
		try{
//			int userId = StringUtil.toInt(args[0]);
			int playerId=StringUtil.toInt(args[1]);
			int type=StringUtil.toInt(args[2]);
			int playerItemId=StringUtil.toInt(args[3]);
			//检测是否输过二级密码
			if(!checkEnterSPW(playerId)){
				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
			}
			
			PlayerItem pi=getService.getPlayerItemByItemId(playerId, type, playerItemId);
			deleteService.deletePlayerItem(playerId,type, pi);
			infoLogger.log(LogServerMessage.deletePlayerItem.name(), Level.INFO_INT, LogUtils.JoinerByTab.join("",playerId,playerItemId,pi.getSysItem().getDisplayNameCN(),type,pi.getQuantity()));
//			log.info("player playerId="+playerId+" delete playerItem type="+type+" playerItemId="+playerItemId);
			return Converter.commonFeedback(null);
		}
		catch (BaseException be) {
			log.debug("Exception in DeletePlayerItem:"+be.getMessage());
			return Converter.commonFeedback(be.getMessage());	
		}
		catch(Exception e){
			log.warn("Error in DeletePlayerItem: " , e);
			return Converter.commonFeedback(e.getMessage());	
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
