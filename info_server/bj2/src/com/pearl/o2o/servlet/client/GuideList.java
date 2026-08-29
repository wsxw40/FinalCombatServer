package com.pearl.o2o.servlet.client;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.gm.pojo.WSysAchievement;
import com.pearl.fcw.gm.service.WSysAchievementService;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerAchievement;
import com.pearl.fcw.lobby.service.WPlayerService;
import com.pearl.fcw.proto.ProtoConverter;
import com.pearl.fcw.proto.enums.EAchievementStatus;
import com.pearl.fcw.proto.rpc.ResponseGuideList;
import com.pearl.fcw.utils.Smarty4jConverter;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;

/**
 * 查看引导信息(页游先用端游框架，但是缓存读写使用了新缓存)
 */
@Service("c_guide_list")
public class GuideList extends BaseClientServlet implements Servletable {

	private static final long serialVersionUID = 1365619336081083574L;
	private static Logger log = LoggerFactory.getLogger(GuideList.class.getName());
	private static final String[] paramNames = { "userId", "playerId", "type", "sysCharacterId" };

	@Resource
	private GuideList c_guide_list;
	@Resource
	private WSysAchievementService wSysAchievementService;
	@Resource
	private WPlayerService wPlayerService;
	@Resource
	private ProtoConverter protoConverter;

	@Override
	protected String innerService(String... args) {
		try {
			return c_guide_list.rpc(args);
		} catch (BaseException e) {
			log.error("Error in c_guide_list: " + getLockedKey(args), e);
			return Converter.error(e.getMessage());
		} catch (Exception e) {
			log.error("Error in c_guide_list: " + getLockedKey(args), e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	public String rpc(String... args) throws Exception {
		int playerId = Integer.parseInt(args[1]);
		int type = Integer.parseInt(args[2]);
		int sysCharacterId = Integer.parseInt(args[3]);

		//查找符合类型和角色的系统成就
		Set<WSysAchievement> wSysAchievementSet = wSysAchievementService.findList(null).stream().filter(p -> {
			return p.getType() == type && p.getCharacterId() == sysCharacterId;
		}).collect(Collectors.toSet());
		Set<Integer> groupSet = wSysAchievementSet.stream().map(p -> p.getGroup()).distinct().collect(Collectors.toSet());
		//查找符合条件的系统成就对应的玩家成就
		List<ProxyWPlayerAchievement> pwPlayerAchievementList = wPlayerService.findProxyWplayerAchievementMap(playerId).values().stream().filter(p -> {
			return groupSet.contains(p.group().get()) && p.get().getSysCharacterId() == sysCharacterId;
		}).collect(Collectors.toList());
		//新增缺少的玩家成就
		for (int group : groupSet) {
			if (pwPlayerAchievementList.stream().filter(p -> p.group().get() == group).count() == 0) {
				wPlayerService.addProxyWPlayerAchievement(playerId, sysCharacterId, group);
			}
		}
		//查找符合条件的系统成就对应的玩家成就（未完成状态）
		pwPlayerAchievementList = wPlayerService.findProxyWplayerAchievementMap(playerId).values().stream().filter(p -> {
			return groupSet.contains(p.group().get()) && p.get().getSysCharacterId() == sysCharacterId && p.status().get() == EAchievementStatus.ACHIEVEMENT_AWAIT.getNumber();
		}).collect(Collectors.toList());
		ResponseGuideList proto = protoConverter.responseGuideList(pwPlayerAchievementList);
		pwPlayerAchievementList.forEach(p -> {
			if (p.backUp().isNull()) {
				p.backUp().set(Instant.now().toString());
			}
		});
		return Smarty4jConverter.proto2Lua(proto);
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

	//	@Override
	//	protected String getLockKey(String[] paramNames) {
	//		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[1]));
	//	}

	@Override
	public String getLockedKey(String... args) {
		return args[1];
	}

}
