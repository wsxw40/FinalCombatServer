package com.pearl.fcw.info.lobby.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.tx.annotation.Service;

import com.pearl.fcw.info.lobby.pojo.SysConfig;

@Service
public class DeleteService extends BaseService {
	static Logger log = LoggerFactory.getLogger(DeleteService.class.getName());
	private MessageService messageService;

	// ===============================================================================
	// User Services
	// ===============================================================================

	// ===============================================================================
	// Character Services
	// ===============================================================================

	public void deleteSysConfig(String key, String value) throws Exception {
		SysConfig sc = new SysConfig();
		sc.setName(key);
		sc.setValue(value);
		sysConfigDao.remove(sc);
	}

}
