package com.pearl.fcw.info.lobby.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.pearl.fcw.info.core.persistence.config.ClassMetadataConfig;
import com.pearl.fcw.info.core.persistence.config.ClassMetadataWrapper;
import com.pearl.fcw.info.core.persistence.dao.CacheSingleGenericDao;
import com.pearl.fcw.info.core.persistence.route.cache.CacheSingleSourceRouter;
import com.pearl.fcw.info.lobby.pojo.Player;
import com.pearl.fcw.info.lobby.utils.StringUtil;

@Repository
public class PlayerDao extends CacheSingleGenericDao<Player> {

    public PlayerDao() {
        super(Player.class);
    }

    public Player getByUserName(String userName) {
        final String sql = "select * from PLAYER p where p.USER_NAME = ? and p.IS_DELETED = 0";
        List<Player> list = getSingleSourceRouter().query(clazz, sql, userName);
        return list.isEmpty() ? null : list.get(0);
    }

    public Player getByName(String name) {
        final String sql = "select * from PLAYER p where p.NAME = ? and p.IS_DELETED = 0";
        List<Player> list = getSingleSourceRouter().query(clazz, sql, name);
        return list.isEmpty() ? null : list.get(0);
    }
    
    public int duplicatePlayerName(String name) {
        final String sql = "select Count(1) from PLAYER p where p.NAME = ? and p.IS_DELETED = 0";
        return getSingleSourceRouter().queryForInt(sql);
    }

    public int getCount() {
        final String sql = "select count(*) from PLAYER where IS_DELETED = 0";
        return getSingleSourceRouter().queryForInt(sql);
    }

    public List<Player> searchPlayerByName(final String likeName, final int start, final int pageNum) {
        return searchPlayerByName(likeName, start, pageNum, null);
    }

    public List<Player> searchPlayerByName(final String likeName, final int start, final int pageNum, Set<Long> limitIds) {
        StringBuilder sql = new StringBuilder("select * from PLAYER p where p.NAME like \"" + StringUtil.escapeSQLValue(likeName) + "%\" and p.IS_DELETED = 0");
        if (limitIds != null && limitIds.size() > 0) {
            for (Long limitId : limitIds) {
                sql.append(" AND p.ID !=").append(limitId).append(" ");
            }
        }
        sql.append(" limit " + start + " ," + pageNum);
        return getSingleSourceRouter().query(clazz, sql.toString());
    }

    public Integer countOfSearchPlayerByName(final String likeName) {
        return countOfSearchPlayerByName(likeName, null);
    }

    public Integer countOfSearchPlayerByName(final String likeName, Set<Long> limitIds) {
        StringBuilder sql = new StringBuilder("select count(*) from PLAYER p where p.NAME like \"" + StringUtil.escapeSQLValue(likeName) + "%\" and p.IS_DELETED = 0");
        if (limitIds != null && limitIds.size() > 0) {
            for (Long limitId : limitIds) {
                sql.append(" AND p.ID !=").append(limitId).append(" ");
            }
        }
        return getSingleSourceRouter().queryForInt(sql.toString());

    }

    public List<Integer> getAllIds() {
        ClassMetadataWrapper<Player> wrapper = ClassMetadataConfig.getClassMetadataWrapper(clazz);
        String sql = wrapper.getQueryAllIdsSql();
        return getSingleSourceRouter().queryForLongList(sql);
    }

    public List<Player> getPagePlayerList(int start, int pageSize) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("pageSize", pageSize);
        namedParameters.put("start", start);
        final String sql = "select * from PLAYER where IS_DELETED = 0 limit :start, :pageSize";
        return getSingleSourceRouter().query(clazz, sql, namedParameters);
    }

    public Player mergeNow(Player player) {
        if (!(getSingleSourceRouter() instanceof CacheSingleSourceRouter)) {
            throw new UnsupportedOperationException();
        }
        CacheSingleSourceRouter cssr = (CacheSingleSourceRouter) getSingleSourceRouter();
//        player.setUpdateTime(StringUtil.toInt(String.valueOf(DateUtil.now().getTime())));
        cssr.updateImmediately(player);
        return player;
    }

}
