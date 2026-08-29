package com.pearl.fcw.gm.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.gm.dao.WUnspeakerDao;
import com.pearl.fcw.gm.pojo.WUnspeaker;

/**
 * 禁言玩家
 */
@Service
public class WUnspeakerService extends DmServiceImpl<WUnspeaker, Integer> {
    @Resource
	private WUnspeakerDao wUnspeakerDao;

    @PostConstruct
    public void init() {
		this.genDao = wUnspeakerDao;
    }

}
