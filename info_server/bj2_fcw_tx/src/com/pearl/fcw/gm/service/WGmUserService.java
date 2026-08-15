package com.pearl.fcw.gm.service;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.dao.CacheDao;
import com.pearl.fcw.core.pojo.BaseEntity;
import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.core.service.GmLogService.GmLogReplaceBatch;
import com.pearl.fcw.core.service.GmLogService.GmLogRollBack;
import com.pearl.fcw.core.service.GmLogService.GmLogUpdate;
import com.pearl.fcw.gm.dao.WGmGroupDao;
import com.pearl.fcw.gm.dao.WGmGroupPrivilegeDao;
import com.pearl.fcw.gm.dao.WGmPrivilegeDao;
import com.pearl.fcw.gm.dao.WGmUserDao;
import com.pearl.fcw.gm.dao.WGmUserGroupDao;
import com.pearl.fcw.gm.pojo.WGmGroup;
import com.pearl.fcw.gm.pojo.WGmGroupPrivilege;
import com.pearl.fcw.gm.pojo.WGmPrivilege;
import com.pearl.fcw.gm.pojo.WGmUser;
import com.pearl.fcw.gm.pojo.WGmUserGroup;
import com.pearl.fcw.utils.Constants;
import com.pearl.fcw.utils.DataTablesPage;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.fcw.utils.MapUtil;
import com.pearl.o2o.pojo.GmUser;
import com.pearl.o2o.utils.PasswordUtil;

/**
 * GM用户相关
 */
@Service
public class WGmUserService extends DmServiceImpl<WGmUser, Integer> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private WGmUserDao wGmUserDao;
    @Resource
    private WGmUserGroupDao wGmUserGroupDao;
    @Resource
    private WGmGroupDao wGmGroupDao;
    @Resource
    private WGmGroupPrivilegeDao wGmGroupPrivilegeDao;
    @Resource
    private WGmPrivilegeDao wGmPrivilegeDao;

    //    private Map<Integer, Integer> userGroupMap = new ConcurrentHashMap<>();
    //    private Map<Integer, List<Integer>> groupPrivilegeMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        this.genDao = wGmUserDao;
        //        try {
        //            wGmUserGroupDao.findList(null).forEach(p -> userGroupMap.put(p.getUserId(), p.getGroupId()));
        //            wGmGroupPrivilegeDao.findList(null).forEach(p -> {
        //                if (!groupPrivilegeMap.containsKey(p.getGroupId())) {
        //                    groupPrivilegeMap.put(p.getGroupId(), new ArrayList<>());
        //                }
        //                groupPrivilegeMap.get(p.getGroupId()).add(p.getPrivilegeId());
        //            });
        //        } catch (Exception e) {
        //            logger.error(e.toString());
        //        }
    }

    /**
     * 分页信息中包含GmUser，GmGroup，GmPrivilege
     * @param param
     * @return
     * @throws Exception
     */
    public DataTablesPage<Map<String, Object>> findPageMap(DataTablesParameter param) throws Exception {
        DataTablesPage<Map<String, Object>> page = new DataTablesPage<Map<String, Object>>();
        DataTablesPage<WGmUser> wGmUserPage = findPage(param);
        page.setDraw(wGmUserPage.getDraw());
        page.setError(wGmUserPage.getError());
        page.setRecordsFiltered(wGmUserPage.getRecordsFiltered());
        page.setRecordsTotal(wGmUserPage.getRecordsTotal());
        List<Map<String, Object>> data = MapUtil.toListMapExcept(wGmUserPage.getData(), "password");
        for (Map<String, Object> map : data) {
            //获取该GM用户的Group信息
            WGmUserGroup wGmUserGroup = new WGmUserGroup();
            wGmUserGroup.setUserId((int) map.get("id"));
            List<WGmUserGroup> wGmUserGroupList = wGmUserGroupDao.findList(wGmUserGroup);
            int groupId = 0;
            if (!wGmUserGroupList.isEmpty()) {
                groupId = wGmUserGroupList.get(0).getGroupId();
            }
            map.put("groupId", groupId);
            String groupName = "";
            WGmGroup wGmGroup = wGmGroupDao.findEntity(groupId);
            if (null != wGmGroup) {
                groupName = wGmGroup.getName();
            }
            map.put("groupName", groupName);
            //获取该GM用户的Privilege信息
            WGmGroupPrivilege wGmGroupPrivilege = new WGmGroupPrivilege();
            wGmGroupPrivilege.setGroupId(groupId);
            Map<Integer, String> privileges = wGmGroupPrivilegeDao.findList(wGmGroupPrivilege).stream().collect(Collectors.toMap(WGmGroupPrivilege::getPrivilegeId, p -> {
                try {
                    WGmPrivilege wGmPrivilege = wGmPrivilegeDao.findEntity(p.getPrivilegeId());
                    return wGmPrivilege.getName();
                } catch (Exception e) {
                    return "";
                }
            }));
            map.put("privileges", privileges);
        }
        page.setData(data);
        return page;
    }

    /**
     * 分页信息中包含GmGroup，GmPrivilege
     * @param param
     * @return
     * @throws Exception
     */
    public DataTablesPage<Map<String, Object>> findGroupPageMap(DataTablesParameter param) throws Exception {
        DataTablesPage<Map<String, Object>> page = new DataTablesPage<Map<String, Object>>();
        DataTablesPage<WGmGroup> wGmGroupPage = wGmGroupDao.findPage(param);
        page.setDraw(wGmGroupPage.getDraw());
        page.setError(wGmGroupPage.getError());
        page.setRecordsFiltered(wGmGroupPage.getRecordsFiltered());
        page.setRecordsTotal(wGmGroupPage.getRecordsTotal());
        List<Map<String, Object>> data = MapUtil.toListMapExcept(wGmGroupPage.getData());
        for (Map<String, Object> map : data) {
            //获取该用户组的privilege信息
            int groupId = (int) map.getOrDefault("id", 0);
            WGmGroupPrivilege wGmGroupPrivilege = new WGmGroupPrivilege();
            wGmGroupPrivilege.setGroupId(groupId);
            Map<Integer, String> privileges = wGmGroupPrivilegeDao.findList(wGmGroupPrivilege).stream().collect(Collectors.toMap(WGmGroupPrivilege::getPrivilegeId, p -> {
                try {
                    WGmPrivilege wGmPrivilege = wGmPrivilegeDao.findEntity(p.getPrivilegeId());
                    return wGmPrivilege.getName();
                } catch (Exception e) {
                    return "";
                }
            }));
            map.put("privileges", privileges);
        }
        page.setData(data);
        return page;
    }

    /**
     * GM新增
     * @param wGmUser
     * @param groupId
     * @param gmUser
     * @throws Exception
     */
    public void addByGm(WGmUser wGmUser, Integer groupId, GmUser gmUser) throws Exception {
        wGmUser.setCreatorId(gmUser.getId());
        wGmUser.setDeleted("N");
        wGmUser.setPassword(PasswordUtil.encrypt(Constants.GM_DEFAULT_PWD));
        wGmUser = add(wGmUser);
        WGmUserGroup wGmUserGroup = new WGmUserGroup();
        wGmUserGroup.setUserId(wGmUser.getId());
        wGmUserGroup.setGroupId(groupId);
        wGmUserGroupDao.add(wGmUserGroup);
    }

    /**
     * GM修改
     * @param wGmUser
     * @param groupId
     * @param gmUser
     * @throws Exception
     */
    @GmLogUpdate
    public void updateByGm(WGmUser wGmUser, Integer groupId, GmUser gmUser) throws Exception {
        WGmUser oldWGmUser = findEntity(wGmUser.getId());
        oldWGmUser.setUserName(wGmUser.getUserName());
        oldWGmUser.setName(wGmUser.getName());
        oldWGmUser.setDeleted("N");
        wGmUser = update(oldWGmUser);
        //删除原有的用户和组的关系再新增
        WGmUserGroup wGmUserGroup = new WGmUserGroup();
        wGmUserGroup.setUserId(wGmUser.getId());
        for (WGmUserGroup p : wGmUserGroupDao.findList(wGmUserGroup)) {
            wGmUserGroupDao.delete(p.getId());
        }
        wGmUserGroup.setGroupId(groupId);
        wGmUserGroupDao.add(wGmUserGroup);
    }

    /**
     * GM重置其他GM用户的密码
     * @param wGmUser
     * @param gmUser
     * @throws Exception
     */
    @GmLogUpdate
    public void resetPwdByGm(WGmUser wGmUser, GmUser gmUser) throws Exception {
        WGmUser oldWGmUser = findEntity(wGmUser.getId());
        oldWGmUser.setPassword(PasswordUtil.encrypt(Constants.GM_DEFAULT_PWD));
        oldWGmUser = update(oldWGmUser);
    }

    /**
     * 重置自己的密码
     * @param pwd
     * @param gmUser
     * @throws Exception
     */
    @GmLogUpdate
    public void resetPwdBySelf(WGmUser wGmUser, GmUser gmUser) throws Exception {
        WGmUser oldWGmUser = findEntity(wGmUser.getId());
        oldWGmUser.setPassword(PasswordUtil.encrypt(wGmUser.getPassword()));
        oldWGmUser = update(oldWGmUser);
    }

    /**
     * GM逻辑删除
     * @param wGmUser
     * @param gmUser
     * @throws Exception
     */
    public void removeByGm(WGmUser wGmUser, GmUser gmUser) throws Exception {
        wGmUser.setDeleted("Y");
        delete(wGmUser.getId());
    }

    /**
     * GM物理删除
     * @param wGmUser
     * @param gmUser
     * @throws Exception
     */
    public void deleteByGm(WGmUser wGmUser, GmUser gmUser) throws Exception {
        //删除外键
        WGmUserGroup wGmUserGroup = new WGmUserGroup();
        wGmUserGroup.setUserId(wGmUser.getId());
        for (WGmUserGroup p : wGmUserGroupDao.findList(wGmUserGroup)) {
            wGmUserGroupDao.delete(p.getId());
        }
        //删除实体
        delete(wGmUser.getId());
    }

    /**
     * 获取GM用户可以访问的页面编码
     * @param gmUserId
     * @return
     * @throws Exception
     */
    public List<Integer> findPrivilegeList(int gmUserId) throws Exception {
        //        int groupId = userGroupMap.getOrDefault(gmUserId, 0);
        //        return groupPrivilegeMap.getOrDefault(groupId, new ArrayList<>());
        WGmUserGroup wGmUserGroup = new WGmUserGroup();
        wGmUserGroup.setUserId(gmUserId);
        int groupId = wGmUserGroupDao.findList(wGmUserGroup).get(0).getGroupId();
        WGmGroupPrivilege wGmGroupPrivilege = new WGmGroupPrivilege();
        wGmGroupPrivilege.setGroupId(groupId);
        return wGmGroupPrivilegeDao.findList(wGmGroupPrivilege).stream().map(WGmGroupPrivilege::getPrivilegeId).collect(Collectors.toList());
    }

    /**
     * 获取所有权限
     * @param t
     * @return
     * @throws Exception
     */
    public List<WGmPrivilege> findPrivilegeList(WGmPrivilege t) throws Exception {
        return wGmPrivilegeDao.findList(t);
    }

    /**
     * 获取指定用户组的权限
     * @param t
     * @return
     * @throws Exception
     */
    public List<WGmGroupPrivilege> findGroupPrivilegeList(WGmGroupPrivilege t) throws Exception {
        return wGmGroupPrivilegeDao.findList(t);
    }

    /**
     * GM新增用户组
     * @param wGmGroup
     * @param privilegeIds
     * @param gmUser
     * @throws Exception
     */
    public void addGroupByGm(WGmGroup wGmGroup, List<Integer> privilegeIds, GmUser gmUser) throws Exception {
        wGmGroup.setCreatorId(gmUser.getId());
        wGmGroup = wGmGroupDao.add(wGmGroup);
        for (Integer privilegeId : privilegeIds) {
            WGmGroupPrivilege w = new WGmGroupPrivilege();
            w.setGroupId(wGmGroup.getId());
            w.setPrivilegeId(privilegeId);
            wGmGroupPrivilegeDao.add(w);
        }
    }

    /**
     * GM修改用户组
     * @param wGmGroup
     * @param privilegeIds
     * @param gmUser
     * @throws Exception
     */
    public void updateGroupByGm(WGmGroup wGmGroup, List<Integer> privilegeIds, GmUser gmUser) throws Exception {
        WGmGroup oldWGmGroup = wGmGroupDao.findEntity(wGmGroup.getId());
        oldWGmGroup.setCode(wGmGroup.getCode());
        oldWGmGroup.setName(wGmGroup.getName());
        oldWGmGroup.setDescription(wGmGroup.getDescription());
        wGmGroup = wGmGroupDao.update(oldWGmGroup);
        //先删除原有的组员权限再新增
        WGmGroupPrivilege wGmGroupPrivilege = new WGmGroupPrivilege();
        wGmGroupPrivilege.setGroupId(wGmGroup.getId());
        for (WGmGroupPrivilege p : wGmGroupPrivilegeDao.findList(wGmGroupPrivilege)) {
            wGmGroupPrivilegeDao.delete(p.getId());
        }
        for (Integer privilegeId : privilegeIds) {
            WGmGroupPrivilege w = new WGmGroupPrivilege();
            w.setGroupId(wGmGroup.getId());
            w.setPrivilegeId(privilegeId);
            wGmGroupPrivilegeDao.add(w);
        }
    }

    /**
     * GM删除用户组
     * @param wGmGroup
     * @param gmUser
     * @throws Exception
     */
    public void deleteGroupByGm(WGmGroup wGmGroup, GmUser gmUser) throws Exception {
        //删除外键
        WGmGroupPrivilege wGmGroupPrivilege = new WGmGroupPrivilege();
        wGmGroupPrivilege.setGroupId(wGmGroup.getId());
        for (WGmGroupPrivilege p : wGmGroupPrivilegeDao.findList(wGmGroupPrivilege)) {
            wGmGroupPrivilegeDao.delete(p.getId());
        }
        //删除实体
        wGmGroupDao.delete(wGmGroup.getId());
    }

    /**
     * 批量导入数据
     * @param modelList
     * @param gmUser
     * @throws Exception
     */
    @GmLogReplaceBatch
    public void replaceBatchByGm(List<BaseEntity> modelList, GmUser gmUser) throws Exception {
        if (!modelList.isEmpty()) {
            CacheDao<? extends BaseEntity, ? extends Serializable> dao = CacheDao.getModelAndDaoMap().get(modelList.get(0).getClass());
            dao.replaceBatch(modelList);
        }
    }

    /**
     * 在回滚中删除一条数据
     * @param m
     * @param gmUser
     * @param dao
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @GmLogRollBack
    public void deleteByGmInRollBack(BaseEntity m, GmUser gmUser, CacheDao dao) throws Exception {
        dao.delete(m.getId());
    }

    /**
     * 在回滚中新增一条数据
     * @param m
     * @param gmUser
     * @param dao
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @GmLogRollBack
    public void addByGmInRollBack(BaseEntity m, GmUser gmUser, CacheDao dao) throws Exception {
        dao.add(m);
    }

    /**
     * 在回滚中修改一条数据
     * @param m
     * @param gmUser
     * @param dao
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @GmLogRollBack
    public void updateByGmInRollBack(BaseEntity m, GmUser gmUser, CacheDao dao) throws Exception {
        dao.update(m);
    }

    /**
     * 回滚多条数据
     * @param updateList
     * @param addList
     * @param deleteList
     * @param gmUser
     * @param updateDaoList
     * @param addDaoList
     * @param deleteDaoList
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @GmLogRollBack
    public void batchByGmInRollBack(LinkedList<BaseEntity> updateList, LinkedList<BaseEntity> addList, LinkedList<BaseEntity> deleteList, GmUser gmUser, LinkedList<CacheDao> updateDaoList,
            LinkedList<CacheDao> addDaoList, LinkedList<CacheDao> deleteDaoList)
            throws Exception {
        for (int i = 0; i < updateList.size(); i++) {
            updateDaoList.get(i).update(updateList.get(i));
        }
        for (int i = 0; i < addList.size(); i++) {
            addDaoList.get(i).add(addList.get(i));
        }
        for (int i = 0; i < deleteList.size(); i++) {
            deleteDaoList.get(i).delete(deleteList.get(i));
        }
    }
}
