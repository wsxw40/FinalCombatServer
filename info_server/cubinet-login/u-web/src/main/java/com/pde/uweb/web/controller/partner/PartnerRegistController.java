
package com.pde.uweb.web.controller.partner;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.pde.uweb.web.utils.MD5Util;
import com.pde.infor.common.web.AbstractCommonController;
import com.pde.uweb.database.partner.partner.PartnerPojo;
import com.pde.uweb.partner.dto.PartnerRegistRequestDto;
import com.pde.uweb.partner.dto.PartnerRegistResultDto;
import com.pde.uweb.partner.service.PartnerService;
import com.pde.uweb.partner.service.PartnerUserService;

/**合作商用户管理 -- 用户注册
 * 
 *  @author Sean.Weng
 */
public class PartnerRegistController extends AbstractCommonController {

	private final static Logger logger = Logger.getLogger(PartnerRegistController.class);
	private PartnerUserService partnerUserService;
	private PartnerService partnerService;

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

		PartnerRegistRequestDto requestDto = new PartnerRegistRequestDto();
		Map<String, String> resultMap = this.bindObjectWithCheck(request, requestDto, "requestDto", false, null);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("userData", requestDto.getUserData());
		
		if (!resultMap.isEmpty()) {
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ]  regist parameters error");
			jsonObj.put("code", "2");
			jsonObj.put("msg", "parameters are not enough");
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}
		
		// check signature is correct
		String source = requestDto.getPartnerId() + requestDto.getGameAccount() + requestDto.getPassword() + requestDto.getEm();
		if (!MD5Util.md5(source).equals(requestDto.getSignature())) {
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ] signature is error");
			jsonObj.put("code", "3");
			jsonObj.put("msg", "signature is incorrect");
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}
		
		// check partner is exist
		if (!partnerService.isPartnerExists(requestDto.getPartnerId(), PartnerPojo.STATUS_ACTIVE)) {
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ]  partner is not exists or invalid error");
			jsonObj.put("code", "4");
			jsonObj.put("msg", "partner is invalid");
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}
		
		// check user is exist
		if (partnerUserService.isUserExistByGameAccount(requestDto.getPartnerId(), requestDto.getGameAccount())) {
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ] game account exists error");
			jsonObj.put("code", "5");
			jsonObj.put("msg", "game account already exists");
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}
		
		// check email is exist
		if (partnerUserService.isUserExistByEm(requestDto.getPartnerId(), requestDto.getEm())) {
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ]  email exist error");
			jsonObj.put("code", "6");
			jsonObj.put("msg", "email already exists");
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}
		
		// create user
		PartnerRegistResultDto resultDto = partnerUserService.doCreateMember(requestDto);
		if (!resultDto.isRegistSuccess()) {
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ] create user error, reason="+resultDto.getRegistError());
			jsonObj.put("code", "7");
			jsonObj.put("msg", "create user failed");
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}
		
		jsonObj.put("code", "1");
		jsonObj.put("msg", "success");
		jsonObj.put("uid", resultDto.getPartnerUid());
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
