package com.pearl.fcw.lobby.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.cache.EntityManager;
import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.gm.service.WSysItemService;
import com.pearl.fcw.lobby.dao.WPlayerTeamDao;
import com.pearl.fcw.lobby.dao.WTeamDao;
import com.pearl.fcw.lobby.dao.WTeamLevelDao;
import com.pearl.fcw.lobby.pojo.TeamLevelConfigPoints;
import com.pearl.fcw.lobby.pojo.WPlayerTeam;
import com.pearl.fcw.lobby.pojo.WTeam;
import com.pearl.fcw.lobby.pojo.WTeamItem;
import com.pearl.fcw.lobby.pojo.WTeamLevel;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerTeam;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWTeam;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWTeamItem;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWTeamLevel;
import com.pearl.fcw.proto.ProtoConverter;
import com.pearl.fcw.proto.enums.EGameType;
import com.pearl.fcw.proto.enums.ETeamJob;
import com.pearl.fcw.proto.rpc.ResponseEditMap;
import com.pearl.fcw.utils.Constants;
import com.pearl.fcw.utils.DataTablesPage;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.fcw.utils.MapUtil;
import com.pearl.fcw.utils.Smarty4jConverter;
import com.pearl.o2o.pojo.GmUser;
import com.pearl.o2o.pojo.Team;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;

/**
 * 战队
 */
@Service
public class WTeamService extends DmServiceImpl<WTeam, Integer> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private WSysItemService wSysItemService;
    @Resource
    private WTeamDao wTeamDao;
    @Resource
    private WPlayerTeamDao wPlayerTeamDao;
    @Resource
    private WTeamLevelDao wTeamLevelDao;
    @Resource
    private EntityManager entityManager;
    @Resource
    private ProtoConverter protoConverter;

    @PostConstruct
    public void init() {
        this.genDao = wTeamDao;
    }

	/**
	 * 利用WTeam作为查询和中转，返回包含Team的Map的已缓存分页数据
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public DataTablesPage<Map<String, Object>> getPageMap(DataTablesParameter param) throws Exception {
		DataTablesPage<WTeam> pageWTeam = findPage(param);
		List<Map<String, Object>> list = new ArrayList<>();

		List<Team> teamList = pageWTeam.getData().stream().map(p -> {
			try {
				return ServiceLocator.getService.getTeam(p.getId());
			} catch (Exception e) {
				return null;
			}
		}).collect(Collectors.toList());
		list = MapUtil.toListMapExcept(teamList);
		list.forEach(p -> {

		});
		DataTablesPage<Map<String, Object>> page = new DataTablesPage<Map<String, Object>>(list);
		page.setDraw(pageWTeam.getDraw());
		page.setError(pageWTeam.getError());
		page.setRecordsFiltered(pageWTeam.getRecordsFiltered());
		page.setRecordsTotal(pageWTeam.getRecordsTotal());
		return page;
	}

	/**
	 * GM修改数据
	 * @param team
	 * @param gmUser
	 * @throws Exception
	 */
	public void updateByGm(final Team team, final GmUser gmUser) throws Exception {
		Team oldTeam = ServiceLocator.getService.getTeam(team.getId());
		//三个时间点不改
		team.settResourceBeginTime(oldTeam.gettResourceBeginTime());
		team.settLastAddResTime(oldTeam.gettLastAddResTime());
		team.setCurWeekRobUpdateTime(oldTeam.getCurWeekRobUpdateTime());
		ServiceLocator.updateService.updateTeamInfo(team);
	}

    /**
	 * 新增TeamLevel<br/>
	 * 有数据初始化
	 * @param teamId @WTeam.id
	 * @param levleId @WSysLevel.id
	 * @return
	 * @throws Exception
	 */
    public ProxyWTeamLevel addProxyWTeamLevel(int teamId, int... levleId) throws Exception {
        WTeamLevel wTeamLevel = new WTeamLevel();
        wTeamLevel.setTeamId(teamId);
        Date now = new Date();
        wTeamLevel.setCreateTime(now);
        wTeamLevel.setLastBeginUpdateTime(now);
        wTeamLevel.setLastUpdateTime(now);
        wTeamLevel.setIsEditable("N");
        int index = new Random().nextInt(Constants.DEFAULT_NEW_TEAM_CONFIG_POINTS.length);
        wTeamLevel.setConfigPoints(Constants.DEFAULT_NEW_TEAM_CONFIG_POINTS[index]);
        wTeamLevel.setOrgLevelId(levleId.length > 0 ? levleId[0] : com.pearl.o2o.utils.Constants.DEFAULT_TEAM_LEVEL_RES_ID);
        wTeamLevel.setLevelType(EGameType.TEAMZYZDZ.getNumber());

        return entityManager.add(ProxyWTeamLevel::new, WTeamLevel::new, wTeamLevel);
    }

    /**
	 * 根据玩家ID查找其所属的战队关系<br/>
	 * PlayerTeam以Team为主逻辑，所以根据playerId获取PlayerTeam必须直接查询数据库。<br/>
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
    public Map<Serializable, ProxyWPlayerTeam> findProxyWPlayerTeamMap(int playerId) throws Exception {
        WPlayerTeam wPlayerTeam = wPlayerTeamDao.findEntityByPlayerId(playerId);
        if (null == wPlayerTeam) {
            return null;
        }
        return entityManager.findProxyMap(ProxyWPlayerTeam::new, WPlayerTeam::new, WPlayerTeam.class, wPlayerTeam.getTeamId());
    }

    /**
	 * 根据战队ID查找战队地图<br/>
	 * 没有战队地图则创建一个
	 * @param teamId
	 * @return
	 * @throws Exception
	 */
    public Map<Serializable, ProxyWTeamLevel> findProxyWTeamLevelMap(int teamId) throws Exception {
        Map<Serializable, ProxyWTeamLevel> map = entityManager.findProxyMap(ProxyWTeamLevel::new, WTeamLevel::new, WTeamLevel.class, teamId);
        if (map.isEmpty()) {
            addProxyWTeamLevel(teamId);
        }
        map = entityManager.findProxyMap(ProxyWTeamLevel::new, WTeamLevel::new, WTeamLevel.class, teamId);
        return map;
    }

    /**
	 * 根据战队ID查找战队
	 * @param id
	 * @return
	 * @throws Exception
	 */
    public ProxyWTeam findProxyWTeam(int id) throws Exception {
        return entityManager.findProxy(ProxyWTeam::new, WTeam.class, id);
    }

    /**
	 * 根据玩家ID查找所属战队
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
    public ProxyWTeam findProxyWTeamByPlayerId(int playerId) throws Exception {
        int teamId = findProxyWPlayerTeamMap(playerId).values().stream().filter(p -> "Y".equals(p.approved().get())).map(p -> p.teamId().get()).findFirst().orElse(0);
        return findProxyWTeam(teamId);
    }

    /**
	 * 根据战队名称查找战队
	 * @param name
	 * @return
	 * @throws Exception
	 */
    public ProxyWTeam findProxyWTeamByName(String name) throws Exception {
        WTeam wTeam = new WTeam();
        wTeam.setName(name);
        int id = wTeamDao.findList(wTeam).stream().map(p -> p.getId()).findFirst().orElse(0);
        return findProxyWTeam(id);
    }

    /**
	 * 获取战队地图
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
    public String getTeamMap(int playerId) throws Exception {
        ProxyWPlayerTeam pwPlayerTeam = findProxyWPlayerTeamMap(playerId).values().stream().filter(p -> "Y".equals(p.approved().get())).findFirst().orElse(null);
        if (null == pwPlayerTeam) {
            return "";
        }
		if (pwPlayerTeam.job().get() != ETeamJob.TEAM_CAPTAIN.getNumber()) {//只有队长能查询战队地图
            return Smarty4jConverter.error(ExceptionMessage.IS_NOT_LEADER);
        }
        int teamId = pwPlayerTeam.teamId().get();
        ProxyWTeam pwTeam = entityManager.findProxy(ProxyWTeam::new, WTeam.class, teamId);
		if (null == pwTeam) {//战队不存在
            return Smarty4jConverter.error(ExceptionMessage.NOT_FIND_TEAM);
        }
        ProxyWTeamLevel pwTeamLevel = findProxyWTeamLevelMap(teamId).values().stream().findFirst().get();
        ResponseEditMap proto = protoConverter.responseEditMap(pwTeam, pwTeamLevel);
		return Smarty4jConverter.proto2Lua(proto);
    }

    /**
	 * 保存战队地图
	 * @param playerId @WPlayer.id
	 * @param sysLevelId @WSysLevel.id
	 * @param cpList @List<@TeamLevelConfigPoints>
	 * @throws Exception
	 */
    public void saveTeamMap(int playerId, int sysLevelId, List<TeamLevelConfigPoints> cpList) throws Exception {
		//PlayerTeam以Team为主逻辑，所以根据playerId获取PlayerTeam必须直接查询数据库
        WPlayerTeam wPlayerTeam = wPlayerTeamDao.findEntityByPlayerId(playerId);
        if (null == wPlayerTeam) {
            logger.error("Get wrong playerId when save map!");
            throw new NullPointerException();
        }
        int teamId = wPlayerTeam.getTeamId();
        ProxyWTeam pwTeam = entityManager.findProxy(ProxyWTeam::new, WTeam.class, teamId);
		if (null == pwTeam) {//战队不存在
            logger.error("Get wrong teamId when save map!");
            throw new NullPointerException();
        }
        ProxyWTeamLevel pwTeamLevel = entityManager.findProxyMap(ProxyWTeamLevel::new, WTeamLevel::new, WTeamLevel.class, teamId).values().stream().findFirst().orElse(null);
		if (null == pwTeamLevel || pwTeamLevel.orgLevelId().ne(sysLevelId)) {//战队地图不存在或者与传入的sysLevelId不符合
            logger.error("Get wrong teamLevelId when save map!");
            throw new NullPointerException();
        }
		//替换地图数据
        Date now = new Date();
        pwTeamLevel.configPointsList().set(cpList);
        pwTeamLevel.lastUpdateId().set(playerId);
        pwTeamLevel.lastUpdateTime().set(now);
        pwTeamLevel.isEditable().set("N");
		//统计地图中物品的使用数量
        for (ProxyWTeamItem pwTeamItem : entityManager.findProxyMap(ProxyWTeamItem::new, WTeamItem::new, WTeamItem.class, teamId).values()) {
            pwTeamItem.usedCount().set((int) cpList.stream().filter(cp -> pwTeamItem.itemId().eq(cp.getSysItemId())).count());
        }
    }
}
