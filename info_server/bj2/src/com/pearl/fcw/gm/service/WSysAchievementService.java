package com.pearl.fcw.gm.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.gm.dao.WSysAchievementDao;
import com.pearl.fcw.gm.pojo.WSysAchievement;

/**
 * 系统成就
 */
@Service
public class WSysAchievementService extends DmServiceImpl<WSysAchievement, Integer> {
    @Resource
    private WSysAchievementDao wSysAchievementDao;

    @PostConstruct
    public void init() {
        this.genDao = wSysAchievementDao;
    }

}
