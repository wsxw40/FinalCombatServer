package com.pearl.manager.service;

import com.pearl.manager.dao.impl.nonjoin.ChargeLogDao;
import com.pearl.manager.dao.impl.nonjoin.DauDao;
import com.pearl.manager.dao.impl.nonjoin.GmGroupDao;
import com.pearl.manager.dao.impl.nonjoin.GmGroupPrivilegeDao;
import com.pearl.manager.dao.impl.nonjoin.GmLogDao;
import com.pearl.manager.dao.impl.nonjoin.GmPrivilegeDao;
import com.pearl.manager.dao.impl.nonjoin.GmUserDao;
import com.pearl.manager.dao.impl.nonjoin.GmUserGroupDao;
import com.pearl.manager.dao.impl.nonjoin.PayLogDao;


public class BaseService {
	
	protected GmUserDao				gmUserDao;
	protected GmLogDao				gmLogDao;
	protected GmGroupDao			gmGroupDao;
	protected GmPrivilegeDao		gmPrivilegeDao;
	protected GmGroupPrivilegeDao	gmGroupPrivilegeDao;
	protected GmUserGroupDao		gmUserGroupDao;
	protected ChargeLogDao 			chargeLogDao;
	protected PayLogDao             payLogDao;
	protected DauDao 			dauDao;
	public ChargeLogDao getChargeLogDao() {
		return chargeLogDao;
	}
	public void setChargeLogDao(ChargeLogDao chargeLogDao) {
		this.chargeLogDao = chargeLogDao;
	}
	public GmUserDao getGmUserDao() {
		return gmUserDao;
	}
	public void setGmUserDao(GmUserDao gmUserDao) {
		this.gmUserDao = gmUserDao;
	}
	public GmLogDao getGmLogDao() {
		return gmLogDao;
	}
	public void setGmLogDao(GmLogDao gmLogDao) {
		this.gmLogDao = gmLogDao;
	}
	public GmGroupDao getGmGroupDao() {
		return gmGroupDao;
	}
	public void setGmGroupDao(GmGroupDao gmGroupDao) {
		this.gmGroupDao = gmGroupDao;
	}
	public GmPrivilegeDao getGmPrivilegeDao() {
		return gmPrivilegeDao;
	}
	public void setGmPrivilegeDao(GmPrivilegeDao gmPrivilegeDao) {
		this.gmPrivilegeDao = gmPrivilegeDao;
	}
	public GmGroupPrivilegeDao getGmGroupPrivilegeDao() {
		return gmGroupPrivilegeDao;
	}
	public void setGmGroupPrivilegeDao(GmGroupPrivilegeDao gmGroupPrivilegeDao) {
		this.gmGroupPrivilegeDao = gmGroupPrivilegeDao;
	}
	public GmUserGroupDao getGmUserGroupDao() {
		return gmUserGroupDao;
	}
	public void setGmUserGroupDao(GmUserGroupDao gmUserGroupDao) {
		this.gmUserGroupDao = gmUserGroupDao;
	}
	public PayLogDao getPayLogDao() {
		return payLogDao;
	}
	public void setPayLogDao(PayLogDao payLogDao) {
		this.payLogDao = payLogDao;
	}
	public DauDao getDauDao() {
		return dauDao;
	}
	public void setDauDao(DauDao dauDao) {
		this.dauDao = dauDao;
	}
	
	
	
	
	
	
}
