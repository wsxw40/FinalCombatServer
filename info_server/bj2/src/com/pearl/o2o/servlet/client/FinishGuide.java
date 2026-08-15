package com.pearl.o2o.servlet.client;

import java.util.LinkedList;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.gm.pojo.WPayment;
import com.pearl.fcw.gm.pojo.WSysAchievement;
import com.pearl.fcw.gm.service.WSysAchievementService;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerAchievement;
import com.pearl.fcw.lobby.service.WPlayerService;
import com.pearl.fcw.proto.ProtoConverter;
import com.pearl.fcw.proto.enums.EAchievementStatus;
import com.pearl.fcw.proto.enums.EPayType;
import com.pearl.fcw.proto.rpc.ResponseFinishGuide;
import com.pearl.fcw.utils.Smarty4jConverter;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerInfo;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * 完成引导信息(页游先用端游框架，但是缓存读写使用了了新旧缓存混合)
 */
@Service("c_finish_guide")
public class FinishGuide extends BaseClientServlet implements Servletable {

	private static final long serialVersionUID = -4714427235636286548L;
	private static Logger log = LoggerFactory.getLogger(FinishGuide.class.getName());
	private static final String[] paramNames = { "userId", "playerId", "group", "status" };

	@Resource
	private FinishGuide c_finish_guide;
	@Resource
	private WSysAchievementService wSysAchievementService;
	@Resource
	private WPlayerService wPlayerService;
	@Resource
	private ProtoConverter protoConverter;

	@Override
	protected String innerService(String... args) {
		try {
			return c_finish_guide.rpc(args);
		} catch (BaseException e) {
			log.error("Error in c_guide_list: ", e);
			return Converter.error(e.getMessage());
		} catch (Exception e) {
			log.error("Error in c_guide_list: ", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	public String rpc(String... args) throws Exception {
		int playerId = Integer.parseInt(args[1]);
		int group = Integer.parseInt(args[2]);
		int status = Integer.parseInt(args[3]);
		//查找指定系列的未完成的玩家成就
		ProxyWPlayerAchievement pwPlayerAchievement = wPlayerService.findProxyWplayerAchievementMap(playerId).values().stream().filter(p -> {
			return p.group().eq(group) && p.status().eq(EAchievementStatus.ACHIEVEMENT_AWAIT.getNumber());
		}).findFirst().orElse(null);
		WSysAchievement nextWSysAchievement = null;
		if (null != pwPlayerAchievement) {
			switch (EAchievementStatus.forNumber(status)) {
			case ACHIEVEMENT_COMPLETE://完成一个成就
				//从系统成就中查找下一个应完成的成就
				int sysAchievementId = 0;
				LinkedList<Integer> sysAchievementIdList = new LinkedList<>();
				sysAchievementIdList.add(sysAchievementId);
				while (sysAchievementIdList.size() < pwPlayerAchievement.level().get() + 1) {
					sysAchievementId = wSysAchievementService.findList(null).stream().filter(p -> {
						return pwPlayerAchievement.group().eq(p.getGroup()) && Integer.parseInt(p.getParent()) == sysAchievementIdList.peekLast();
					}).map(p -> p.getId()).findFirst().orElse(0);
					sysAchievementIdList.add(sysAchievementId);
				}
				WSysAchievement wSysAchievement = wSysAchievementService.findEntity(sysAchievementId);
				//玩家成就完成数加一
				pwPlayerAchievement.number().increase(1);
				nextWSysAchievement = wSysAchievement;
				//应完成的成就存在且完成数达标，则修改玩家成就数据，并利用旧缓存结构送出奖励
				if (null != wSysAchievement && pwPlayerAchievement.number().ge(wSysAchievement.getNumber())) {
					pwPlayerAchievement.level().increase(1);
					//旧缓存送奖励
					for (WPayment wPayment : wSysAchievement.getGiftList()) {
						Player player = getService.getPlayerById(playerId);
						switch (EPayType.forNumber(wPayment.getPayType())) {
						case PAY_GP:
							player.setGPoint(player.getGPoint() + wPayment.getCost() * wPayment.getUnit());
							updateService.updatePlayerInfo(player);
							break;
						case PAY_FC_POINT:
							player = getService.getPlayerById(playerId);
							PlayerInfo playerInfo = getService.getPlayerInfoById(playerId);
							playerInfo.setXunleiPoint(playerInfo.getXunleiPoint() + wPayment.getCost() * wPayment.getUnit());
							updateService.updatePlayerInfo(player);
							break;
						case PAY_ITEM:
							SysItem sysItem = getService.getSysItemByItemId(wPayment.getItemId());
							Payment payment = new Payment();
							payment.setItemId(wPayment.getItemId());
							payment.setCost(wPayment.getCost());
							payment.setUnit(wPayment.getUnit());
							payment.setUnitType(wPayment.getUnitType());
							createService.sendItem(sysItem, player, payment, "N", "N", "N");
							break;
						default:
							break;
						}

					}

					//该成就完成后检查是否还有下一个。没有则玩家成就状态为完成，有则玩家成就完成数数归零
					nextWSysAchievement = wSysAchievementService.findList(null).stream().filter(p -> {
						return p.getGroup().equals(wSysAchievement.getGroup()) && Integer.parseInt(p.getParent()) == wSysAchievement.getId();
					}).findFirst().orElse(null);
					if (null == nextWSysAchievement) {
						pwPlayerAchievement.status().set(EAchievementStatus.ACHIEVEMENT_COMPLETE.getNumber());
					} else {
						pwPlayerAchievement.number().set(0);
					}
				}
				break;
			case ACHIEVEMENT_DISCARD://废弃一个系列的成就
				pwPlayerAchievement.status().set(status);
				break;
			default:
				break;
			}
		}

		ResponseFinishGuide proto = protoConverter.responseFinishGuide(pwPlayerAchievement, nextWSysAchievement);
		return Smarty4jConverter.proto2Lua(proto);
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[1]));
	}

	@Override
	public String getLockedKey(String... args) {
		return args[1];
	}

}
