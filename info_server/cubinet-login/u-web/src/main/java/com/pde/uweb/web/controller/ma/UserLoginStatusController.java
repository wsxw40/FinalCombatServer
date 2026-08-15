/**
 * 
 */
package com.pde.uweb.web.controller.ma;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.pde.infor.common.web.AbstractCommonController;
import com.pde.uweb.web.utils.HttpSessionUtil;
import com.pde.uweb.web.vo.ma.UserVO;

/**
 * @author Huanggang
 * 
 */
public class UserLoginStatusController extends AbstractCommonController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserVO vo = HttpSessionUtil.getLoginUser(request);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("loginStatus", vo != null);
		this.handlerJSCallback(request, response, jsonObj.toJSONString());
		return null;
	}

}
