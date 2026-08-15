package com.pde.uweb.web.controller.ma;

import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.pde.infor.common.web.AbstractCommonController;
import com.pde.uweb.bbs.api.BBSUCenterClient;
import com.pde.uweb.database.ma.user.UserPojo;
import com.pde.uweb.ma.dto.ModifyPwRequestDto;
import com.pde.uweb.ma.service.MemberService;
import com.pde.uweb.utils.UCXMLHelper;

/**
 *  逻辑控制--用户修改密码
 * 
 *  @author Huanggang
 */
public class UserModifyPasswordController extends AbstractCommonController {
	
	private static final Logger logger = Logger.getLogger(UserModifyPasswordController.class); 
	
	/** 会员管理业务方法接口 */
	private MemberService memberService;
		
	/** BBS UCenter */
	private BBSUCenterClient bbsuCenterClient;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("come from remote address  [ " + request.getRemoteAddr() + " ] modify pw");
		
		ModifyPwRequestDto requestDto = new ModifyPwRequestDto();
		JSONObject jsonObj = new JSONObject();		
		jsonObj.put("isSuccess", false);
		
		// 校验注册的请求参数
		Map<String, String> resultMap = this.bindObjectWithCheck(request, requestDto, "requestDto", false, null);
		if (!resultMap.isEmpty()) { // 参数校验未通过
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ]  modify user pw parameters error" );
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}
		
		long uid = requestDto.getUserId();
		// 获得指定user信息
		UserPojo user = memberService.getUser(uid);
		if (user==null) {
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ]  could not find user" );
			jsonObj.put("error", "原密码不正确");
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}

		String oldpw = requestDto.getOldPw();
		String newpw = requestDto.getNewPw();
		String reNewpw = requestDto.getReNewPw();

		// 新密码和确认新密码必须相同
		if (!newpw.equals(reNewpw)) {
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ]  password not same error" );
			jsonObj.put("error", "新密码和确认密码不相同");
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}
		
		// 新密码和旧密码不能相同
		if (newpw.equals(oldpw)) {
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ]  password not same error" );
			jsonObj.put("error", "新密码和原始密码不能相同");
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}
		
		// 原始密码是否正确
		boolean isSuccess = oldpw.equals(user.getPassword());
		if (!isSuccess) {
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ] password not correct error" );
			jsonObj.put("error", "原始密码不正确");
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}
		
		// 修改user的密码
		isSuccess = memberService.modifyUserPassword(uid, newpw);
		if (!isSuccess) {
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ]  modify user pw error" );
			jsonObj.put("error", "密码修改失败");
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}
		
		// 同步新密码到 discuz
		String result = bbsuCenterClient.userEdit(user.getUserName(), oldpw, newpw, user.getEmail(), 0, null, null);
		if (!"1".equals(result)) { //只有 1 才表示更新成功
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ]  modify discuz pw error" );
			jsonObj.put("error", "同步到discuz失败");
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}
		
		// 用新密码登录 discuz
		result = this.bbsuCenterClient.userLogin(user.getUserName(), newpw);
		LinkedList<String> rs = UCXMLHelper.unserialize(result);
		if (rs.size() > 0) {
			long userid = Long.parseLong(rs.get(0));
			String username = rs.get(1);
			String password = rs.get(2);
			String ucsynlogin = this.bbsuCenterClient.userSynLogin(userid);
			Cookie auth = new Cookie("auth", this.bbsuCenterClient.authCode(password + "\t" + uid, "ENCODE"));
			response.addCookie(auth);

			Cookie cookie = new Cookie("uchome_loginuser", URLEncoder.encode(username, "UTF-8"));
			response.addCookie(cookie);
			jsonObj.put("bbsUserLogin", ucsynlogin);
			jsonObj.put("isSuccess", true);
			logger.info("discuz login succfully");
		} else {
			jsonObj.put("error", "新密码登录discuz失败");
		}
		this.handlerJSCallback(request, response, jsonObj.toString());
		return null;
	}

	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public void setBbsuCenterClient(BBSUCenterClient bbsuCenterClient) {
		this.bbsuCenterClient = bbsuCenterClient;
	}
}
