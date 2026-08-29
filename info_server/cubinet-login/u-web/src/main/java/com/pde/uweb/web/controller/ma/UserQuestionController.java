package com.pde.uweb.web.controller.ma;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.pde.infor.common.web.AbstractCommonController;
import com.pde.uweb.cache.service.StaticResourceService;
import com.pde.uweb.database.ws.channel.ChannelPojo;
import com.pde.uweb.framework.constants.UWEBConstant;
import com.pde.uweb.ma.dto.QuestionRequestDto;
import com.pde.uweb.ma.service.MemberService;
import com.pde.uweb.web.utils.HttpSessionUtil;
import com.pde.uweb.web.vo.ma.UserVO;

/**
 *  逻辑控制--用户设置安全问题
 * 
 *  @author Huanggang
 */
public class UserQuestionController extends AbstractCommonController {
	
	private static final Logger logger = Logger.getLogger(UserQuestionController.class); 
	
	/** 会员管理业务方法接口 */
	private MemberService memberService;
	
	/** 个人中心首页 */
	private String userCenterPage;
	
	/** 安全问题设置页面 */
	private String userQuestionPage;
	
	private StaticResourceService staticResourceService;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("come from remote address  [ " + request.getRemoteAddr() + " ] setting question ");
		
		QuestionRequestDto requestDto = new QuestionRequestDto();
		// 校验请求参数
		Map<String, String> resultMap = this.bindObjectWithCheck(request, requestDto, "requestDto", false, null);
		if (!resultMap.isEmpty()) { // 参数校验未通过
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ]  setting user's question error" );
			return new ModelAndView(this.userQuestionPage, resultMap);
		}

		// 设置安全问题操作
		boolean result = memberService.questionAudit(requestDto.getUserId(), requestDto.getQuestionId(), requestDto.getAnswer());
		if (!result) {
			resultMap.put("error", "设置安全问题失败");
			return new ModelAndView(this.userQuestionPage, resultMap);
		}
		
		// 更新 session 中的 user对象
		UserVO vo = HttpSessionUtil.getLoginUser(request);
		vo.setQuestionId(requestDto.getQuestionId());
		vo.setAnswer(requestDto.getAnswer());
		
		// 把缓存数据塞给页面
		Map<String, Object> cacheMap = new HashMap<String, Object>();
		
		// channel的缓存数据
		Object channelResources = staticResourceService.getStaticResource(UWEBConstant.CHANNEL_CACHE_NAME);
		if (channelResources != null) {
			cacheMap.put(UWEBConstant.CHANNEL_CACHE_NAME, ((Map<String, ChannelPojo>) channelResources));
		}
		return new ModelAndView(this.userCenterPage, cacheMap);
	}

	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public void setUserCenterPage(String userCenterPage) {
		this.userCenterPage = userCenterPage;
	}
	public void setUserQuestionPage(String userQuestionPage) {
		this.userQuestionPage = userQuestionPage;
	}
	public void setStaticResourceService(StaticResourceService staticResourceService) {
		this.staticResourceService = staticResourceService;
	}

}
