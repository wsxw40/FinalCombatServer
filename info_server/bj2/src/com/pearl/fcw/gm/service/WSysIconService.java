package com.pearl.fcw.gm.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.gm.dao.WSysIconDao;
import com.pearl.fcw.gm.pojo.WSysIcon;

@Service
public class WSysIconService extends DmServiceImpl<WSysIcon, Integer> {
    @Resource
    private WSysIconDao wSysIconDao;

    @PostConstruct
    public void init() {
        this.genDao = wSysIconDao;
    }
}
