/**
 * 
 */
package com.pde.uweb.partner.service.impl;

import com.pde.uweb.database.partner.partner.PartnerDao;
import com.pde.uweb.partner.service.PartnerService;

public class PartnerServiceImpl implements PartnerService {

	private PartnerDao partnerDao;

	@Override
	public boolean isPartnerExists(String partnerId, int status) {
		
		long partnerNum = partnerDao.getPartnerPkId(partnerId, status);
		return partnerNum > Long.valueOf(0);
	}
	
	public void setPartnerDao(PartnerDao partnerDao) {
		this.partnerDao = partnerDao;
	}
}
