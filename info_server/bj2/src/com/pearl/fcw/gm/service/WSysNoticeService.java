package com.pearl.fcw.gm.service;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.core.service.GmLogService.GmLogAdd;
import com.pearl.fcw.core.service.GmLogService.GmLogUpdate;
import com.pearl.fcw.gm.dao.WSysNoticeDao;
import com.pearl.fcw.gm.pojo.WSysNotice;
import com.pearl.o2o.pojo.GmUser;
import com.pearl.o2o.socket.SocketClientNew;

/**
 * 系统公告
 */
@Service
public class WSysNoticeService extends DmServiceImpl<WSysNotice, Integer> {
    @Resource
	private WSysNoticeDao wSysNoticeDao;
	@Resource
	private SocketClientNew socketClientNew;

    @PostConstruct
    public void init() {
		this.genDao = wSysNoticeDao;
    }

	/**
	 * GM新增
	 * @param m
	 * @param gmUser
	 * @throws Exception
	 */
	@GmLogAdd
	public void addByGm(WSysNotice m, GmUser gmUser) throws Exception {
		m.setIsRemoved(false);
		add(m);
		socketClientNew.refreashGMNotice();
	}

	/**
	 * GM修改
	 * @param m
	 * @param gmUser
	 * @throws Exception
	 */
	@GmLogUpdate
	public void updateByGm(WSysNotice m, GmUser gmUser) throws Exception {
		WSysNotice m1 = findEntity(m.getId());
		m.setIsRemoved(false);
		m.setCreateTime(m1.getCreateTime());
		m.setUpdateTime(new Date());
		update(m);
		socketClientNew.refreashGMNotice();
	}

	/**
	 * GM逻辑删除
	 * @param m
	 * @param gmUser
	 * @throws Exception
	 */
	public void removeByGm(WSysNotice m, GmUser gmUser) throws Exception {
		WSysNotice m1 = findEntity(m.getId());
		m.setIsRemoved(true);
		m.setCreateTime(m1.getCreateTime());
		m.setUpdateTime(new Date());
		update(m);
		socketClientNew.refreashGMNotice();
	}

}
