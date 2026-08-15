package com.pde.uweb.web.controller.ma;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.pde.infor.common.web.AbstractCommonController;
import com.pde.uweb.cache.service.StaticResourceService;
import com.pde.uweb.database.ma.user.UserPojo;
import com.pde.uweb.database.ws.channel.ChannelPojo;
import com.pde.uweb.framework.constants.UWEBConstant;
import com.pde.uweb.ma.dto.BindEmailRequestDto;
import com.pde.uweb.ma.service.MemberService;
import com.pde.uweb.web.utils.HttpSessionUtil;
import com.pde.uweb.web.vo.ma.UserVO;

/**
 *  逻辑控制--用户绑定邮箱
 * 
 *  @author Huanggang
 */
public class UserBindEmailController extends AbstractCommonController {
	
	private static final Logger logger = Logger.getLogger(UserBindEmailController.class); 
	
	/** 会员管理业务方法接口 */
	private MemberService memberService;
	
	/** 个人中心首页 */
	private String userCenterPage;
	
	/** 邮箱绑定页面 */
	private String userBindEmailPage;
	
	private StaticResourceService staticResourceService;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("come from remote address  [ " + request.getRemoteAddr() + " ] bind email ");
		
		BindEmailRequestDto requestDto = new BindEmailRequestDto();
		// 校验注册的请求参数
		Map<String, String> resultMap = this.bindObjectWithCheck(request, requestDto, "requestDto", false, null);
		if (!resultMap.isEmpty()) { // 参数校验未通过
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ]  bind email parameters error" );
			return new ModelAndView(this.userBindEmailPage, resultMap);
		}
		
		long userId = requestDto.getUserId();
		
		//查询email是否已存在
		long uid = memberService.getUserIdByEmail(requestDto.getEmail());
		if (uid > Long.valueOf(0) && uid != userId) {
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ]  email exist error" );
			resultMap.put("emailError", "邮箱已经存在");
			return new ModelAndView(this.userBindEmailPage, resultMap);
		}

		// 绑定邮箱操作
		boolean result = memberService.bindEmail(userId, requestDto.getEmail());
		if (!result) {
			resultMap.put("error", "绑定邮箱失败");
			return new ModelAndView(this.userBindEmailPage, resultMap);
		}
		
		// 更新 session 中的 user对象
		UserVO vo = HttpSessionUtil.getLoginUser(request);
		vo.setEmail(requestDto.getEmail());
		vo.setEmailStatus(UserPojo.STATUS_EMAIL_BIND_YES);
		
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
	public void setUserBindEmailPage(String userBindEmailPage) {
		this.userBindEmailPage = userBindEmailPage;
	}
	public void setStaticResourceService(StaticResourceService staticResourceService) {
		this.staticResourceService = staticResourceService;
	}

}
