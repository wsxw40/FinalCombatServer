package com.pearl.fcw.info.lobby.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.info.core.lock.InfoServerLock;
import com.pearl.fcw.info.lobby.dao.PlayerDao;
import com.pearl.fcw.info.lobby.dao.SysItemDao;
import com.pearl.fcw.info.lobby.exception.BaseException;
import com.pearl.fcw.info.lobby.exception.DataTooLongException;
import com.pearl.fcw.info.lobby.exception.DuplicatePlayerException;
import com.pearl.fcw.info.lobby.exception.EmptyInputException;
import com.pearl.fcw.info.lobby.exception.IllegalCharacterException;
import com.pearl.fcw.info.lobby.pojo.Player;
import com.pearl.fcw.info.lobby.utils.ExceptionMessage;
import com.pearl.fcw.info.lobby.utils.KeywordFilterUtil;
import com.pearl.fcw.info.lobby.utils.StringUtil;

@Service
public class PlayerService {

    @Resource
    private SysItemDao sysItemDao;
    @Resource
    private PlayerDao playerDao;
//    @Resource
//    private PlayerItemDao playerItemDao;
    @Resource
    protected InfoServerLock lock;
    @Resource
    private GetService getService;

    /**
     * 获得角色信息
     * @param id 角色ID
     * @return Player
     */
    public Player get(Integer id) {
        Player p = playerDao.get(id);
        return p;
    }

    public Player getByUserName(String userName) {
        List<Integer> ids = new ArrayList<>();
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
    
    public Player createPlayer(String userName, String name,int accountType) throws Exception{
    	// 1.check list:
		// empty input;too long input;illegal character input;duplicate
		// player;player size
		if (name == null || "".equals(name)) {
			throw new EmptyInputException(
					ExceptionMessage.EMPTY_STR_CREATE_PLAYER);
		}
		//FIXME
		if (name.length() > StringUtil.toInt(getService.getSysConfig().get("playername.maxlength"))) {
			throw new DataTooLongException(
					ExceptionMessage.TOO_LONG_CREATE_PLAYER);
		}else if(name.length()< StringUtil.toInt(getService.getSysConfig().get("playername.minlength"))){
			throw new BaseException(ExceptionMessage.TOO_SHORT_CREATE_PLAYER);
		}
		if (StringUtil.filter(name)) {
			throw new IllegalCharacterException(
					ExceptionMessage.ILLEGAL_CHARACTER_CREATE_PLAYER);
		}
		if (!KeywordFilterUtil.isLegalInput(name)) {
			throw new IllegalCharacterException(
					ExceptionMessage.ILLEGAL_CHARACTER_CREATE_PLAYER);
		}
		int size = playerDao.duplicatePlayerName(name);
		if (size > 0) {
			throw new DuplicatePlayerException();
		}
		
		Player player = new Player();
		player.setName(name);
		player.setUserName(userName);
		
    	return playerDao.save(player);
    }
}
