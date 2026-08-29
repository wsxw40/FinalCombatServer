package com.pearl.fcw.gm.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pearl.fcw.core.controller.DmController;
import com.pearl.fcw.core.controller.UrlCode;
import com.pearl.fcw.gm.service.WLogService;
import com.pearl.fcw.lobby.service.WPlayerService;
import com.pearl.fcw.utils.Constants;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.o2o.pojo.GmUpdateLog;
import com.pearl.o2o.pojo.GmUser;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerInfo;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.ServiceLocator;

/**
 * GM操作游戏内玩家
 */
@Controller
@RequestMapping(value = "fcw/gm")
@UrlCode(4)
public class PlayerController extends DmController {
    @Resource
    private WPlayerService wPlayerService;
    @Resource
    private WLogService wLogService;

    /**
     * 玩家列表
     * @param request
     * @param response
     * @return
     * @throws Exception1
     */
    @RequestMapping(value = "players")
    public @ResponseBody Object getPageMap(HttpServletRequest request, HttpServletResponse response, DataTablesParameter param) throws Exception {
        return wPlayerService.getPageMap(param);
    }

    /**
     * 保存玩家Player和PlayerInfo数据
     * @param request
     * @param response
     * @param player
     * @param playerInfo
     * @throws Exception
     */
    @RequestMapping(value = "savePlayer")
    public void save(HttpServletRequest request, HttpServletResponse response, Player player, PlayerInfo playerInfo) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        wPlayerService.updateByGm(player, playerInfo, gmUser);
        response.sendRedirect(request.getContextPath() + "/fcw/#/players");
    }

    /**
     * 玩家物品列表
     * @param request
     * @param response
     * @param param
     * @param pid
     * @return
     */
    @RequestMapping(value = "playerItems")
    public @ResponseBody Object getPlayerItemPageMap(HttpServletRequest request, HttpServletResponse response, DataTablesParameter param, @RequestParam("pId") Integer playerId) throws Exception {
        return wPlayerService.getPlayerItemPageMap(param, playerId);
    }

    /**
     * 逻辑删除玩家物品
     * @param request
     * @param response
     * @param playerItem
     * @throws Exception
     */
    @RequestMapping(value = "removePlayerItem")
    public void removePlayerItem(HttpServletRequest request, HttpServletResponse response, @RequestParam("id") Integer id, @RequestParam("pId") Integer playerId) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        wPlayerService.removePlayerItem(id, playerId, gmUser);
        response.sendRedirect(request.getContextPath() + "/fcw/#/playerItems");
    }

    @RequestMapping(value = "savePlayerItem")
    public void savePlayerItem(HttpServletRequest request, HttpServletResponse response, PlayerItem playerItem) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        PlayerItem target = ServiceLocator.getService.getPlayerItemById(playerItem.getPlayerId(), playerItem.getId());
        target.setLevel(playerItem.getLevel());
        target.setGunProperty1(playerItem.getGunProperty1());
        target.setGunProperty2(playerItem.getGunProperty2());
        target.setGunProperty3(playerItem.getGunProperty3());
        target.setGunProperty4(playerItem.getGunProperty4());
        target.setGunProperty5(playerItem.getGunProperty5());
        target.setGunProperty6(playerItem.getGunProperty6());
        target.setGunProperty7(playerItem.getGunProperty7());
        target.setIsDeleted("N");
        PlayerItem old = ServiceLocator.getService.getPlayerItemById(playerItem.getPlayerId(), playerItem.getId());
        ServiceLocator.updateService.updatePlayerItem(target);
        target = ServiceLocator.getService.getPlayerItemById(playerItem.getPlayerId(), playerItem.getId());
        wLogService.writeGmLog(new GmUpdateLog(gmUser, old, target, new Date()));
        response.sendRedirect(request.getContextPath() + "/fcw/#/playerItems");
    }
}
