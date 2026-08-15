package com.pearl.fcw.gm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.core.service.GmLogService.GmLogAdd;
import com.pearl.fcw.core.service.GmLogService.GmLogDelete;
import com.pearl.fcw.core.service.GmLogService.GmLogUpdate;
import com.pearl.fcw.gm.dao.WSysChestDao;
import com.pearl.fcw.gm.dao.WSysItemDao;
import com.pearl.fcw.gm.pojo.WSysChest;
import com.pearl.fcw.gm.pojo.WSysItem;
import com.pearl.fcw.utils.DataTablesPage;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.fcw.utils.GmThreadPool;
import com.pearl.fcw.utils.MapUtil;
import com.pearl.o2o.pojo.GmUser;

/**
 * 系统宝箱
 */
@Service
public class WSysChestService extends DmServiceImpl<WSysChest, Integer> {
    @Resource
    private WSysChestDao wSysChestDao;
    @Resource
    private WSysItemDao wSysItemDao;
    @Resource
    private GmThreadPool gmThreadPool;

    @PostConstruct
    public void init() {
        this.genDao = wSysChestDao;
    }

    public DataTablesPage<Map<String, Object>> getPageMap(DataTablesParameter param) throws Exception {
        DataTablesPage<WSysChest> pageWSysChest = findPage(param);
        List<Map<String, Object>> list = new ArrayList<>();
        List<WSysChest> wSysChestList = pageWSysChest.getData();
        list = MapUtil.toListMapExcept(wSysChestList);

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
        page.setDraw(pageWSysChest.getDraw());
        page.setError(pageWSysChest.getError());
        page.setRecordsFiltered(pageWSysChest.getRecordsFiltered());
        page.setRecordsTotal(pageWSysChest.getRecordsTotal());
        return page;
    }

    /**
	 * GM新增
	 * @param wSysChest
	 * @param gmUser
	 * @throws Exception
	 */
    @GmLogAdd
    public void addByGm(WSysChest wSysChest, GmUser gmUser) throws Exception {
        wSysChest.setIsRemoved(false);
        wSysChest = add(wSysChest);
        gmThreadPool.add(wSysChest, gmUser);
    }

    /**
	 * GM修改
	 * @param wSysChest
	 * @param gmUser
	 * @throws Exception
	 */
    @GmLogUpdate
    public void updateByGm(WSysChest wSysChest, GmUser gmUser) throws Exception {
        wSysChest.setIsRemoved(false);
        wSysChest = update(wSysChest);
        gmThreadPool.update(wSysChest, gmUser);
    }

    /**
	 * GM物理删除
	 * @param wSysChest
	 * @param gmUser
	 * @throws Exception
	 */
    @GmLogDelete
    public void deleteByGm(WSysChest wSysChest, GmUser gmUser) throws Exception {
        delete(wSysChest.getId());
    }
}
