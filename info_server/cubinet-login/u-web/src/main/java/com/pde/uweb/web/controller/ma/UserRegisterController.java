/**
 * 
 */
package com.pde.uweb.web.controller.ma;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.pde.infor.common.asserts.Asserts;
import com.pde.infor.common.web.AbstractCommonController;
import com.pde.uweb.bbs.api.BBSUCenterClient;
import com.pde.uweb.database.ma.user.UserPojo;
import com.pde.uweb.ma.dto.RegisterRequestDto;
import com.pde.uweb.ma.dto.RegisterResultDto;
import com.pde.uweb.ma.service.MemberService;
import com.pde.uweb.utils.UCXMLHelper;
import com.pde.uweb.web.utils.HttpSessionUtil;
import com.pde.uweb.web.vo.ma.UserVO;

/**
 * 逻辑控制--用户注册
 * 
 * @author Huanggang
 */
public class UserRegisterController extends AbstractCommonController {

	private final static Logger logger = Logger.getLogger(UserRegisterController.class);

	/** 会员管理业务方法接口 */
	private MemberService memberService;

	private BBSUCenterClient bbsuCenterClient;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		// ajax的java实现方法（临时处理方法，稍后会优化）
		String method = request.getParameter("method");
		if (!Asserts.assertStringIsNull(method)) {
			switch (method) {
				case "isUnameExist":
					return this.isUnameExist(request, response);
					
				case "isEmExist":
					return this.isEmailExist(request, response);
					
				default:
					break;
			}
		}
		
		JSONObject jsonObj = new JSONObject();
		// 包装为注册需要的request对象
		RegisterRequestDto requestDto = new RegisterRequestDto();
		
		// 校验注册的请求参数
		Map<String, String> resultMap = this.bindObjectWithCheck(request, requestDto, "requestDto", false, null);
		if (!resultMap.isEmpty()) { // 参数校验未通过
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ]  regist parameters error" );
			jsonObj.put("isSuccess", false);
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}
		
		// 乱码处理
		requestDto.setUserName(URLDecoder.decode(requestDto.getUserName(), "UTF-8"));
		requestDto.setIdName(URLDecoder.decode(requestDto.getIdName(), "UTF-8"));

		// 校验密码和确认密码是否相同
		if (!requestDto.getPassword().equals(requestDto.getRepassword())) {
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ]  password not same error" );
			jsonObj.put("isSuccess", false);
			jsonObj.put("repwdError", "密码和确认密码不相同");
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}
		
		//查询用户名是否已经被注册
		if (memberService.isLoginNameExist(requestDto.getUserName())) {
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ]  login name exist error" );
			jsonObj.put("isSuccess", false);
			jsonObj.put("uNameError", "用户名已经存在");
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}
		
		//查询email是否已存在
		if (memberService.getUserIdByEmail(requestDto.getEm()) > Long.valueOf(0)) {
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ]  email exist error" );
			jsonObj.put("isSuccess", false);
			jsonObj.put("emailError", "邮箱已经存在");
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}
		
		// 创建用户
		RegisterResultDto resultDto = memberService.doCreateMemberRnTx(requestDto);
		if (!resultDto.isRegisterSuccess()) { // 创建失败
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ]  create user error" );
			jsonObj.put("isSuccess", false);
			jsonObj.put("registerUserError", resultDto.getRegisterError());
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}

		// 包装为页面显示需要的user对象
		UserPojo user = resultDto.getUser();
		UserVO vo = new UserVO();
		vo.setUserId(user.getUserId());
		vo.setUserName(user.getUserName());
		vo.setIdName(user.getIdName());
		vo.setIdNumber(user.getIdNumber());
		vo.setEmail(user.getEmail());
		vo.setEmailStatus(user.getEmailStatus());
		vo.setMobile(user.getMobile());
		vo.setMobileStatus(user.getMobileStatus());
		vo.setStatus(user.getStatus());
		vo.setQuestionId(user.getQuestionId());
		vo.setAnswer(user.getAnswer());
		
		// 创建成功，自动登录
		HttpSessionUtil.setLoginUser(request, vo);
		jsonObj.put("user", vo);
		
		// 同步注册到discuz
		this.bbsuCenterClient.userRegister(user.getUserName(), user.getPassword(), user.getEmail());
		
		// 同步登录到discuz
		String result = this.bbsuCenterClient.userLogin(requestDto.getUserName(), requestDto.getPassword());
		LinkedList<String> rs = UCXMLHelper.unserialize(result);
		if (rs.size() > 0) {
			int uid = Integer.parseInt(rs.get(0));
			String username = rs.get(1);
			String password = rs.get(2);
			String ucsynlogin = this.bbsuCenterClient.userSynLogin(uid);
			Cookie auth = new Cookie("auth", this.bbsuCenterClient.authCode(password + "\t" + uid, "ENCODE"));
			response.addCookie(auth);

			Cookie userCookie = new Cookie("uchome_loginuser", URLEncoder.encode(username, "UTF-8"));
			response.addCookie(userCookie);
			jsonObj.put("bbsUserLogin", ucsynlogin);
			logger.info("discuz login succfully");
		}
		jsonObj.put("isSuccess", true);
		this.handlerJSCallback(request, response, jsonObj.toString());
		return null;
	}
	
	private ModelAndView isUnameExist(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		boolean isExist = memberService.isLoginNameExist(URLDecoder.decode(request.getParameter("userName"), "UTF-8"));
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("isExist", isExist);
		this.handlerJSCallback(request, response, jsonObj.toString());
		return null;
	}
	
	private ModelAndView isEmailExist(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		
		long userId = memberService.getUserIdByEmail(request.getParameter("em"));
		boolean isExist =  userId> Long.valueOf(0);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("isExist", isExist);
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
