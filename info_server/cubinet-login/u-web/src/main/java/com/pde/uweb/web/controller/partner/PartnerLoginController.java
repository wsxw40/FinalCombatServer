package com.pde.uweb.web.controller.partner;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.pde.infor.common.web.AbstractCommonController;
import com.pde.uweb.database.partner.partner.PartnerPojo;
import com.pde.uweb.database.partner.partneruser.PartnerUserPojo;
import com.pde.uweb.database.partner.webcafelist.WebcafeIpListPojo;
import com.pde.uweb.partner.dto.PartnerLoginRequestDto;
import com.pde.uweb.partner.service.PartnerService;
import com.pde.uweb.partner.service.PartnerUserService;
import com.pde.uweb.web.utils.MD5Util;

/**
 * 合作商用户管理 -- 用户登录
 * 
 * @author Sean.Weng
 */
public class PartnerLoginController extends AbstractCommonController {

	private final static Logger logger = Logger.getLogger(PartnerLoginController.class);
	private PartnerUserService partnerUserService;
	private PartnerService partnerService;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PartnerLoginRequestDto requestDto = new PartnerLoginRequestDto();
		Map<String, String> resultMap = this.bindObjectWithCheck(request, requestDto, "requestDto", false, null);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("userData", requestDto.getUserData());

		if (!resultMap.isEmpty()) {
			logger.error("come from remote address [ " + request.getRemoteAddr() + " ] login parameters error");
			jsonObj.put("code", "2");
			jsonObj.put("msg", "parameters are not enough");
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}

		// check signature is correct
		String source = requestDto.getPartnerId() + requestDto.getGameAccount() + requestDto.getPassword();

		// String xx = requestDto.getGameAccount();
		// System.out.println("aa="+xx);
		// System.out.println("bb="+new String(xx.getBytes("UTF-8")));
		// System.out.println("cc="+URLDecoder.decode(xx, "UTF-8"));
		// System.out.println("dd="+request.getParameter("gameAccount"));
		// System.out.println("ee="+new String(xx.getBytes("ISO-8859-1")));
		// System.out.println("ff="+URLDecoder.decode(xx, "ISO-8859-1"));
		// System.out.println("gg="+new String(xx.getBytes("ISO-8859-1"), "UTF-8"));
		//
		// System.out.println("source="+source);
		// System.out.println("md5="+MD5Util.md5(source));
		// System.out.println("signature="+requestDto.getSignature());

		if (!MD5Util.md5(source).equals(requestDto.getSignature())) {
			logger.error("come from remote address [ " + request.getRemoteAddr() + " ] signature is incorrect");
			jsonObj.put("code", "3");
			jsonObj.put("msg", "signature is incorrect");
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}

		// check partner is exist
		if (!partnerService.isPartnerExists(requestDto.getPartnerId(), PartnerPojo.STATUS_ACTIVE)) {
			logger.error("come from remote address [ " + request.getRemoteAddr() + " ]  partner is not exists or invalid");
			jsonObj.put("code", "4");
			jsonObj.put("msg", "partner is invalid");
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}

		PartnerUserPojo user = partnerUserService.getPartnerUser(requestDto.getPartnerId(), requestDto.getGameAccount());
		if (user == null) {
			logger.error("come from remote address [ " + request.getRemoteAddr() + " ] game account exists");
			jsonObj.put("code", "5");
			jsonObj.put("msg", "user does not exist");
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}
		if (!user.getPassword().equals(requestDto.getPassword())) {
			logger.error("come from remote address [ " + request.getRemoteAddr() + " ] password is incorrect");
			jsonObj.put("code", "6");
			jsonObj.put("msg", "password is incorrect");
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}

		jsonObj.put("code", "1");
		jsonObj.put("msg", "success");
		jsonObj.put("uid", requestDto.getGameAccount());
		WebcafeIpListPojo pj = partnerUserService.getWebcafeIpByRemotIp(request.getRemoteAddr());
		if (pj == null) {
			jsonObj.put("level", 0);
		} else {
			jsonObj.put("level", pj.getType());
		}
		String signature = requestDto.getSignature();
		// 返回的校验码规则：传过来的校验码的前5+后4+结果值1+pde
		String signa = signature.substring(0, 5) + signature.substring(signature.length() - 4, signature.length()) + "1pde";
		jsonObj.put("signature", MD5Util.md5(signa));

		this.handlerJSCallback(request, response, jsonObj.toString());
		return null;
	}

	public void setPartnerUserService(PartnerUserService partnerUserService) {
		this.partnerUserService = partnerUserService;
	}

	public void setPartnerService(PartnerService partnerService) {
		this.partnerService = partnerService;
	}
}
