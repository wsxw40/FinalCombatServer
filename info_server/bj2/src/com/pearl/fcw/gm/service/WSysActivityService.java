package com.pearl.fcw.gm.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.gm.dao.WSysActivityDao;
import com.pearl.fcw.gm.pojo.WSysActivity;

/**
 * 系统活动
 */
@Service
public class WSysActivityService extends DmServiceImpl<WSysActivity, Integer> {
    @Resource
	private WSysActivityDao wSysActivityDao;

    @PostConstruct
    public void init() {
		this.genDao = wSysActivityDao;
    }

}
