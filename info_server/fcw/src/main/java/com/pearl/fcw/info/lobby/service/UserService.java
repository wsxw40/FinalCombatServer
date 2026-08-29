package com.pearl.fcw.info.lobby.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.info.lobby.dao.UserDao;
import com.pearl.fcw.info.lobby.pojo.User;
import com.pearl.fcw.info.lobby.utils.DateUtil;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    public User get(long id) {
        return userDao.get(id);
    }

    public User get(String name) {
        return userDao.get(name);
    }

    public User create(String name) {
        User user = new User();
        user.setUserName(name);
        user.setActivateTime(DateUtil.now());
        user.setLastLoginTime(DateUtil.now());
        userDao.save(user);
        return user;
    }
}
