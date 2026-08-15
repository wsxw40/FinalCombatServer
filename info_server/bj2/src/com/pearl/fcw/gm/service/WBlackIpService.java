package com.pearl.fcw.gm.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.gm.dao.WBlackIpDao;
import com.pearl.fcw.gm.pojo.WBlackIp;

/**
 * IP黑名单
 */
@Service
public class WBlackIpService extends DmServiceImpl<WBlackIp, Integer> {
    @Resource
    private WBlackIpDao wBlackIpDao;

    @PostConstruct
    public void init() {
        this.genDao = wBlackIpDao;
    }

}
