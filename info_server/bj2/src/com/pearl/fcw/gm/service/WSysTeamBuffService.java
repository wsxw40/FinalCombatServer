package com.pearl.fcw.gm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.core.service.GmLogService.GmLogAdd;
import com.pearl.fcw.core.service.GmLogService.GmLogUpdate;
import com.pearl.fcw.gm.dao.WSysItemDao;
import com.pearl.fcw.gm.dao.WSysTeamBuffDao;
import com.pearl.fcw.gm.pojo.WSysItem;
import com.pearl.fcw.gm.pojo.WSysTeamBuff;
import com.pearl.fcw.utils.DataTablesPage;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.fcw.utils.MapUtil;
import com.pearl.o2o.pojo.GmUser;

/**
 * 战队交易有buff的道具
 */
@Service
public class WSysTeamBuffService extends DmServiceImpl<WSysTeamBuff, Integer> {
	@Resource
	private WSysItemDao wSysItemDao;
    @Resource
	private WSysTeamBuffDao wSysTeamBuffDao;

    @PostConstruct
    public void init() {
		this.genDao = wSysTeamBuffDao;
    }

	/**
	 * GM分页查询
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public DataTablesPage<Map<String, Object>> getPageMap(DataTablesParameter param) throws Exception {
		DataTablesPage<WSysTeamBuff> wSysTeamBuffPage = findPage(param);
		List<Map<String, Object>> list = new ArrayList<>();
		List<WSysTeamBuff> wSysTeamBuffList = wSysTeamBuffPage.getData();
		list = MapUtil.toListMapExcept(wSysTeamBuffList);

		list.forEach(p -> {
			try {
				WSysItem wSysItem = wSysItemDao.findEntity(Integer.parseInt(p.get("sysItemId").toString()));
				p.put("sysItemName", wSysItem.getName());
				p.put("sysItemDisplayName", wSysItem.getDisplayName());
			} catch (Exception e) {
				p.put("sysItemName", "");
				p.put("sysItemDisplayName", "");
			}
		});
		DataTablesPage<Map<String, Object>> page = new DataTablesPage<Map<String, Object>>(list);
		page.setDraw(page.getDraw());
		page.setError(page.getError());
		page.setRecordsFiltered(page.getRecordsFiltered());
		page.setRecordsTotal(page.getRecordsTotal());
		return page;
	}

	/**
	 * GM新增
	 * @param m
	 * @param gmUser
	 * @throws Exception
	 */
	@GmLogAdd
	public void addByGm(WSysTeamBuff m, GmUser gmUser) throws Exception {
		Date now = new Date();
		m.setIsRemoved(false);
		m.setCreateTime(now);
		m.setUpdateTime(now);
		add(m);
	}

	/**
	 * GM修改
	 * @param m
	 * @param gmUser
	 * @throws Exception
	 */
	@GmLogUpdate
	public void updateByGm(WSysTeamBuff m, GmUser gmUser) throws Exception {
		WSysTeamBuff m1 = findEntity(m.getId());
		m.setIsRemoved(false);
		m.setCreateTime(m1.getCreateTime());
		m.setUpdateTime(new Date());
		update(m);
	}

	/**
	 * GM逻辑删除
	 * @param m
	 * @param gmUser
	 * @throws Exception
	 */
	public void removeByGm(WSysTeamBuff m, GmUser gmUser) throws Exception {
		WSysTeamBuff m1 = findEntity(m.getId());
		m.setIsRemoved(true);
		m.setCreateTime(m1.getCreateTime());
		m.setUpdateTime(new Date());
		update(m);
	}

}
