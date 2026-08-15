package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.exception.CRNotEnoughException;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * 资源争夺战物品转换，购买
 * 
 * @author leo.zhang
 */
public class ZyzdzTransform extends BaseClientServlet {
	private static final long serialVersionUID = -3928363495923777848L;

	static Logger logger = LoggerFactory.getLogger(ZyzdzTransform.class
			.getName());

	/**
	 * <li>pid:玩家id</li>
	 * <li>type:1-个人转换; 2-团队转换 ;3-团队购买</li>
	 * <li>fcquantity:使用的FC数量</li>
	 */
	private String[] paramNames = { "pid", "type", "fccost" };

	@Override
	protected String innerService(String... args) {

		String result = "";
		try {
			int playerId = StringUtil.toInt(args[0]);
			int type = StringUtil.toInt(args[1]);
			int fccost = StringUtil.toInt(args[2]);

			int reward = 0;
			//检测是否输过二级密码
			if(!checkEnterSPW(playerId)){
				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
			}
			boolean suc = false;
			switch (type) {
			case Constants.TeamSpaceConstants.SHOP_P_TF:
				suc = createService.createPTransForm(playerId, fccost);
				break;
			case Constants.TeamSpaceConstants.SHOP_T_TF:
				reward = createService.createTTransForm(playerId, fccost);
				suc = true;
				break;
			case Constants.TeamSpaceConstants.SHOP_T_BUY:
				reward = createService.createTBUYStone(playerId, fccost);
				suc = true;
				break;
			default:
				;
				break;
			}
			result = Converter.getZYZDZTransform(suc, reward);
			return result;
		} catch (CRNotEnoughException e) {
			logger.warn(e.getMessage(), e);
			return Converter.commonFeedback(ExceptionMessage.NOT_ENOUGH_CR);
		} catch (BaseException e) {
			logger.warn(e.getMessage(), e);
			return Converter.commonFeedback(e.getMessage());
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
			return Converter.commonFeedback(ExceptionMessage.ERROR_MESSAGE_ALL);
		}

	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
