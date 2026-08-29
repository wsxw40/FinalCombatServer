package com.pearl.fcw.lobby.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.pojo.proxy.ProxyInterruptException;
import com.pearl.fcw.gm.pojo.WPayment;
import com.pearl.fcw.gm.pojo.WSysItem;
import com.pearl.fcw.gm.pojo.WSysItemGunProperty;
import com.pearl.fcw.gm.pojo.enums.ItemSubType;
import com.pearl.fcw.gm.service.WSysCharacterService;
import com.pearl.fcw.gm.service.WSysConfigService;
import com.pearl.fcw.gm.service.WSysItemGunPropertyService;
import com.pearl.fcw.gm.service.WSysItemService;
import com.pearl.fcw.lobby.pojo.ItemGunProperty;
import com.pearl.fcw.lobby.pojo.columnDescriptor.WPlayerItemColumnDescriptor;
import com.pearl.fcw.lobby.pojo.json.JsonPlayerInfoCaches;
import com.pearl.fcw.lobby.pojo.proxy.ProxyItemGunProperty;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayer;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerBuff;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerInfo;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerItem;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerMelting;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerPack;
import com.pearl.fcw.proto.ProtoConverter;
import com.pearl.fcw.proto.enums.EItemColor;
import com.pearl.fcw.proto.enums.EItemCombineType;
import com.pearl.fcw.proto.enums.EItemGunPropertyType;
import com.pearl.fcw.proto.enums.EItemIBuffId;
import com.pearl.fcw.proto.enums.EItemMType;
import com.pearl.fcw.proto.enums.EItemNumberParam;
import com.pearl.fcw.proto.enums.EItemRareType;
import com.pearl.fcw.proto.enums.EItemSubType;
import com.pearl.fcw.proto.enums.EItemType;
import com.pearl.fcw.proto.enums.EMeltingResult;
import com.pearl.fcw.proto.enums.EPayType;
import com.pearl.fcw.proto.enums.EUnitType;
import com.pearl.fcw.proto.rpc.ResponseConvertPer;
import com.pearl.fcw.proto.rpc.ResponseInsert;
import com.pearl.fcw.proto.rpc.ResponseMeltingInfoGet;
import com.pearl.fcw.proto.rpc.ResponseMeltingInput;
import com.pearl.fcw.proto.rpc.ResponseRemove;
import com.pearl.fcw.proto.rpc.ResponseSloting;
import com.pearl.fcw.proto.rpc.ResponseStrengthItemList;
import com.pearl.fcw.proto.rpc.ResponseStrengthen;
import com.pearl.fcw.proto.rpc.ResponseTwoToOne;
import com.pearl.fcw.utils.DateUtil;
import com.pearl.fcw.utils.LobbyCompareUtil;
import com.pearl.fcw.utils.RandomUnit;
import com.pearl.fcw.utils.Smarty4jConverter;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.socket.SocketClientNew;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ExceptionMessage;

@Service
public class CombineService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private WSysItemService wSysItemService;
	@Resource
	private WSysItemGunPropertyService wSysItemGunPropertyService;
	@Resource
	private WSysCharacterService wSysCharacterService;
	@Resource
	private WPlayerService wPlayerService;
	@Resource
	private ItemService ItemService;
	@Resource
	private ProtoConverter protoConverter;
	@Resource
	private SocketClientNew socketClientNew;

	/**
	 * 获取与合成相关的玩家道具
	 * @param playerId
	 * @param combineType
	 * @param itemType
	 * @param sysCharacterId
	 * @param page 当前页码
	 * @return
	 * @throws Exception
	 */
	public String getCombienItems(int playerId, EItemCombineType combineType, EItemType itemType, int sysCharacterId, int page) throws Exception {
		Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap = wPlayerService.findProxyWPlayerItemMap(playerId);
		Map<Serializable, ProxyWPlayerPack> pwPlayerPackMap = wPlayerService.findProxyWPlayerPackMap(playerId);
		Map<Integer, WSysItem> wSysItemMap = wSysItemService.findMap(null);

		page = page < 1 ? 1 : page;
		int pageSize = WSysConfigService.getUiPage().getItemTypeAndPageSizeInCombine().get(itemType.getNumber());

		pwPlayerItemMap.entrySet().removeIf(kv -> {
			ProxyWPlayerItem pwPlayerItem = kv.getValue();
			try {
				WSysItem wSysItem = wSysItemService.findEntity(pwPlayerItem.itemId().get());
				if (wSysItem.getType() != itemType.getNumber()) {//排除道具类型不对应
					return true;
				}
				if (pwPlayerPackMap.values().stream().map(p -> p.playerItemId().get()).collect(Collectors.toSet()).contains(pwPlayerItem.id().get())) {//排除正在装备的道具
					return true;
				}
				switch (itemType) {
				case WEAPON:
				case COSTUME:
				case PART:
					if (!wSysCharacterService.findList(null).stream().map(p -> p.getId()).collect(Collectors.toSet()).contains(sysCharacterId)
							|| !wSysItem.getCharacterIdList().contains(sysCharacterId)) {//排除系统角色不对应
						return true;
					}
					if (pwPlayerItem.isDefault().eq("Y") || wSysItem.getIsStrengthen() == 0 || pwPlayerItem.unitType().ne((byte) EUnitType.FOREVER.getNumber())) {//排除默认道具或不可强化或非永久持有
						return true;
					}
					switch (combineType) {
					case MELTING://熔炼
						if (pwPlayerItem.level().le(0) && wSysItem.getEvaluateRmb() <= 0 && wSysItem.getmType() != EItemMType.M_MELTING_UNIT.getNumber()
								&& wSysItem.getmType() != EItemMType.M_MELTING_FORMULA.getNumber()) {
							return true;
						}
						if (wSysItem.getmType() == EItemMType.M_NOT_MELTING_NOT_RENEWAL.getNumber()) {
							return true;
						}
						break;
					case GST://升星
						if (wSysItem.getRareLevel() < WSysConfigService.getSysItemRareTypeRareLevel().get(EItemRareType.RARE_1.getNumber())) {//普通稀有度不可升星
							return true;
						}
						break;
					default:
						break;
					}
					break;
				case ITEM:
					switch (combineType) {
					case MELTING://熔炼
						if (pwPlayerItem.level().le(0) && wSysItem.getEvaluateRmb() <= 0 && wSysItem.getmType() != EItemMType.M_MELTING_UNIT.getNumber()
								&& wSysItem.getmType() != EItemMType.M_MELTING_FORMULA.getNumber()) {
							return true;
						}
						break;
					default:
						break;
					}
					break;
				case MATERIAL:
					switch (combineType) {
					case ENHANCE://非强化用材料
						switch (EItemMType.forNumber(wSysItem.getmType())) {
						case M_STRENGTH_PART:
						case M_ADD_S:
						case M_NOT_BREAK:
						case M_NOT_BACK_LEVEL:
						case M_STRENGTH_ADD_S:
							break;
						default:
							return true;
						}
						break;
					case SLOT://非改装（开槽镶嵌）用材料
						switch (EItemMType.forNumber(wSysItem.getmType())) {
						case M_WEAPON_PART:
						case M_CLOTH_PART:
						case M_INSERT_PART:
							break;
						default:
							return true;
						}
						break;
					case MELTING://熔炼
						if (pwPlayerItem.level().le(0) && wSysItem.getEvaluateRmb() <= 0 && wSysItem.getmType() != EItemMType.M_MELTING_UNIT.getNumber()
								&& wSysItem.getmType() != EItemMType.M_MELTING_FORMULA.getNumber()) {
							return true;
						}
						break;
					default:
						break;
					}
					break;
				default:
					break;
				}

			} catch (Exception e) {
				logger.warn("", e);
			}
			return false;
		});
		pwPlayerItemMap = pwPlayerItemMap.values().stream().sorted(new LobbyCompareUtil(wSysItemMap).PROXY_W_PLAYER_ITEM_COMPARATOR).skip((page - 1) * pageSize).limit(pageSize)
				.collect(Collectors.toMap(p -> p.id().get(), p -> p));
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
		ProxyWPlayerInfo pwPlayerInfo = wPlayerService.findProxyWPlayerInfo(playerId);
		Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap = wPlayerService.findProxyWPlayerBuffMap(playerId);

		ResponseStrengthItemList proto = protoConverter.responseStrengthItemList(pwPlayer, pwPlayerInfo, pwPlayerItemMap, pwPlayerPackMap, pwPlayerBuffMap, page, pageSize);
		String str = Smarty4jConverter.proto2Lua(proto);
		return str;
	}

	/**
	 * 强化道具
	 * @param playerId
	 * @param targetPlayerItemId 目标的玩家道具ID，必需
	 * @param partPlayerItemId 强化配件的玩家道具ID，必需
	 * @param safePlayerItemId 不掉级保障部件的玩家道具ID，非必需
	 * @param stablePlayerItemId 提高成功率的玩家道具ID，非必需
	 * @return
	 * @throws Exception
	 */
	public String enhance(int playerId, int targetPlayerItemId, int partPlayerItemId, int safePlayerItemId, int stablePlayerItemId) throws Exception {
		//TODO 二级密码检测
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
		ProxyWPlayerInfo pwPlayerInfo = wPlayerService.findProxyWPlayerInfo(playerId);
		Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap = wPlayerService.findProxyWPlayerItemMap(playerId);
		//检查玩家道具
		ProxyWPlayerItem targetPwPlayerItem = pwPlayerItemMap.get(targetPlayerItemId);
		ProxyWPlayerItem partPwPlayerItem = pwPlayerItemMap.get(partPlayerItemId);
		ProxyWPlayerItem safePwPlayerItem = pwPlayerItemMap.get(safePlayerItemId);
		ProxyWPlayerItem stablePwPlayerItem = pwPlayerItemMap.get(stablePlayerItemId);
		if (null == targetPwPlayerItem || null == partPwPlayerItem || (null == safePwPlayerItem && safePlayerItemId != 0) || (null == stablePwPlayerItem && stablePlayerItemId != 0)) {
			throw new BaseException(ExceptionMessage.NOT_FIND_ITEM);
		}
		WSysItem targetWSysItem = wSysItemService.findEntity(targetPwPlayerItem.itemId().get());
		WSysItem partWSysItem = wSysItemService.findEntity(partPwPlayerItem.itemId().get());
		WSysItem safeWSysItem = null;
		if (null != safePwPlayerItem) {
			safeWSysItem = wSysItemService.findEntity(safePwPlayerItem.itemId().get());
		}
		WSysItem stableWSysItem = null;
		if (null != stablePwPlayerItem) {
			stableWSysItem = wSysItemService.findEntity(stablePwPlayerItem.itemId().get());
		}
		//强化等级限制
		if (targetWSysItem.getIsStrengthen() == 0) {
			throw new BaseException(ExceptionMessage.CANNOT_STRENGTH + ":" + targetWSysItem.getDisplayName() + " id=" + targetWSysItem.getId());
		}
		int maxEnhanceLevel = WSysConfigService.getSysItemEnhanceLevel().keySet().stream().filter(p -> p > targetPwPlayerItem.level().get()).min((p1, p2) -> p1 - p2).orElse(0);
		if (null == WSysConfigService.getSysItemEnhanceLevel().get(maxEnhanceLevel)) {
			throw new BaseException(ExceptionMessage.ITEM_IS_MAX_LEVEL + ":" + targetWSysItem.getDisplayName() + " id=" + targetWSysItem.getId());
		}
		if (targetPwPlayerItem.level().get() + 1 == maxEnhanceLevel && WSysConfigService.getSysItemEnhanceLevel().get(maxEnhanceLevel).get("player") > pwPlayer.rank().get()) {
			throw new BaseException(CommonUtil.messageFormatI18N(CommonMsg.ENHANCE_MSG_C782, WSysConfigService.getSysItemEnhanceLevel().get(maxEnhanceLevel).get("player")));
		}
		//检查系统道具
		if (partWSysItem.getmType() != EItemMType.M_STRENGTH_PART.getNumber()) {//非强化部件
			throw new BaseException(ExceptionMessage.STRENGTH_ITEM_NOT_MATCH);
		}
		if (null != safeWSysItem && safeWSysItem.getmType() != EItemMType.M_NOT_BREAK.getNumber() && safeWSysItem.getmType() != EItemMType.M_NOT_BACK_LEVEL.getNumber()) {//非成功保障道具
			throw new BaseException(ExceptionMessage.STRENGTH_ITEM_NOT_MATCH);
		}
		if (null != stableWSysItem && stableWSysItem.getmType() != EItemMType.M_ADD_S.getNumber() && stableWSysItem.getmType() != EItemMType.M_STRENGTH_ADD_S.getNumber()) {//非增加成功率道具
			throw new BaseException(ExceptionMessage.STRENGTH_ITEM_NOT_MATCH);
		}
		//检查强化配件数量
		int costFactor = targetWSysItem.getType() == EItemType.WEAPON.getNumber() ? 6 : targetWSysItem.getType() == EItemType.COSTUME.getNumber() ? 3 : (targetWSysItem.getSubType()
				+ targetWSysItem.getType() * ItemSubType.SEP == EItemSubType.PART_ADORNMENT.getNumber()) ? 2 : 1;
		int partCost = costFactor * (targetPwPlayerItem.level().get() + 1);//强化需要的配件数量
		if (partPwPlayerItem.quantity().get() < costFactor * (targetPwPlayerItem.level().get() + 1)) {
			throw new BaseException(CommonUtil.messageFormatI18N(ExceptionMessage.NOT_ENOUGH_STRENGTHEN_ITEM, partCost, partCost - partPwPlayerItem.quantity().get()));
		}
		ItemService.reducePlayerItem(playerId, partPwPlayerItem.itemId().get(), partCost);
		//扣除强化费用
		WPayment wPayment = new WPayment();
		wPayment.setUnit(1);
		wPayment.setUnitType(EUnitType.QUANTITY_VALUE);
		wPayment.setPayType(EPayType.PAY_GP.getNumber());
		int cost = (targetPwPlayerItem.level().get() + 1) * 1000 * costFactor;
		wPayment.setCost(cost);
		wPlayerService.pay(pwPlayer, wPayment);
		//计算强化成功率
		double successRate = 0;
		if (null != stableWSysItem && stableWSysItem.getmType() == EItemMType.M_STRENGTH_ADD_S.getNumber() && stableWSysItem.getmValue() > targetPwPlayerItem.level().get()) {//强化必定成功的特殊道具
			successRate = 1;
		} else {
			double baseSuccessRate = 2.1848 * (targetPwPlayerItem.level().get() + 1) / Math.pow(1.8, targetPwPlayerItem.level().get() + 1);//基础成功率
			double activityFactor = 1;//活动影响系数
			//FIXME		double activityFactor= 1+updatePlayerActivity(Constants.ACTION_ACTIVITY.STRENGTH_NON_LOSE.getValue(), player.getId(), new Date(), 0, 0, null, null);
			double activityFactor1 = 1;//活动影响系数
			//FIXME		activityFactor1=1+ updatePlayerActivity(Constants.ACTION_ACTIVITY.STRENGTH_ADD.getValue(), player.getId(), new Date(), 0, 0, null, null);
			double teamFactor = 1;//战队影响系数
			int maxTeamLevel = 0;//FIXME 应取team表里level最大的值
			teamFactor = Math.pow(1.06, Math.max(0, maxTeamLevel - 5)) * Math.pow(1.16, maxEnhanceLevel - targetPwPlayerItem.level().get() + 1);
			double stableFactor = 1;//使用增加成功率道具后的系数
			if (null != stableWSysItem) {
				stableFactor += stableWSysItem.getmValue() / 100.0;
			}
			double vipFactor = 1;//VIP影响系数
			vipFactor += wPlayerService.findProxyWPlayerBuffMap(playerId).values().stream().map(pwPlayerBuff -> {
				try {
					int sysItemId = pwPlayerItemMap.get(pwPlayerBuff.playerItemId()).itemId().get();
					WSysItem wSysItem = wSysItemService.findEntity(sysItemId);
					if (wSysItem.getiBuffId() == EItemIBuffId.BUFF_STRENGTH_VIP.getNumber()) {
						return Integer.parseInt(wSysItem.getiValue());
					}
				} catch (Exception e) {
				}
				return 0;
			}).max((p1, p2) -> p1 - p2).orElse(0);
			successRate = baseSuccessRate * activityFactor * activityFactor1 * teamFactor * stableFactor * vipFactor;
		}
		successRate = successRate < 0 ? 0 : successRate;
		successRate = successRate > 1 ? 1 : successRate;
		double rate = new Random().nextDouble();
		if (targetPwPlayerItem.isBind().ne("Y")) {//绑定
			targetPwPlayerItem.isBind().set("Y");
		}
		int result = 0;
		List<WSysItem> failWardSysItemList = new ArrayList<>();
		if (rate <= successRate) {//强化成功
			targetPwPlayerItem.level().increase(1);
			int maxHoleCount = WSysConfigService.getPlayerItemCombineInsert().getItemTypeAndPlayerItemLevelAndMaxHoleCount().get((int) targetWSysItem.getType()).entrySet().stream().filter(kv -> {
				return kv.getKey() <= targetPwPlayerItem.level().get();
			}).max((kv1, kv2) -> kv1.getKey() - kv2.getKey()).map(kv -> kv.getValue()).orElse(0);//获取升级后的最大可开孔数量
			if (maxHoleCount > targetPwPlayerItem.numberParamMap().get(EItemNumberParam.maxHoleCount.toString()).get().getValue().intValue()) {//升级后可开孔数可能增加
				wPlayerService.openHoleInPlayerItem(targetPwPlayerItem);
			}
			WPlayerItemColumnDescriptor.refreshNumberParamMap(targetPwPlayerItem);//升级后刷新numberParam属性
			//根据强化后的道具颜色发布广播
			int index = 0;
			for (int level : WSysConfigService.getPlayerItemLevelColor().keySet().stream().sorted((p1, p2) -> p2 - p1).collect(Collectors.toList())) {
				if (index > 3) {
					break;//只对高等级的四种颜色发出广播
				}
				if (targetPwPlayerItem.level().get() >= level) {
					String arg0 = index == 0 ? CommonMsg.STRENGTH_16_SYS : index == 1 ? CommonMsg.STRENGTH_MAX_SYS : index == 2 ? CommonMsg.STRENGTH_MIDDLE_SYS : CommonMsg.STRENGTH_SYS;
					String arg1 = EItemColor.COLORFUL.getNumber() + "@!" + pwPlayer.name().get() + "@!" + EItemColor.COLORFUL.getNumber() + "@!" + pwPlayer.id().get();
					String arg2 = targetPwPlayerItem.numberParamMap().get(EItemNumberParam.color.toString()).get().getValue() + "@!" + "<SN" + targetWSysItem.getId() + "^0>";
					String arg3 = targetPwPlayerItem.numberParamMap().get(EItemNumberParam.color.toString()).get().getValue() + "@!" + targetPwPlayerItem.level().get();
					String arg4 = EItemColor.GOLD.getNumber() + "@!" + pwPlayer.name().get();
					socketClientNew.proxyBroadCast(Constants.MSG_NBA, Constants.SYSTEM_SPEAKER, CommonUtil.messageFormatI18N(arg0, arg1, arg2, arg3));
					socketClientNew.updateBillBoardList(CommonUtil.messageFormatI18N(arg0, arg4, arg2, arg3));
					break;
				}
				index++;
			}
		} else {
			if (safeWSysItem.getmType() == EItemMType.M_NOT_BACK_LEVEL.getNumber()) {//强化失败但是使用了保障道具不掉级
				result = 1;
			} else if (rate * 2 <= 1 + successRate) {//强化失败后不掉级概率默认50%
				result = 1;
			} else {//失败掉级
				result = 2;
				targetPwPlayerItem.level().increase(-1);
				int maxHoleCount = WSysConfigService.getPlayerItemCombineInsert().getItemTypeAndPlayerItemLevelAndMaxHoleCount().get((int) targetWSysItem.getType()).entrySet().stream().filter(kv -> {
					return kv.getKey() <= targetPwPlayerItem.level().get();
				}).max((kv1, kv2) -> kv1.getKey() - kv2.getKey()).map(kv -> kv.getValue()).orElse(0);//获取掉级后的最大可开孔数量
				if (maxHoleCount < targetPwPlayerItem.numberParamMap().get(EItemNumberParam.maxHoleCount.toString()).get().getValue().intValue()) {//掉级后可开孔数可能减少
					wPlayerService.closeHole(targetPwPlayerItem);
				}
				WPlayerItemColumnDescriptor.refreshNumberParamMap(targetPwPlayerItem);//掉级后刷新numberParam属性
			}
			//强化失败后返回碎片
			int frapCount = (int) (5 * partCost * (0.5 * (1 + Math.random() + Math.random())));
			for (int i = 0; i < WSysConfigService.getCombineFailAwardSysItemId().size(); i++) {
				int unit = (int) (frapCount % Math.pow(5, i + 1) / Math.pow(5, i));
				int sysItemId = WSysConfigService.getCombineFailAwardSysItemId().get(i);
				wPayment = new WPayment();
				wPayment.setUnit(unit);
				wPayment.setUnitType(EUnitType.QUANTITY.getNumber());
				wPayment.setPayType(EPayType.PAY_ITEM.getNumber());
				WSysItem wSysItem = wSysItemService.findEntity(sysItemId);
				failWardSysItemList.add(wSysItem);
				ItemService.sendPlayerItem(pwPlayer, wSysItem, wPayment, false, false, false);
			}
		}

		Map<Serializable, ProxyWPlayerPack> pwPlayerPackMap = wPlayerService.findProxyWPlayerPackMap(playerId);
		Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap = wPlayerService.findProxyWPlayerBuffMap(playerId);
		ResponseStrengthen proto = protoConverter.responseStrengthen(result, targetPwPlayerItem, partPwPlayerItem, safePwPlayerItem, stablePwPlayerItem, failWardSysItemList, pwPlayerInfo,
				pwPlayerPackMap, pwPlayerBuffMap);
		return Smarty4jConverter.proto2Lua(proto);
	}

	/**
	 * 开槽
	 * @param playerId
	 * @param targetPlayerItemId 目标的玩家道具ID，必需
	 * @param partPlayerItemId 开槽配件的玩家道具ID，必需
	 * @param slotId 开槽槽位号，1-6
	 * @return
	 * @throws Exception
	 */
	public String slot(int playerId, int targetPlayerItemId, int partPlayerItemId, int slotId) throws Exception {
		//数据检查
		//TODO 检测是否输过二级密码
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
		ProxyWPlayerInfo pwPlayerInfo = wPlayerService.findProxyWPlayerInfo(playerId);
		Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap = wPlayerService.findProxyWPlayerItemMap(playerId);
		ProxyWPlayerItem targetPwPlayerItem = pwPlayerItemMap.get(targetPlayerItemId);
		ProxyWPlayerItem partPwPlayerItem = pwPlayerItemMap.get(partPlayerItemId);
		if (null == targetPwPlayerItem) {//开槽对象是必需品
			throw new BaseException(ExceptionMessage.NOT_FIND_ITEM);
		}
		if (null == partPwPlayerItem) {//开槽部件是必需品
			throw new BaseException(ExceptionMessage.NOT_SLOT_ITEM);
		}
		WSysItem targetWSysItem = wSysItemService.findEntity(targetPwPlayerItem.itemId().get());
		WSysItem partWSysItem = wSysItemService.findEntity(partPwPlayerItem.itemId().get());
		if (targetWSysItem.getIsStrengthen() == 0) {//开槽对象必须可强化
			throw new BaseException(ExceptionMessage.CANNOT_STRENGTH);
		}
		int maxHoleCount = targetPwPlayerItem.numberParamMap().get(EItemNumberParam.maxHoleCount.toString()).get().getValue().intValue();//最大可开孔数量
		int slotCount = targetPwPlayerItem.numberParamMap().get(EItemNumberParam.slotCount.toString()).get().getValue().intValue();//已开槽数量
		if (slotCount >= maxHoleCount) {//不能继续开槽
			throw new BaseException(ExceptionMessage.NOT_ENOUGH_HOLES);
		}
		if (WSysConfigService.getPlayerItemSlotRate().get((int) targetWSysItem.getType()).keySet().stream().filter(p -> p < slotId && slotId <= p).count() == 0) {//武器只能在1到6号位置打孔//衣服配饰只能在1到3号位置打孔
			throw new BaseException(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		ProxyItemGunProperty pItemGunProperty = targetPwPlayerItem.gunPropertyMap().get(slotId + 1 + "");
		if (null != pItemGunProperty && pItemGunProperty.open().eq(1)) {//对应位置已开槽
			throw new BaseException(ExceptionMessage.IS_SLOTTED);
		}
		if (partWSysItem.getmType() != EItemMType.M_INSERT_PART.getNumber()) {//非改装部件
			throw new BaseException(ExceptionMessage.NOT_SLOT_ITEM);
		}

		//扣除开槽费用
		int cost = Constants.SLOTTING_COST;//FIXME 开槽费用，代码常量应改到数据库
		WPayment wPayment = new WPayment();
		wPayment.setPayType(EPayType.PAY_GP.getNumber());
		wPayment.setUnitType(EUnitType.QUANTITY.getNumber());
		wPayment.setUnit(1);
		wPayment.setCost(cost);
		wPlayerService.pay(pwPlayer, wPayment);
		//减少配件数量
		ItemService.reducePlayerItem(playerId, partPwPlayerItem.itemId().get(), 1);

		//开槽
		float rate = WSysConfigService.getPlayerItemSlotRate().get((int) targetWSysItem.getType()).get(slotCount);//开槽成功率
		//TODO VIP影响成功率
		int result = 0;
		if (Math.random() < rate) {//开槽成功
			pItemGunProperty.open().set(1);
			WPlayerItemColumnDescriptor.refreshNumberParamMap(targetPwPlayerItem);//重刷目标道具的属性
		} else {
			result = 1;
		}

		Map<Serializable, ProxyWPlayerPack> pwPlayerPackMap = wPlayerService.findProxyWPlayerPackMap(playerId);
		Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap = wPlayerService.findProxyWPlayerBuffMap(playerId);
		ResponseSloting proto = protoConverter.responseSloting(result, targetPwPlayerItem, partPwPlayerItem, pwPlayerInfo, pwPlayerPackMap, pwPlayerBuffMap);
		return Smarty4jConverter.proto2Lua(proto);
	}

	/**
	 * 镶嵌
	 * @param playerId
	 * @param targetPlayerItemId 目标的玩家道具ID，必需
	 * @param partPlayerItemId 镶嵌配件的玩家道具ID，必需
	 * @param slotId 镶嵌槽位号，1-6
	 * @return
	 * @throws Exception
	 */
	public String insert(int playerId, int targetPlayerItemId, int partPlayerItemId, int slotId) throws Exception {
		//数据检查
		//TODO 检测是否输过二级密码
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
		ProxyWPlayerInfo pwPlayerInfo = wPlayerService.findProxyWPlayerInfo(playerId);
		Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap = wPlayerService.findProxyWPlayerItemMap(playerId);
		ProxyWPlayerItem targetPwPlayerItem = pwPlayerItemMap.get(targetPlayerItemId);
		ProxyWPlayerItem partPwPlayerItem = pwPlayerItemMap.get(partPlayerItemId);
		if (null == targetPwPlayerItem) {//镶嵌对象是必需品
			throw new BaseException(ExceptionMessage.NOT_FIND_ITEM);
		}
		if (null == partPwPlayerItem) {//镶嵌部件是必需品
			throw new BaseException(ExceptionMessage.NO_PROPERTY_ITEM);
		}
		WSysItem targetWSysItem = wSysItemService.findEntity(targetPwPlayerItem.itemId().get());
		WSysItem partWSysItem = wSysItemService.findEntity(partPwPlayerItem.itemId().get());
		if (targetWSysItem.getIsStrengthen() == 0) {//镶嵌对象必须可强化
			throw new BaseException(ExceptionMessage.CANNOT_STRENGTH);
		}
		if (targetWSysItem.getType() == EItemType.WEAPON.getNumber() && partWSysItem.getmType() != EItemMType.M_WEAPON_PART.getNumber()) {//非武器镶嵌部件
			throw new BaseException(ExceptionMessage.PROPERTY_ITEM_NOT_MATCH);
		}
		if ((targetWSysItem.getType() == EItemType.COSTUME.getNumber() || targetWSysItem.getType() == EItemType.PART.getNumber()) && partWSysItem.getmType() != EItemMType.M_CLOTH_PART.getNumber()) {//非服装镶嵌部件
			throw new BaseException(ExceptionMessage.PROPERTY_ITEM_NOT_MATCH);
		}
		ProxyItemGunProperty pItemGunProperty = targetPwPlayerItem.gunPropertyMap().get(slotId + 1 + "");
		if (null == pItemGunProperty || pItemGunProperty.open().isNull() || pItemGunProperty.open().ne(1)) {//未开槽
			throw new BaseException(ExceptionMessage.HOLE_NOT_SLOT);
		}

		//扣除镶嵌费用
		int cost = Constants.INSERT_COST[partWSysItem.getmValue() - 1];//FIXME 镶嵌费用，代码常量应改到数据库
		WPayment wPayment = new WPayment();
		wPayment.setPayType(EPayType.PAY_GP.getNumber());
		wPayment.setUnitType(EUnitType.QUANTITY.getNumber());
		wPayment.setUnit(1);
		wPayment.setCost(cost);
		wPlayerService.pay(pwPlayer, wPayment);
		//减少配件数量
		ItemService.reducePlayerItem(playerId, partPwPlayerItem.itemId().get(), 1);

		//镶嵌
		List<WSysItemGunProperty> wSysItemGunPropertyList = wSysItemGunPropertyService.findList(null).stream().filter(wSysItemGunProperty -> {
			//查找符合镶嵌目标父子类型，以及镶嵌部件mType的系统道具附加属性数据
				int itemSubType = wSysItemGunProperty.getMultiTypeMap().get(EItemGunPropertyType.INSERT_TARGET_ITEM_SUB_TYPE).stream().findFirst().orElse(0);
				int mValue = wSysItemGunProperty.getMultiTypeMap().get(EItemGunPropertyType.INSERT_PART_M_VALUE).stream().findFirst().orElse(0);
				if (itemSubType == targetWSysItem.getType() * ItemSubType.SEP + targetWSysItem.getSubType() && mValue == partWSysItem.getmValue()) {
					return true;
				}
				return false;
			}).collect(Collectors.toList());
		WSysItemGunProperty wSysItemGunProperty = new WSysItemGunProperty();
		wSysItemGunProperty.setIndex(0);
		int sameIndexCount = 1;
		while (sameIndexCount > 0) {//按权重随机一个目标道具不重复的附加属性的ID
			int index = RandomUnit.getRandomObjectByWeight(wSysItemGunPropertyList).getIndex();
			wSysItemGunProperty.setIndex(index);
			sameIndexCount = targetPwPlayerItem.gunPropertyMap().filterValue(p -> {
				return p.getGunPropertyList().stream().filter(pp -> pp.getIndex() == wSysItemGunProperty.getIndex()).count() > 0;
			}).size();
		}
		if (wSysItemGunProperty.getIndex() == 0) {//附加属性的ID必须大于0
			throw new BaseException(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		wSysItemGunPropertyList.removeIf(p -> p.getIndex() != wSysItemGunProperty.getIndex());//只保留对应附加属性ID的系统数据
		int minValue = wSysItemGunPropertyList.stream().map(p -> p.getValue()).min((p1, p2) -> p1 - p2).orElse(0);//在系统数据的最大最小值之间取随机
		int maxValue = wSysItemGunPropertyList.stream().map(p -> p.getValue()).min((p1, p2) -> p1 - p2).orElse(0);
		int minValue2 = wSysItemGunPropertyList.stream().map(p -> p.getValue2()).min((p1, p2) -> p1 - p2).orElse(0);
		int maxValue2 = wSysItemGunPropertyList.stream().map(p -> p.getValue2()).min((p1, p2) -> p1 - p2).orElse(0);
		int minTime = wSysItemGunPropertyList.stream().map(p -> p.getTime()).min((p1, p2) -> p1 - p2).orElse(0);
		int maxTime = wSysItemGunPropertyList.stream().map(p -> p.getTime()).min((p1, p2) -> p1 - p2).orElse(0);
		wSysItemGunProperty.setValue(RandomUnit.random(minValue, maxValue));
		wSysItemGunProperty.setValue2(RandomUnit.random(minValue2, maxValue2));
		wSysItemGunProperty.setTime(RandomUnit.random(minTime, maxTime));
		pItemGunProperty.gunPropertyList().add(wSysItemGunProperty);

		Map<Serializable, ProxyWPlayerPack> pwPlayerPackMap = wPlayerService.findProxyWPlayerPackMap(playerId);
		Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap = wPlayerService.findProxyWPlayerBuffMap(playerId);
		ResponseInsert proto = protoConverter.responseInsert(0, targetPwPlayerItem, partPwPlayerItem, pwPlayerInfo, pwPlayerPackMap, pwPlayerBuffMap);
		return Smarty4jConverter.proto2Lua(proto);
	}

	/**
	 * 移除镶嵌
	 * @param playerId
	 * @param targetPlayerItemId 目标的玩家道具ID，必需
	 * @param slotId 镶嵌槽位号，1-6
	 * @param slotId
	 * @return
	 * @throws Exception
	 */
	public String remove(int playerId, int targetPlayerItemId, int slotId) throws Exception {
		//数据检查
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
		ProxyWPlayerInfo pwPlayerInfo = wPlayerService.findProxyWPlayerInfo(playerId);
		Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap = wPlayerService.findProxyWPlayerItemMap(playerId);
		ProxyWPlayerItem targetPwPlayerItem = pwPlayerItemMap.get(targetPlayerItemId);
		if (null == targetPwPlayerItem) {//对象是必需品
			throw new BaseException(ExceptionMessage.NOT_FIND_ITEM);
		}
		WSysItem targetWSysItem = wSysItemService.findEntity(targetPwPlayerItem.itemId().get());
		if (targetWSysItem.getIsStrengthen() == 0) {//对象必须可强化
			throw new BaseException(ExceptionMessage.CANNOT_STRENGTH);
		}
		ProxyItemGunProperty pItemGunProperty = targetPwPlayerItem.gunPropertyMap().get(slotId + 1 + "");
		if (null == pItemGunProperty || pItemGunProperty.open().isNull() || pItemGunProperty.open().ne(1)) {//未开槽
			throw new BaseException(ExceptionMessage.HOLE_NOT_SLOT);
		}
		if (pItemGunProperty.mValue().get() <= 0 || pItemGunProperty.gunPropertyList().isEmpty()) {//未镶嵌
			throw new BaseException(ExceptionMessage.HOLE_UNINSERT);
		}

		//扣除费用
		int cost = Constants.REMOVE_COST;//FIXME 镶嵌费用，代码常量应改到数据库
		WPayment wPayment = new WPayment();
		wPayment.setPayType(EPayType.PAY_GP.getNumber());
		wPayment.setUnitType(EUnitType.QUANTITY.getNumber());
		wPayment.setUnit(1);
		wPayment.setCost(cost);
		wPlayerService.pay(pwPlayer, wPayment);

		//移除镶嵌
		pItemGunProperty.mValue().set(null);
		pItemGunProperty.gunPropertyList().remove(pItemGunProperty.gunPropertyList().size() - 1);

		Map<Serializable, ProxyWPlayerPack> pwPlayerPackMap = wPlayerService.findProxyWPlayerPackMap(playerId);
		Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap = wPlayerService.findProxyWPlayerBuffMap(playerId);
		ResponseRemove proto = protoConverter.responseRemove(0, targetPwPlayerItem, pwPlayerInfo, pwPlayerPackMap, pwPlayerBuffMap);
		return Smarty4jConverter.proto2Lua(proto);
	}

	/**
	 * 升星
	 * @param playerId
	 * @param targetPlayerItemId 目标的玩家道具ID
	 * @param partPlayerItemIdList 配件的玩家道具ID
	 * @param isBuyGstExp 是否购买经验
	 * @return
	 * @throws Exception
	 */
	public String gst(int playerId, int targetPlayerItemId, List<Integer> partPlayerItemIdList, boolean isBuyGstExp) throws Exception {
		//数据检查
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
		ProxyWPlayerInfo pwPlayerInfo = wPlayerService.findProxyWPlayerInfo(playerId);
		Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap = wPlayerService.findProxyWPlayerItemMap(playerId);
		ProxyWPlayerItem targetPwPlayerItem = pwPlayerItemMap.get(targetPlayerItemId);
		if (null == targetPwPlayerItem) {//对象是必需品
			throw new BaseException(ExceptionMessage.NOT_FIND_ITEM);
		}
		if (targetPwPlayerItem.gstLevel().get() >= WSysConfigService.getPlayerItemMaxGstLevel()) {//目标星级已经最大
			throw new BaseException(ExceptionMessage.ITEM_START_MAX_LEVEL);
		}
		if (null == partPlayerItemIdList || partPlayerItemIdList.isEmpty()) {//升星配件至少一个
			throw new BaseException(ExceptionMessage.NOT_FIND_ITEM);
		}
		Map<Serializable, ProxyWPlayerItem> partPwPlayerItemMap = pwPlayerItemMap.entrySet().stream().filter(p -> partPlayerItemIdList.contains(p.getKey()))
				.collect(Collectors.toMap(kv -> kv.getKey(), kv -> kv.getValue()));//升星配件的玩家道具
		WSysItem targetWSysItem = wSysItemService.findEntity(targetPwPlayerItem.itemId().get());
		switch (EItemSubType.forNumber(targetWSysItem.getType() * ItemSubType.SEP + targetWSysItem.getSubType())) {//目标的系统道具父子类型限制
		case WEAPON_MAIN:
		case CHARACTER_SUIT:
		case PART_HAT:
		case PART_ADORNMENT:
			break;
		default:
			throw new BaseException(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
		for (ProxyWPlayerItem partPwPlayerItem : partPwPlayerItemMap.values()) {
			WSysItem partWSysItem = wSysItemService.findEntity(partPwPlayerItem.itemId().get());
			if (!partWSysItem.getType().equals(targetWSysItem.getType())) {//目标和配件的系统道具类型必须吻合
				throw new BaseException(ExceptionMessage.ERROR_MESSAGE_ALL);
			}
		}

		//计算星级经验
		int totalAddGstExp = 0;//增加的总星级经验
		for (ProxyWPlayerItem partPwPlayerItem : partPwPlayerItemMap.values()) {//一个配件根据自身的稀有度等级增加一次经验
			WSysItem partWSysItem = wSysItemService.findEntity(partPwPlayerItem.itemId().get());
			int partRareType = WSysConfigService.getSysItemRareTypeRareLevel().entrySet().stream().filter(kv -> kv.getValue() <= partWSysItem.getRareLevel()).map(kv -> kv.getKey())
					.max((p1, p2) -> p1 - p2).orElse(EItemRareType.RARE_0.getNumber());//配件的系统道具稀有度类型
			int addGstExp = (int) Math.pow(47.63 * 4, partRareType);
			if (partWSysItem.equals(targetWSysItem)) {//如果升星目标和材料是同一个系统道具，目标增加的星级经验值要乘以一个系数
				addGstExp *= 3;
			}
			totalAddGstExp += addGstExp;
		}
		int targetRareType = WSysConfigService.getSysItemRareTypeRareLevel().entrySet().stream().filter(kv -> kv.getValue() <= targetWSysItem.getRareLevel()).map(kv -> kv.getKey())
				.max((p1, p2) -> p1 - p2).orElse(EItemRareType.RARE_0.getNumber());//目标的系统道具稀有度类型
		if (isBuyGstExp) {//每日一次购买升星经验
			if (!DateUtil.isSameDay(pwPlayerInfo.cachesEntity().get().getRareTypeAndBuyGstExpTime().values().stream().max((p1, p2) -> (int) (p1 - p2)).get(), System.currentTimeMillis())) {
				totalAddGstExp += WSysConfigService.getSysItemRareTypeGstExpInCombine().getOrDefault(targetRareType, 0);
			}
		}
		while (totalAddGstExp > 0) {//可能会出现多次升级
			int nextTargetGstLevelExp = wPlayerService.getNextGstLevelExp(targetPwPlayerItem.gstLevel().get(), targetWSysItem.getRareLevel());//下一星级的经验
			if (totalAddGstExp + targetPwPlayerItem.gstLevelExp().get() >= nextTargetGstLevelExp) {
				targetPwPlayerItem.gstLevel().increase(1);
				totalAddGstExp -= nextTargetGstLevelExp - targetPwPlayerItem.gstLevelExp().get();
				targetPwPlayerItem.gstLevelExp().set(0);
			} else {
				totalAddGstExp = 0;
				targetPwPlayerItem.gstLevelExp().increase(totalAddGstExp);
			}
		}

		//扣除费用
		if (isBuyGstExp) {//购买经验才扣除费用
			int cost = WSysConfigService.getSysItemRareTypeGpointInCombine().getOrDefault(targetRareType, 0);
			WPayment wPayment = new WPayment();
			wPayment.setPayType(EPayType.PAY_GP.getNumber());
			wPayment.setUnitType(EUnitType.QUANTITY.getNumber());
			wPayment.setUnit(1);
			wPayment.setCost(cost);
			wPlayerService.pay(pwPlayer, wPayment);
		}

		//逻辑删除配件道具，并且重刷目标道具的属性
		for (ProxyWPlayerItem partPwPlayerItem : partPwPlayerItemMap.values()) {
			wPlayerService.remove(partPwPlayerItem);
		}
		WPlayerItemColumnDescriptor.refreshNumberParamMap(targetPwPlayerItem);

		Map<Serializable, ProxyWPlayerPack> pwPlayerPackMap = wPlayerService.findProxyWPlayerPackMap(playerId);
		Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap = wPlayerService.findProxyWPlayerBuffMap(playerId);
		ResponseTwoToOne proto = protoConverter.responseTwoToOne(targetPwPlayerItem, pwPlayerInfo, pwPlayerPackMap, pwPlayerBuffMap);
		return Smarty4jConverter.proto2Lua(proto);
	}

	/**
	 * 转换（互换等级和部分附加属性）预览。通过抛出ProxyInterruptException异常来送出数据，但是不修改代理实例
	 * @param playerId
	 * @param playerItemId1
	 * @param playerItemId2
	 * @throws ProxyInterruptException 避免代理实例在事务的最后发生修改而故意抛出的异常
	 * @throws Exception
	 */
	public void previewConvert(int playerId, int playerItemId1, int playerItemId2) throws ProxyInterruptException, Exception {
		//数据检查
		Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap = wPlayerService.findProxyWPlayerItemMap(playerId);
		ProxyWPlayerItem pwPlayerItem1 = pwPlayerItemMap.get(playerItemId1);
		ProxyWPlayerItem pwPlayerItem2 = pwPlayerItemMap.get(playerItemId2);
		if (null == pwPlayerItem1 || null == pwPlayerItem2) {//转换双方是必需品
			throw new BaseException(ExceptionMessage.NOT_FIND_ITEM);
		}
		WSysItem wSysItem1 = wSysItemService.findEntity(pwPlayerItem1.itemId().get());
		WSysItem wSysItem2 = wSysItemService.findEntity(pwPlayerItem2.itemId().get());
		if (wSysItem1.getIsStrengthen() == 0 || wSysItem2.getIsStrengthen() == 0) {//转换双方必须可强化
			throw new BaseException(ExceptionMessage.CANT_STRENGTHEN);
		}
		if (!wSysItem1.getType().equals(wSysItem2.getType()) || !wSysItem1.getSubType().equals(wSysItem2.getSubType())) {//系统道具的父子类型必须吻合
			throw new BaseException(ExceptionMessage.PLAYER_ITEM_NOT_MATCH);
		}

		//不掉级率
		Map<Integer, Float> playerItemLevelAndSuccessRate = WSysConfigService.getPlayerItemCombineConvert().getPlayerItemLevelAndHoldLevelRate();
		float rate1 = playerItemLevelAndSuccessRate.get(pwPlayerItem1.level().get()) + (wSysItem2.getRareLevel() - wSysItem1.getRareLevel()) / 100F;
		rate1 = rate1 < 0 ? 0 : rate1 > 1 ? 1 : rate1;
		float rate2 = playerItemLevelAndSuccessRate.get(pwPlayerItem2.level().get()) + (wSysItem1.getRareLevel() - wSysItem2.getRareLevel()) / 100F;
		rate2 = rate2 < 0 ? 0 : rate2 > 1 ? 1 : rate2;

		//等级互换
		int level1 = pwPlayerItem1.level().get();
		pwPlayerItem1.level().set(pwPlayerItem2.level().get());
		pwPlayerItem2.level().set(level1);
		WPlayerItemColumnDescriptor.refreshNumberParamMap(pwPlayerItem1);
		WPlayerItemColumnDescriptor.refreshNumberParamMap(pwPlayerItem2);

		ProxyWPlayerInfo pwPlayerInfo = wPlayerService.findProxyWPlayerInfo(playerId);
		Map<Serializable, ProxyWPlayerPack> pwPlayerPackMap = wPlayerService.findProxyWPlayerPackMap(playerId);
		Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap = wPlayerService.findProxyWPlayerBuffMap(playerId);
		ResponseConvertPer proto = protoConverter.responseConvertPer(rate1, rate2, pwPlayerItem1, pwPlayerItem2, pwPlayerInfo, pwPlayerPackMap, pwPlayerBuffMap);
		String msg = Smarty4jConverter.proto2Lua(proto);
		throw new ProxyInterruptException(msg);//特意抛出异常，避免代理数据发生修改
	}

	/**
	 * 转换（互换等级和部分附加属性）
	 * @param playerId
	 * @param playerItemId1
	 * @param playerItemId2
	 * @param isHoldLevel 是否消耗货币确保不掉级
	 * @return
	 * @throws Exception
	 */
	public String convert(int playerId, int playerItemId1, int playerItemId2, boolean isHoldLevel) throws Exception {
		//数据检查
		Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap = wPlayerService.findProxyWPlayerItemMap(playerId);
		ProxyWPlayerItem pwPlayerItem1 = pwPlayerItemMap.get(playerItemId1);
		ProxyWPlayerItem pwPlayerItem2 = pwPlayerItemMap.get(playerItemId2);
		if (null == pwPlayerItem1 || null == pwPlayerItem2) {//转换双方是必需品
			throw new BaseException(ExceptionMessage.NOT_FIND_ITEM);
		}
		WSysItem wSysItem1 = wSysItemService.findEntity(pwPlayerItem1.itemId().get());
		WSysItem wSysItem2 = wSysItemService.findEntity(pwPlayerItem2.itemId().get());
		if (wSysItem1.getIsStrengthen() == 0 || wSysItem2.getIsStrengthen() == 0) {//转换双方必须可强化
			throw new BaseException(ExceptionMessage.CANT_STRENGTHEN);
		}
		if (!wSysItem1.getType().equals(wSysItem2.getType()) || !wSysItem1.getSubType().equals(wSysItem2.getSubType())) {//系统道具的父子类型必须吻合
			throw new BaseException(ExceptionMessage.PLAYER_ITEM_NOT_MATCH);
		}

		//不掉级率
		Map<Integer, Float> playerItemLevelAndSuccessRate = WSysConfigService.getPlayerItemCombineConvert().getPlayerItemLevelAndHoldLevelRate();
		float rate1 = playerItemLevelAndSuccessRate.get(pwPlayerItem1.level().get()) + (wSysItem2.getRareLevel() - wSysItem1.getRareLevel()) / 100F;
		rate1 = rate1 < 0 ? 0 : rate1 > 1 ? 1 : rate1;
		float rate2 = playerItemLevelAndSuccessRate.get(pwPlayerItem2.level().get()) + (wSysItem1.getRareLevel() - wSysItem2.getRareLevel()) / 100F;
		rate2 = rate2 < 0 ? 0 : rate2 > 1 ? 1 : rate2;
		if (Math.random() > rate1 && pwPlayerItem1.level().gt(0)) {//失败掉一级。如果掉级后最大可开孔数量小于已存在的开孔数量，关闭一个孔位
			pwPlayerItem1.level().increase(-1);
			int maxHoleCount = WSysConfigService.getPlayerItemCombineInsert().getItemTypeAndPlayerItemLevelAndMaxHoleCount().get((int) wSysItem1.getType()).entrySet().stream().filter(kv -> {
				return kv.getKey() <= pwPlayerItem1.level().get();
			}).map(kv -> kv.getValue()).max((p1, p2) -> p1 - p2).orElse(0);
			if (maxHoleCount < pwPlayerItem1.numberParamMap().get(EItemNumberParam.holeCount.toString()).get().getValue().intValue()) {
				wPlayerService.closeHole(pwPlayerItem1);
			}
		}
		if (Math.random() > rate2 && pwPlayerItem2.level().gt(0)) {//失败掉一级。如果掉级后最大可开孔数量小于已存在的开孔数量，关闭一个孔位
			pwPlayerItem2.level().increase(-1);
			int maxHoleCount = WSysConfigService.getPlayerItemCombineInsert().getItemTypeAndPlayerItemLevelAndMaxHoleCount().get((int) wSysItem2.getType()).entrySet().stream().filter(kv -> {
				return kv.getKey() <= pwPlayerItem2.level().get();
			}).map(kv -> kv.getValue()).max((p1, p2) -> p1 - p2).orElse(0);
			if (maxHoleCount < pwPlayerItem2.numberParamMap().get(EItemNumberParam.holeCount.toString()).get().getValue().intValue()) {
				wPlayerService.closeHole(pwPlayerItem2);
			}
		}

		//消耗货币
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
		WPayment wPayment = new WPayment();
		wPayment.setPayType(EPayType.PAY_GP.getNumber());
		wPayment.setUnitType(EUnitType.QUANTITY.getNumber());
		wPayment.setUnit(1);
		wPayment.setCost(WSysConfigService.getPlayerItemCombineConvert().getCostGp());
		if (isHoldLevel) {//增加货币消耗保证不掉级
			double factor1 = 100.0 * WSysConfigService.getPlayerItemCombineConvert().getItemTypeAndHoldLevelGpFactor().get((int) wSysItem1.getType());
			factor1 = factor1 * (rate1 * Math.pow(1.8, pwPlayerItem1.level().get()) + rate2 * Math.pow(1.8, pwPlayerItem2.level().get()));
			double factor2 = Math.pow(10, (Math.floor(Math.log10(factor1)) - 1));
			int cost = (int) (Math.floor(factor1 / factor2) * factor2);
			wPayment.setCost(wPayment.getCost() + cost);
			rate1 = 1;
			rate2 = 1;
		}
		wPlayerService.pay(pwPlayer, wPayment);

		//等级互换
		int newLevel2 = pwPlayerItem1.level().get();
		pwPlayerItem1.level().set(pwPlayerItem2.level().get());
		pwPlayerItem2.level().set(newLevel2);
		//部分附加属性互换
		Map<String, ItemGunProperty> newGunPropertyMap2 = pwPlayerItem1.gunPropertyMap().fetch().entrySet().stream().filter(kv -> !kv.getKey().equals("1"))
				.collect(Collectors.toMap(kv -> kv.getKey(), kv -> kv.getValue().get()));
		Map<String, ItemGunProperty> newGunPropertyMap1 = pwPlayerItem2.gunPropertyMap().fetch().entrySet().stream().filter(kv -> !kv.getKey().equals("1"))
				.collect(Collectors.toMap(kv -> kv.getKey(), kv -> kv.getValue().get()));
		newGunPropertyMap1.forEach((k, v) -> pwPlayerItem1.gunPropertyMap().put(k, v));
		newGunPropertyMap2.forEach((k, v) -> pwPlayerItem2.gunPropertyMap().put(k, v));
		WPlayerItemColumnDescriptor.refreshNumberParamMap(pwPlayerItem1);
		WPlayerItemColumnDescriptor.refreshNumberParamMap(pwPlayerItem2);

		ProxyWPlayerInfo pwPlayerInfo = wPlayerService.findProxyWPlayerInfo(playerId);
		Map<Serializable, ProxyWPlayerPack> pwPlayerPackMap = wPlayerService.findProxyWPlayerPackMap(playerId);
		Map<Serializable, ProxyWPlayerBuff> pwPlayerBuffMap = wPlayerService.findProxyWPlayerBuffMap(playerId);
		ResponseConvertPer proto = protoConverter.responseConvertPer(rate1, rate2, pwPlayerItem1, pwPlayerItem2, pwPlayerInfo, pwPlayerPackMap, pwPlayerBuffMap);
		return Smarty4jConverter.proto2Lua(proto);
	}

	/**
	 * 预览熔炼信息
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	public String previewMelting(int playerId) throws Exception {
		ProxyWPlayerMelting pwPlayerMelting = wPlayerService.findProxyWPlayerMelting(playerId);
		ProxyWPlayerInfo pwPlayerInfo = wPlayerService.findProxyWPlayerInfo(playerId);
		ResponseMeltingInfoGet proto = protoConverter.responseMeltingInfoGet(pwPlayerMelting, pwPlayerInfo);
		return Smarty4jConverter.proto2Lua(proto);
	}

	/**
	 * 熔炼
	 * @param playerId
	 * @param targetPlayerItemIdList 熔炼对象的玩家道具ID
	 * @param partPlayerItemId 熔炼配件的玩家道具ID
	 * @return
	 * @throws Exception
	 */
	public String melt(int playerId, List<Integer> targetPlayerItemIdList, int partPlayerItemId) throws Exception {
		Map<Serializable, ProxyWPlayerItem> pwPlayerItemMap = wPlayerService.findProxyWPlayerItemMap(playerId);
		Map<Integer, WSysItem> wSysItemMap = wSysItemService.findMap(null);
		ProxyWPlayerInfo pwPlayerInfo = wPlayerService.findProxyWPlayerInfo(playerId);
		ProxyWPlayerMelting pwPlayerMelting = wPlayerService.findProxyWPlayerMelting(playerId);
		ProxyWPlayerItem partPwPlayerItem = null;
		WSysItem partWSysItem = null;
		ResponseMeltingInput proto = ResponseMeltingInput.getDefaultInstance();
		//玩家使用了熔炼部件
		if (partPlayerItemId > 0) {
			partPwPlayerItem = pwPlayerItemMap.get(partPlayerItemId);
			if (null == partPwPlayerItem) {//熔炼部件的玩家道具ID不存在
				proto = protoConverter.responseMeltingInput(EMeltingResult.MELTING_PART_NOT_EXIST);
				return Smarty4jConverter.proto2Lua(proto);
			}
			partWSysItem = wSysItemMap.get(partPwPlayerItem.id().get());
			if (partWSysItem.getmType() != EItemMType.M_MELTING_UNIT.getNumber() && partWSysItem.getmType() != EItemMType.M_MELTING_FORMULA.getNumber()) {//不是正确的熔炼部件
				proto = protoConverter.responseMeltingInput(EMeltingResult.MELTING_PART_ERROR);
				return Smarty4jConverter.proto2Lua(proto);
			}
			if (partWSysItem.getmType() == EItemMType.M_MELTING_UNIT.getNumber() && partPwPlayerItem.quantity().get() < 1) {//熔炼部件数量不足
				proto = protoConverter.responseMeltingInput(EMeltingResult.MELTING_PART_NOT_ENOUGH);
				return Smarty4jConverter.proto2Lua(proto);
			}
		}
		//检查熔炼对象
		if (targetPlayerItemIdList.size() > pwPlayerMelting.level().get() + 1) {
			proto = protoConverter.responseMeltingInput(EMeltingResult.MELTING_NUM_NOT_ENOUGH);
			return Smarty4jConverter.proto2Lua(proto);
		}
		for (int targetPlayerItemId : targetPlayerItemIdList) {
			ProxyWPlayerItem targetPwPlayerItem = pwPlayerItemMap.get(targetPlayerItemId);
			if (null == targetPwPlayerItem) {//熔炼对象不存在
				proto = protoConverter.responseMeltingInput(EMeltingResult.MELTING_TARGET_NOT_EXIST);
				return Smarty4jConverter.proto2Lua(proto);
			}
			if (targetPwPlayerItem.unitType().get() == EUnitType.QUANTITY.getNumber() && targetPwPlayerItem.quantity().get() < 1) {//熔炼对象数量不足
				proto = protoConverter.responseMeltingInput(EMeltingResult.MELTING_TARGET_NOT_ENOUGH);
				return Smarty4jConverter.proto2Lua(proto);
			}
			WSysItem targetWSysItem = wSysItemMap.get(targetPwPlayerItem.id().get());
			if (targetWSysItem.getEvaluateRmb() <= 0 || targetPwPlayerItem.level().get() <= 0) {//熔炼对象错误
				proto = protoConverter.responseMeltingInput(EMeltingResult.MELTING_TARGET_ERROR);
				return Smarty4jConverter.proto2Lua(proto);
			}
		}
		//计算熔炼价值
		double sum1 = 0, sum2 = 0, meltingQuality = 0;
		for (int targetPlayerItemId : targetPlayerItemIdList) {
			ProxyWPlayerItem targetPwPlayerItem = pwPlayerItemMap.get(targetPlayerItemId);
			WSysItem targetWSysItem = wSysItemMap.get(targetPwPlayerItem.id().get());
			sum1 += targetWSysItem.getEvaluateRmb();
			double param = 0;
			switch (EItemType.forNumber(targetWSysItem.getType())) {
			case WEAPON:
				param = 9;
				break;
			case COSTUME:
				param = 4.5;
				break;
			case PART:
				param = 1.5;
				break;
			case ITEM:
				param = 3;
				break;
			default:
				break;
			}
			sum2 += param * (Math.pow(1.8, targetPwPlayerItem.level().get()) - 1) / 0.8;
		}
		meltingQuality = (sum1 + sum2) * (0.7 + 0.03 * targetPlayerItemIdList.size());
		meltingQuality *= (0.8 + (null == partWSysItem ? 0 : partWSysItem.getmValue()) / 100.0);
		meltingQuality += pwPlayerMelting.remaind().get();
		int meltingCount = 0;
		JsonPlayerInfoCaches jsonCaches = pwPlayerInfo.cachesEntity().get();
		Date now = new Date();
		if (DateUtil.isSameDay(now.getTime(), jsonCaches.getLastMeltingTime())) {//获取当天已熔炼的次数
			meltingCount = jsonCaches.getMeltingCount();
		}
		if (meltingCount > WSysConfigService.getPlayerItemCombineMelting().getMeltingCountThreshold()) {
			meltingQuality = meltingCount > WSysConfigService.getPlayerItemCombineMelting().getMeltingCountThreshold() ? (meltingQuality * Math.pow(0.7, meltingCount / 10.0)) : meltingQuality;
		}
		int maxMeltingQuantity = WSysConfigService.getPlayerItemCombineMelting().getMaxMeltingQuantity();//单次可获得的最大熔炼价值
		meltingQuality = meltingQuality > maxMeltingQuantity ? maxMeltingQuantity : meltingQuality;
		//计算残留值和等级经验
		pwPlayerMelting.remaind().set(meltingQuality / 10.0);
		int exp = (int) (pwPlayerMelting.exp().get() + meltingQuality * 50);
		int level = pwPlayerMelting.level().get();
		Map<Integer, Integer> levelAndExp = WSysConfigService.getPlayerItemCombineMelting().getLevelAndExp();
		int maxLevel = levelAndExp.keySet().stream().max((p1, p2) -> p1 - p2).orElse(0);
		int expInThisLevel = levelAndExp.getOrDefault(level, 0);//当前等级到下一级需要的完整经验
		while (level < maxLevel) {
			if (expInThisLevel > 0 && expInThisLevel <= exp) {
				level++;
				expInThisLevel = levelAndExp.getOrDefault(level, 0);
				exp -= expInThisLevel;
			}
		}
		pwPlayerMelting.level().set(level);
		pwPlayerMelting.exp().set(exp);
		//当天熔炼记录
		jsonCaches.setMeltingCount(++meltingCount);//次数
		jsonCaches.setLastMeltingTime(now.getTime());//时间
		for (int i = 0; i < 3; i++) {
			int sysItemId = RandomUnit.getRandomObjectByWeight(WSysConfigService.getPlayerItemCombineMelting().getAward()).getItemId();//奖励对象
			int awardQuantity = (int) Math.ceil(meltingQuality * (0.5 + Math.random()) * 0.1);//奖励数量
			Map<Integer, Integer> map = new HashMap<>();
			map.put(sysItemId, awardQuantity);
			jsonCaches.getMeltingAward().put(i + 1, map);
		}
		pwPlayerInfo.cachesEntity().set(jsonCaches);

		proto = protoConverter.responseMeltingInput(pwPlayerMelting, pwPlayerInfo, new HashMap<>(), getMeltingAwardPreviewCost(pwPlayerMelting));
		return Smarty4jConverter.proto2Lua(proto);
	}

	/**
	 * 预览熔炼奖励
	 * @param playerId
	 * @param index 客户端输入的奖励索引
	 * @return
	 * @throws Exception
	 */
	public String previewMeltingAward(int playerId, int index) throws Exception {
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
		ProxyWPlayerInfo pwPlayerInfo = wPlayerService.findProxyWPlayerInfo(playerId);
		ProxyWPlayerMelting pwPlayerMelting = wPlayerService.findProxyWPlayerMelting(playerId);
		Map<Integer, WSysItem> wSysItemMap = wSysItemService.findMap(null);
		//扣除预览熔炼奖励的花费
		int cost = getMeltingAwardPreviewCost(pwPlayerMelting);
		WPayment wPayment = new WPayment();
		wPayment.setPayType(EPayType.PAY_FC_POINT.getNumber());
		wPayment.setUnitType(EUnitType.QUANTITY.getNumber());
		wPayment.setCost(cost);
		wPayment.setUnit(1);
		wPlayerService.pay(pwPlayer, wPayment);
		//奖励预览
		JsonPlayerInfoCaches jsonCaches = pwPlayerInfo.cachesEntity().get();
		Map<WSysItem, Integer> award = jsonCaches.getMeltingAward().get(index).entrySet().stream().collect(Collectors.toMap(kv -> wSysItemMap.get(kv.getKey()), kv -> kv.getValue()));
		ResponseMeltingInput proto = protoConverter.responseMeltingInput(pwPlayerMelting, pwPlayerInfo, award, 0);
		return Smarty4jConverter.proto2Lua(proto);
	}

	/**
	 * 选取熔炼奖励
	 * @param playerId
	 * @param index 客户端输入的奖励索引
	 * @return
	 * @throws Exception
	 */
	public String meltingAward(int playerId, int index) throws Exception {
		ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
		ProxyWPlayerInfo pwPlayerInfo = wPlayerService.findProxyWPlayerInfo(playerId);
		ProxyWPlayerMelting pwPlayerMelting = wPlayerService.findProxyWPlayerMelting(playerId);
		Map<Integer, WSysItem> wSysItemMap = wSysItemService.findMap(null);
		JsonPlayerInfoCaches jsonCaches = pwPlayerInfo.cachesEntity().get();
		Map<WSysItem, Integer> award = jsonCaches.getMeltingAward().get(index).entrySet().stream().collect(Collectors.toMap(kv -> wSysItemMap.get(kv.getKey()), kv -> kv.getValue()));
		for (WSysItem wSysItem : award.keySet()) {
			WPayment wPayment = new WPayment();
			wPayment.setItemId(wSysItem.getId());
			wPayment.setPayType(EPayType.PAY_ITEM.getNumber());
			wPayment.setUnitType(EUnitType.QUANTITY.getNumber());
			wPayment.setUnit(award.get(wSysItem.getId()));
			ItemService.sendPlayerItem(pwPlayer, wSysItem, wPayment, false, true, false);
		}
		ResponseMeltingInput proto = protoConverter.responseMeltingInput(pwPlayerMelting, pwPlayerInfo, award, 0);
		return Smarty4jConverter.proto2Lua(proto);
	}

	/**
	 * 预览熔炼奖励的花费
	 * @param pwPlayerMelting
	 * @return
	 */
	private int getMeltingAwardPreviewCost(ProxyWPlayerMelting pwPlayerMelting) {
		double cost = pwPlayerMelting.remaind().get() * 10;
		cost = cost * (cost < Math.pow(20, 1.5) ? 0.337 : cost < Math.pow(40, 1.5) ? 0.412 : 2.35);
		return (int) Math.ceil(cost);
	}

}
