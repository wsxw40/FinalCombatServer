package com.pearl.o2o.servlet.client;

import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.StringUtil;
/**
 * just for testing only,must be deleted after testing
 * @author wangzhilong
 *
 */
public class SendMagicBoxForTest extends BaseClientServlet {
	private static final long serialVersionUID = -8841489031336394452L;
	private static final String[] paramNames = { "pid","mbxsid","quantity"};
	@Override
	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			int mbxSid = StringUtil.toInt(args[1]);
			int quantity = StringUtil.toInt(args[2]);
			Player player = getService.getPlayerById(playerId);
			
			SysItem magicBox = getService.getSysItemByItemId(mbxSid);
			Payment payment = new Payment();
			payment.setUnit(quantity);
			payment.setUnitType(1);
			createService.awardToPlayer(player, magicBox, payment, null, Constants.BOOLEAN_YES);
			return "SUCCESS";
			
		} catch (Exception e) {
			return "ERROR:" + e.getMessage();
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
