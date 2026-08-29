package com.pearl.o2o.servlet.client;

import com.pearl.o2o.pojo.PlayerItem;
/**
 * just for testing only,must be deleted after testing
 * @author wangzhilong
 *
 */
public class SendVipForTest extends BaseClientServlet {

	
	private static final long serialVersionUID = -8841445031336394452L;
	private static final String[] paramNames = {};
	@Override
	protected String innerService(String... args) {
		try {
			PlayerItem playerBuff = getService.getPlayerBuff(1092, 61);
			System.out.println(playerBuff);
			return "";
			
		} catch (Exception e) {
			return "ERROR:" + e.getMessage();
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
