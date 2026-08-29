package com.pearl.fcw.info.lobby.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.pearl.fcw.info.core.persistence.dao.CacheSingleGenericDao;
import com.pearl.fcw.info.lobby.pojo.User;

@Repository
public class UserDao extends CacheSingleGenericDao<User> {

    public UserDao() {
        super(User.class);
    }

    public User get(String userName) {
        final String sql = "select * from USER u where u.USER_NAME=? and u.IS_DELETED = 0";
        List<User> result = getSingleSourceRouter().query(clazz, sql, userName);
        User user = null;
        if (!result.isEmpty()) {
            user = get(result.get(0).getId());   // 再次从缓存中取得该User信息
        }
        return user;
    }

    public Integer countOfSearchUserByName(final String likeName) {
        final String sql = "select count(*) from USER p where p.USER_NAME like binary \"" + likeName + "%\" and p.IS_DELETED = 0";
        return getSingleSourceRouter().queryForInt(sql.toString());
    }

    public List<User> searchUserByName(final String likeName, int start, int pageNum) {
        Map<String, Object> param = new HashMap<>();
        param.put("likeName", likeName + "%");
        param.put("pageNum", pageNum);
        param.put("start", start);
        final String sql = "select * from USER p where p.USER_NAME like binary :likeName and p.IS_DELETED = 0 limit :start, :pageNum";
        return getSingleSourceRouter().query(clazz, sql, param);
    }

    public List<User> getPageUserList(int start, int pageSize) {
        start = start <= 0 ? 0 : start;
        Map<String, Object> param = new HashMap<>();
        param.put("pageSize", pageSize);
        param.put("start", start);
        final String sql = "select * from USER where IS_DELETED = 0 AND ID > 0 limit :start, :pageSize";
        return getSingleSourceRouter().query(clazz, sql, param);
    }

    public int getCount() {
        final String sql = "select count(*) from USER where IS_DELETED = 0";
        return getSingleSourceRouter().queryForInt(sql);
    }
}
