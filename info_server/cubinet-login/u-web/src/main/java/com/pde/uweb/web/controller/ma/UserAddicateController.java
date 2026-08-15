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
import com.pde.uweb.ma.dto.AddicateRequestDto;
import com.pde.uweb.ma.service.MemberService;
import com.pde.uweb.web.utils.HttpSessionUtil;
import com.pde.uweb.web.vo.ma.UserVO;

/**
 *  逻辑控制--防沉迷设置
 * 
 *  @author Huanggang
 */
public class UserAddicateController extends AbstractCommonController {
	
	private static final Logger logger = Logger.getLogger(UserAddicateController.class); 
	
	/** 会员管理业务方法接口 */
	private MemberService memberService;
	
	/** 个人中心首页 */
	private String userCenterPage;
	
	/** 防沉迷设置页面 */
	private String userAddicatePage;
	
	private StaticResourceService staticResourceService;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.info("come from remote address  [ " + request.getRemoteAddr() + " ] addicate audit ");
		
		AddicateRequestDto requestDto = new AddicateRequestDto();
		// 校验请求参数
		Map<String, String> resultMap = this.bindObjectWithCheck(request, requestDto, "requestDto", false, null);
		if (!resultMap.isEmpty()) { // 参数校验未通过
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ]  bind email parameters error" );
			return new ModelAndView(this.userAddicatePage, resultMap);
		}
		
		// 防沉迷认证操作
		boolean isSuccess = memberService.addicateAudit(requestDto.getUserId(), requestDto.getIdName(), requestDto.getIdNumber());
		if (!isSuccess) {
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ] addicate audit error" );
			resultMap.put("error", "防沉迷认证操作失败");
			return new ModelAndView(this.userAddicatePage, resultMap);
		}

		// 更新 session 中的 user对象
		UserVO vo = HttpSessionUtil.getLoginUser(request);
		vo.setIdName(requestDto.getIdName());
		vo.setIdNumber(requestDto.getIdNumber());
		vo.setStatus(UserPojo.STATUS_ACTIVE);
		
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
	public void setUserAddicatePage(String userAddicatePage) {
		this.userAddicatePage = userAddicatePage;
	}
	public void setStaticResourceService(StaticResourceService staticResourceService) {
		this.staticResourceService = staticResourceService;
	}

}
