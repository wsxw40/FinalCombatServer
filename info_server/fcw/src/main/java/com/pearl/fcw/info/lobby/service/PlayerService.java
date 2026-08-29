package com.pearl.fcw.info.lobby.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.info.core.lock.InfoServerLock;
import com.pearl.fcw.info.lobby.dao.PlayerDao;
import com.pearl.fcw.info.lobby.dao.PlayerItemDao;
import com.pearl.fcw.info.lobby.dao.SysItemDao;
import com.pearl.fcw.info.lobby.dao.UserDao;
import com.pearl.fcw.info.lobby.pojo.Player;

@Service
public class PlayerService {

    @Resource
    private SysItemDao sysItemDao;
    @Resource
    private UserDao userDao;
    @Resource
    private PlayerDao playerDao;
    @Resource
    private PlayerItemDao playerItemDao;

    @Resource
    protected InfoServerLock lock;

    /**
     * 获得角色信息
     * @param id 角色ID
     * @return Player
     */
    public Player get(long id) {
        Player p = playerDao.get(id);
        return p;
    }

    public Player getByUserName(String userName) {
        List<Long> ids = new ArrayList<>();
        Player player = playerDao.getByUserName(userName);
        if (null == player) {
            return null;
        }
        ids.add(player.getId());
        return playerDao.getByIdsList(ids).get(0);
    }

    public Player merge(Player player) {
        return playerDao.merge(player);
    }
}
