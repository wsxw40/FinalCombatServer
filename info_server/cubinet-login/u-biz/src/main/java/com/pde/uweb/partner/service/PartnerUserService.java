/**
 * 
 */
package com.pde.uweb.partner.service;

import java.util.Map;

import com.pde.uweb.database.partner.partneruser.PartnerUserPojo;
import com.pde.uweb.database.partner.webcafelist.WebcafeIpListPojo;
import com.pde.uweb.partner.dto.PartnerRegistRequestDto;
import com.pde.uweb.partner.dto.PartnerRegistResultDto;

/**
 *  合作商业务处理接口
 * 
 *  @author Sean.Weng
 */
public interface PartnerUserService {

	/**
	 *  创建合作商导入的会员
	 * 
	 *  @param requestDto 注册请求对象
	 *  @return 返回注册结果对象
	 */
	public PartnerRegistResultDto doCreateMember(PartnerRegistRequestDto requestDto);

	/**
	 *  根据游戏账号校验用户是否存在 
	 * 
	 *  @param partnerId 合作商id
	 *  @param gameAccount 游戏账号
	 */
	public boolean isUserExistByGameAccount(String partnerId, String gameAccount);
	
	/**
	 *  根据email校验用户是否存在
	 * 
	 *  @param partnerId 合作商id
	 *  @param em 邮箱
	 */
	public boolean isUserExistByEm(String partnerId, String em);
	
	/**
	 *  修改用户密码
	 *
	 *  @param partnerId 合作商id
	 *  @param gameAccount 游戏账号
	 *  @param oldPw 旧密码
	 *  @param newPw 新密码
	 *  @return 返回修改结果（0-修改失败；1-修改成功；2-用户名不存在；3-原密码不正确）
	 */
	public int modifyUserPassword(String partnerId, String gameAccount, String oldPw, String newPw);

	/**
	 *  获得 partner user 对象
	 *
	 *  @param partnerId 合作商id
	 *  @param gameAccount 游戏账号
	 *  @return 返回合作商导入的用户对象，找不到返回null
	 */
	public PartnerUserPojo getPartnerUser(String partnerId, String gameAccount);
	
	public Map<String,WebcafeIpListPojo> getWebcafeIpList();
	public WebcafeIpListPojo getWebcafeIpByRemotIp(String remoteAddress);

}
