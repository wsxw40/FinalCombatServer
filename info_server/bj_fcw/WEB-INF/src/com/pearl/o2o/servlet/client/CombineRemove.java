package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * 合成接口--移除属性石接口
 * @author wuxiaofei
 *
 */
public class CombineRemove extends BaseClientServlet {
	private static final long serialVersionUID = -2470899400867670372L;
	static Logger log = LoggerFactory.getLogger(CombineRemove.class.getName());
	private String[] paramNames = { "pid", "playerItemId", "index"};

	public CombineRemove() {
		super();
	}

	/* (non-Javadoc)
	 * @see com.pearl.o2o.servlet.client.BaseClientServlet#getLockKey(java.lang.String[])
	 */
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]));
	}
	
	@Override
	protected String innerService(String... args) {
		int playerId = Integer.parseInt(args[0]);
		int playerItemId = Integer.parseInt(args[1]);
		int index = Integer.parseInt(args[2]);
		try {
			if(playerId <= 0){
				throw new BaseException(ExceptionMessage.NO_HAVE_THE_CHARACTER);
			}
			
			//检测是否输过二级密码
			if(!checkEnterSPW(playerId)){
				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
			}
			int result = updateService.clearDiamond(playerId, playerItemId, index);
			PlayerItem pi = getService.getPlayerItemById(playerId, playerItemId);
			return Converter.combineRemove(result, pi);
		} catch (Exception e) {
			log.warn("Error in Slotting: ", e);
			return Converter.error(e.getMessage());
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
