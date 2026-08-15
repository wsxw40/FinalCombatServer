package com.pearl.fcw.gm.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.gm.dao.WSysQuestDao;
import com.pearl.fcw.gm.pojo.WSysQuest;

/**
 * 页游整合后的任务
 */
@Service
public class WSysQuestService extends DmServiceImpl<WSysQuest, Integer> {
    @Resource
	private WSysQuestDao wSysQuestDao;

    @PostConstruct
    public void init() {
		this.genDao = wSysQuestDao;
    }

}
