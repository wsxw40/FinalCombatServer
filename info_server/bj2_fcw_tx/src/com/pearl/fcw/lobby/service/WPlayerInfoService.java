package com.pearl.fcw.lobby.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.lobby.dao.WPlayerInfoDao;
import com.pearl.fcw.lobby.pojo.WPlayerInfo;

@Service
public class WPlayerInfoService extends DmServiceImpl<WPlayerInfo, Integer> {

    @Resource
    private WPlayerInfoDao wPlayerInfoDao;

    @PostConstruct
    public void init() {
        this.genDao = wPlayerInfoDao;
    }
}
