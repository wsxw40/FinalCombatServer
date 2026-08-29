package com.pearl.fcw.lobby.service;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.cache.EntityManager;
import com.pearl.fcw.core.pojo.BaseEntity;
import com.pearl.fcw.core.pojo.proxy.EntityProxy;
import com.pearl.fcw.core.pojo.proxy.Operation;
import com.pearl.fcw.core.pojo.proxy.PropertyProxy;
import com.pearl.fcw.gm.pojo.WSysQuest;
import com.pearl.fcw.gm.service.WSysQuestService;
import com.pearl.fcw.lobby.pojo.json.JsonQuestTrigger;
import com.pearl.fcw.lobby.pojo.json.JsonQuestTrigger.Trigger;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerQuest;
import com.pearl.fcw.proto.ProtoConverter;
import com.pearl.fcw.proto.enums.EQuestStatus;
import com.pearl.fcw.proto.enums.EQuestUiType;
import com.pearl.fcw.proto.rpc.ResponseOnlineAward;
import com.pearl.fcw.utils.DateUtil;
import com.pearl.fcw.utils.O2oUtil;
import com.pearl.fcw.utils.Smarty4jConverter;
import com.pearl.fcw.utils.StringUtil;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.GeneralStageClearInfo;
import com.pearl.o2o.pojo.StageClearPlayerInfo;
import com.pearl.o2o.pojo.StageClearPlayerInfo.SingleCharacterInfo;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;

/**
 * 页游整合的任务
 */
@Service
public class QuestService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private WSysQuestService wSysQuestService;
	@Resource
	private WPlayerService wPlayerService;
	@Resource
	private ItemService itemService;
	@Resource
	private ProtoConverter protoConverter;
	@Resource
	private EntityManager entityManager;

	/**
	 * 获取在线时长的任务和奖励
	 * @param playerId
	 * @param awardSysQuestId 获取奖励的系统任务ID,0表示不获取奖励
	 * @return
	 * @throws Exception
	 */
	public String getOnlineAward(int playerId, int awardSysQuestId) throws Exception {
		Date now = new Date();
		Date tomorrowZero = DateUtil.add(DateUtil.getZero(now), Calendar.DATE, 1);//明天零点时刻
		Date loginTime = Date.from(Instant.ofEpochSecond(ServiceLocator.getService.getPlayerById(playerId).getLastLoginTime()));//FIXME 为了保证逻辑正确，暂时使用端游缓存的登录时间
		Map<Integer, WSysQuest> wSysQuestMap = wSysQuestService.findMap(null);
		Map<Serializable, ProxyWPlayerQuest> pwPlayerQuestMap = wPlayerService.findProxyWPlayerQuestMap(playerId);
		//取得在线奖励任务
		pwPlayerQuestMap = pwPlayerQuestMap.entrySet().stream().filter(kv -> {
			int sysQuestId = kv.getValue().sysQuestId().get();
			WSysQuest wSysQuest = wSysQuestMap.get(sysQuestId);
			if (!wSysQuest.getUiType().equals(EQuestUiType.ONLINE_INTERVAL.getNumber())) {//只显示用于在线时长界面的数据
					return false;
				}
				return DateUtil.before(now, tomorrowZero, Calendar.SECOND, wSysQuest.getNumber().intValue());//当前时刻超前明天零点的秒数必须大于任务要求的计数才显示
			}).collect(Collectors.toMap(kv -> kv.getKey(), kv -> kv.getValue()));
		//获取指定系统任务的奖励
		List<Integer> awardSysQuestIdList = new ArrayList<>();
		if (awardSysQuestId > 0) {
			ProxyWPlayerQuest pwPlayerQuest = pwPlayerQuestMap.values().stream().filter(p -> {
				return p.sysQuestId().eq(awardSysQuestId);
			}).findFirst().orElse(null);
			if (null == pwPlayerQuest) {//无该任务
				throw new BaseException(ExceptionMessage.MISSION_NOT_FIND);
			}
			switch (EQuestStatus.forNumber(pwPlayerQuest.status().get())) {
			case QUEST_AWAIT:
			case QUEST_DISCARD:
				throw new BaseException(ExceptionMessage.MISSION_NOT_FINISH);//任务未完成
			case QUEST_AWARD:
				throw new BaseException(ExceptionMessage.MISSION_ALREADY_AWARD);//奖励已领取
			default:
				WSysQuest wSysQuest = wSysQuestMap.get(pwPlayerQuest.sysQuestId().get());
				//FIXME 为了保证逻辑正确,暂时使用端游缓存发送奖励和广播信息
				ServiceLocator.updateService.earnCurrenyOrItem(playerId, wSysQuest.getItemList(), false, true, false);
				awardSysQuestIdList.add(awardSysQuestId);
				pwPlayerQuest.status().set(EQuestStatus.QUEST_AWARD.getNumber());
			}
		}
		//取得玩家任务真实的秒计数
		Map<ProxyWPlayerQuest, Long> map = pwPlayerQuestMap.values().stream().collect(Collectors.toMap(pwPlayerQuest -> pwPlayerQuest, pwPlayerQuest -> {
			WSysQuest wSysQuest = wSysQuestMap.get(pwPlayerQuest.sysQuestId().get());
			if (pwPlayerQuest.status().eq(EQuestStatus.QUEST_AWAIT.getNumber())) {//检查当天未完成的任务，计数是否达标
					long seconds = now.getTime() / 1000 - loginTime.getTime() / 1000;
					if (!DateUtil.isSameDay(now, loginTime)) {//登录时间不是当天，改为从现在到当天零点的秒计数
					seconds = now.getTime() / 1000 - DateUtil.getZero(now).getTime() / 1000;
				}
				if (seconds >= wSysQuest.getNumber()) {//完成任务的计数目标，状态改为已完成
					pwPlayerQuest.status().set(EQuestStatus.QUEST_COMPLETE.getNumber());
				} else {
					return seconds;
				}
			}
			return wSysQuest.getNumber();
		}));
		ResponseOnlineAward proto = protoConverter.ResponseOnlineAward(map, awardSysQuestIdList, loginTime);
		return Smarty4jConverter.proto2Lua(proto);
	}

	/**
	 * 自动更新玩家任务计数和状态
	 * @param playerId
	 * @param uri 客户端接口名称
	 * @param msgs 消息
	 * @throws Exception
	 */
	public <M extends BaseEntity, P extends EntityProxy<M>> void updateQuestStatusAuto(int playerId, String uri, Collection<?> msgs) throws Exception {
		Map<Serializable, ProxyWPlayerQuest> pwPlayerQuestMap = wPlayerService.findProxyWPlayerQuestMap(playerId);
		Set<Integer> wSysQuestIds = pwPlayerQuestMap.values().stream().map(p -> p.sysQuestId().get()).collect(Collectors.toSet());

		Collection<WSysQuest> wSysQuests = wSysQuestService.findMap(null).values();
		for (WSysQuest wSysQuest : wSysQuests) {
			if (!wSysQuestIds.contains(wSysQuest.getId())) {
				continue;//无对应的玩家任务
			}
			JsonQuestTrigger trigger = wSysQuest.getTrigger();
			if (null == trigger || trigger.getConditionList().isEmpty()) {
				continue;//无自动触发的目标
			}
			if (!trigger.getInterfaceList().isEmpty() && !trigger.getInterfaceList().contains(uri)) {
				continue;//有接口要求但是不吻合
			}
			//判断自动条件是否全部满足
			boolean b = true;
			for (List<Trigger> ts : trigger.getConditionList()) {//分号间隔
				ProxyWPlayerQuest proxyM = null;//代理实例
				WSysQuest sysM = null;//对应代理实例的系统表数据
				for (Trigger t : ts) {//逗号间隔
					//====Quest
					if (t.getCls().equals(ProxyWPlayerQuest.class)) {
						for (Object obj : msgs.stream().filter(p -> p.getClass().equals(t.getCls())).collect(Collectors.toSet())) {
							if (compare(obj, t.getProperty(), t.getComparator(), t.getNumber())) {
								proxyM = (ProxyWPlayerQuest) obj;
								sysM = wSysQuestService.findEntity(proxyM.sysQuestId().get());
							} else {
								b = false;
								break;
							}
						}
					} else if (t.getCls().equals(WSysQuest.class)) {
						if (null == sysM) {
							b = false;
							break;
						} else if (!compare(sysM, t.getProperty(), t.getComparator(), t.getNumber())) {
							b = false;
							break;
						}
					}
					//==StageClear
					else if (t.getCls().equals(GeneralStageClearInfo.class)) {
						for (Object obj : msgs.stream().filter(p -> p.getClass().equals(t.getCls())).collect(Collectors.toSet())) {
							if (!compare(obj, t.getProperty(), t.getComparator(), t.getNumber())) {
								b = false;
								break;
							}
						}
					} else if (t.getCls().equals(StageClearPlayerInfo.class)) {
						Object obj = msgs.stream().filter(p -> p.getClass().equals(t.getCls())).findFirst().orElse(null);
						if (null == obj) {
							b = false;
							break;
						}
						GeneralStageClearInfo stageClear = (GeneralStageClearInfo) obj;
						StageClearPlayerInfo stageClearPlayer = stageClear.getStageClearPlayerIfno(playerId, false);
						if (null == stageClearPlayer) {
							b = false;
							break;
						}
						if (!compare(stageClearPlayer, t.getProperty(), t.getComparator(), t.getNumber())) {
							b = false;
							break;
						}
					} else if (t.getCls().equals(SingleCharacterInfo.class)) {

					}
					if (!b) {
						break;
					}
				}
				if (!b) {
					break;
				}
			}
			//条件完全满足，则更改玩家任务的计数和状态
			if (b) {
				//计数增量，默认1
				long numberIncrease = msgs.stream().filter(p -> {
					if (null != trigger.getNumber() && p.getClass().equals(trigger.getNumber().getCls())) {
						return true;
					}
					return false;
				}).findAny().map(p -> getNumber(p, trigger.getNumber().getProperty(), playerId)).orElse(1L);
				ProxyWPlayerQuest pwPlayerQuest = pwPlayerQuestMap.values().stream().filter(p -> p.sysQuestId().eq(wSysQuest.getId())).findFirst().get();
				if (pwPlayerQuest.completeCount().get() < wSysQuest.getCompleteCount()) {
					if (pwPlayerQuest.number().get() < wSysQuest.getNumber()) {
						pwPlayerQuest.number().increase(numberIncrease);
					}
				}
				if (pwPlayerQuest.number().get() >= wSysQuest.getNumber()) {
					pwPlayerQuest.completeCount().increase(1);
					pwPlayerQuest.number().set(0L);
				}
				if (pwPlayerQuest.completeCount().get() >= wSysQuest.getCompleteCount()) {
					pwPlayerQuest.status().set(EQuestStatus.QUEST_COMPLETE.getNumber());
				}
			}
			//任务完成后自动发放奖励
			if (wSysQuest.getIsAutoAward() > 0) {
				itemService.earnOrPayItemPrice(playerId, wSysQuest.getItemList());
				itemService.earnOrPayItemPrice(playerId, wSysQuest.getVipItemList());
			}
		}
	}

	/**
	 * 取得任务计数增长<br/>
	 * 异常返回默认增长1
	 * @param target
	 * @param propertyName
	 * @return
	 */
	private <M extends BaseEntity, P extends EntityProxy<M>> long getNumber(Object target, String propertyName,int playerId) {
		try {
			if (target instanceof GeneralStageClearInfo) {//过关结算
				GeneralStageClearInfo stageClear = (GeneralStageClearInfo) target;
				Object obj = stageClear.getStageClearPlayerIfno(playerId, false);
				for (String proName : propertyName.split("\\.")) {
					obj = obj.getClass().getMethod("get" + StringUtil.toPascal(proName)).invoke(obj);
				}
				return Long.parseLong(obj.toString());
			} else if (target instanceof EntityProxy) {//代理类
				Object obj = target;
				for (String proName : propertyName.split("\\.")) {
					obj = obj.getClass().getMethod(proName).invoke(obj);
				}
				obj = obj.getClass().getMethod("get").invoke(obj);
				return Long.parseLong(obj.toString());
			} else {
				Object obj = target;
				for (String proName : propertyName.split("\\.")) {
					obj = obj.getClass().getMethod("get" + StringUtil.toPascal(proName)).invoke(obj);
				}
				return Long.parseLong(obj.toString());
			}
		} catch (Exception e) {
			logger.warn("", e);
		}
		return 1L;
	}

	/**
	 * 任务条件比较
	 * 抛异常会返回false
	 * @param target
	 * @param propertyName
	 * @param comparator
	 * @param number
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private <M extends BaseEntity, P extends EntityProxy<M>> boolean compare(Object target, String propertyName, String comparator, double number) {
		try {
			if (target instanceof EntityProxy) {//代理类
				P proxyM = (P) target;
				if (propertyName.endsWith("+")) {//数值增减
					propertyName = propertyName.substring(0, propertyName.length() - 1);
					Object obj = proxyM;
					for (String proName : propertyName.split(".")) {
						obj = obj.getClass().getMethod(proName).invoke(obj);
					}
					obj = obj.getClass().getMethod("get").invoke(obj);
					PropertyProxy<?> proxyM1 = (PropertyProxy<?>) obj;
					for (Operation op : proxyM1.getListener().getOperations()) {
						if (op.getType().equals(Operation.Type.INCREASE) && O2oUtil.compare(op.getValue(), comparator, number)) {//代理数据内某个属性增减
							return true;
						} /*else if (op.getType().equals(Operation.Type.NEWINSTANCE) && O2oUtil.compare(op.getValue(), comparator, number)) {//创建了一个新的代理数据
						  return true;
						  }*/
					}
				} else {//常规取值
					Object obj = proxyM;
					for (String proName : propertyName.split("\\.")) {
						obj = obj.getClass().getMethod(proName).invoke(obj);
					}
					obj = obj.getClass().getMethod("get").invoke(obj);
					return O2oUtil.compare(obj, comparator, number);
				}
			} else if (target instanceof GeneralStageClearInfo) {//过关结算特殊处理类

			} else if (target instanceof StageClearPlayerInfo) {//过关结算特殊处理类

			} else {
				Object obj = target;
				for (String proName : propertyName.split("\\.")) {
					if ("size".equals(proName) || "length".equals(proName)) {//java的特殊方法
						obj = obj.getClass().getMethod(proName).invoke(obj);
					} else {
						obj = obj.getClass().getMethod("get" + StringUtil.toPascal(proName)).invoke(obj);
					}
				}
				return O2oUtil.compare(obj, comparator, number);
			}
		} catch (Exception e) {
			logger.warn("", e);
		}
		return false;
	}
}
