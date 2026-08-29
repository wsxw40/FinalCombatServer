/**
 * 
 */
package com.pde.uweb.partner.service;

/**
 *  合作商字典表处理接口
 * 
 *  @author Sean.Weng
 */
public interface PartnerService {

	/**
	 *  判断指定的合作商是否存在
	 * 
	 *  @param partnerId 合作商编号
	 *  @param status 记录状态
	 *  @return 合作商是否存在
	 */
	public boolean isPartnerExists(String partnerId, int status);

}
