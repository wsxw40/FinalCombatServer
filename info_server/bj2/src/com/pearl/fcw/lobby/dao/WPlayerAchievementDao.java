package com.pearl.fcw.lobby.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.pearl.fcw.core.dao.CacheDao;
import com.pearl.fcw.gm.dao.WSysAchievementDao;
import com.pearl.fcw.lobby.pojo.WPlayerAchievement;

@Repository
public class WPlayerAchievementDao extends CacheDao<WPlayerAchievement, Serializable> {
    @Resource
    private WSysAchievementDao wSysAchievementDao;

    @Override
    public WPlayerAchievement findEntity(WPlayerAchievement m) throws Exception {
        if (null != m && m.getClass() != WPlayerAchievement.class) {
            WPlayerAchievement mClone = (WPlayerAchievement) m.clone();
            cloneTo(super.findEntity(m), mClone);
            return mClone;
        }
        return super.findEntity(m);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<Serializable, WPlayerAchievement> findMap(WPlayerAchievement t) throws Exception {
        Map<Serializable, WPlayerAchievement> map = new HashMap<>();
        int shareId = t.getShareId();

        List<WPlayerAchievement> list = wSysAchievementDao.findList(null).stream().map(p -> p.getCharacterId()).distinct().map(p -> {
            try {
                return ((Class<WPlayerAchievement>) Class.forName(entityClass.getName() + p)).newInstance();
            } catch (Exception e) {
                return null;
            }
        }).collect(Collectors.toList());

        put(map, shareId, list);
        return map;
    }

    private void cloneTo(final WPlayerAchievement source, final WPlayerAchievement target) {
        target.setId(source.getId());
        target.setSysAchievementIds(source.getSysAchievementIds());
        target.setSysAchievementIdsList(source.getSysAchievementIdsList());
        target.setLevel(source.getLevel());
        target.setNumber(source.getNumber());
        target.setStatus(source.getStatus());
        target.setTotalLevel(source.getTotalLevel());
        target.setGroup(source.getGroup());
        target.setBackUp(source.getBackUp());
    }

    private void put(final Map<Serializable, WPlayerAchievement> map, int shareId, List<WPlayerAchievement> achievements) throws Exception {
        for (WPlayerAchievement m : achievements) {
            if (null == m) {
                continue;
            }
            m.setShareId(shareId);
            for (Entry<Serializable, WPlayerAchievement> kv : super.findMap(m).entrySet()) {
                WPlayerAchievement mClone = (WPlayerAchievement) m.clone();
                cloneTo(kv.getValue(), mClone);
                String key = mClone.getSysCharacterId() + "|" + mClone.getId();
                map.put(key, mClone);
            }
        }
    }

}
