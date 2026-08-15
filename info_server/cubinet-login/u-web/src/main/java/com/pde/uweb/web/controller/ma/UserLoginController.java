/**
 * 
 */
package com.pde.uweb.web.controller.ma;

import java.net.URLEncoder;
import java.util.HashMap;
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
import com.pde.uweb.ma.dto.LoginRequestDto;
import com.pde.uweb.ma.dto.LoginResultDto;
import com.pde.uweb.ma.service.MemberService;
import com.pde.uweb.utils.UCXMLHelper;
import com.pde.uweb.web.utils.HttpSessionUtil;
import com.pde.uweb.web.vo.ma.UserVO;

/**
 * 逻辑控制--用户登录
 * 
 * @author Huanggang
 */
public class UserLoginController extends AbstractCommonController {

	private final static Logger log = Logger.getLogger(UserLoginController.class);

	private MemberService memberService;

	private BBSUCenterClient bbsuCenterClient;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

		JSONObject jsonObj = new JSONObject();
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			// 包装为注册需要的request对象
			LoginRequestDto requestDto = new LoginRequestDto();

			// 校验登录的请求参数
			map = this.bindObjectWithCheck(request, requestDto, "requestDto", false, null);

			// 参数校验未通过
			if (!map.isEmpty()) {
				logger.error("come from remote address [ " + request.getRemoteAddr() + " ]  login parameters error");
				jsonObj.put("error", map);
				this.handlerJSCallback(request, response, jsonObj.toString());
				return null;
			}

			// 执行登录操作
			LoginResultDto resultDto = memberService.doLogin(requestDto);
			if (resultDto.isLoginSuccess()) { // 登录成功
				String result = this.bbsuCenterClient.userLogin(requestDto.getUserName(), requestDto.getPassword());
				LinkedList<String> rs = UCXMLHelper.unserialize(result);
				if (rs.size() > 0) {
					int uid = Integer.parseInt(rs.get(0));
					String username = rs.get(1);
					String password = rs.get(2);
					String ucsynlogin = this.bbsuCenterClient.userSynLogin(uid);
					Cookie auth = new Cookie("auth", this.bbsuCenterClient.authCode(password + "\t" + uid, "ENCODE"));
					response.addCookie(auth);

					Cookie user = new Cookie("uchome_loginuser", URLEncoder.encode(username, "UTF-8"));
					response.addCookie(user);
					jsonObj.put("bbsUserLogin", ucsynlogin);
					logger.info("discuz login successfully");
				}

				// pojo转为userVO
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
				HttpSessionUtil.setLoginUser(request, vo);
				
				jsonObj.put("user", vo);
				
			} else { // 登录失败
				logger.error("come from remote address [ " + request.getRemoteAddr() + " ]  login error");
				jsonObj.put("error", resultDto.getLoginError());
			}
		} catch (Exception e) {
			log.error("exception in UserLoginController" + e.getMessage());
			jsonObj.put("error", e.getMessage());
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
