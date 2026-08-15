package com.pearl.fcw.gm.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.gm.dao.WSysItemGunPropertyDao;
import com.pearl.fcw.gm.pojo.WSysItemGunProperty;

/**
 * 系统物品
 */
@Service
public class WSysItemGunPropertyService extends DmServiceImpl<WSysItemGunProperty, Integer> {
    @Resource
	private WSysItemGunPropertyDao wSysItemGunpropertyDao;

    @PostConstruct
    public void init() {
		this.genDao = wSysItemGunpropertyDao;
    }

}
