package com.pearl.fcw.lobby.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import net.rubyeye.xmemcached.MemcachedClient;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.cache.EntityManager;
import com.pearl.fcw.core.cache.redis.RedisClient;
import com.pearl.fcw.core.pojo.proxy.EntityProxy;
import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.gm.dao.WPaymentDao;
import com.pearl.fcw.gm.dao.WSysAchievementDao;
import com.pearl.fcw.gm.dao.WSysActivityDao;
import com.pearl.fcw.gm.dao.WSysCharacterDao;
import com.pearl.fcw.gm.dao.WSysConfigDao;
import com.pearl.fcw.gm.dao.WSysGrowthMissionDao;
import com.pearl.fcw.gm.dao.WSysItemDao;
import com.pearl.fcw.gm.dao.WSysLevelDao;
import com.pearl.fcw.gm.dao.WSysQuestDao;
import com.pearl.fcw.gm.pojo.WPayment;
import com.pearl.fcw.gm.pojo.WSysAchievement;
import com.pearl.fcw.gm.pojo.WSysActivity;
import com.pearl.fcw.gm.pojo.WSysCharacter;
import com.pearl.fcw.gm.pojo.WSysGrowthMission;
import com.pearl.fcw.gm.pojo.WSysItem;
import com.pearl.fcw.gm.pojo.WSysLevel;
import com.pearl.fcw.gm.pojo.WSysQuest;
import com.pearl.fcw.gm.pojo.enums.ItemSubType;
import com.pearl.fcw.gm.service.WLogService;
import com.pearl.fcw.gm.service.WSysConfigService;
import com.pearl.fcw.lobby.dao.WPlayerDao;
import com.pearl.fcw.lobby.pojo.ItemGunProperty;
import com.pearl.fcw.lobby.pojo.ParamObject;
import com.pearl.fcw.lobby.pojo.WBuyitemRecord;
import com.pearl.fcw.lobby.pojo.WCharacterData;
import com.pearl.fcw.lobby.pojo.WPlayer;
import com.pearl.fcw.lobby.pojo.WPlayerAchievement;
import com.pearl.fcw.lobby.pojo.WPlayerActivity;
import com.pearl.fcw.lobby.pojo.WPlayerBuff;
import com.pearl.fcw.lobby.pojo.WPlayerGrowthMission;
import com.pearl.fcw.lobby.pojo.WPlayerInfo;
import com.pearl.fcw.lobby.pojo.WPlayerItem;
import com.pearl.fcw.lobby.pojo.WPlayerMelting;
import com.pearl.fcw.lobby.pojo.WPlayerPack;
import com.pearl.fcw.lobby.pojo.WPlayerQuest;
import com.pearl.fcw.lobby.pojo.WPlayerTrack;
import com.pearl.fcw.lobby.pojo.columnDescriptor.WCharacterDataColumnDescriptor;
import com.pearl.fcw.lobby.pojo.columnDescriptor.WPlayerItemColumnDescriptor;
import com.pearl.fcw.lobby.pojo.json.JsonPlayerInfoCaches;
import com.pearl.fcw.lobby.pojo.proxy.ProxyItemGunProperty;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWBuyitemRecord;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWCharacterData;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayer;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerAchievement;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerActivity;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerBuff;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerGrowthMission;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerInfo;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerItem;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerMelting;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerPack;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerQuest;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerTrack;
import com.pearl.fcw.proto.ProtoConverter;
import com.pearl.fcw.proto.enums.EAchievementAction;
import com.pearl.fcw.proto.enums.EAchievementStatType;
import com.pearl.fcw.proto.enums.EAchievementStatus;
import com.pearl.fcw.proto.enums.EAchievementType;
import com.pearl.fcw.proto.enums.EActivityActionType;
import com.pearl.fcw.proto.enums.EActivityAwardStatus;
import com.pearl.fcw.proto.enums.EActivityStatus;
import com.pearl.fcw.proto.enums.ECharacterDataNumberParam;
import com.pearl.fcw.proto.enums.EClientModuleStatus;
import com.pearl.fcw.proto.enums.EClientModuleType;
import com.pearl.fcw.proto.enums.EGameType;
import com.pearl.fcw.proto.enums.EGrowthMissionType;
import com.pearl.fcw.proto.enums.EItemColor;
import com.pearl.fcw.proto.enums.EItemIBuffId;
import com.pearl.fcw.proto.enums.EItemIId;
import com.pearl.fcw.proto.enums.EItemNumberParam;
import com.pearl.fcw.proto.enums.EItemRareType;
import com.pearl.fcw.proto.enums.EItemSubType;
import com.pearl.fcw.proto.enums.EItemType;
import com.pearl.fcw.proto.enums.EPayType;
import com.pearl.fcw.proto.enums.EPlayerAccountType;
import com.pearl.fcw.proto.enums.EPlayerVipType;
import com.pearl.fcw.proto.enums.EQuestStatus;
import com.pearl.fcw.proto.enums.EQuestUiType;
import com.pearl.fcw.proto.enums.ERepeatType;
import com.pearl.fcw.proto.enums.EUnitType;
import com.pearl.fcw.utils.DataTablesPage;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.fcw.utils.DateUtil;
import com.pearl.fcw.utils.LobbyCacheKey;
import com.pearl.fcw.utils.MapUtil;
import com.pearl.fcw.utils.O2oUtil;
import com.pearl.fcw.utils.StringUtil;
import com.pearl.o2o.dao.impl.nonjoin.PlayerDao;
import com.pearl.o2o.dao.impl.nonjoin.PlayerInfoDao;
import com.pearl.o2o.dao.impl.nonjoin.PlayerItemDao;
import com.pearl.o2o.dao.impl.nonjoin.SysItemDao;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.exception.DataTooLongException;
import com.pearl.o2o.exception.DuplicatePlayerException;
import com.pearl.o2o.exception.EmptyInputException;
import com.pearl.o2o.exception.IllegalCharacterException;
import com.pearl.o2o.pojo.GeneralStageClearInfo;
import com.pearl.o2o.pojo.GmUpdateLog;
import com.pearl.o2o.pojo.GmUser;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerInfo;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.StageClearPlayerInfo;
import com.pearl.o2o.pojo.SysCharacter;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.socket.SocketClientNew;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.GrowthMissionConstants.GrowthMissionType;
import com.pearl.o2o.utils.KeywordFilterUtil;
import com.pearl.o2o.utils.MeltingConstants;
import com.pearl.o2o.utils.ServiceLocator;

/**
 * 玩家<br/>
 * findProxy***方法有数据校验，但为了避免校验时出现循环，互相之间不要调用<br/>
 * addProxy***方法没有数据校验，除了新建玩家账号，外部最好不引用这些方法，而用updateProxy***方法<br/>
 * updateProxy***方法依赖findProxy***方法，有数据校验，根据情况可能会自动调用addProxy***方法
 */
@Service
public class WPlayerService extends DmServiceImpl<WPlayer, Integer> {
	//    private Logger logger = LoggerFactory.getLogger(getClass());

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
    private WSysGrowthMissionDao wSysGrowthMissionDao;
    @Resource
    private WSysActivityDao wSysActivityDao;
    @Resource
    private WSysAchievementDao wSysAchievementDao;
    @Resource
    private WSysItemDao wSysItemDao;
    @Resource
    private WSysCharacterDao wSysCharacterDao;
    @Resource
    private WSysLevelDao wSysLevelDao;
    @Resource
    private WPaymentDao wPaymentDao;
    @Resource
	private WSysQuestDao wSysQuestDao;
	@Resource
    private WSysConfigDao wSysConfigDao;
    @Resource
    private WLogService wLogService;
    @Resource
    private SocketClientNew socketClientNew;
    @Resource
    private LobbyCacheKey lobbyCacheKey;
    @Resource
    private RedisClient redisClient;
    @Resource
    private EntityManager entityManager;
    @Resource
    private ProtoConverter protoConverter;

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
        player.setTutorial(newPlayer.getTutorial());
		player.setCharacterSize(newPlayer.getCharacterSize());
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
            list.addAll(ServiceLocator.getService.getPlayerItems(playerId, EItemType.WEAPON.getNumber(), sc.getId(), 0));
			list.addAll(ServiceLocator.getService.getPlayerItems(playerId, EItemType.COSTUME.getNumber(), sc.getId(), 0));
            list.addAll(ServiceLocator.getService.getPlayerItems(playerId, EItemType.PART.getNumber(), sc.getId(), 0));
        }
        list.addAll(ServiceLocator.getService.getPlayerItems(playerId, EItemType.MATERIAL.getNumber(), 0, 0));
        list.addAll(ServiceLocator.getService.getPlayerItems(playerId, EItemType.BLUEPRINT.getNumber(), 0, 0));
        list.addAll(ServiceLocator.getService.getPlayerItems(playerId, EItemType.PACKAGE.getNumber(), 0, 0));
        list.addAll(ServiceLocator.getService.getPlayerItems(playerId, EItemType.ITEM.getNumber(), 0, 0));
        list.addAll(ServiceLocator.getService.getPlayerItems(playerId, EItemType.OPEN.getNumber(), 0, 0));
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

    /**
	 * 根据playerId获取Player代理
	 * @param playerId 用户id
	 * @return
	 * @throws Exception
	 */
    public ProxyWPlayer findProxyWPlayer(int playerId) throws Exception {
        return entityManager.findProxy(ProxyWPlayer::new, WPlayer.class, playerId);
    }

    /**
	 * 根据playerId获取CharacterData代理<br/>
	 * 排除SysCharacter已不存在和不适用于玩家的数据<br/>
	 * 不记录在数据库但是储存在缓存的中间值已经重新计算
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
    public Map<Serializable, ProxyWCharacterData> findProxyWCharacterDataMap(int playerId) throws Exception {
        Set<Integer> sysItemIdSet = wSysItemDao.findMap(null).keySet();
        Set<Integer> sysCharacterIdSet = wSysCharacterDao.findMap(null).entrySet().stream().filter(kv -> kv.getValue().getIsEnable() == 1)
                .collect(Collectors.toMap(kv -> kv.getKey(), kv -> kv.getValue())).keySet();

        Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap = entityManager.findProxyMap(ProxyWPlayerItem::new, WPlayerItem::new, WPlayerItem.class, playerId).entrySet().stream().filter(kv -> {
            return sysItemIdSet.contains(kv.getValue().itemId().get());
        }).collect(Collectors.toMap(kv -> kv.getKey(), kv -> kv.getValue()));
		for (ProxyWPlayerItem pwPlayerItem : pwPlayerItemMap.values()) {
			WPlayerItemColumnDescriptor.refreshNumberParamMap(pwPlayerItem);
		}
        Set<ProxyWPlayerPack> pwPlayerPackSet = entityManager.findProxyMap(ProxyWPlayerPack::new, WPlayerPack::new, WPlayerPack.class, playerId).entrySet().stream().filter(kv -> {
            return pwPlayerItemMap.keySet().contains(kv.getValue().playerItemId().get()) && sysCharacterIdSet.contains(kv.getValue().sysCharacterId().get());
        }).map(kv -> kv.getValue()).collect(Collectors.toSet());

		//玩家角色的某些属性会因为更换装备而发生改变，所以查询时需要重新计算
        Map<Serializable, ProxyWCharacterData> map = entityManager.findProxyMap(ProxyWCharacterData::new, WCharacterData::new, WCharacterData.class, playerId).entrySet().stream().filter(kv -> {
            return sysCharacterIdSet.contains(kv.getValue().characterId().get());
        }).collect(Collectors.toMap(kv -> kv.getKey(), kv -> kv.getValue()));
        for (ProxyWCharacterData pwCharacterData : map.values()) {
			if (null == pwCharacterData.numberParamMap().get(ECharacterDataNumberParam.characterFightNum.toString())) {
                WCharacterDataColumnDescriptor.refreshNumberParamMap(pwCharacterData, pwPlayerPackSet, pwPlayerItemMap);
            }
        }
        return map;
    }

    /**
	 * 根据playerId获取PlayerActivity代理<br/>
	 * 排除SysActivity已不存在的数据
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
    public Map<Serializable, ProxyWPlayerActivity> findProxyWPlayerActivityMap(int playerId) throws Exception {
        Set<Integer> sysActivityIdSet = wSysActivityDao.findMap(null).keySet();
        return entityManager.findProxyMap(ProxyWPlayerActivity::new, WPlayerActivity::new, WPlayerActivity.class, playerId).entrySet().stream().filter(kv -> {
            return sysActivityIdSet.contains(kv.getValue().activityId().get());
        }).collect(Collectors.toMap(kv -> kv.getKey(), kv -> kv.getValue()));
    }

    /**
	 * 根据playerId获取PlayerAchievement代理 <br/>
	 * 排除SysAchievement中已不存在的group
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
    public Map<Serializable, ProxyWPlayerAchievement> findProxyWplayerAchievementMap(int playerId) throws Exception {
        Set<Integer> groupList = wSysAchievementDao.findList(null).stream().map(p -> p.getGroup()).distinct().collect(Collectors.toSet());
        return entityManager.findProxyMap(ProxyWPlayerAchievement::new, WPlayerAchievement::new, WPlayerAchievement.class, playerId).entrySet().stream().filter(kv -> {
            return groupList.contains(kv.getValue().group().get());
        }).collect(Collectors.toMap(kv -> kv.getKey(), kv -> kv.getValue()));
    }

	/**
	 * 根据playerId获取PlayerInfo代理。<br/>
	 * 如果没有PlayerInfo实例，根据Player自动创建一个(PlayerInfo和Player是一对一关系，而且PlayerInfo不可缺少，所以要在这里自动创建)
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
    public ProxyWPlayerInfo findProxyWPlayerInfo(int playerId) throws Exception {
        Map<Serializable, ProxyWPlayerInfo> map = entityManager.findProxyMap(ProxyWPlayerInfo::new, WPlayerInfo::new, WPlayerInfo.class, playerId);
        if (map.isEmpty()) {
            if (null == findProxyWPlayer(playerId)) {
                throw new Exception("playerId " + playerId + " is not exist.");
            }
            addProxyWPlayerInfo(playerId);
            map = entityManager.findProxyMap(ProxyWPlayerInfo::new, WPlayerInfo::new, WPlayerInfo.class, playerId);
        }
        return map.values().stream().findFirst().get();
    }

    /**
	 * 根据playerId获取PlayerMelting代理。<br/>
	 * 如果没有PlayerMelting实例，根据Player自动创建一个(PlayerMelting和Player是一对一关系，而且PlayerMelting不可缺少，所以要在这里自动创建)
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
    public ProxyWPlayerMelting findProxyWPlayerMelting(int playerId) throws Exception {
        Map<Serializable, ProxyWPlayerMelting> map = entityManager.findProxyMap(ProxyWPlayerMelting::new, WPlayerMelting::new, WPlayerMelting.class, playerId);
        if (map.isEmpty()) {
            if (null == findProxyWPlayer(playerId)) {
                throw new Exception("playerId " + playerId + " is not exist.");
            }
            addProxyWPlayerMelting(playerId);
            map = entityManager.findProxyMap(ProxyWPlayerMelting::new, WPlayerMelting::new, WPlayerMelting.class, playerId);
        }
        return map.values().stream().findFirst().get();
    }

    /**
	 * 根据playerId获取PlayerItem代理<br/>
	 * 排除SysItem中已不存在的数据
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
    public Map<Serializable, ProxyWPlayerItem> findProxyWPlayerItemMap(int playerId) throws Exception {
        Set<Integer> sysItemIdSet = wSysItemDao.findMap(null).keySet();
        Map<Serializable, ProxyWPlayerItem> map = entityManager.findProxyMap(ProxyWPlayerItem::new, WPlayerItem::new, WPlayerItem.class, playerId).entrySet().stream().filter(kv -> {
            return sysItemIdSet.contains(kv.getValue().itemId().get());
        }).collect(Collectors.toMap(kv -> kv.getKey(), kv -> kv.getValue()));
        for (ProxyWPlayerItem pwPlayerItem : map.values()) {
            if (null == pwPlayerItem.numberParamMap().get(EItemNumberParam.fightNum.toString())) {
                WPlayerItemColumnDescriptor.refreshNumberParamMap(pwPlayerItem);
            }
        }
        return map;
    }

    /**
	 * 根据playerId获取PlayerPack代理<br/>
	 * 排除SysCharacter和PlayerItem中已不存在的数据
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
    public Map<Serializable, ProxyWPlayerPack> findProxyWPlayerPackMap(int playerId) throws Exception {
        Set<Integer> sysCharacterIdSet = wSysCharacterDao.findMap(null).values().stream().filter(p -> "Y".equals(p.getIsDefault()) && p.getIsEnable() == 1).map(p -> p.getId())
                .collect(Collectors.toSet());
        Set<Integer> sysItemIdSet = wSysItemDao.findMap(null).keySet();
        Set<Integer> playerItemIdSet = entityManager.findProxyMap(ProxyWPlayerItem::new, WPlayerItem::new, WPlayerItem.class, playerId).entrySet().stream().filter(kv -> {
            return sysItemIdSet.contains(kv.getValue().itemId().get());
        }).map(kv -> kv.getValue().id().get()).collect(Collectors.toSet());
        Map<Serializable, ProxyWPlayerPack> map = entityManager.findProxyMap(ProxyWPlayerPack::new, WPlayerPack::new, WPlayerPack.class, playerId);
        return map.entrySet().stream().filter(kv -> {
            return playerItemIdSet.contains(kv.getValue().playerItemId().get()) && sysCharacterIdSet.contains(kv.getValue().sysCharacterId().get());
        }).collect(Collectors.toMap(kv -> kv.getKey(), kv -> kv.getValue()));
    }

    /**
	 * 根据playerId获取PlayerBuff代理<br/>
	 * 排除PlayerItem和SysItem中已不存在的数据
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
    public Map<Serializable, ProxyWPlayerBuff> findProxyWPlayerBuffMap(int playerId) throws Exception {
        Set<Integer> sysItemIdSet = wSysItemDao.findMap(null).keySet();
        Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap = entityManager.findProxyMap(ProxyWPlayerItem::new, WPlayerItem::new, WPlayerItem.class, playerId).entrySet().stream().filter(kv -> {
            return sysItemIdSet.contains(kv.getValue().itemId().get());
        }).collect(Collectors.toMap(kv -> kv.getKey(), kv -> kv.getValue()));
        return entityManager.findProxyMap(ProxyWPlayerBuff::new, WPlayerBuff::new, WPlayerBuff.class, playerId).entrySet().stream().filter(kv -> {
            return pwPlayerItemMap.containsKey(kv.getValue().playerItemId().get());
        }).collect(Collectors.toMap(kv -> kv.getKey(), kv -> kv.getValue()));
    }

	/**
	 * 根据playerId获取PlayerQuest代理<br/>
	 * 排除SysQuest中已不存在的数据<br/>
	 * 自动检查和重置周期任务的状态
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	public Map<Serializable, ProxyWPlayerQuest> findProxyWPlayerQuestMap(int playerId) throws Exception {
		Map<Integer, WSysQuest> wSysQuestMap = wSysQuestDao.findMap(null);
		Map<Serializable, ProxyWPlayerQuest> pwPlayerQuestMap = entityManager.findProxyMap(ProxyWPlayerQuest::new, WPlayerQuest::new, WPlayerQuest.class, playerId).entrySet().stream().filter(kv -> {
			return wSysQuestMap.keySet().contains(kv.getValue().sysQuestId().get());
		}).collect(Collectors.toMap(kv -> kv.getKey(), kv -> kv.getValue()));
		Date now = new Date();
		//无前置ID的系统任务，玩家任务里必须要有对应数据
		for (WSysQuest wSysQuest : wSysQuestMap.values()) {
			if (wSysQuest.getParentId() == 0 && pwPlayerQuestMap.values().stream().filter(p -> p.sysQuestId().eq(wSysQuest.getId())).count() == 0) {
				addProxyWPlayerQuest(playerId, wSysQuest.getId());
			}
		}
		//玩家已领取报酬的任务，如果有后续任务，保证玩家有对应数据
		for (ProxyWPlayerQuest pwPlayerQuest : pwPlayerQuestMap.values()) {
			if (pwPlayerQuest.status().eq(EQuestStatus.QUEST_AWARD.getNumber())) {
				List<WSysQuest> nextWSysQuestList = wSysQuestMap.values().stream().filter(p -> pwPlayerQuest.sysQuestId().eq(p.getParentId())).collect(Collectors.toList());
				for (WSysQuest wSysQuest : nextWSysQuestList) {
					if (pwPlayerQuestMap.values().stream().filter(p -> p.sysQuestId().eq(wSysQuest.getId())).count() == 0) {
						if (wSysQuest.getUiType() == EQuestUiType.AULD_ACHIEVEMENT.getNumber()) {//分组式老成就。同一组系统任务在玩家任务内使用同一条数据,状态重置但是计数保持
							pwPlayerQuest.sysQuestId().set(wSysQuest.getId());
							pwPlayerQuest.status().set(EQuestStatus.QUEST_AWAIT.getNumber());
						} else {
							addProxyWPlayerQuest(playerId, wSysQuest.getId());
						}
					}
				}
			}
		}
		//对过期的周期性任务计数和状态重置
		for (ProxyWPlayerQuest pwPlayerQuest : pwPlayerQuestMap.values()) {
			if (pwPlayerQuest.status().ne(EQuestStatus.QUEST_COMPLETE.getNumber()) && pwPlayerQuest.status().ne(EQuestStatus.QUEST_AWARD.getNumber())) {
				continue;//状态为完成和已领取奖励的需要重置（？已完成未领取奖励的任务是否需要重置？）
			}
			WSysQuest wSysQuest = wSysQuestMap.get(pwPlayerQuest.sysQuestId().get());
			boolean b = false;
			switch (ERepeatType.forNumber(wSysQuest.getRepeatType())) {
			case EVERY_DAY://每天
				b = !DateUtil.isSameDay(now, pwPlayerQuest.get().getUpdateTime());
				break;
			case EVERY_WEEK://每周
			case DAY_OF_WEEK://每周的某一天
				b = !DateUtil.isSameWeek(now, pwPlayerQuest.get().getUpdateTime());
				break;
			case EVERY_MONTH://每月
			case DAY_OF_MONTH://每月的某一天
				b = !DateUtil.isSameMonth(now, pwPlayerQuest.get().getUpdateTime());
				break;
			case AWAYS://无限重复
				if (pwPlayerQuest.status().ne(EQuestStatus.QUEST_AWARD.getNumber())) {//无限重复的任务要领取奖励后才刷新
					b = true;
				}
				break;
			default:
				break;
			}
			if (b) {
				pwPlayerQuest.number().set(0L);
				pwPlayerQuest.status().set(EQuestStatus.QUEST_AWAIT.getNumber());
			}
		}
		//重新获取一次，保证信息最新最全
		pwPlayerQuestMap = entityManager.findProxyMap(ProxyWPlayerQuest::new, WPlayerQuest::new, WPlayerQuest.class, playerId).entrySet().stream().filter(kv -> {
			return wSysQuestMap.keySet().contains(kv.getValue().sysQuestId().get());
		}).collect(Collectors.toMap(kv -> kv.getKey(), kv -> kv.getValue()));
		return pwPlayerQuestMap;
	}

    /**
	 * 根据玩家ID查找购买记录
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
    public Map<Serializable, ProxyWBuyitemRecord> findProxyWBuyitemRecordMap(int playerId) throws Exception {
        return entityManager.findProxyMap(ProxyWBuyitemRecord::new, WBuyitemRecord::new, WBuyitemRecord.class, playerId);
    }

    /**
	 * 根据用户名或者昵称获取Player代理
	 * @param name 昵称
	 * @param userName 用户名
	 * @return
	 * @throws Exception
	 */
    public ProxyWPlayer findProxyWPlayerByName(String name, String userName) throws Exception {
		//从数据库根据用户名和昵称找到玩家
        WPlayer wPlayer = new WPlayer();
        wPlayer.setName(name);
        wPlayer.setUserName(userName);
        wPlayer = findList(wPlayer).stream().findFirst().orElse(null);
        if (null == wPlayer) {
            return null;
        }
		//根据玩家ID获取玩家的代理实例
        ProxyWPlayer pwPlayer = entityManager.findProxy(ProxyWPlayer::new, WPlayer.class, wPlayer.getId());
        return pwPlayer;
    }

    /**
	 * 根据用户名、昵称、账号类型创建Player代理
	 * @param name 昵称
	 * @param userName 用户名
	 * @param accountType @EPlayerAccountType 账号类型
	 * @return
	 * @throws Exception
	 */
    public ProxyWPlayer addProxyWPlayer(String name, String userName, int accountType) throws Exception {
        WPlayer wPlayer = new WPlayer();
        wPlayer.setName(userName);
        wPlayer.setUserName(userName);
        wPlayer.setAccountType((byte) accountType);

		//FIXME 以下验证玩家的name和userName，其中KeywordFilterUtil使用memcached，应取缔
        if (StringUtil.isEmpty(name) || StringUtil.isEmpty(userName)) {
            throw new EmptyInputException(ExceptionMessage.EMPTY_STR_CREATE_PLAYER);
        }
        if (name.length() > Integer.parseInt(wSysConfigDao.findEntity("playername.maxlength").getValue())) {
            throw new DataTooLongException(ExceptionMessage.TOO_LONG_CREATE_PLAYER);
        } else if (name.length() < Integer.parseInt(wSysConfigDao.findEntity("playername.minlength").getValue())) {
            throw new BaseException(ExceptionMessage.TOO_SHORT_CREATE_PLAYER);
        }
        if (com.pearl.o2o.utils.StringUtil.filter(name)) {
            throw new IllegalCharacterException(ExceptionMessage.ILLEGAL_CHARACTER_CREATE_PLAYER);
        }
        if (!KeywordFilterUtil.isLegalInput(name)) {
            throw new IllegalCharacterException(ExceptionMessage.ILLEGAL_CHARACTER_CREATE_PLAYER);
        }
        if (!wPlayerDao.findList(wPlayer).isEmpty()) {
            throw new DuplicatePlayerException();
        }

		//填初始值
        Date now = new Date();
        wPlayer.setCredit(com.pearl.o2o.utils.Constants.DEFAULT_PLAYER_CR);
        wPlayer.setVoucher(com.pearl.o2o.utils.Constants.DEFAULT_PLAYER_V);
        wPlayer.setTutorial(com.pearl.o2o.utils.Constants.TUTORIAL_DEFAULT);
        wPlayer.setLastLoginIp("127.0.0.1");
        wPlayer.setLastLoginTime(now);
        wPlayer.setLastLogoutTime(now);
        wPlayer.setCreateTime(now);
        wPlayer.setIsNew(0);
        wPlayer.setIsVip(accountType == EPlayerAccountType.XUNLEI.getNumber() ? EPlayerVipType.XUNLEI_VIP.getNumber() : 0);
        wPlayer.setIcon(com.pearl.o2o.utils.Constants.DEFAULT_PLAYER_ICON);
        wPlayer.setTitle("");
        wPlayer.setAchievement("");
        wPlayer.setTotal(wSysAchievementDao.findList(null).size());
		//FIXME 下面的三个字段在新缓存体系下无用
        //        wPlayer.setCharacters(wSysCharacterDao.findList(null).stream().filter(p -> p.getIsEnable() == 1).map(p -> p.getId().toString()).collect(Collectors.joining(",")));
        //        wPlayer.setCharacterSize((byte) com.pearl.o2o.utils.Constants.DEFAULT_CHARACTER_SIZE);
        //        wPlayer.setSelectedCharacters(wSysCharacterDao.findList(null).stream().filter(p -> p.getIsEnable() == 1 && "Y".equals(p.getIsDefault())).map(p -> p.getId().toString())
        //                .collect(Collectors.joining(",")));
        wPlayer.setIsXunlei((byte) (accountType == EPlayerAccountType.XUNLEI.getNumber() ? 1 : 0));
        wPlayer.setUnusableResource(com.pearl.o2o.utils.Constants.DEFAULT_PLAYER_UNUSABLE_RESOURCE);
        wPlayer.setUsableResource(com.pearl.o2o.utils.Constants.DEFAULT_PLAYER_USABLE_RESOURCE);
        wPlayer.setRank(com.pearl.o2o.utils.Constants.DEFAULT_PLAYER_RANK);
        wPlayer.setExp(com.pearl.o2o.utils.Constants.DEFAULT_PLAYER_EXP);
        wPlayer.setgPoint(com.pearl.o2o.utils.Constants.DEFAULT_PLAYER_GP);
        wPlayer.setTutorial(ConfigurationUtil.DEFAULT_PLAYER_MODULE);
        wPlayer.setIsCheck(true);
		wPlayer.setMaxFightNum(com.pearl.o2o.utils.Constants.DEFAULT_PLAYER_FIGHTNUM);//设置玩家默认战斗力
        wPlayer.setAccountType((byte) accountType);
        wPlayer.setpResourceBeginTime(now);

        return entityManager.add(ProxyWPlayer::new, wPlayer);
    }

    /**
	 * 根据玩家ID创建PlayerTrack代理
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
    public ProxyWPlayerTrack addProxyWPlayerTrack(int playerId) throws Exception {
        WPlayerTrack m = new WPlayerTrack();
        m.setPlayerId(playerId);
        m.setGiftTime("");
        m.setMedalCount(0);
        m.setGoldCount(0);
        m.setFirstCost(0.0);
        m.setFirstCostTime("");
        m.setNewRenewItem("");
        return entityManager.add(ProxyWPlayerTrack::new, WPlayerTrack::new, m);
    }

    /**
	 * 根据玩家ID创建PlayerMelting代理
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
    public ProxyWPlayerMelting addProxyWPlayerMelting(int playerId) throws Exception {
        WPlayerMelting m = new WPlayerMelting();
        m.setId(playerId);
		m.setLevel(WSysConfigService.getPlayerItemCombineMelting().getLevelAndExp().keySet().stream().min((p1, p2) -> p1 - p2).orElse(1));
        m.setExp(0);
        m.setRecovery(0);
        m.setNum(WSysConfigService.getPlayerItemCombineMelting().getDefalutMeltingEnergyNum());
        m.setStartTime(0L);
        m.setGrandTotalTime(0L);
        return entityManager.add(ProxyWPlayerMelting::new, WPlayerMelting::new, m);
    }

    /**
	 * 根据玩家ID和系统任务ID创建PlayerGrowthMission代理
	 * @param playerId
	 * @param sysGrowthMissionId
	 * @return
	 * @throws Exception
	 */
    public ProxyWPlayerGrowthMission addProxyWPlayerGrowthMission(int playerId, int sysGrowthMissionId) throws Exception {
        WPlayerGrowthMission m = new WPlayerGrowthMission();
        m.setId(sysGrowthMissionId);
        m.setPlayerId(playerId);
        return entityManager.add(ProxyWPlayerGrowthMission::new, m);
    }

    /**
	 * 根据玩家ID和系统活动ID创建PlayerActivity代理
	 * @param playerId
	 * @param sysActivityId
	 * @return
	 * @throws Exception
	 */
    public ProxyWPlayerActivity addProxyWPlayerActivity(int playerId, int sysActivityId) throws Exception {
        WPlayerActivity m = new WPlayerActivity();
        m.setPlayerId(playerId);
        m.setActivityId(sysActivityId);
        WSysActivity wSysActivity = wSysActivityDao.findEntity(sysActivityId);
        m.setName(wSysActivity.getName());
		if (wSysActivity.getAction() == EActivityActionType.ACHIEVEMENT_ACTIVITY.getNumber()) {//系统活动的类型为成就类型，玩家活动的名称有变化
            String str = CommonUtil.messageFormatI18N2("" + 357, "<C", 2, DateUtil.formatToFlag(wSysActivity.getStartTime(), "yyyy-MM-dd HH"),
                    DateUtil.formatToFlag(wSysActivity.getEndTime(), "yyyy-MM-dd HH"))
                    + "<AD" + wSysActivity.getName() + "^0>";
            m.setName(str);
        }
        m.setAction(wSysActivity.getAction());
        m.setStatus(EActivityStatus.ACTIVITY_AWAIT.getNumber());
        m.setNumber(0);
        m.setTarget(wSysActivity.getValue());
		switch (wSysActivity.getAction()) {//部分活动类型，玩家活动的目标数取值不同
        case EActivityActionType.ACHIEVEMENT_ACTIVITY_VALUE:
        case EActivityActionType.KILL_ACTIVITY_VALUE:
        case EActivityActionType.BOSS_KILL_ACTIVITY_VALUE:
        case EActivityActionType.CHARGE_FC_VALUE:
        case EActivityActionType.KILL_BIOCHEMICAL_VALUE:
        case EActivityActionType.PAY_FC_VALUE:
        case EActivityActionType.KILL_BOSS2_ACTIVITY_VALUE:
            m.setTarget(wSysActivity.getTargetNum());
        }
        m.setAward(EActivityAwardStatus.ACTIVITY_AWARD_AWAIT.getNumber());
        m.setStartTime(wSysActivity.getStartTime());
        m.setEndTime(wSysActivity.getEndTime());
        m.setCreateTime(new Date());
        m.setCharacterId(wSysActivity.getCharacterId());
        m.setAchievementAction(wSysActivity.getAchievementAction());

        return entityManager.add(ProxyWPlayerActivity::new, WPlayerActivity::new, m);
    }

    /**
	 * 根据玩家ID，系统角色ID，系统成就组创建PlayerAchievement代理
	 * @param playerId
	 * @param sysCharacterId 系统角色ID，0代表所有系统角色通用。作为分表的一级后缀
	 * @param sysAchievementGroup @WSysAchievement.group
	 * @return
	 * @throws Exception
	 */
    public ProxyWPlayerAchievement addProxyWPlayerAchievement(int playerId, int sysCharacterId, int sysAchievementGroup) throws Exception {
        List<WSysAchievement> wSysAchievementList = wSysAchievementDao.findList(null).stream().filter(p -> p.getGroup() == sysAchievementGroup).collect(Collectors.toList());
		WPlayerAchievement m = (WPlayerAchievement) Class.forName(WPlayerAchievement.class.getName() + sysCharacterId).newInstance();//实体的实际对象表明分表后缀
        m.setLevel(1);
        m.setNumber(0);
        m.setPlayerId(playerId);
        m.setStatus(EAchievementStatus.ACHIEVEMENT_AWAIT.getNumber());
        m.getSysAchievementIdsList().addAll(wSysAchievementList.stream().map(p -> new ParamObject<Integer>(p.getId())).collect(Collectors.toList()));
        m.setGroup(sysAchievementGroup);
        m.setTotalLevel(m.getSysAchievementIdsList().size());
        return entityManager.add(ProxyWPlayerAchievement::new, WPlayerAchievement::new, m);
    }

    /**
	 * 根据玩家ID，系统物品ID等参数创建新的玩家物品
	 * @param playerId @WPlayer.id
	 * @param sysItemId @WSysItem.id
	 * @param unit 物品数量或者有效时间(天)
	 * @param unitType @EUnitType
	 * @param isGift 是否赠送礼物
	 * @param isBind 是否绑定玩家物品在特定玩家上
	 * @param isDefault 是否默认物品
	 * @return
	 * @throws Exception
	 */
    public ProxyWPlayerItem addProxyWPlayerItem(int playerId, int sysItemId, int unit, int unitType, boolean isGift, boolean isBind, boolean isDefault) throws Exception {
        Date now = new Date();
        WSysItem wSysItem = wSysItemDao.findEntity(sysItemId);
        WPlayerItem m = new WPlayerItem();
        m.setPlayerId(playerId);
        m.setItemId(sysItemId);
        m.setModifiedDesc(wSysItem.getModifiedDesc());
        m.setCurrency((byte) 1);
        m.setIsBind(isBind ? "Y" : "N");
        m.setIsDeleted("N");
        m.setIsGift(isGift ? "Y" : "N");
        m.setIsDefault(isDefault ? "Y" : "N");
        m.setGunProperty1(wSysItem.getGunProperty1());
        m.setUnitType((byte) unitType);
        m.setValidTime(now);
        m.setQuantity(1);
        switch (EUnitType.forNumber(unitType)) {
		case TIME://创建的物品消耗类型为基于时间
            m.setExpireTime(DateUtil.add(now, Calendar.DAY_OF_YEAR, unit));
            m.setLeftSeconds(24 * 60 * unit);
            break;
		case QUANTITY://创建的物品消耗类型为基于数量
            m.setQuantity(unit);
        default:
            m.setExpireTime(now);
        }
        ProxyWPlayerItem pwPlayerItem = entityManager.add(ProxyWPlayerItem::new, WPlayerItem::new, m);
		//物品强化和打孔数量
		if (wSysItem.getIsStrengthen() > 0) {//系统物品可强化，玩家物品按照最大可开孔数量开孔
            pwPlayerItem.level().set((int) wSysItem.getStrengthLevel());
            int holeCount = pwPlayerItem.numberParamMap().get(EItemNumberParam.holeCount.toString()).value().get().intValue();
            for (int i = 0; i < holeCount; i++) {
                openHoleInPlayerItem(pwPlayerItem);
            }
		} else {//系统物品不可强化，玩家物品开孔开槽状态复制系统物品的开孔开槽状态
			if (WSysConfigService.getSysItemIdLimitTime().contains(pwPlayerItem.id().get())) {//限时物品的玩家物品等级需要重设
                pwPlayerItem.level().set((int) wSysItem.getStrengthLevel());
            }
            pwPlayerItem.gunProperty2().set(wSysItem.getGunProperty2());
            pwPlayerItem.gunProperty3().set(wSysItem.getGunProperty3());
            pwPlayerItem.gunProperty4().set(wSysItem.getGunProperty4());
            pwPlayerItem.gunProperty5().set(wSysItem.getGunProperty5());
            pwPlayerItem.gunProperty6().set(wSysItem.getGunProperty6());
            pwPlayerItem.gunProperty7().set(wSysItem.getGunProperty7());
        }

        return pwPlayerItem;
    }

    /**
	 * 根据玩家ID创建PlayerInfo代理
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
    public ProxyWPlayerInfo addProxyWPlayerInfo(int playerId) throws Exception {
        WPlayerInfo m = new WPlayerInfo();
        m.setShareId(playerId);
		JsonPlayerInfoCaches jsonCaches = m.getCachesEntity();
		for (EItemRareType p : EItemRareType.values()) {
			if (p != EItemRareType.UNRECOGNIZED) {
				jsonCaches.getRareTypeAndBuyGstExpTime().put(p.getNumber(), 0L);
			}
		}
        return entityManager.add(ProxyWPlayerInfo::new, WPlayerInfo::new, m);
    }

    /**
	 * 根据玩家ID、玩家道具、系统角色、背包ID创建PlayerPack代理
	 * @param playerId
	 * @param pwPlayerItem
	 * @param wSysCharacter
	 * @param packId
	 * @return
	 * @throws Exception
	 */
    public ProxyWPlayerPack addProxyWPlayerPack(int playerId, ProxyWPlayerItem pwPlayerItem, WSysCharacter wSysCharacter, int packId) throws Exception {
        WPlayerPack m = new WPlayerPack();
        m.setPlayerId(playerId);
        m.setPlayerItemId(pwPlayerItem.id().get());
        m.setSysCharacterId(wSysCharacter.getId());
        WSysItem wSysItem = wSysItemDao.findEntity(pwPlayerItem.itemId().get());
        int seq = wSysItem.getNumberParamMap().get(EItemNumberParam.seq.toString()).intValue();
        m.setSeq((short) seq);
        m.setPackId((short) 1);
        switch (EItemType.forNumber(wSysItem.getType())) {
        case WEAPON:
            m.setType("W");
            break;
		case COSTUME:
        case PART:
            m.setType("C");
            break;
        default:
            break;
        }
        return entityManager.add(ProxyWPlayerPack::new, WPlayerPack::new, m);
    }

    /**
	 * 根据玩家ID、玩家物品ID和系统BuffId创建PlayerBuff代理
	 * @param playerId
	 * @param playerItemId
	 * @param ibuffId
	 * @return
	 * @throws Exception
	 */
    public ProxyWPlayerBuff addProxyWPlayerBuff(int playerId, int playerItemId, EItemIBuffId ibuffId) throws Exception {
        WPlayerBuff m = new WPlayerBuff();
        m.setBuffId((short) ibuffId.getNumber());
        m.setPlayerId(playerId);
        m.setPlayerItemId(playerItemId);
        return entityManager.add(ProxyWPlayerBuff::new, WPlayerBuff::new, m);
    }

	/**
	 * 根据玩家ID、系统任务sysQuestId创建PlayerQuest代理
	 * @param playerId
	 * @param sysQuestId
	 * @return
	 * @throws Exception
	 */
	public ProxyWPlayerQuest addProxyWPlayerQuest(int playerId, int sysQuestId) throws Exception {
		WPlayerQuest m = new WPlayerQuest();
		m.setPlayerId(playerId);
		m.setSysQuestId(sysQuestId);
		m.setStatus(EQuestStatus.QUEST_AWAIT.getNumber());
		m.setNumber(0L);
		return entityManager.add(ProxyWPlayerQuest::new, WPlayerQuest::new, m);
	}

    /**
	 * 根据玩家ID，系统道具，系统交易创建交易记录
	 * @param playerId
	 * @param wSysItem
	 * @param wPayment
	 * @return
	 * @throws Exception
	 */
    public ProxyWBuyitemRecord addProxyWBuyitemRecord(int playerId, WSysItem wSysItem, WPayment wPayment) throws Exception {
        WBuyitemRecord m = new WBuyitemRecord();
        m.setPlayerId(playerId);
        m.setItemId(wSysItem.getId());
        m.setCostId(wPayment.getId());
        m.setPayType(wPayment.getPayType());
        Date now = new Date();
        m.setLastBuyDate(now);
        m.setCreateTime(now);
        return entityManager.add(ProxyWBuyitemRecord::new, m);
    }

    public void remove(EntityProxy<?> p) throws Exception {
        entityManager.remove(p);
    }

    /**
	 * 登录时检查玩家的熔炼数据<br/>
	 * @param pwPlayerMelting
	 * @throws Exception
	 */
    public void checkPlayerMeltingAtLogin(ProxyWPlayerMelting pwPlayerMelting) throws Exception {
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        cal.setTimeInMillis(pwPlayerMelting.lastInit().get());
		if (!DateUtil.isSameDay(now, cal.getTime())) {//玩家熔炼上一次初始化不是今天，熔炼数据清零
            pwPlayerMelting.num().set(MeltingConstants.DefalutMeltingEnergyNum);
            pwPlayerMelting.recovery().set(0);
            pwPlayerMelting.startTime().set(0L);
            pwPlayerMelting.grandTotalTime().set(0L);
            pwPlayerMelting.lastInit().set(now.getTime());
            if (pwPlayerMelting.num().lt(MeltingConstants.MeltingEnergyNum)) {
				pwPlayerMelting.recovery().increase(1);
                pwPlayerMelting.startTime().set(now.getTime());
            }
		} else if (pwPlayerMelting.num().lt(MeltingConstants.MeltingEnergyNum) && pwPlayerMelting.recovery().gt(0)) {//熔炼上次初始化是今天，并且格子数小于系统值，恢复格子数大于0
			pwPlayerMelting.num().increase(1);
            long resumeInterval = now.getTime() - pwPlayerMelting.startTime().get() + pwPlayerMelting.grandTotalTime().get();
            long recoverInterval = 60 * 1000 * (pwPlayerMelting.recovery().eq(0) ? 0 : pwPlayerMelting.recovery().gt(2) ? MeltingConstants.RecoveryTime[3]
                    : MeltingConstants.RecoveryTime[pwPlayerMelting.recovery().get()]);
			while (resumeInterval >= recoverInterval && pwPlayerMelting.num().lt(MeltingConstants.MeltingEnergyNum)) {//再生时间不小于恢复时间，并且熔炼的格子数小于系统默认
				pwPlayerMelting.num().increase(1);
				pwPlayerMelting.recovery().increase(1);
                pwPlayerMelting.startTime().set(pwPlayerMelting.startTime().get() - pwPlayerMelting.grandTotalTime().get() + recoverInterval);
                pwPlayerMelting.grandTotalTime().set(0L);
            }
        }
    }

    /**
	 * 登录检查玩家活动(不新增缺少的玩家活动，减少线程处理时间)
	 * @param pwPlayer
	 * @throws Exception
	 */
    public void checkPlayerActivityAtLogin(final ProxyWPlayer pwPlayer) throws Exception {
		//是否当天首次登录
        boolean isFirstLoginToday = DateUtil.isSameDay(pwPlayer.lastLoginTime().get(), new Date());
		//系统活动未逻辑删除，正在激活中，并且活动类型不是TOP_PLAYER_ACTIVITY
        List<WSysActivity> wSysActivityList = wSysActivityDao.findList(null).stream().filter(p -> {
            return "Y".equals(p.getTheSwitch()) && p.getAction() != EActivityActionType.TOP_PLAYER_ACTIVITY.getNumber();
        }).collect(Collectors.toList());
        Map<Serializable, ProxyWPlayerActivity> pwPlayerActivityMap = findProxyWPlayerActivityMap(pwPlayer.id().get());
        for (WSysActivity wSysActivity : wSysActivityList) {
            ProxyWPlayerActivity pwPlayerActivity = pwPlayerActivityMap.values().stream().filter(p -> p.activityId().eq(wSysActivity.getId())).findFirst().orElse(null);
			if (isFirstLoginToday) {//当天首次登录，某些类型的活动需要重置状态等字段
                switch (EActivityActionType.forNumber(pwPlayerActivity.action().get())) {
				//以下类型的任务重置状态和奖励状态
                case HOUR2HOUR_ACTIVITY:
                case LEVEL_FIRST_LOGIN:
                    pwPlayerActivity.status().set(EActivityStatus.ACTIVITY_AWAIT.getNumber());
                    pwPlayerActivity.award().set(EActivityAwardStatus.ACTIVITY_AWARD_AWAIT.getNumber());
                    break;
				//以下类型的任务重置状态和奖励状态、计数
                case LOGIN_ACTIVITY:
                case KILL_ACTIVITY:
                case CHARGE_FC:
                case TEAM_COMBAT_FINISH:
                    pwPlayerActivity.status().set(EActivityStatus.ACTIVITY_AWAIT.getNumber());
                    pwPlayerActivity.award().set(EActivityAwardStatus.ACTIVITY_AWARD_AWAIT.getNumber());
                    pwPlayerActivity.number().set(0);
                    break;
                default:
                    break;
                }
            }
        }
    }

    /**
	 * 登录检查玩家任务<br/>
	 * @param pwPlayer
	 * @throws Exception
	 */
    public void checkPlayerGrowthMissionAtLogin(final ProxyWPlayer pwPlayer) throws Exception {
		List<WSysGrowthMission> sysGrowthMissionList = wSysGrowthMissionDao.findList(null);//系统任务
        Map<Serializable, ProxyWPlayerGrowthMission> playerGrowthMissionMap = entityManager.findProxyMap(ProxyWPlayerGrowthMission::new, WPlayerGrowthMission::new, WPlayerGrowthMission.class,
				pwPlayer.id().get());//玩家任务
		boolean isUpdateModleMission = false;//player.tutorial中的系统任务内容是否变更

        for (WSysGrowthMission wSysGrowthMission : sysGrowthMissionList) {
			//系统任务可开放，开放等级小于等于玩家等级，并且玩家任务中没有该系统任务。新增对应的玩家任务
            if (wSysGrowthMission.getStatus() && pwPlayer.rank().ge(wSysGrowthMission.getRank()) && !playerGrowthMissionMap.containsKey(wSysGrowthMission.getId())) {
                ProxyWPlayerGrowthMission pwPlayerGrowthMission = addProxyWPlayerGrowthMission(pwPlayer.id().get(), wSysGrowthMission.getId());
                playerGrowthMissionMap.put(pwPlayerGrowthMission.id().get(), pwPlayerGrowthMission);
                ProxyWPlayerGrowthMission pwPlayerParentGrowthMisson = playerGrowthMissionMap.getOrDefault(wSysGrowthMission.getParent(), null);
				//player.tutorial中的系统任务内容未变更的前提下，该系统任务没有前置任务，或者玩家任务的前置任务已收到奖励附件，player.tutorial中的系统任务内容必须变更
                if (!isUpdateModleMission && (wSysGrowthMission.getParent() <= 0 || (null != pwPlayerParentGrowthMisson && pwPlayerParentGrowthMisson.received().get()))) {
                    isUpdateModleMission = true;
                }
            }
			//新手等级奖励任务
            else if (wSysGrowthMission.getId() >= GrowthMissionType.NEW_AWARD_1.getGrowthMissionId() && wSysGrowthMission.getId() <= GrowthMissionType.NEW_AWARD_8.getGrowthMissionId()) {
                ProxyWPlayerGrowthMission pwPlayerGrowthMission = playerGrowthMissionMap.getOrDefault(wSysGrowthMission.getId(), null);
                if (null == pwPlayerGrowthMission) {
                    continue;
                }
				if (pwPlayer.rank().lt(pwPlayerGrowthMission.number().get())) {//玩家等级上升导致玩家任务的计数变化
                    pwPlayerGrowthMission.number().set(pwPlayer.rank().get());
                }
				if (pwPlayerGrowthMission.number().le(wSysGrowthMission.getNumber())) {//玩家任务的计数大于等于系统任务的要求计数后，修改玩家任务的状态
                    pwPlayerGrowthMission.status().set(true);
                }
            }
        }

		//玩家任务“完成3局游戏”对player.tutorial有影响
        ProxyWPlayerGrowthMission pwPlayerGrowthMission = playerGrowthMissionMap.getOrDefault(EGrowthMissionType.COMPLETE_LIMIT_3.getNumber(), null);
        if (null != pwPlayerGrowthMission && pwPlayerGrowthMission.status().get()) {
            pwPlayer.tutorialMap().get(EClientModuleType.MODULE_SHOT_VALUE + "").set(new ParamObject<Integer>(EClientModuleStatus.MODULE_FLASH.getNumber()));
        }
		//收到奖励的任务对player.tutorial有影响
        if (isUpdateModleMission) {
            pwPlayer.tutorialMap().get(EClientModuleType.MODULE_MISSION_VALUE + "").set(new ParamObject<Integer>(EClientModuleStatus.MODULE_FLASH.getNumber()));
        }
    }

    /**
	 * 修改玩家任务前检查玩家任务数据的完整性。如果没有对应的玩家任务，会新增数据
	 * @param pwPlayer
	 * @param activityActionType
	 * @param wSysActivityList 判断玩家任务是否完整的系统任务列表
	 * @return 返回完整的玩家任务和系统任务
	 * @throws Exception
	 */
    private Map<Serializable, ProxyWPlayerActivity> checkPlayerActivityBeforeUpdate(final ProxyWPlayer pwPlayer, EActivityActionType activityActionType, List<WSysActivity> wSysActivityList)
            throws Exception {
		//获取指定类型的玩家活动
        Map<Serializable, ProxyWPlayerActivity> pwPlayerActivityMap = findProxyWPlayerActivityMap(pwPlayer.id().get()).entrySet().stream().filter(p -> {
            return p.getValue().action().get() == activityActionType.getNumber();
        }).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		//玩家活动无系统活动对应的数据，新增玩家活动
        boolean isRefresh = false;
        for (WSysActivity wSysActivity : wSysActivityList) {
			if (pwPlayerActivityMap.values().stream().map(p -> p.activityId().get() == wSysActivity.getId()).count() == 0) {//玩家活动无系统活动，新增数据
                addProxyWPlayerActivity(pwPlayer.id().get(), wSysActivity.getId());
                isRefresh = true;
            }
        }
        if (isRefresh) {
            pwPlayerActivityMap = findProxyWPlayerActivityMap(pwPlayer.id().get()).entrySet().stream().filter(p -> {
                return p.getValue().action().get() == activityActionType.getNumber();
            }).collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        }
        return pwPlayerActivityMap;
    }

    /**
	 * 修改玩家成就前检查玩家成就数据完整性，可能会新增或者修改玩家成就数据
	 * @param pwPlayer
	 * @param action
	 * @param wSysAchievementList 判断玩家成就完整性的系统成就数据
	 * @return 返回的玩家成就已提交事务，但是没有向玩家推送消息
	 * @throws Exception
	 */
    private Map<Serializable, ProxyWPlayerAchievement> checkPlayerAchievementBeforeUpdate(final ProxyWPlayer pwPlayer, EAchievementAction action, List<WSysAchievement> wSysAchievementList)
            throws Exception {
		List<Integer> groupList = wSysAchievementList.stream().map(p -> p.getGroup()).distinct().collect(Collectors.toList());//系统成就组
		//获取指定类型的玩家成就
        Map<Serializable, ProxyWPlayerAchievement> pwPlayerAchievementMap = findProxyWplayerAchievementMap(pwPlayer.id().get()).entrySet().stream()
                .filter(kv -> groupList.contains(kv.getValue().group().get())).collect(Collectors.toMap(kv -> kv.getKey(), kv -> kv.getValue()));
		//补充玩家成就中缺少的系统成就数据
        boolean isRefresh = false;
		for (int group : groupList) {//玩家成就无对应成就组，新增数据
            if (pwPlayerAchievementMap.values().stream().filter(p -> p.group().get() == group).count() == 0) {
                isRefresh = true;
                for (WSysAchievement wSysAchievement : wSysAchievementList.stream().filter(p -> p.getGroup() == group).collect(Collectors.toList())) {
                    addProxyWPlayerAchievement(pwPlayer.id().get(), wSysAchievement.getId(), wSysAchievement.getGroup());
                }
			} else {//玩家成就有对应成就组，无对应的系统成就，修改数据
                ProxyWPlayerAchievement pwPlayerAchievement = pwPlayerAchievementMap.values().stream().filter(p -> p.group().get() == group).findFirst().get();
                Set<Integer> sysAchievementIdsInP = pwPlayerAchievement.sysAchievementIdsList().get().stream().map(p -> p.getValue()).collect(Collectors.toSet());
                Set<Integer> sysAchievementIds = wSysAchievementList.stream().filter(p -> p.getGroup() == group).map(p -> p.getId()).collect(Collectors.toSet());
                for (int sysAchievementId : sysAchievementIds) {
                    if (!sysAchievementIdsInP.contains(sysAchievementId)) {
                        isRefresh = true;
                        pwPlayerAchievement.sysAchievementIdsList().add(new ParamObject<Integer>(sysAchievementId));
						pwPlayerAchievement.totalLevel().increase(1);
                    }
                }
            }
        }
        if (isRefresh) {
            pwPlayerAchievementMap = findProxyWplayerAchievementMap(pwPlayer.id().get()).entrySet().stream().filter(kv -> groupList.contains(kv.getValue().group().get()))
                    .collect(Collectors.toMap(kv -> kv.getKey(), kv -> kv.getValue()));
        }
        return pwPlayerAchievementMap;
    }

    /**
	 * 玩家成长任务完成数加一。如果没有该玩家任务，根据该玩家任务对应的系统任务检查是否有前置任务、前置任务是否已完成来确定是否新增玩家任务<br/>
	 * @param pwPlayer
	 * @param growthMissionType
	 * @return false：该玩家任务由于前置任务的缘故不能新增或者修改
	 * @throws Exception
	 */
    public boolean updateProxyWPlayerGrowthMission(final ProxyWPlayer pwPlayer, EGrowthMissionType growthMissionType) throws Exception {
        Map<Serializable, ProxyWPlayerGrowthMission> pwPlayerGrowthMissionMap = entityManager.findProxyMap(ProxyWPlayerGrowthMission::new, WPlayerGrowthMission::new, WPlayerGrowthMission.class,
                pwPlayer.id().get());
        ProxyWPlayerGrowthMission pwPlayerGrowthMission = pwPlayerGrowthMissionMap.get(growthMissionType.getNumber());
        WSysGrowthMission wSysGrowthMission = wSysGrowthMissionDao.findEntity(growthMissionType.getNumber());
		//没有对应的玩家任务，可能需要新建数据
        if (null == pwPlayerGrowthMission) {
			if (wSysGrowthMissionDao.findMap(null).keySet().contains(wSysGrowthMission.getParent())) {//该系统任务有前置任务
                ProxyWPlayerGrowthMission parentPwPlayerGrowthMission = pwPlayerGrowthMissionMap.get(wSysGrowthMission.getParent());
				if (null == parentPwPlayerGrowthMission || parentPwPlayerGrowthMission.status().eq(false)) {//该前置系统任务没有对应的玩家任务，或者前置任务对应的玩家任务没有完成，结束该玩家任务的修改逻辑
                    return false;
                }
            }
			//没有前置任务或者前置任务已完成，新建一个对应的玩家任务
            pwPlayerGrowthMission = addProxyWPlayerGrowthMission(pwPlayer.id().get(), growthMissionType.getNumber());
        }
		//修改玩家任务
		if (wSysGrowthMissionDao.findMap(null).keySet().contains(wSysGrowthMission.getParent())) {//该玩家任务有前置任务，而且未完成
            ProxyWPlayerGrowthMission parentPwPlayerGrowthMission = pwPlayerGrowthMissionMap.get(wSysGrowthMission.getParent());
            if (parentPwPlayerGrowthMission.status().eq(false)) {
                return false;
            }
        }
		if (pwPlayerGrowthMission.status().eq(false)) {//玩家任务未完成的状态下才修改数据
			pwPlayerGrowthMission.number().increase(1);
			if (pwPlayerGrowthMission.number().ge(wSysGrowthMission.getNumber())) {//玩家任务完成数达到要求
                pwPlayerGrowthMission.status().set(true);
            }
        }
		//影响Player的tutorial字段
        pwPlayer.tutorialMap().get(EClientModuleType.MODULE_MISSION_VALUE + "").set(new ParamObject<Integer>(EClientModuleStatus.MODULE_FLASH.getNumber()));

        return true;
    }

    /**
	 * 修改玩家任务。如果没有对应的玩家任务，会新增数据后再修改
	 * @param pwPlayer
	 * @param activityActionType 该方法修改的活动类型有限制，不符合的类型将抛出异常
	 * @param numberAdd 针对playerActivity的number字段，该参数输入的可能是number的增值，也可能是覆盖值
	 * @return
	 * @throws Exception 返回修改的玩家任务，但是没有向玩家推送消息
	 */
    public List<ProxyWPlayerActivity> updateProxyWPlayerActivity(final ProxyWPlayer pwPlayer, EActivityActionType activityActionType, int numberAdd) throws Exception {
		//获取指定类型的系统活动(同类型，未逻辑删除，已开启活动)
        List<WSysActivity> wSysActivityList = wSysActivityDao.findList(null).stream().filter(p -> p.getAction() == activityActionType.getNumber() && "N".equals(p.getTheSwitch()))
                .collect(Collectors.toList());
		//获取指定类型的玩家活动
        Map<Serializable, ProxyWPlayerActivity> pwPlayerActivityMap = checkPlayerActivityBeforeUpdate(pwPlayer, activityActionType, wSysActivityList);

        for (WSysActivity wSysActivity : wSysActivityList) {
            switch (EActivityActionType.forNumber(wSysActivity.getAction())) {
			//升级
            case LEVEL_ACTIVITY:
                ProxyWPlayer oldPwPlayer = findProxyWPlayer(pwPlayer.id().get());
                if (oldPwPlayer.rank().get() < wSysActivity.getValue() && pwPlayer.rank().get() >= wSysActivity.getValue()) {
                    pwPlayerActivityMap.values().forEach(p -> {
                        p.status().set(EActivityStatus.ACTIVITY_COMPLETE.getNumber());
                        p.award().set(EActivityAwardStatus.ACTIVITY_AWARD_COMPLETE.getNumber());
                    });
                }
                break;
			//和登录行为有关
            case LOGIN_ACTIVITY:
            case RANDOM_ACTIVITY:
                pwPlayerActivityMap.values().forEach(p -> {
                    p.status().set(EActivityStatus.ACTIVITY_COMPLETE.getNumber());
                    p.award().set(EActivityAwardStatus.ACTIVITY_AWARD_COMPLETE.getNumber());
                });
                break;
			//升级后首次登录
            case LEVEL_FIRST_LOGIN:
				//玩家等级必须大于等于系统活动要求的等级，并且该玩家活动未完成状态
                if (pwPlayer.rank().get() >= wSysActivity.getValue()) {
                    pwPlayerActivityMap.values().stream().filter(p -> p.status().get() != EActivityStatus.ACTIVITY_COMPLETE.getNumber()).forEach(p -> {
                        p.status().set(EActivityStatus.ACTIVITY_COMPLETE.getNumber());
                        p.award().set(EActivityAwardStatus.ACTIVITY_AWARD_COMPLETE.getNumber());
                    });
                }
                break;
			//以下活动累加计数需要达成系统活动targetNum字段的数字目标
            case KILL_ACTIVITY:
            case BOSS_KILL_ACTIVITY:
            case KILL_BOSS_ACTIVITY:
            case CHARGE_FC:
            case KILL_BIOCHEMICAL:
            case PAY_FC:
            case KILL_BOSS2_ACTIVITY:
            case TASK_ACTIVITY:
            case TEAM_COMBAT_FINISH:
                pwPlayerActivityMap.values().forEach(p -> {
					p.number().increase(numberAdd);
                    if (p.number().get() >= wSysActivity.getTargetNum()) {
                        p.status().set(EActivityStatus.ACTIVITY_COMPLETE.getNumber());
                    }
                });
                break;
			//以下活动计数需要达成系统活动targetNum字段的数字目标
            case STRENGTH_TO_RANK:
                pwPlayerActivityMap.values().forEach(p -> {
                    p.number().set(numberAdd);
                    if (p.number().get() >= wSysActivity.getTargetNum()) {
                        p.status().set(EActivityStatus.ACTIVITY_COMPLETE.getNumber());
                    }
                });
                break;
			//时段活动，靠登录登出触发
            case HOUR2HOUR_ACTIVITY:
                ProxyWPlayerInfo pwPlayerInfo = findProxyWPlayerInfo(pwPlayer.id().get());
				//                ProtoPlayerInfoProto.Builder builder = pwPlayerInfo.protoEntity().get().toBuilder();
				JsonPlayerInfoCaches jsonCaches = pwPlayerInfo.cachesEntity().get();
				long lastHour2HourAwardTime = jsonCaches.getLastHour2HourAwardTime();//上次因时段活动领奖的时间
                for (ProxyWPlayerActivity p : pwPlayerActivityMap.values()) {
                    if (p.status().get() == EActivityStatus.ACTIVITY_AWAIT.getNumber()) {
                        int startHour = wSysActivity.getValue();
                        int endHour = wSysActivity.getTargetNum();
                        int nowHour = 0;
						boolean isPrize = false;//是否发送奖励
						if (pwPlayer.lastLogoutTime().isNull() || DateUtil.after(pwPlayer.lastLoginTime().get(), pwPlayer.lastLogoutTime().get(), Calendar.MILLISECOND, 1)) {//当前动作登录是登录
                            nowHour = DateUtil.getHourOfDay(pwPlayer.lastLoginTime().get());
							//时段内登录，且当天没有奖励记录
                            if (startHour <= nowHour && nowHour < endHour && !DateUtil.isSameDay(lastHour2HourAwardTime, pwPlayer.lastLoginTime().getTime())) {
                                isPrize = true;
								//以登录时间作为奖励时间记录
								jsonCaches.setLastHour2HourAwardTime(pwPlayer.lastLoginTime().getTime());
								pwPlayerInfo.cachesEntity().set(jsonCaches);
                            }
						} else {//当前动作是登出
                            nowHour = DateUtil.getHourOfDay(pwPlayer.lastLogoutTime().get());
							//时段外登录，时段内登出，且当天没有奖励记录
                            if (nowHour < startHour && endHour <= nowHour && !DateUtil.isSameDay(lastHour2HourAwardTime, pwPlayer.lastLogoutTime().getTime())) {
                                isPrize = true;
								//以登出时间作为奖励时间记录
								jsonCaches.setLastHour2HourAwardTime(pwPlayer.lastLogoutTime().getTime());
								pwPlayerInfo.cachesEntity().set(jsonCaches);
                            }
                        }
                        if (isPrize) {
                            p.status().set(EActivityStatus.ACTIVITY_COMPLETE.getNumber());
                            p.award().set(EActivityAwardStatus.ACTIVITY_AWARD_COMPLETE.getNumber());
                        }
                    }
                }
                break;
            default:
                throw new Exception("Wrong activityActionType : " + activityActionType);
            }
        }
		//TODO 给玩家奖励物品
		//FIXME 应该在该Service外使用此推送
        //soClient.puchCMDtoClient(player.getName(), Converter.completeActivity(completeList));

        return pwPlayerActivityMap.values().stream().filter(p -> !p.getListener().getOperations().isEmpty()).collect(Collectors.toList());
    }

    /**
	 * 不修改玩家任务，无直接奖励，但是返回值会影响其他模块的逻辑运算。如果没有对应的玩家任务，会新增数据
	 * @param pwPlayer
	 * @param activityActionType 该方法修改的活动类型有限制，不符合的类型将抛出异常
	 * @return 返回影响其他模块的逻辑运算的参数
	 * @throws Exception
	 */
    public int updateProxyWPlayerActivity(final ProxyWPlayer pwPlayer, EActivityActionType activityActionType) throws Exception {
		//获取指定类型的系统活动(同类型，未逻辑删除，已开启活动)
        List<WSysActivity> wSysActivityList = wSysActivityDao.findList(null).stream().filter(p -> p.getAction() == activityActionType.getNumber() && "N".equals(p.getTheSwitch()))
                .collect(Collectors.toList());
		//获取指定类型的玩家活动
        Map<Serializable, ProxyWPlayerActivity> pwPlayerActivityMap = checkPlayerActivityBeforeUpdate(pwPlayer, activityActionType, wSysActivityList);

        for (WSysActivity wSysActivity : wSysActivityList) {
            switch (EActivityActionType.forNumber(wSysActivity.getAction())) {
			//以下部分活动无奖励，但是返回的整数对玩家在其他模块的操作影响计算
            case STRENGTH_NON_LOSE:
            case STRENGTH_ADD:
                Calendar c = Calendar.getInstance();
                int h = c.get(Calendar.HOUR_OF_DAY);
                if (wSysActivity.getValue() > h || wSysActivity.getValue() <= h) {
                    return 0;
                }
            case STRENGTH_MUST:
                return wSysActivity.getUnit();
            case TEAM_BATTLE_ADD:
                c = Calendar.getInstance();
                h = c.get(Calendar.HOUR_OF_DAY);
                if (wSysActivity.getValue() > h || wSysActivity.getTargetNum() <= h) {
                    return 0;
                }
            case IGNORE_DEAD:
            case IGNORE_LOSE:
            case OPEN_LUCKYPACKAGE:
                return 1;
            case ONLINE_ACTIVITY:
                return pwPlayerActivityMap.values().stream().filter(p -> p.activityId().get() == wSysActivity.getId()).map(p -> p.target().get() > 1 ? p.target().get() : 1).findFirst().get();
            default:
                throw new Exception("Wrong activityActionType : " + activityActionType);
            }
        }
        return 0;
    }

    /**
	 * 修改和副本有紧密关系的玩家任务。如果没有对应的玩家任务，会新增数据后再修改
	 * @param pwPlayer
	 * @param activityActionType 该方法修改的活动类型有限制，不符合的类型将抛出异常
	 * @param playerInfo
	 * @param stageClearInfo
	 * @return 返回的玩家任务已提交事务，但是没有向玩家推送消息
	 * @throws Exception
	 */
    public List<ProxyWPlayerActivity> updateProxyWPlayerActivity(final ProxyWPlayer pwPlayer, EActivityActionType activityActionType, StageClearPlayerInfo playerInfo,
            GeneralStageClearInfo stageClearInfo) throws Exception {
		//获取指定类型的系统活动(同类型，未逻辑删除，已开启活动)
        List<WSysActivity> wSysActivityList = wSysActivityDao.findList(null).stream().filter(p -> p.getAction() == activityActionType.getNumber() && "N".equals(p.getTheSwitch()))
                .collect(Collectors.toList());
		//获取指定类型的玩家活动
        Map<Serializable, ProxyWPlayerActivity> pwPlayerActivityMap = checkPlayerActivityBeforeUpdate(pwPlayer, activityActionType, wSysActivityList);

        for (WSysActivity wSysActivity : wSysActivityList) {
            switch (EActivityActionType.forNumber(wSysActivity.getAction())) {
			//成就
            case ACHIEVEMENT_ACTIVITY:
                int numberThisRound = 0;
                WSysLevel wSysLevel = wSysLevelDao.findEntity(stageClearInfo.getLevelId());
                for (ProxyWPlayerActivity pwPlayerActivity : pwPlayerActivityMap.values()) {
                    switch (EAchievementAction.forNumber(pwPlayerActivity.achievementAction().get())) {
                    case GAMEWIN:
						if (playerInfo.getIsWiner() == 1) {// 如果赢了一盘游戏，进度+1
                            numberThisRound = 1;
                        }
                        break;
					case CONTROLKILL:// 控制击杀
                        numberThisRound = playerInfo.getControlNum();
                        break;
					case REVENGEKILL:// 复仇击杀
                        numberThisRound = playerInfo.getRevengeNum();
                        break;
					case CHARCTERKILL:// 杀死特定职业
                        numberThisRound = playerInfo.getKillCharacter()[pwPlayerActivity.characterId().get()];
                        break;
					case BLOODBAG:// 吃血包
                        if (playerInfo.getBottleHpNum() >= pwPlayerActivity.target().get()) {
                            numberThisRound = playerInfo.getBottleHpNum();
                        }
					case BULLETBAG:// 吃弹药包
                        if (playerInfo.getBulletNum() >= pwPlayerActivity.target().get()) {
                            numberThisRound = playerInfo.getBulletNum();
                        }
                        break;
					case FIRSTKILL:// 首杀
                        if (playerInfo.getIsFirstKill() == 1 && wSysLevel.getType() != EGameType.HITBOSS.getNumber()) {
                            numberThisRound = 1;
                        }
					case FIRSTDEAD:// 首死
                        if (playerInfo.getIsFirstDead() == 1 && wSysLevel.getType() != EGameType.HITBOSS.getNumber()) {
                            numberThisRound = 1;
                        }
					case CONTINUEKILL:// 生存
						if ((playerInfo.getMaxKillNum() - 1) >= pwPlayerActivity.target().get() && wSysLevel.getType() != EGameType.HITBOSS.getNumber()) {// 连杀数达标
                            numberThisRound = playerInfo.getMaxKillNum() - 1;
                        }
                        break;
                    default:
                        break;
                    }
					pwPlayerActivity.number().increase(numberThisRound);
                    if (numberThisRound > 0 && pwPlayerActivity.target().get() <= pwPlayerActivity.number().get()) {
                        pwPlayerActivity.status().set(EActivityStatus.ACTIVITY_COMPLETE.getNumber());
                    }
                }
                break;
            default:
                throw new Exception("Wrong activityActionType : " + activityActionType);
            }
        }
		//TODO 玩家奖励
		//FIXME 应该在该Service外使用此推送
        //soClient.puchCMDtoClient(player.getName(), Converter.completeActivity(completeList));

        return pwPlayerActivityMap.values().stream().filter(p -> !p.getListener().getOperations().isEmpty()).collect(Collectors.toList());
    }

    /**
	 * 修改玩家成就<br/>
	 * @param pwPlayer
	 * @param action
	 * @param number 成就本次计数
	 * @return 返回修改的玩家成就
	 * @throws Exception
	 */
    public List<ProxyWPlayerAchievement> updateProxyWPlayerAchievement(final ProxyWPlayer pwPlayer, EAchievementAction action, int number) throws Exception {
		//获取指定类型的系统成就(同类型，未逻辑删除)
        List<WSysAchievement> wSysAchievementList = wSysAchievementDao.findList(null).stream().filter(p -> p.getAction() == action.getNumber()).collect(Collectors.toList());
		//获取指定类型的玩家成就
        Map<Serializable, ProxyWPlayerAchievement> pwPlayerAchievementMap = checkPlayerAchievementBeforeUpdate(pwPlayer, action, wSysAchievementList);
        for (ProxyWPlayerAchievement pwPlayerAchievement : pwPlayerAchievementMap.values()) {
			//由于玩家成就可能对应多个系统成就，此时取得的系统成就由玩家成就的level-1确定
            WSysAchievement wSysAchievement = wSysAchievementList.stream().filter(p -> p.getGroup() == pwPlayerAchievement.group().get()).collect(Collectors.toList())
                    .get(pwPlayerAchievement.level().get() - 1);
			//重新计算本局游戏后获得的完成数
            int numberThisRound = 0;
            switch (EAchievementType.forNumber(wSysAchievement.getType())) {
            case ACHIEVEMENT_NONE:
                switch (EAchievementAction.forNumber(wSysAchievement.getAction())) {
                case DALABA:
                    numberThisRound = 1;
                    break;
                case FRIENDS:
                case USECR:
                case GIFTCR:
                case NEWPLAYER:
                    numberThisRound = number;
                    break;
                default:
                    break;
                }
                break;
            case ACHIEVEMENT_CHARACTER:
                switch (EAchievementAction.forNumber(wSysAchievement.getAction())) {
                case GETWEAPON:
                case GETKNIFE:
                case GETCOSTUME:
                    numberThisRound = 1;
                    break;
                default:
                    break;
                }
                break;
            default:
                break;
            }
			if (numberThisRound > 0) {//完成数量大于0才修改数据
				//完成数按照单次最大方式统计，但本次完成数没有达到系统成就的阈值，不做修改
                if (EAchievementStatType.STAT_MAX.getNumber() == wSysAchievement.getStatType() && numberThisRound < wSysAchievement.getNumber()) {
                    continue;
                }
				pwPlayerAchievement.number().increase(numberThisRound);
				if (pwPlayerAchievement.number().get() >= wSysAchievement.getNumber()) {//完成数已达到系统成就的阈值，修改玩家成就的状态
                    if (pwPlayerAchievement.totalLevel().get() > pwPlayerAchievement.level().get()) {
						pwPlayerAchievement.level().increase(1);
                    }
					pwPlayerAchievement.status().set(EAchievementStatus.ACHIEVEMENT_COMPLETE.getNumber());//状态这个字段其实没用处，判断成就是否达成看level和totalLevel
                }
				//修改的玩家成就影响玩家奖牌数量
                switch (EItemColor.forNumber(wSysAchievement.getColor())) {
                case RED:
					pwPlayer.bronze().increase(1);
                    break;
                case PURPLE:
					pwPlayer.silver().increase(1);
                    break;
                case BLUE:
					pwPlayer.gold().increase(1);
                    break;
                default:
                    break;
                }
                if (StringUtil.isEmpty(pwPlayer.title().get())) {
					pwPlayer.total().increase(1);
                }
            }
        }
		//FIXME 通知客户端完成成就应在该service之外执行
        //ServiceLocator.soClient.sendAchievementCompleted(finishedList,player.getId(), player.getName());

        return pwPlayerAchievementMap.values().stream().filter(p -> !p.getListener().getOperations().isEmpty()).collect(Collectors.toList());
    }

    /**
	 * 修改玩家背包记录。根据playerId，type，seq，sysCharacterId,packId来确定唯一性<br/>
	 * 不存在背包记录则新增数据
	 * @param pwPlayer
	 * @param pwPlayerItem
	 * @param wSysCharacter
	 * @param packId
	 * @throws Exception
	 */
    public void updateProxyWPlayerPack(final ProxyWPlayer pwPlayer, final ProxyWPlayerItem pwPlayerItem, WSysCharacter wSysCharacter, int packId) throws Exception {
        WSysItem wSysItem = wSysItemDao.findEntity(pwPlayerItem.itemId().get());
        int seq = wSysItem.getNumberParamMap().get(EItemNumberParam.seq.toString()).intValue();
        ProxyWPlayerPack pwPlayerPack = findProxyWPlayerPackMap(pwPlayer.id().get()).values().stream().filter(p -> {
            boolean b = p.sysCharacterId().get() == wSysCharacter.getId() && p.seq().get() == seq && p.packId().get() == packId;
            if (wSysItem.getType() == EItemType.WEAPON.getNumber()) {
                b = b && p.type().get().equals("W");
			} else if (wSysItem.getType() == EItemType.PART.getNumber() || wSysItem.getType() == EItemType.COSTUME.getNumber()) {
                b = b && p.type().get().equals("C");
            }
            return b;
        }).findFirst().orElse(null);
        if (null == pwPlayerPack) {
            addProxyWPlayerPack(pwPlayer.id().get(), pwPlayerItem, wSysCharacter, packId);
        } else {
            pwPlayerPack.playerItemId().set(pwPlayerItem.id().get());
        }
    }

    /**
	 * 修改玩家Buff记录。根据playerId,buffId来确定唯一性<br/>
	 * 不存在Buff记录则新增数据
	 * @param pwPlayer
	 * @param pwPlayerItem
	 * @param ibuffId
	 * @throws Exception
	 */
    public void updateProxyWPlayerBuff(final ProxyWPlayer pwPlayer, final ProxyWPlayerItem pwPlayerItem, EItemIBuffId ibuffId) throws Exception {
        ProxyWPlayerBuff pwPlayerBuff = findProxyWPlayerBuffMap(pwPlayer.id().get()).values().stream().filter(p -> p.buffId().get() == ibuffId.getNumber()).findFirst().orElse(null);
        if (null == pwPlayerBuff) {
            addProxyWPlayerBuff(pwPlayer.id().get(), pwPlayerItem.id().get(), ibuffId);
        } else if (pwPlayerBuff.playerItemId().get() != pwPlayerItem.id().get()) {
            pwPlayerBuff.playerItemId().set(pwPlayerItem.id().get());
        }
    }

    /**
	 * 修改玩家购买记录。根据playerId，sysItemId,paymentId来确定数据的唯一性。<br/>
	 * record字段购买次数，payType冗余字段，payAmount购买金额累加<br/>
	 * 如果无唯一数据自动创建后再修改。
	 * @param pwPlayer
	 * @param wSysItem
	 * @param wPayment
	 * @throws Exception
	 */
    public void updateProxyWBuyitemRecord(final ProxyWPlayer pwPlayer, WSysItem wSysItem, WPayment wPayment) throws Exception {
        ProxyWBuyitemRecord pwBuyitemRecord = findProxyWBuyitemRecordMap(pwPlayer.id().get()).values().stream().filter(p -> {
            return p.itemId().get() == wSysItem.getId() && p.costId().get() == wPayment.getId();
        }).findFirst().orElse(null);
        if (null == pwBuyitemRecord) {
            pwBuyitemRecord = addProxyWBuyitemRecord(pwPlayer.id().get(), wSysItem, wPayment);
        }
		pwBuyitemRecord.record().increase(1);
        pwBuyitemRecord.payType().set(wPayment.getPayType());
		pwBuyitemRecord.payAmount().increase(wPayment.getCost() * wPayment.getUnit());
        pwBuyitemRecord.lastBuyDate().set(new Date());
    }

    /**
	 * 扣除玩家货币。扣除量大于玩家持有量会抛出异常
	 * @param pwPlayer
	 * @param wPayment
	 * @throws Exception
	 */
    public void pay(final ProxyWPlayer pwPlayer, WPayment wPayment) throws Exception {
        if (wPayment.getCost() <= 0) {
            return;
        }
        switch (EPayType.forNumber(wPayment.getPayType())) {
		case PAY_GP://C币
			if (pwPlayer.gPoint().get() < wPayment.getCost()) {
				throw new BaseException(ExceptionMessage.NOT_ENOUGH_GPOINT);
            }
			pwPlayer.gPoint().increase(-wPayment.getCost());
            break;
		case PAY_FC_POINT://FC点
            ProxyWPlayerInfo pwPlayerInfo = entityManager.findProxy(ProxyWPlayerInfo::new, WPlayerInfo.class, pwPlayer.id().get());
            if (pwPlayerInfo.xunleiPoint().get() < wPayment.getCost() * wPayment.getUnit()) {
				throw new BaseException(ExceptionMessage.NOT_ENOUGH_CR);
            }
			pwPlayerInfo.xunleiPoint().increase(-wPayment.getCost());
            break;
        case PAY_VOUCHER:
			if (pwPlayer.voucher().get() < wPayment.getCost()) {
				throw new BaseException(ExceptionMessage.NOT_ENOUGH_V);
            }
			pwPlayer.voucher().increase(-wPayment.getCost());
            break;
        default:
            break;
        }
    }

    /**
	 * 给玩家添加货币，货币量到达Integer.max后不再增加
	 * @param pwPlayer
	 * @param wPayment
	 * @throws Exception
	 */
    public void earn(final ProxyWPlayer pwPlayer, WPayment wPayment) throws Exception {
        if (wPayment.getCost() <= 0) {
            return;
        }
        switch (EPayType.forNumber(wPayment.getPayType())) {
		case PAY_GP://C币
			if (pwPlayer.gPoint().get() > Integer.MAX_VALUE - wPayment.getCost()) {
                pwPlayer.gPoint().set(Integer.MAX_VALUE);
            }
			pwPlayer.gPoint().increase(wPayment.getCost());
            break;
		case PAY_FC_POINT://FC点
            ProxyWPlayerInfo pwPlayerInfo = entityManager.findProxy(ProxyWPlayerInfo::new, WPlayerInfo.class, pwPlayer.id().get());
			if (pwPlayerInfo.xunleiPoint().get() > Integer.MAX_VALUE - wPayment.getCost()) {
                pwPlayerInfo.xunleiPoint().set(Integer.MAX_VALUE);
            }
			pwPlayerInfo.xunleiPoint().increase(wPayment.getCost());
            break;
        case PAY_VOUCHER:
			if (pwPlayer.voucher().get() > Integer.MAX_VALUE - wPayment.getCost()) {
                pwPlayer.voucher().set(Integer.MAX_VALUE);
            }
			pwPlayer.gPoint().increase(wPayment.getCost());
            break;
        default:
            break;
        }
    }

    /**
	 * 重新计算物品的堆叠数
	 * @param originalCount 原始的物品总数（m堆）
	 * @param addCount 新增加的物品数量
	 * @param maxStackSize 允许的每堆物品数量上限
	 * @return 计算得到n+1堆，其中前面n堆数量达到每堆允许的数量上限；最后1堆数量必定小于等于每堆允许的数量上限并且n+1≥m
	 */
    public List<Integer> getPlayerItemStackList(long originalCount, long addCount, int maxStackSize) {
        List<Integer> list = new ArrayList<>();
        long totalCount = originalCount + addCount;
        long stackCount = totalCount / maxStackSize;
        for (int i = 0; i < stackCount; i++) {
            list.add(maxStackSize);
        }
        if (totalCount % maxStackSize != 0) {
            list.add((int) (totalCount % maxStackSize));
        }
        return list;
    }

    /**
	 * 指定的系统道具是否获得立刻使用
	 * @param wSysItem
	 * @return
	 */
    public boolean isItemUseImmediately(WSysItem wSysItem) {
        boolean isUseImmediately = false;
        switch (EItemSubType.forNumber(wSysItem.getType() * ItemSubType.SEP + wSysItem.getSubType())) {
        case ITEM_ADDTION:
        case ITEM_CARD:
        case ITEM_SPRAY:
            isUseImmediately = true;
            break;
        default:
            break;
        }
        switch (EItemIBuffId.forNumber(wSysItem.getiBuffId())) {
        case UNFAIL_MITH:
        case DEAD_CONTRACTION:
        case HIDE_1:
        case HIDE_2:
        case HIDE_3:
        case HIDE_4:
        case HIDE_5:
        case PERSONAL_CARD:
            isUseImmediately = true;
            break;
        default:
            break;
        }
        switch (EItemIId.forNumber(wSysItem.getiId())) {
        case ITEM_GAME_COIN:
        case VIP:
        case WEAL_STONE:
            isUseImmediately = true;
            break;
        default:
            break;
        }
        return isUseImmediately;
    }

	/**
	 * 获取道具在指定星级和稀有度在下一个星级的经验
	 * @param gstLevel @WPlayerItem.gstLevel
	 * @param rareLevel @WSysItem.rareLevel
	 * @return
	 * @throws Exception
	 */
	public int getNextGstLevelExp(int gstLevel, int rareLevel) throws Exception {
		if (gstLevel >= WSysConfigService.getPlayerItemMaxGstLevel()) {//最大星级限制
			return 0;
		}
		//稀有度
		int rareType = WSysConfigService.getSysItemRareTypeRareLevel().entrySet().stream().filter(kv -> {
			return kv.getValue() <= rareLevel;
		}).map(kv -> kv.getKey()).max((p1, p2) -> p1 - p2).get();
		//经验计算
		double exp = 200 * (gstLevel + 1) * (rareType * 0.6 + 1);
		return (int) Math.round(exp);
	}

    /**
	 * 如果玩家物品仍有可开孔位置，给玩家物品开一个孔。未刷新numberParam属性
	 * @param pwPlayerItem
	 * @throws Exception
	 */
	public void openHoleInPlayerItem(final ProxyWPlayerItem pwPlayerItem) throws Exception {
        int maxHoleCount = pwPlayerItem.numberParamMap().get(EItemNumberParam.maxHoleCount.toString()).value().get().intValue();
        int holeCount = pwPlayerItem.numberParamMap().get(EItemNumberParam.holeCount.toString()).value().get().intValue();
        int slotCount = pwPlayerItem.numberParamMap().get(EItemNumberParam.slotCount.toString()).value().get().intValue();
        if (holeCount + slotCount < maxHoleCount) {
			for (int i = 2; i < 8; i++) {
				ProxyItemGunProperty pItemGunProperty = pwPlayerItem.gunPropertyMap().get(i + "");
				if (pItemGunProperty == null) {
					pwPlayerItem.gunPropertyMap().put(i + "", new ItemGunProperty());
					pItemGunProperty = pwPlayerItem.gunPropertyMap().get(i + "");
				}
				if (pItemGunProperty.open().isNull() && pItemGunProperty.gunPropertyList().isEmpty()) {
					pItemGunProperty.open().set(0);
					break;
				}
			}
        }
    }

	/**
	 * 如果玩家物品仍有孔位或者槽位，关闭一个孔或槽。未刷新numberParam属性
	 * @param pwPlayerItem
	 * @throws Exception
	 */
	public void closeHole(final ProxyWPlayerItem pwPlayerItem) throws Exception {
		int holeCount = pwPlayerItem.numberParamMap().get(EItemNumberParam.holeCount.toString()).value().get().intValue();
		int slotCount = pwPlayerItem.numberParamMap().get(EItemNumberParam.slotCount.toString()).value().get().intValue();
		if (holeCount > 0) {//已开孔但未开槽镶嵌
			for (int i = 7; i > 0; i++) {
				ProxyItemGunProperty pItemGunProperty = pwPlayerItem.gunPropertyMap().get(i + "");
				if (pItemGunProperty == null) {
					pwPlayerItem.gunPropertyMap().put(i + "", new ItemGunProperty());
					pItemGunProperty = pwPlayerItem.gunPropertyMap().get(i + "");
				}
				if (!pItemGunProperty.open().isNull() && pItemGunProperty.gunPropertyList().isEmpty()) {
					pItemGunProperty.open().set(null);
					break;
				}
			}
		} else if (slotCount > 0) {//开槽镶嵌
			for (int i = 7; i > 0; i++) {
				ProxyItemGunProperty pItemGunProperty = pwPlayerItem.gunPropertyMap().get(i + "");
				if (pItemGunProperty == null) {
					pwPlayerItem.gunPropertyMap().put(i + "", new ItemGunProperty());
					pItemGunProperty = pwPlayerItem.gunPropertyMap().get(i + "");
				}
				if (!pItemGunProperty.open().isNull() && pItemGunProperty.gunPropertyList().isEmpty()) {
					pItemGunProperty.open().set(null);
					break;
				}
			}
		}
	}
}
