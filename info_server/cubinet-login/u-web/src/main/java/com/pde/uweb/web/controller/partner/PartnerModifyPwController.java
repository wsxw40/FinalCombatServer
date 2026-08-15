
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
import com.pde.uweb.partner.dto.PartnerModifyPwRequestDto;
import com.pde.uweb.partner.service.PartnerService;
import com.pde.uweb.partner.service.PartnerUserService;

/**
 *  合作商用户管理 -- 修改密码
 * 
 *  @author Sean.Weng
 */
public class PartnerModifyPwController extends AbstractCommonController {

	private final static Logger logger = Logger.getLogger(PartnerModifyPwController.class);
	private PartnerUserService partnerUserService;
	private PartnerService partnerService;
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {

		PartnerModifyPwRequestDto requestDto = new PartnerModifyPwRequestDto();
		Map<String, String> resultMap = this.bindObjectWithCheck(request, requestDto, "requestDto", false, null);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("userData", requestDto.getUserData());
		
		if (!resultMap.isEmpty()) {
			logger.error("come from remote address [ "+request.getRemoteAddr()+" ] login parameters error");
			jsonObj.put("code", "2");
			jsonObj.put("msg", "parameters are not enough");
			this.handlerJSCallback(request, response, jsonObj.toString());
			return null;
		}
		
		// check signature is correct
		String source = requestDto.getPartnerId() + requestDto.getGameAccount() + requestDto.getOldPassword() + requestDto.getNewPassword();
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

		int result = partnerUserService.modifyUserPassword(
				requestDto.getPartnerId(), requestDto.getGameAccount(),
				requestDto.getOldPassword(), requestDto.getNewPassword());
		
		switch (result) {
			case 1:
				jsonObj.put("code", "1");
				jsonObj.put("msg", "success");
				break;
				
			case 2:
				jsonObj.put("code", "5");
				jsonObj.put("msg", "game account does not exist");
				break;
				
			case 3:
				jsonObj.put("code", "6");
				jsonObj.put("msg", "old password is not correct");
				break;
				
			case 0:
				jsonObj.put("code", "7");
				jsonObj.put("msg", "modify password failed");
				break;
	
			default:
				break;
		}

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
