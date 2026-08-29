/**
 * 
 */
package com.pde.uweb.partner.service.impl;

import java.util.Map;

import com.pde.infor.common.utils.KeyUtil;
import com.pde.uweb.database.partner.partneruser.PartnerUserDao;
import com.pde.uweb.database.partner.partneruser.PartnerUserPojo;
import com.pde.uweb.database.partner.webcafelist.WebcafeIpListDao;
import com.pde.uweb.database.partner.webcafelist.WebcafeIpListPojo;
import com.pde.uweb.partner.dto.PartnerRegistRequestDto;
import com.pde.uweb.partner.dto.PartnerRegistResultDto;
import com.pde.uweb.partner.service.PartnerUserService;

public class PartnerUserServiceImpl implements PartnerUserService {

	private PartnerUserDao partnerUserDao;
	private WebcafeIpListDao webcafeIpListDao;



	@Override
	public PartnerRegistResultDto doCreateMember(PartnerRegistRequestDto requestDto) {
		
		PartnerRegistResultDto result = new PartnerRegistResultDto();
		try {
			PartnerUserPojo user = new PartnerUserPojo();
			user.setUserId(KeyUtil.generateDBKey());
			user.setPartnerId(requestDto.getPartnerId());
			user.setGameAccount(requestDto.getGameAccount());
			user.setPassword(requestDto.getPassword());
			user.setEmail(requestDto.getEm());
			user.setStatus(PartnerUserPojo.STATUS_ACTIVE);
			partnerUserDao.add(user);
			
			result.setPartnerUid(String.valueOf(user.getUserId()));
			result.setRegistSuccess(true);
		} catch (Exception e) {
			result.setRegistSuccess(false);
			result.setRegistError(e.getMessage());
		}
		return result;
	}

	public void setWebcafeIpListDao(WebcafeIpListDao webcafeIpListDao) {
		this.webcafeIpListDao = webcafeIpListDao;
	}

	@Override
	public boolean isUserExistByGameAccount(String partnerId, String gameAccount) {
		
		long userId = partnerUserDao.getUidByGameAccount(partnerId, gameAccount);
		return userId > Long.valueOf(0);
	}
	
	@Override
	public boolean isUserExistByEm(String partnerId, String em) {
		
		long userId = partnerUserDao.getUidByEm(partnerId, em);
		return userId > Long.valueOf(0);
	}

	/**
	 *  0-修改失败；1-修改成功；2-用户名不存在；3-原密码不正确
	 */
	@Override
	public int modifyUserPassword(String partnerId, String gameAccount, String oldPw, String newPw) {
		
		PartnerUserPojo user = this.getPartnerUser(partnerId, gameAccount);
		if (user==null)
			return 2;
		if (!user.getPassword().equals(oldPw))
			return 3;
		user.setPassword(newPw);
		boolean result = partnerUserDao.updateForField(user);
		return result ? 1 : 0;
	}
	
	@Override
	public PartnerUserPojo getPartnerUser(String partnerId, String gameAccount) {
		long partnerUserId = partnerUserDao.getUidByGameAccount(partnerId, gameAccount);
		return partnerUserDao.select(partnerUserId);
	}
	
	public void setPartnerUserDao(PartnerUserDao partnerUserDao) {
		this.partnerUserDao = partnerUserDao;
	}

	@Override
	public Map<String, WebcafeIpListPojo> getWebcafeIpList() {
		return webcafeIpListDao.selectAll();
	}
	public WebcafeIpListPojo getWebcafeIpByRemotIp(String remoteAddress){
		WebcafeIpListPojo pojo=webcafeIpListDao.selectAll().get(remoteAddress);
		return pojo;
	}
}
