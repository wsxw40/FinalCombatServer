package com.pearl.fcw.gm.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.gm.dao.WSysOnlineAwardDao;
import com.pearl.fcw.gm.pojo.WSysOnlineAward;

@Service
public class WSysOnlineAwardService extends DmServiceImpl<WSysOnlineAward, Integer> {
    @Resource
	private WSysOnlineAwardDao wSysOnlineAwardDao;

    @PostConstruct
    public void init() {
		this.genDao = wSysOnlineAwardDao;
    }
}
