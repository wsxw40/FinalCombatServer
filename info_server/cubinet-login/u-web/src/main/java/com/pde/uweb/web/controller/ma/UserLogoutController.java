/**
 * 
 */
package com.pde.uweb.web.controller.ma;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.pde.infor.common.web.AbstractCommonController;
import com.pde.uweb.bbs.api.BBSUCenterClient;
import com.pde.uweb.web.utils.HttpSessionUtil;
import com.pde.uweb.web.vo.ma.UserVO;

/**
 *  逻辑控制--用户退出
 *
 *  @author Huanggang
 */
public class UserLogoutController extends AbstractCommonController {

	private final static Logger log = Logger.getLogger(UserLogoutController.class);
	
	private BBSUCenterClient bbsuCenterClient;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		JSONObject jsonObj = new JSONObject();
		try {
			UserVO user = HttpSessionUtil.getLoginUser(request);

			if (user==null)
				return null;

			// 清73u的session
			HttpSessionUtil.removeLoginUser(request);
			
			// 调discuz的退出方法
			String result = this.bbsuCenterClient.userSynLogout(user.getUserId());
			jsonObj.put("bbsUserLogout", result);
			jsonObj.put("isSuccess", true);
		} catch (Exception e) {
			log.error(e);
			jsonObj.put("isSuccess", false);
		}
		
		this.handlerJSCallback(request, response, jsonObj.toString());
		return null;
	}


	public void setBbsuCenterClient(BBSUCenterClient bbsuCenterClient) {
		this.bbsuCenterClient = bbsuCenterClient;
	}

}
