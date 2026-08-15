package com.pearl.fcw.lobby.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import net.rubyeye.xmemcached.MemcachedClient;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.gm.pojo.enums.ItemType;
import com.pearl.fcw.gm.service.WLogService;
import com.pearl.fcw.lobby.dao.WPlayerDao;
import com.pearl.fcw.lobby.pojo.WPlayer;
import com.pearl.fcw.utils.DataTablesPage;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.fcw.utils.MapUtil;
import com.pearl.fcw.utils.O2oUtil;
import com.pearl.o2o.dao.impl.nonjoin.PlayerDao;
import com.pearl.o2o.dao.impl.nonjoin.PlayerInfoDao;
import com.pearl.o2o.dao.impl.nonjoin.PlayerItemDao;
import com.pearl.o2o.dao.impl.nonjoin.SysItemDao;
import com.pearl.o2o.pojo.GmUpdateLog;
import com.pearl.o2o.pojo.GmUser;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerInfo;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysCharacter;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.socket.SocketClientNew;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.ServiceLocator;

@Service
public class WPlayerService extends DmServiceImpl<WPlayer, Integer> {

    @Resource
    private WPlayerDao wPlayerDao;
    @Resource
    private PlayerDao playerDao;
    @Resource
    private MemcachedClient memcachedClient;
    @Resource
    private PlayerInfoDao playerInfoDao;
    @Resource
    private PlayerItemDao playerItemDao;
    @Resource
    private WLogService wLogService;
    @Resource
    private SocketClientNew socketClientNew;

    @PostConstruct
    public void init() {
        this.genDao = wPlayerDao;
    }

    /**
     * 利用WPlayer作为查询和中转，返回包含Player和PlayerInfo的Map的已缓存分页数据
     * @param param
     * @return
     * @throws Exception
     */
    public DataTablesPage<Map<String, Object>> getPageMap(DataTablesParameter param) throws Exception {
        DataTablesPage<WPlayer> pageWPlayer = findPage(param);
        List<Map<String, Object>> list = new ArrayList<>();
        List<Player> playerList = pageWPlayer.getData().stream().map(p -> playerDao.getPlayerById(p.getId())).collect(Collectors.toList());
        list = MapUtil.toListMapExcept(playerList);
        list.forEach(p -> {
            PlayerInfo playerInfo = playerInfoDao.getPlayerInfoByPid((int) p.get("id"));
            p.put("xunleiPoint", playerInfo.getXunleiPoint());
        });
        DataTablesPage<Map<String, Object>> page = new DataTablesPage<Map<String, Object>>(list);
        page.setDraw(pageWPlayer.getDraw());
        page.setError(pageWPlayer.getError());
        page.setRecordsFiltered(pageWPlayer.getRecordsFiltered());
        page.setRecordsTotal(pageWPlayer.getRecordsTotal());
        return page;
    }

    /**
     * GM修改玩家数据
     * @param newPlayer
     * @param newPlayerInfo
     * @param gmUser
     * @throws Exception
     */
    public void updateByGm(final Player newPlayer, final PlayerInfo newPlayerInfo, final GmUser gmUser) throws Exception {
        Date now = new Date();

        //FIXME 保证和原有的缓存数据一致。start
        //===========================================
        Player player = playerDao.getPlayerById(newPlayer.getId());
        Player oldPlayer = (Player) BeanUtils.cloneBean(player);
        player.setRank(newPlayer.getRank());
        player.setExp(newPlayer.getExp());
        player.setIsVip(newPlayer.getIsVip());
        player.setVipExp(newPlayer.getVipExp());
        player.setGPoint(newPlayer.getGPoint());
        player.setGScore(newPlayer.getGScore());
        player.setVoucher(newPlayer.getVoucher());
        player.setUnusableResource(newPlayer.getUnusableResource());
        player.setUsableResource(newPlayer.getUsableResource());
        player.setpResourceBeginTime((int) (now.getTime() / 1000));
        player.setMatchWin(newPlayer.getMatchWin());
        playerDao.updatePlayerInCache(player);
        wLogService.writeGmLog(new GmUpdateLog(gmUser, oldPlayer, player, now));

        PlayerInfo playerInfo = playerInfoDao.getPlayerInfoByPid(player.getId());
        PlayerInfo oldPlayerInfo = (PlayerInfo) BeanUtils.cloneBean(playerInfo);
        playerInfo.setXunleiPoint(newPlayerInfo.getXunleiPoint());
        playerInfoDao.update(playerInfo);
        wLogService.writeGmLog(new GmUpdateLog(gmUser, oldPlayerInfo, playerInfo, now));

        if (player.getIsVip() > oldPlayer.getIsVip()) {
            ServiceLocator.createService.createItemForVipLevelUp(player, player.getIsVip());
        }

        memcachedClient.delete(CacheUtil.oPlayerInfo(player.getId()));
        memcachedClient.delete(CacheUtil.oPlayer(player.getId()));
        memcachedClient.delete(CacheUtil.sPlayer(player.getId()));
        socketClientNew.messageUpdatePlayerMoney(player);
        //===========================================
        //FIXME 保证和原有的缓存数据一致。end
    }

    /**
     * 根据playerId获取PlayerItem的分页数据
     * @param param
     * @param playerId
     * @return
     * @throws Exception
     */
    public DataTablesPage<PlayerItem> getPlayerItemPageMap(DataTablesParameter param, Integer playerId) throws Exception {
        List<PlayerItem> list = new ArrayList<>();
        for (SysCharacter sc : ServiceLocator.getService.getDefaultSysCharacterList()) {
            list.addAll(ServiceLocator.getService.getPlayerItems(playerId, ItemType.WEAPON.getValue(), sc.getId(), 0));
            list.addAll(ServiceLocator.getService.getPlayerItems(playerId, ItemType.CHARACTER.getValue(), sc.getId(), 0));
            list.addAll(ServiceLocator.getService.getPlayerItems(playerId, ItemType.PART.getValue(), sc.getId(), 0));
        }
        list.addAll(ServiceLocator.getService.getPlayerItems(playerId, ItemType.MATERIAL.getValue(), 0, 0));
        list.addAll(ServiceLocator.getService.getPlayerItems(playerId, ItemType.BLUEPRINT.getValue(), 0, 0));
        list.addAll(ServiceLocator.getService.getPlayerItems(playerId, ItemType.PACKAGE.getValue(), 0, 0));
        list.addAll(ServiceLocator.getService.getPlayerItems(playerId, ItemType.ITEM.getValue(), 0, 0));
        list.addAll(ServiceLocator.getService.getPlayerItems(playerId, ItemType.OPEN.getValue(), 0, 0));
        return O2oUtil.getPage(PlayerItem.class, param, list, "getId", "getItemId", "getItemDisplayName");
    }

    /**
     * 逻辑删除玩家物品
     * @param id
     * @param playerId
     * @param gmUser
     * @throws Exception
     */
    public void removePlayerItem(Integer id, Integer playerId, GmUser gmUser) throws Exception {
        PlayerItem playerItem = playerItemDao.getPlayerItemByIdForPersonal(playerId, id);
        if (null != playerItem) {
            playerItemDao.softDeletePlayerItem(playerItem);
            SysItem sysItem = SysItemDao.getAllSysitem().get(playerItem.getItemId());
            if (null != sysItem) {
                ServiceLocator.deleteService.deletePlayerItemInMemcached(playerId, sysItem);
            }
        }
    }

    public void addPlayerItem(Integer playerId, Integer sysItemid, Integer unitType, Integer unit) throws Exception {
        PlayerItem playerItem = ServiceLocator.getService.getPlayerItemById(playerId, sysItemid);
        //玩家没有该物品，新增
        if (null == playerItem) {
            return;
        }
    }
}
